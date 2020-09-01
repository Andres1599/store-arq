package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.constants.Constants;
import unis.stores.entities.Product;
import unis.stores.result.product.*;
import unis.stores.services.product.ProductService;
import unis.stores.services.vehicle.VehicleService;

import java.util.Map;

@CrossOrigin
@Controller
public class ProductController {

    /**
     * The product service to connect to the database
     */
    @Autowired
    private ProductService productService;

    /**
     * The vehicle service to connect to the database
     */
    @Autowired
    private VehicleService vehicleService;

    /**
     * Create a product in the system
     *
     * @param     body contains the information to create the product
     * @return    returns the result of the creation action
     */
    @PostMapping("/product")
    public ResponseEntity<CreateProductResult> create(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.PRODUCT_NAME_LABEL) || !body.containsKey(Constants.PRODUCT_DESCRIPTION_LABEL)
        || !body.containsKey(Constants.PRODUCT_PART_NO_LABEL) || !body.containsKey(Constants.PRODUCT_PRICE_LABEL) ||
        !body.containsKey(Constants.PRODUCT_STOCK_LABEL))
            return ResponseEntity.badRequest().body(new CreateProductResult(false, "Bad Request", null));

        try {
            double price = Double.parseDouble(body.get(Constants.PRODUCT_PRICE_LABEL));
            int stock = Integer.parseInt(body.get(Constants.PRODUCT_STOCK_LABEL));
            int fabricId = Integer.parseInt(body.get(Constants.PRODUCT_FABRIC_ID));

            Product product = productService.createProduct(body.get(Constants.PRODUCT_NAME_LABEL), body.get(Constants.PRODUCT_DESCRIPTION_LABEL),
                    body.get(Constants.PRODUCT_PART_NO_LABEL), price, stock, fabricId);

            if (product == null)
                return ResponseEntity.badRequest().body(new CreateProductResult(false, "Error creating the product", null));
            else
                return ResponseEntity.ok().body(new CreateProductResult(true, "Product created", product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CreateProductResult(false, e.getMessage(), null));
        }
    }

    /**
     * Update a product in the system
     *
     * @param     body contains the information to update the product
     * @return    returns the result of the update action
     */
    @PutMapping("/product")
    public ResponseEntity<UpdateProductResult> update(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.PRODUCT_NAME_LABEL) || !body.containsKey(Constants.PRODUCT_DESCRIPTION_LABEL)
                || !body.containsKey(Constants.PRODUCT_PART_NO_LABEL) || !body.containsKey(Constants.PRODUCT_PRICE_LABEL)
                || !body.containsKey(Constants.PRODUCT_ID_LABEL) || !body.containsKey(Constants.PRODUCT_FABRIC_ID))
            return ResponseEntity.badRequest().body(new UpdateProductResult(false, "Bad Request", null));

        try {
            int id = Integer.parseInt(body.get(Constants.PRODUCT_ID_LABEL));
            double price = Double.parseDouble(body.get(Constants.PRODUCT_PRICE_LABEL));
            int fabricId = Integer.parseInt(body.get(Constants.PRODUCT_FABRIC_ID));

            Product product = productService.updateProduct(id, body.get(Constants.PRODUCT_NAME_LABEL), body.get(Constants.PRODUCT_DESCRIPTION_LABEL),
                    body.get(Constants.PRODUCT_PART_NO_LABEL), price, fabricId);

            if (product == null)
                return ResponseEntity.badRequest().body(new UpdateProductResult(false, "Error updating the product", null));
            else
                return ResponseEntity.ok().body(new UpdateProductResult(true, "Product updated", product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateProductResult(false, e.getMessage(), null));
        }
    }

    /**
     * Delete a product in the system
     *
     * @param     id the id product we want to delete
     * @return    returns the result of the deletion action
     */
    @DeleteMapping("/product/{id}")
    public ResponseEntity<DeleteProductResult> delete(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new DeleteProductResult(false, "Bad Request"));

        try {
            int productId = Integer.parseInt(id);

            if (productService.deleteProduct(productId))
                return ResponseEntity.ok().body(new DeleteProductResult(true, "Product deleted successfully"));
            else
                return ResponseEntity.badRequest().body(new DeleteProductResult(false, "Error deleting the product"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DeleteProductResult(false, e.getMessage()));
        }
    }

    /**
     * Gets the system products
     *
     * @return    returns the list of products in the system
     */
    @GetMapping("/product")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    /**
     * Gets a product
     *
     * @param     id the id of the product we want to get
     * @return    returns the founded product
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new GetProductResult(false, "Bad Request"));

        try {
            int productId = Integer.parseInt(id);

            Product product = productService.getProduct(productId);

            if (product == null)
                return ResponseEntity.badRequest().body(new GetProductResult(false, "Product doesn't exists"));
            else
                return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GetProductResult(false, e.getMessage()));
        }
    }

    /**
     * Gets the products of an specific fabric
     *
     * @param     id the id of the fabric we want to get its products
     * @return    returns the founded products
     */
    @GetMapping("/product/fabric/{id}")
    public ResponseEntity<Object> getFabricProducts(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().build();

        try {
            int fabricId = Integer.parseInt(id);

            return ResponseEntity.ok(productService.searchByFabric(fabricId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update a product stock in the system
     *
     * @param     body contains the information to update the product
     * @return    returns the result of the update action
     */
    @PutMapping("/product/stock")
    public ResponseEntity<UpdateProductStockResult> updateStock(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.PRODUCT_STOCK_LABEL) || !body.containsKey(Constants.PRODUCT_ID_LABEL))
            return ResponseEntity.badRequest().body(new UpdateProductStockResult(false, "Bad Request", null));

        try {
            int id = Integer.parseInt(body.get(Constants.PRODUCT_ID_LABEL));
            int stock = Integer.parseInt(body.get(Constants.PRODUCT_STOCK_LABEL));

            Product product = productService.updateStock(id, stock);

            if (product == null)
                return ResponseEntity.badRequest().body(new UpdateProductStockResult(false, "Error updating the product stock", null));
            else
                return ResponseEntity.ok().body(new UpdateProductStockResult(true, "Product stock updated", product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateProductStockResult(false, e.getMessage(), null));
        }
    }

    /**
     * Assign a vehicle to a product in the system
     *
     * @param     body contains the information to make the assign
     * @return    returns the result of the update action
     */
    @PutMapping("/product/vehicle/add")
    public ResponseEntity<AssignVehicleResult> assignVehicle(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.PRODUCT_ID_LABEL) || !body.containsKey(Constants.VEHICLE_ID_LABEL))
            return ResponseEntity.badRequest().body(new AssignVehicleResult(false, "Bad Request", null));

        try {
            int id = Integer.parseInt(body.get(Constants.PRODUCT_ID_LABEL));
            String vehicleId = body.get(Constants.VEHICLE_ID_LABEL);

            Product product = productService.assignVehicle(id, vehicleId);

            if (product == null)
                return ResponseEntity.badRequest().body(new AssignVehicleResult(false, "Error assigning the vehicle", null));
            else
                return ResponseEntity.ok().body(new AssignVehicleResult(true, "Vehicle assigned", product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AssignVehicleResult(false, e.getMessage(), null));
        }
    }

    /**
     * Un assign a vehicle to a product in the system
     *
     * @param     body contains the information to make the un assign
     * @return    returns the result of the update action
     */
    @PutMapping("/product/vehicle/remove")
    public ResponseEntity<UnAssignVehicleResult> unAssignVehicle(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.PRODUCT_ID_LABEL) || !body.containsKey(Constants.VEHICLE_ID_LABEL))
            return ResponseEntity.badRequest().body(new UnAssignVehicleResult(false, "Bad Request", null));

        try {
            int id = Integer.parseInt(body.get(Constants.PRODUCT_ID_LABEL));
            String vehicleId = body.get(Constants.VEHICLE_ID_LABEL);

            Product product = productService.unAssignVehicle(id, vehicleId);

            if (product == null)
                return ResponseEntity.badRequest().body(new UnAssignVehicleResult(false, "Error un assigning the vehicle", null));
            else
                return ResponseEntity.ok().body(new UnAssignVehicleResult(true, "Vehicle un assigned", product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UnAssignVehicleResult(false, e.getMessage(), null));
        }
    }

}
