package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Line;

@Repository
public interface LineRepository extends CrudRepository<Line, Integer> {

    /**
     * Gets a Line searched by the name
     *
     * @param     name is the value for the name we are searching for.
     * @return    null if no Line founded and a Line if founded
     */
    Line findByName(String name);
}
