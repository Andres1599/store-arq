package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    /**
     * Gets a list of products searched by the part number
     *
     * @param     partNo is the value for the partNo we are searching for.
     * @return    null if no products founded and a products if founded
     */
    List<Product> findByPartNo(String partNo);
    /**
     * Gets a product searched by the name
     *
     * @param     name is the value for the name we are searching for.
     * @return    null if no products founded and a products if founded
     */
    Product findByName(String name);
    /**
     * Gets a list of products searched by the description
     *
     * @param     description is the value for the description we are searching for.
     * @return    null if no products founded and a products if founded
     */
    List<Product> findByDescription(String description);
    /**
     * Gets a list of products searched by the price
     *
     * @param     price is the value for the price we are searching for.
     * @return    null if no products founded and a products if founded
     */
    List<Product> findByPrice(double price);
    /**
     * Gets a list of products searched by the fabric id
     *
     * @param     fabricId is the value for the fabricId we are searching for.
     * @return    null if no products founded and a products if founded
     */
    List<Product> findAllByFabric_Id(int fabricId);

}
