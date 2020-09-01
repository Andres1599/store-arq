package unis.stores.result.brand;

import unis.stores.entities.Brand;
import unis.stores.result.BaseResult;

public class CreateBrandResult extends BaseResult {

    private Brand brand;

    public CreateBrandResult() {
    }

    public CreateBrandResult(boolean success, String message, Brand brand) {
        super(success, message);
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
