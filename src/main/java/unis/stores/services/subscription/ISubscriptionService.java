package unis.stores.services.subscription;

import unis.stores.entities.Subscription;

import java.util.List;

public interface ISubscriptionService {
    /**
     * Returns the subscriptions of the system
     *
     * @return    the list of the subscriptions from the database.
     */
    List<Subscription> getSubscriptions();
    /**
     * Returns a subscription searched by the id
     *
     * @param     id this is the id of the subscription that we are looking for
     * @return    the subscription retrieved from the database
     */
    Subscription getSubscriptionById(int id);
    /**
     * Creates a subscription in the system
     *
     * @param     name this is the name of the subscription we want to create
     * @param     discount this is the discount of the subscription we want to create
     * @return    the created subscription
     */
    Subscription createSubscription(String name, int discount);
    /**
     * Updates the param of a subscription
     *
     * @param     id this is the id of the subscription that we want to update
     * @param     name this is the name of the subscription we want to update
     * @param     discount this is the discount of the subscription we want to update
     * @return    the updated subscription
     */
    Subscription updateSubscription(int id, String name, int discount);
    /**
     * Deletes a subscription
     *
     * @param     id this is the id of the subscription that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    boolean deleteSubscription(int id);
}
