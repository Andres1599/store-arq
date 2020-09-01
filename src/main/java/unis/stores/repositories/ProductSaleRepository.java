package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.ProductSale;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductSaleRepository extends CrudRepository<ProductSale, Integer> {

    /**
     * Gets a list of products sale searched by the Id
     *
     * @param     saleId is the value for the id we are searching for.
     * @return    null if no product sale founded and a product sale if founded
     */
    List<ProductSale> findAllBySale_Id(int saleId);

    /**
     * Gets a list of product sale in a date range
     *
     * @param     fabricId is the value for the id of the fabric we are searching for.
     * @param     start is the start date of the range we are looking for.
     * @param     end is the end date of the range we are looking for.
     * @return    null if no product sale founded and a product sale if founded
     */
    List<ProductSale> findAllByProduct_Fabric_IdAndSale_DateAfterAndSale_DateBefore(int fabricId, Date start, Date end);
}
