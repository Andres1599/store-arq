package unis.stores.result.sale;

import unis.stores.entities.Sale;
import unis.stores.result.BaseResult;

public class CreateSaleResult extends BaseResult {

    private Sale sale;

    public CreateSaleResult() {
    }

    public CreateSaleResult(boolean success, String message, Sale sale) {
        super(success, message);
        this.sale = sale;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
