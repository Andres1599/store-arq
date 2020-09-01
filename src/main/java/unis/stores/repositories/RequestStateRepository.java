package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.RequestState;

@Repository
public interface RequestStateRepository extends CrudRepository<RequestState, Integer> {

    /**
     * Gets a request state searched by the name
     *
     * @param     name is the value for the name we are searching for.
     * @return    null if no request state founded and a request state if founded
     */
    RequestState findByName(String name);
}
