package unis.stores.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderState {

    /**
     * The id of the product
     */
    @Id
    @GenericGenerator(
            name = "idOrderStateSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "ORDER_STATE_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idOrderStateSequenceGenerator")
    private int id;

    /**
     * The name of the product
     */
    @Column(name = "name")
    private String name;

    public OrderState() {
    }

    /**
     * Returns the id of the order state
     *
     * @return    the order state id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the order state
     *
     * @param     id the value we want be the id of the order state.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the order state
     *
     * @return    the order state name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name to the order state
     *
     * @param     name the value we want be the name of the order state.
     */
    public void setName(String name) {
        this.name = name;
    }
}
