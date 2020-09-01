package unis.stores.result.requeststate;

import unis.stores.result.BaseResult;

public class DeleteRequestStateResult extends BaseResult {

    public DeleteRequestStateResult() {
    }

    public DeleteRequestStateResult(boolean success, String message) {
        super(success, message);
    }
}
