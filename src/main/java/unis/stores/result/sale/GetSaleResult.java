package unis.stores.result.sale;

import unis.stores.entities.Sale;
import unis.stores.result.BaseResult;

public class GetSaleResult extends BaseResult {

    private Sale sale;

    public GetSaleResult() {
    }

    public GetSaleResult(boolean success, String message, Sale sale) {
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
