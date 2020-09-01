package unis.stores.result.subscription;

import unis.stores.result.BaseResult;

public class GetSubscriptionResult extends BaseResult {

    public GetSubscriptionResult() {
    }

    public GetSubscriptionResult(boolean success, String message) {
        super(success, message);
    }
}
