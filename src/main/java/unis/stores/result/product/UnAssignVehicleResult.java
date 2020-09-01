package unis.stores.result.product;

import unis.stores.entities.Product;
import unis.stores.result.BaseResult;

public class UnAssignVehicleResult extends BaseResult {

    private Product product;

    public UnAssignVehicleResult() {
    }

    public UnAssignVehicleResult(boolean success, String message, Product product) {
        super(success, message);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
