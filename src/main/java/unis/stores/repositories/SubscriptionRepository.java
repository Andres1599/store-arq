package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
}
