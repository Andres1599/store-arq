package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.CreditSale;

@Repository
public interface CreditSaleRepository extends CrudRepository<CreditSale, Integer> {
}
