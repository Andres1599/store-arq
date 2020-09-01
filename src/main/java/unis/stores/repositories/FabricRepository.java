package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Fabric;

@Repository
public interface FabricRepository extends CrudRepository<Fabric, Integer> {

    /**
     * Gets a Fabric searched by the name
     *
     * @param     password is the value for the password we are searching for.
     * @return    null if no Fabric founded and a Fabric if founded
     */
    Fabric findByServicePassword(String password);
}
