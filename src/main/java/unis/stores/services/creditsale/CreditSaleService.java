package unis.stores.services.creditsale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.CreditSale;
import unis.stores.repositories.CreditSaleRepository;

import java.util.List;

@Service
public class CreditSaleService implements ICreditSaleService {

    /**
     * The credit sale repository to connect to the database
     */
    @Autowired
    private CreditSaleRepository creditSaleRepository;

    /**
     * Returns the credit sales of the system
     *
     * @return    the list of the credit sales from the database.
     */
    @Override
    public List<CreditSale> getCreditSales() {
        return (List<CreditSale>) creditSaleRepository.findAll();
    }

    /**
     * Pays a credit sale
     *
     * @param     id of the credit sale we want to pay
     * @return    a boolean specifying true if success and false in case of error
     */
    @Override
    public boolean payCreditSale(int id) {
        if (!creditSaleRepository.exists(id))
            return false;

        creditSaleRepository.delete(id);
        return true;
    }
}
