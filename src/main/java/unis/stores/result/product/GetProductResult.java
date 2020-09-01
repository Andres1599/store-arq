package unis.stores.result.product;

import unis.stores.result.BaseResult;

public class GetProductResult extends BaseResult {

    public GetProductResult() {
    }

    public GetProductResult(boolean success, String message) {
        super(success, message);
    }
}
