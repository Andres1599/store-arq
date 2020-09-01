package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Sale;

import java.util.List;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Integer> {

    /**
     * Gets a list of sales searched by the client id and the order state
     *
     * @param     clientId is the value for the clientId we are searching for.
     * @param     orderState is the value for the orderState we are searching for.
     * @return    null if no sales founded and a sales if founded
     */
    List<Sale> findAllByClient_IdAndOrderState_Id(int clientId, int orderState);
    /**
     * Gets a sale searched by the request id
     *
     * @param     requestId is the value for the requestId we are searching for.
     * @return    null if no sales founded and a sales if founded
     */
    Sale findByRequest_Id(int requestId);
}
