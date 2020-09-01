package unis.stores.services.orderstate;

import unis.stores.entities.OrderState;

import java.util.List;

public interface IOrderStateService {

    /**
     * Returns the order states of the system
     *
     * @return    the list of the order states from the database.
     */
    List<OrderState> getOrderStates();
    /**
     * Returns a order state searched by the id
     *
     * @param     id this is the id of the order state that we are looking for
     * @return    the order state retrieved from the database
     */
    OrderState getOrderState(int id);
    /**
     * Creates a order state in the system
     *
     * @param     name this is the name of the order state we want to create
     * @return    the created order state
     */
    OrderState createOrderState(String name);
    /**
     * Updates the param of a order state
     *
     * @param     id this is the id of the order state that we want to update
     * @param     name this is the name of the order state we want to update
     * @return    the updated order state
     */
    OrderState updateOrderState(int id, String name);
    /**
     * Deletes a order state
     *
     * @param     id this is the id of the order state that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    boolean deleteOrderState(int id);
    /**
     * Returns a order state searched by the name
     *
     * @param     name this is the name of the order state that we are looking for
     * @return    the order state retrieved from the database
     */
    OrderState searchByName(String name);
}
