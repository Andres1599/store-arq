package unis.stores.result.subscription;

import unis.stores.result.BaseResult;

public class DeleteSubscriptionResult extends BaseResult {

    public DeleteSubscriptionResult(boolean success, String message) {
        super(success, message);
    }

    public DeleteSubscriptionResult() {
    }
}
