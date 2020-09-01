package unis.stores.result.subscription;

import unis.stores.entities.Subscription;
import unis.stores.result.BaseResult;

public class UpdateSubscriptionResult extends BaseResult {

    private Subscription subscription;

    public UpdateSubscriptionResult(boolean success, String message, Subscription subscription) {
        super(success, message);
        this.subscription = subscription;
    }

    public UpdateSubscriptionResult() {
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
