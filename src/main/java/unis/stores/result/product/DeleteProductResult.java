package unis.stores.result.product;

import unis.stores.result.BaseResult;

public class DeleteProductResult extends BaseResult {

    public DeleteProductResult() {
    }

    public DeleteProductResult(boolean success, String message) {
        super(success, message);
    }
}
