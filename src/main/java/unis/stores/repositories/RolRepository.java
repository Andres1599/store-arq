package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Rol;

@Repository
public interface RolRepository extends CrudRepository<Rol, Integer> {

    /**
     * Gets a rol searched by the name
     *
     * @param     name is the value for the name we are searching for.
     * @return    null if no rol founded and a rol if founded
     */
    Rol findByName(String name);
}
