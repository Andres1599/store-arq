package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.entities.Sale;
import unis.stores.result.sale.CreateSaleResult;
import unis.stores.result.sale.GetSaleResult;
import unis.stores.result.sale.UpdateSaleStateResult;
import unis.stores.services.sale.SaleService;

import java.util.List;

@CrossOrigin
@Controller
public class SaleController {

    /**
     * The sale service to connect to the database
     */
    @Autowired
    private SaleService saleService;

    /**
     * Gets the system sales
     *
     * @return    returns the list of sales in the system
     */
    @GetMapping("/sale")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok().body(saleService.getSales());
    }

    /**
     * Gets the sales that are ready to deliver
     *
     * @param     id is the client user we are looking for its ready sales
     * @return    returns the list of ready sales
     */
    @GetMapping("/sale/client/available/{id}")
    public ResponseEntity<Object> getAvailableRequest(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().build();

        try {
            int clientId = Integer.parseInt(id);

            return ResponseEntity.ok(saleService.getAvailableOrder(clientId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Gets a sale
     *
     * @param     id the id of the sale we want to get
     * @return    returns the founded sale
     */
    @GetMapping("/sale/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new GetSaleResult(false, "Bad Request", null));

        try {
            int saleId = Integer.parseInt(id);

            Sale sale = saleService.getSale(saleId);

            if (sale == null)
                return ResponseEntity.badRequest().body(new GetSaleResult(false, "Sale doesn't exists", null));
            else
                return ResponseEntity.ok().body(sale);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GetSaleResult(false, e.getMessage(), null));
        }
    }

    /**
     * Create a sale in the system
     *
     * @param     sale contains the information to create the sale
     * @return    returns the result of the creation action
     */
    @PostMapping("/sale")
    public ResponseEntity<Object> create(@RequestBody Sale sale) {
        if (sale.getDate() == null || sale.getTotal() == 0 || sale.getClient() == null || sale.getProductSales().size() == 0)
            return ResponseEntity.badRequest().body(new CreateSaleResult(false, "Bad Request", null));

        if (!saleService.createSale(sale))
            return  ResponseEntity.badRequest().body(new CreateSaleResult(false, "Error creating the sale", null));
        else
            return ResponseEntity.ok().build();
    }

    /**
     * Update a sale in the system
     *
     * @param     sale contains the information to update the sale
     * @return    returns the result of the update action
     */
    @PutMapping("/sale")
    public ResponseEntity<Object> update(@RequestBody Sale sale) {
        if (sale.getOrderState() == null || sale.getId() == 0)
            return ResponseEntity.badRequest().body(new CreateSaleResult(false, "Bad Request", null));

        Sale updatedSale = saleService.updateSaleState(sale.getId(), sale.getOrderState().getId());

        if (updatedSale == null)
            return ResponseEntity.badRequest().body(new UpdateSaleStateResult(false, "Error updating the sale", null));
        else
            return ResponseEntity.ok(new UpdateSaleStateResult(false, "Sale updated", updatedSale));
    }

    /**
     * Delivers a sale to the client
     *
     * @param     id is the sale if we want to deliver
     * @return    returns the result of the delivery
     */
    @PutMapping("/sale/deliver/{id}")
    public ResponseEntity<Object> deliverSale(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().build();

        try {
            int saleId = Integer.parseInt(id);

            Sale sale = saleService.deliverSale(saleId);

            if (sale == null)
                return ResponseEntity.badRequest().build();
            else
                return ResponseEntity.ok(sale);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Gets the products sale in a period of time to a certain fabric
     *
     * @param     password is the password of the fabric that is looking for its report
     * @return    returns the list of sold products
     */
    @GetMapping("/sale/fabric/{password}")
    public ResponseEntity<Object> getSaleReport(@PathVariable("password") String password) {
        if (password == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body(saleService.getSaleReport(password));
    }
}
