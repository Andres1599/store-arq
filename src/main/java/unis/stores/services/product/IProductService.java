package unis.stores.services.product;

import unis.stores.entities.Product;

import java.util.List;

public interface IProductService {

    /**
     * Returns the products of the system
     *
     * @return    the list of the products from the database.
     */
    List<Product> getProducts();
    /**
     * Returns a product searched by the id
     *
     * @param     id this is the id of the product that we are looking for
     * @return    the product retrieved from the database
     */
    Product getProduct(int id);
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
    Product createProduct(String name, String description, String partNo, double price, int stock, int fabricId);
    /**
     * Assigns a vehicle to a product
     *
     * @param     id this is the id of the product that we want to update
     * @param     vehicleId this id of the vehicle we want to assign
     * @return    the updated product
     */
    Product assignVehicle(int id, String vehicleId);
    /**
     * Un assigns a vehicle to a product
     *
     * @param     id this is the id of the product that we want to update
     * @param     vehicleId this id of the vehicle we want to un assign
     * @return    the updated product
     */
    Product unAssignVehicle(int id, String vehicleId);
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
    Product updateProduct(int id, String name, String description, String partNo, double price, int fabricId);
    /**
     * Updates the stock of the product
     *
     * @param     id this is the id of the product that we want to update
     * @param     stock this is the stock of the product we want to update
     * @return    the updated product
     */
    Product updateStock(int id, int stock);
    /**
     * Deletes a product
     *
     * @param     id this is the id of the product that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    boolean deleteProduct(int id);
    /**
     * Returns a list of products searched by the fabric id
     *
     * @param     fabricId this is the fabricId of the fabric that we are looking for
     * @return    the products retrieved from the database
     */
    List<Product> searchByFabric(int fabricId);

}
