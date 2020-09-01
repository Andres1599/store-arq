package unis.stores.services.sale;

import unis.stores.entities.Product;
import unis.stores.entities.Sale;

import java.util.List;

public interface ISaleService {

    /**
     * Returns the sales of the system
     *
     * @return    the list of the sales from the database.
     */
    List<Sale> getSales();
    /**
     * Returns a sale searched by the id
     *
     * @param     id this is the id of the sale that we are looking for
     * @return    the sale retrieved from the database
     */
    Sale getSale(int id);
    /**
     * Creates a sale in the system
     *
     * @param     sale this object contains the sale we want to create
     * @return    the created sale
     */
    boolean createSale(Sale sale);
    /**
     * Updates the sale state of a sale
     *
     * @param     id this is the id of the sale that we want to update
     * @param     saleState this is the id of the sale state we want to put to the sale
     * @return    the updated sale
     */
    Sale updateSaleState(int id, int saleState);
    /**
     * Returns a list of available sales from a client
     *
     * @param     clientId this is the client id we are looking its sales
     * @return    the list of available sales.
     */
    boolean getAvailableOrder(int clientId);
    /**
     * Delivers a sale and updates the stock automatically
     *
     * @param     saleId this is the id of the sale we are delivering
     * @return    the sale updated
     */
    Sale deliverSale(int saleId);
    /**
     * Gets the sold products of a fabric
     *
     * @param     password this is the password of the fabric
     * @return    the list of products sold for a fabric
     */
    List<Product> getSaleReport(String password);

}
