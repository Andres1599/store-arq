package unis.stores.services.orderstate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.OrderState;
import unis.stores.repositories.OrderStateRepository;

import java.util.List;

@Service
public class OrderStateService implements IOrderStateService{

    /**
     * The order state repository to connect to the database
     */
    @Autowired
    private OrderStateRepository orderStateRepository;

    /**
     * Returns the order states of the system
     *
     * @return    the list of the order states from the database.
     */
    @Override
    public List<OrderState> getOrderStates() {
        return (List<OrderState>) orderStateRepository.findAll();
    }

    /**
     * Returns a order state searched by the id
     *
     * @param     id this is the id of the order state that we are looking for
     * @return    the order state retrieved from the database
     */
    @Override
    public OrderState getOrderState(int id) {
        return orderStateRepository.findOne(id);
    }

    /**
     * Creates a order state in the system
     *
     * @param     name this is the name of the order state we want to create
     * @return    the created order state
     */
    @Override
    public OrderState createOrderState(String name) {
        OrderState orderState = new OrderState();
        orderState.setName(name);

        return orderStateRepository.save(orderState);
    }

    /**
     * Updates the param of a order state
     *
     * @param     id this is the id of the order state that we want to update
     * @param     name this is the name of the order state we want to update
     * @return    the updated order state
     */
    @Override
    public OrderState updateOrderState(int id, String name) {
        if (!orderStateRepository.exists(id))
            return null;

        OrderState orderState = orderStateRepository.findOne(id);
        orderState.setName(name);

        return orderStateRepository.save(orderState);
    }

    /**
     * Deletes a order state
     *
     * @param     id this is the id of the order state that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    @Override
    public boolean deleteOrderState(int id) {
        if (!orderStateRepository.exists(id))
            return false;

        orderStateRepository.delete(id);
        return true;
    }

    /**
     * Returns a order state searched by the name
     *
     * @param     name this is the name of the order state that we are looking for
     * @return    the order state retrieved from the database
     */
    @Override
    public OrderState searchByName(String name) {
        return orderStateRepository.findByName(name);
    }
}
