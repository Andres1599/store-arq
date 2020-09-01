package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.OrderState;

@Repository
public interface OrderStateRepository extends CrudRepository<OrderState, Integer> {

    /**
     * Gets a order state searched by the name
     *
     * @param     name is the value for the name we are searching for.
     * @return    null if no order state founded and a order state if founded
     */
    OrderState findByName(String name);
}
