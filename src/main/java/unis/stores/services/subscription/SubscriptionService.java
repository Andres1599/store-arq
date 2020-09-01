package unis.stores.services.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Subscription;
import unis.stores.repositories.SubscriptionRepository;

import java.util.List;

@Service
public class SubscriptionService implements ISubscriptionService {

    /**
     * The subscription repository to connect to the database
     */
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    /**
     * Returns the subscriptions of the system
     *
     * @return    the list of the subscriptions from the database.
     */
    @Override
    public List<Subscription> getSubscriptions() {
        return (List<Subscription>) subscriptionRepository.findAll();
    }

    /**
     * Returns a subscription searched by the id
     *
     * @param     id this is the id of the subscription that we are looking for
     * @return    the subscription retrieved from the database
     */
    @Override
    public Subscription getSubscriptionById(int id) {
        if (!subscriptionRepository.exists(id))
            return null;

        return subscriptionRepository.findOne(id);
    }

    /**
     * Creates a subscription in the system
     *
     * @param     name this is the name of the subscription we want to create
     * @param     discount this is the discount of the subscription we want to create
     * @return    the created subscription
     */
    @Override
    public Subscription createSubscription(String name, int discount) {
        if (name == null)
            return null;

        Subscription newSubscription = new Subscription();

        newSubscription.setName(name);
        newSubscription.setDiscount(discount);

        return subscriptionRepository.save(newSubscription);
    }

    /**
     * Updates the param of a subscription
     *
     * @param     id this is the id of the subscription that we want to update
     * @param     name this is the name of the subscription we want to update
     * @param     discount this is the discount of the subscription we want to update
     * @return    the updated subscription
     */
    @Override
    public Subscription updateSubscription(int id, String name, int discount) {
        if (!subscriptionRepository.exists(id))
            return null;

        Subscription updateSubscription = subscriptionRepository.findOne(id);

        updateSubscription.setName(name);
        updateSubscription.setDiscount(discount);

        return subscriptionRepository.save(updateSubscription);
    }

    /**
     * Deletes a subscription
     *
     * @param     id this is the id of the subscription that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    @Override
    public boolean deleteSubscription(int id) {
        if (!subscriptionRepository.exists(id))
            return false;

        subscriptionRepository.delete(id);
        return true;
    }
}
