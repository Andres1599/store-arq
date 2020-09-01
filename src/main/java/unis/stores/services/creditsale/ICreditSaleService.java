package unis.stores.services.creditsale;

import unis.stores.entities.CreditSale;

import java.util.List;

public interface ICreditSaleService {

    /**
     * Returns the credit sales of the system
     *
     * @return    the list of the credit sales from the database.
     */
    List<CreditSale> getCreditSales();
    /**
     * Pays a credit sale
     *
     * @param     id of the credit sale we want to pay
     * @return    a boolean specifying true if success and false in case of error
     */
    boolean payCreditSale(int id);
}
