package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Brand;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Integer> {

    /**
     * Gets a brand searched by the name
     *
     * @param     name is the value for the name we are searching for.
     * @return    null if no brand founded and a brand if founded
     */
    Brand findByName(String name);
}
