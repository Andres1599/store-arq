package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import unis.stores.services.creditsale.CreditSaleService;

@Controller
@CrossOrigin
public class CreditSaleController {

    /**
     * The credit sale service to connect to the database
     */
    @Autowired
    private CreditSaleService creditSaleService;

    /**
     * Gets the system credit sales
     *
     * @return    returns the list of credit sales in the system
     */
    @GetMapping("/sale/credit")
    public ResponseEntity<Object> get() {
        return ResponseEntity.ok(creditSaleService.getCreditSales());
    }

    /**
     * Pays an existing credit sale
     *
     * @param     id is the id of the credit sale we want to pay
     * @return    returns the result of the pay
     */
    @DeleteMapping("/sale/credit/{id}")
    public ResponseEntity<Object> pay(@PathVariable("id") String id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            int creditId = Integer.parseInt(id);
            if (creditSaleService.payCreditSale(creditId))
                return ResponseEntity.ok().build();
            else
                return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
