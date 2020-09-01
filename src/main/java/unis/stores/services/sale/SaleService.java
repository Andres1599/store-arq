package unis.stores.services.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.*;
import unis.stores.repositories.*;
import unis.stores.services.http.RestService;

import java.util.*;

@Service
public class SaleService implements ISaleService {

    /**
     * The sale repository to connect to the database
     */
    @Autowired
    private SaleRepository saleRepository;

    /**
     * The order state repository to connect to the database
     */
    @Autowired
    private OrderStateRepository orderStateRepository;

    /**
     * The request state repository to connect to the database
     */
    @Autowired
    private RequestStateRepository requestStateRepository;

    /**
     * The product repository to connect to the database
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * The credit sale repository to connect to the database
     */
    @Autowired
    private CreditSaleRepository creditSaleRepository;

    /**
     * The product sale repository to connect to the database
     */
    @Autowired
    private ProductSaleRepository productSaleRepository;

    /**
     * The request repository to connect to the database
     */
    @Autowired
    private RequestRepository requestRepository;

    /**
     * The product request repository to connect to the database
     */
    @Autowired
    private ProductRequestRepository productRequestRepository;

    /**
     * The fabric repository to connect to the database
     */
    @Autowired
    private FabricRepository fabricRepository;

    /**
     * The service to coonect to the fabric services
     */
    @Autowired
    private RestService restService;

    /**
     * Returns the sales of the system
     *
     * @return    the list of the sales from the database.
     */
    @Override
    public List<Sale> getSales() {
        List<Sale> sales = (List<Sale>) saleRepository.findAll();

        for (Sale sale :
                sales) {
            List<ProductSale> productSales = productSaleRepository.findAllBySale_Id(sale.getId());
            for (ProductSale productSale :
                    productSales) {
                productSale.setSale(null);
            }
            sale.setProductSales(productSales);
        }

        return sales;
    }

    /**
     * Returns a sale searched by the id
     *
     * @param     id this is the id of the sale that we are looking for
     * @return    the sale retrieved from the database
     */
    @Override
    public Sale getSale(int id) {
        return saleRepository.findOne(id);
    }

    /**
     * Creates a sale in the system
     *
     * @param     sale this object contains the sale we want to create
     * @return    the created sale
     */
    @Override
    public boolean createSale(Sale sale) {

        Map<Integer, List<ProductSale>> map = new HashMap<>();
        List<Sale> sales = new ArrayList<>();

        for (ProductSale productSale :
                sale.getProductSales()) {
            Product product = productRepository.findOne(productSale.getProduct().getId());
            if (!map.containsKey(product.getFabric().getId())) {
                map.put(product.getFabric().getId(), new ArrayList<>());
            }
            map.get(product.getFabric().getId()).add(productSale);
        }

        Sale availableSale;
        Sale noAvailableSale;

        for (Map.Entry me : map.entrySet()) {
            availableSale = new Sale();
            noAvailableSale = new Sale();
            for (ProductSale productSale :
                    ((ArrayList<ProductSale>)me.getValue())) {
                Product product = productRepository.findOne(productSale.getProduct().getId());
                productSale.setProduct(product);
                if (product.getStock() < productSale.getSoldStock()) {
                    if (noAvailableSale.getProductSales() == null)
                        noAvailableSale.setProductSales(new ArrayList<>());

                    noAvailableSale.getProductSales().add(productSale);
                    noAvailableSale.setTotal(noAvailableSale.getTotal() + product.getSalePrice());
                } else {
                    if (availableSale.getProductSales() == null)
                        availableSale.setProductSales(new ArrayList<>());

                    availableSale.getProductSales().add(productSale);
                    availableSale.setTotal(availableSale.getTotal() + product.getSalePrice());
                }
            }

            if (availableSale.getProductSales() != null && availableSale.getProductSales().size() > 0) {
                List<ProductSale> productSales = availableSale.getProductSales();
                availableSale.setDate(new Date());
                availableSale.setClient(sale.getClient());
                OrderState orderState = orderStateRepository.findOne(3);
                availableSale.setOrderState(orderState);
                availableSale.setProductSales(null);

                availableSale = saleRepository.save(availableSale);

                if (sale.isCredit()) {
                    CreditSale creditSale = new CreditSale();
                    creditSale.setClient(availableSale.getClient());
                    creditSale.setSale(availableSale);
                    creditSaleRepository.save(creditSale);
                }

                for (ProductSale productSale :
                        productSales) {
                    productSale.setSale(availableSale);
                    productSale.setSalePrice(productSale.getProduct().getSalePrice());
                    productSaleRepository.save(productSale);

                    Product product = productRepository.findOne(productSale.getProduct().getId());
                    product.setStock(product.getStock() -  productSale.getSoldStock());
                    productRepository.save(product);
                }
            }

            if (noAvailableSale.getProductSales() != null && noAvailableSale.getProductSales().size() > 0) {
                List<ProductSale> productSales = noAvailableSale.getProductSales();
                noAvailableSale.setDate(new Date());
                noAvailableSale.setClient(sale.getClient());
                OrderState orderState = orderStateRepository.findOne(1);
                noAvailableSale.setOrderState(orderState);
                noAvailableSale.setProductSales(null);

                noAvailableSale = saleRepository.save(noAvailableSale);

                if (sale.isCredit()) {
                    CreditSale creditSale = new CreditSale();
                    creditSale.setClient(noAvailableSale.getClient());
                    creditSale.setSale(noAvailableSale);
                    creditSaleRepository.save(creditSale);
                }

                for (ProductSale productSale :
                        productSales) {
                    productSale.setSale(noAvailableSale);
                    productSale.setSalePrice(productSale.getProduct().getSalePrice());
                    productSaleRepository.save(productSale);
                }

                Request request = new Request();
                request.setDate(new Date());
                request.setFabric(productSales.get(0).getProduct().getFabric());
                RequestState requestState = requestStateRepository.findOne(1);
                request.setRequestState(requestState);

                request = requestRepository.save(request);

                noAvailableSale.setRequest(request);
                saleRepository.save(noAvailableSale);

                request.setProductRequests(new ArrayList<>());
                for (ProductSale productSale :
                        productSales) {
                    ProductRequest productRequest = new ProductRequest();
                    productRequest.setProduct(productSale.getProduct());
                    productRequest.setRequest(request);
                    productRequest.setRequestedStock(productSale.getSoldStock());

                    productRequestRepository.save(productRequest);

                    ProductRequest newProductRequest = new ProductRequest();

                    newProductRequest.setRequest(null);
                    newProductRequest.setRequestedStock(productRequest.getRequestedStock());
                    newProductRequest.setProduct(productRequest.getProduct());

                    request.getProductRequests().add(newProductRequest);
                }

                Fabric fabric = fabricRepository.findOne(request.getFabric().getId());
                request.setPassword(fabric.getServicePassword());
                Date date = new Date();
                try {
                    request.setPassword(fabric.getServicePassword());
                    date = restService.createRequest(request, fabric.getIp());
                } catch (Exception e) {
                    date = null;
                }
                request.setDeliveryDate(date);
                request.setProductRequests(null);
                requestRepository.save(request);
            }
        }

        return true;
    }

    /**
     * Updates the sale state of a sale
     *
     * @param     id this is the id of the sale that we want to update
     * @param     saleState this is the id of the sale state we want to put to the sale
     * @return    the updated sale
     */
    @Override
    public Sale updateSaleState(int id, int saleState) {
        if (!orderStateRepository.exists(saleState) || !saleRepository.exists(id))
            return null;

        Sale sale = saleRepository.findOne(id);
        OrderState orderState = orderStateRepository.findOne(saleState);

        sale.setOrderState(orderState);

        return saleRepository.save(sale);
    }

    /**
     * Returns a list of available sales from a client
     *
     * @param     clientId this is the client id we are looking its sales
     * @return    the list of available sales.
     */
    @Override
    public boolean getAvailableOrder(int clientId) {
        return saleRepository.findAllByClient_IdAndOrderState_Id(clientId, 2).size() > 0;
    }

    /**
     * Delivers a sale and updates the stock automatically
     *
     * @param     saleId this is the id of the sale we are delivering
     * @return    the sale updated
     */
    @Override
    public Sale deliverSale(int saleId) {
        if (!saleRepository.exists(saleId))
            return null;

        Sale sale = saleRepository.findOne(saleId);
        OrderState orderState = orderStateRepository.findOne(3);

        sale.setOrderState(orderState);

        List<ProductSale> productSales = productSaleRepository.findAllBySale_Id(saleId);

        for (ProductSale productSale :
                productSales) {
            Product product = productRepository.findOne(productSale.getProduct().getId());
            product.setStock(product.getStock() - productSale.getSoldStock());
            productRepository.save(product);
        }

        return saleRepository.save(sale);
    }

    /**
     * Gets the sold products of a fabric
     *
     * @param     password this is the password of the fabric
     * @return    the list of products sold for a fabric
     */
    @Override
    public List<Product> getSaleReport(String password) {
        List<Product> products = new ArrayList<>();

        Fabric fabric = fabricRepository.findByServicePassword(password);

        if (fabric == null)
            return null;

        List<ProductSale> productSales = productSaleRepository.findAllByProduct_Fabric_IdAndSale_DateAfterAndSale_DateBefore(fabric.getId(),
                fabric.getLastDateRequest(), new Date());

        for (ProductSale productSale :
                productSales) {
            Product product = productSale.getProduct();
            product.setStock(productSale.getSoldStock());
            products.add(product);
        }

        fabric.setLastDateRequest(new Date());
        fabricRepository.save(fabric);
        return products;
    }
}
