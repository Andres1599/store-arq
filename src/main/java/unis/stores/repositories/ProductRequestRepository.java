package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.ProductRequest;

import java.util.List;

@Repository
public interface ProductRequestRepository extends CrudRepository<ProductRequest, Integer> {

    /**
     * Gets a product request searched by the Id
     *
     * @param     requestId is the value for the id we are searching for.
     * @return    null if no product request founded and a product request if founded
     */
    List<ProductRequest> findAllByRequest_Id(int requestId);
}
