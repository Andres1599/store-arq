package unis.stores.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.*;
import unis.stores.repositories.*;
import unis.stores.services.http.RestService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService implements IProductService {

    /**
     * The product repository to connect to the database
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * The vehicle repository to connect to the database
     */
    @Autowired
    private VehicleRepository vehicleRepository;

    /**
     * The fabric repository to connect to the database
     */
    @Autowired
    private FabricRepository fabricRepository;

    /**
     * The service to connect to the fabrics systems
     */
    @Autowired
    private RestService restService;

    /**
     * The brand repository to connect to the database
     */
    @Autowired
    private BrandRepository brandRepository;

    /**
     * The line repository to connect to the database
     */
    @Autowired
    private LineRepository lineRepository;

    /**
     * Returns the products of the system
     *
     * @return    the list of the products from the database.
     */
    @Override
    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }

    /**
     * Returns a product searched by the id
     *
     * @param     id this is the id of the product that we are looking for
     * @return    the product retrieved from the database
     */
    @Override
    public Product getProduct(int id) {
        return productRepository.findOne(id);
    }

    /**
     * Creates a product in the system
     *
     * @param     name this is the name of the product we want to create
     * @param     description this is the description of the product we want to create
     * @param     partNo this is the partNo of the product we want to create
     * @param     price this is the price of the product we want to create
     * @param     stock this is the stock of the product we want to create
     * @param     fabricId this is the fabricId of the product we want to create
     * @return    the created product
     */
    @Override
    public Product createProduct(String name, String description, String partNo, double price, int stock, int fabricId) {
        if (!fabricRepository.exists(fabricId))
            return null;

        Product product = new Product();
        Fabric fabric = fabricRepository.findOne(fabricId);

        product.setName(name);
        product.setDescription(description);
        product.setPartNo(partNo);
        product.setPrice(price);
        product.setStock(stock);
        product.setFabric(fabric);

        return productRepository.save(product);
    }

    /**
     * Assigns a vehicle to a product
     *
     * @param     id this is the id of the product that we want to update
     * @param     vehicleId this id of the vehicle we want to assign
     * @return    the updated product
     */
    @Override
    public Product assignVehicle(int id, String vehicleId) {
        if (!productRepository.exists(id) || !vehicleRepository.exists(vehicleId))
            return null;

        Vehicle vehicle = vehicleRepository.findOne(vehicleId);
        Product product = productRepository.findOne(id);

        product.getVehicles().add(vehicle);

        return productRepository.save(product);
    }

    /**
     * Un assigns a vehicle to a product
     *
     * @param     id this is the id of the product that we want to update
     * @param     vehicleId this id of the vehicle we want to un assign
     * @return    the updated product
     */
    @Override
    public Product unAssignVehicle(int id, String vehicleId) {
        if (!productRepository.exists(id) || !vehicleRepository.exists(vehicleId))
            return null;

        Vehicle vehicle = vehicleRepository.findOne(vehicleId);
        Product product = productRepository.findOne(id);

        product.getVehicles().remove(vehicle);

        return productRepository.save(product);
    }

    /**
     * Updates the param of a product
     *
     * @param     id this is the id of the product that we want to update
     * @param     name this is the name of the product we want to update
     * @param     description this is the description of the product we want to update
     * @param     partNo this is the partNo of the product we want to update
     * @param     price this is the price of the product we want to update
     * @param     fabricId this is the fabricId of the product we want to update
     * @return    the updated product
     */
    @Override
    public Product updateProduct(int id, String name, String description, String partNo, double price, int fabricId) {
        if (!fabricRepository.exists(fabricId))
            return null;

        if (!productRepository.exists(id))
            return null;

        Product updatedProduct = productRepository.findOne(id);
        Fabric fabric = fabricRepository.findOne(fabricId);

        updatedProduct.setName(name);
        updatedProduct.setDescription(description);
        updatedProduct.setPartNo(partNo);
        updatedProduct.setPrice(price);
        updatedProduct.setFabric(fabric);

        return productRepository.save(updatedProduct);
    }

    /**
     * Updates the stock of the product
     *
     * @param     id this is the id of the product that we want to update
     * @param     stock this is the stock of the product we want to update
     * @return    the updated product
     */
    @Override
    public Product updateStock(int id, int stock) {
        if (!productRepository.exists(id))
            return null;

        Product product = productRepository.findOne(id);
        product.setStock(stock);

        return productRepository.save(product);
    }

    /**
     * Deletes a product
     *
     * @param     id this is the id of the product that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    @Override
    public boolean deleteProduct(int id) {
        if (!productRepository.exists(id))
            return false;

        productRepository.delete(id);
        return true;
    }

    /**
     * Returns a list of products searched by the fabric id
     *
     * @param     fabricId this is the fabricId of the fabric that we are looking for
     * @return    the products retrieved from the database
     */
    @Override
    public List<Product> searchByFabric(int fabricId) {

        Fabric fabric = fabricRepository.findOne(fabricId);
        List<ProductFabric> fabricProducts = Arrays.asList(restService.getProductList(fabric.getIp()));

        for (ProductFabric productFabric :
                fabricProducts) {
            Brand brand = new Brand();
            Line line = new Line();
            Vehicle vehicle = new Vehicle();

            for (FabricVehicle fabricVehicle :
                    productFabric.getVehicles()) {
                 brand = brandRepository.findByName(fabricVehicle.getBrand());
                 line = lineRepository.findByName(fabricVehicle.getLine());
                 vehicle = vehicleRepository.findOne(fabricVehicle.getUniversalCode());

                if (brand == null) {
                    brand = new Brand();
                    brand.setName(fabricVehicle.getBrand());
                    brand = brandRepository.save(brand);
                }
                if (line == null) {
                    line = new Line();
                    line.setName(fabricVehicle.getLine());
                    line = lineRepository.save(line);
                }
                if (vehicle == null) {
                    vehicle = new Vehicle();
                    vehicle.setUniversalCode(fabricVehicle.getUniversalCode());
                    vehicle.setBrand(brand);
                    vehicle.setLine(line);
                    vehicle.setYear(fabricVehicle.getYear());
                    vehicle = vehicleRepository.save(vehicle);
                }
            }

            Product product = productRepository.findByName(productFabric.getName());
            if (product == null) {
                product = new Product();
                product.setName(productFabric.getName());
                product.setDescription(productFabric.getDescription());
                product.setStock(0);
                product.setPrice(productFabric.getPrice());
                product.setPartNo(productFabric.getPartNo());
                product.setFabric(fabric);
                product.setVehicles(new ArrayList<>());

                product.getVehicles().add(vehicle);
                productRepository.save(product);
            }
        }
        return productRepository.findAllByFabric_Id(fabricId);
    }
}
