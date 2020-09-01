package unis.stores.result.orderstate;

import unis.stores.result.BaseResult;

public class DeleteOrderStateResult extends BaseResult {

    public DeleteOrderStateResult() {
    }

    public DeleteOrderStateResult(boolean success, String message) {
        super(success, message);
    }
}
