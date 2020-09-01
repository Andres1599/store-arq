package unis.stores.result.orderstate;

import unis.stores.entities.OrderState;
import unis.stores.result.BaseResult;

public class UpdateOrderStateResult extends BaseResult {

    private OrderState orderState;

    public UpdateOrderStateResult() {
    }

    public UpdateOrderStateResult(boolean success, String message, OrderState orderState) {
        super(success, message);
        this.orderState = orderState;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}
