package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    /**
     * Gets a client searched by the nit
     *
     * @param     nit is the value for the nit we are searching for.
     * @return    null if no client founded and a client if founded
     */
    Client findByNit(String nit);
}
