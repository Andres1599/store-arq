package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Request;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<Request, Integer> {

}
