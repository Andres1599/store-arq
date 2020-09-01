package unis.stores.services.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.*;
import unis.stores.repositories.*;
import unis.stores.services.http.RestService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RequestService implements IRequestService {

    /**
     * The request repository to connect to the database
     */
    @Autowired
    private RequestRepository requestRepository;

    /**
     * The request state repository to connect to the database
     */
    @Autowired
    private RequestStateRepository requestStateRepository;

    /**
     * The order state repository to connect to the database
     */
    @Autowired
    private OrderStateRepository orderStateRepository;

    /**
     * The product request repository to connect to the database
     */
    @Autowired
    private ProductRequestRepository productRequestRepository;

    /**
     * The product repository to connect to the database
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * The sale repository to connect to the database
     */
    @Autowired
    private SaleRepository saleRepository;

    /**
     * The service to connect to the fabrics systems
     */
    @Autowired
    private RestService restService;

    /**
     * The fabric repository to connect to the database
     */
    @Autowired
    private FabricRepository fabricRepository;

    /**
     * Returns the requests of the system
     *
     * @return    the list of the requests from the database.
     */
    @Override
    public List<Request> getRequests() {
        List<Request> requests = (List<Request>) requestRepository.findAll();

        for (Request request :
                requests) {
            List<ProductRequest> productRequests = productRequestRepository.findAllByRequest_Id(request.getId());
            for (ProductRequest productRequest :
                    productRequests) {
                productRequest.setRequest(null);
            }
            request.setProductRequests(productRequests);
        }
        return requests;
    }

    /**
     * Returns a request searched by the id
     *
     * @param     id this is the id of the request that we are looking for
     * @return    the request retrieved from the database
     */
    @Override
    public Request getRequest(int id) {
        return requestRepository.findOne(id);
    }

    /**
     * Creates a request in the system
     *
     * @param     request this is the object of the request we want to create
     * @return    the created request
     */
    @Override
    public Request createRequest(Request request) {

        Request newRequest = new Request();
        newRequest.setDate(new Date());
        newRequest.setFabric(request.getFabric());
        newRequest.setProductRequests(new ArrayList<>());
        newRequest.setRequestState(request.getRequestState());

        newRequest = requestRepository.save(newRequest);
        newRequest.setProductRequests(new ArrayList<>());
        for (ProductRequest productRequest :
                request.getProductRequests()) {
            ProductRequest newProductRequest = new ProductRequest();
            newProductRequest.setProduct(productRequest.getProduct());
            newProductRequest.setRequest(newRequest);
            newProductRequest.setRequestedStock(productRequest.getRequestedStock());
            productRequestRepository.save(newProductRequest);

            productRequest.setRequest(null);
            newRequest.getProductRequests().add(productRequest);
        }

        Fabric fabric = fabricRepository.findOne(request.getFabric().getId());
        Date date = new Date();
        try {
            newRequest.setPassword(fabric.getServicePassword());
            date = restService.createRequest(newRequest, fabric.getIp());
        } catch (Exception e) {
            date = null;
        }

        newRequest.setDeliveryDate(date);
        newRequest.setProductRequests(null);

        return requestRepository.save(newRequest);
    }

    /**
     * Updates the request state of a request
     *
     * @param     id this is the id of the request that we want to update
     * @param     requestStateId this is the id of the request state we want to put to the request
     * @return    the updated request
     */
    @Override
    public Request updateRequestState(int id, int requestStateId) {
        if (!requestRepository.exists(id) || !requestStateRepository.exists(requestStateId))
            return null;

        RequestState requestState = requestStateRepository.findOne(requestStateId);
        Request request = requestRepository.findOne(id);

        request.setRequestState(requestState);

        if (requestStateId == 3) {
            Sale sale = saleRepository.findByRequest_Id(request.getId());

            if (sale != null) {
                OrderState orderState = orderStateRepository.findOne(4);
                sale.setOrderState(orderState);
                saleRepository.save(sale);
            }
        }

        return requestRepository.save(request);
    }

    /**
     * Receives a request and updates the stock automatically
     *
     * @param     id this is the id of the request we are receiving
     * @return    the request updated
     */
    @Override
    public Request receiveRequest(int id) {
        if (!requestRepository.exists(id))
            return null;

        Request request = requestRepository.findOne(id);
        List<ProductRequest> productRequests = productRequestRepository.findAllByRequest_Id(request.getId());
        for (ProductRequest productRequest :
                productRequests) {
            Product product = productRepository.findOne(productRequest.getProduct().getId());
            product.setStock(product.getStock() + productRequest.getRequestedStock());
            productRepository.save(product);
        }

        RequestState requestState = requestStateRepository.findOne(2);
        OrderState orderState = orderStateRepository.findOne(2);

        Sale sale = saleRepository.findByRequest_Id(request.getId());

        if (sale != null) {
            sale.setOrderState(orderState);
            saleRepository.save(sale);
        }

        request.setRequestState(requestState);
        return requestRepository.save(request);
    }
}
