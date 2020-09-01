package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.constants.Constants;
import unis.stores.entities.OrderState;
import unis.stores.result.orderstate.CreateOrderStateResult;
import unis.stores.result.orderstate.DeleteOrderStateResult;
import unis.stores.result.orderstate.GetOrderStateResult;
import unis.stores.result.orderstate.UpdateOrderStateResult;
import unis.stores.services.orderstate.OrderStateService;

import java.util.Map;

@CrossOrigin
@Controller
public class OrderStateController {

    /**
     * The order state service to connect to the database
     */
    @Autowired
    private OrderStateService orderstateService;

    /**
     * Create a order state in the system
     *
     * @param     body contains the information to create the order state
     * @return    returns the result of the creation action
     */
    @PostMapping("/order/state")
    public ResponseEntity<Object> create(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.ORDER_STATE_NAME_LABEL))
            return ResponseEntity.badRequest().body(new CreateOrderStateResult(false, "Bad Request", null));

        if (orderstateService.searchByName(body.get(Constants.ORDER_STATE_NAME_LABEL)) != null)
            return ResponseEntity.badRequest().body(new CreateOrderStateResult(false, "The order state already exists", null));

        OrderState orderstate = orderstateService.createOrderState(body.get(Constants.ORDER_STATE_NAME_LABEL));

        if (orderstate == null)
            return ResponseEntity.badRequest().body(new CreateOrderStateResult(false, "Error creating the order state", null));
        else
            return ResponseEntity.ok(new CreateOrderStateResult(true, "OrderState created", orderstate));
    }

    /**
     * Update a order state in the system
     *
     * @param     body contains the information to update the order state
     * @return    returns the result of the update action
     */
    @PutMapping("/order/state")
    public ResponseEntity<Object> update(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.ORDER_STATE_ID_LABEL) || !body.containsKey(Constants.ORDER_STATE_NAME_LABEL))
            return ResponseEntity.badRequest().body(new UpdateOrderStateResult(false, "Bad Request", null));

        try {
            int id = Integer.parseInt(body.get(Constants.ORDER_STATE_ID_LABEL));
            OrderState orderstate = orderstateService.updateOrderState(id, body.get(Constants.ORDER_STATE_NAME_LABEL));

            if (orderstate == null)
                return ResponseEntity.badRequest().body(new UpdateOrderStateResult(false, "Error updating the order state", null));
            else
                return ResponseEntity.ok(new UpdateOrderStateResult(true, "OrderState updated", orderstate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateOrderStateResult(false, "Bad Request", null));
        }
    }

    /**
     * Delete a order state in the system
     *
     * @param     id the id order state we want to delete
     * @return    returns the result of the deletion action
     */
    @DeleteMapping("/order/state/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new DeleteOrderStateResult(false, "Bad Request"));

        try {
            int orderStateId = Integer.parseInt(id);

            if (orderstateService.deleteOrderState(orderStateId))
                return ResponseEntity.ok(new DeleteOrderStateResult(true, "OrderState deleted"));
            else
                return ResponseEntity.badRequest().body(new DeleteOrderStateResult(false, "Error deleting the order state"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DeleteOrderStateResult(false, "Bad Request"));
        }
    }

    /**
     * Gets the system order states
     *
     * @return    returns the list of order states in the system
     */
    @GetMapping("/order/state")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(orderstateService.getOrderStates());
    }

    /**
     * Gets a order state
     *
     * @param     id the id of the order state we want to get
     * @return    returns the founded order state
     */
    @GetMapping("/order/state/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new GetOrderStateResult(false, "Bad Request", null));

        try {
            int orderStateId = Integer.parseInt(id);

            OrderState orderstate = orderstateService.getOrderState(orderStateId);

            return orderstate == null ? ResponseEntity.badRequest().body(new GetOrderStateResult(false, "Error getting the order state", null)) :
                    ResponseEntity.ok().body(new GetOrderStateResult(true, "", orderstate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GetOrderStateResult(false, "Bad Request", null));
        }
    }
}
