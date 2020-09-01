package unis.stores.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "subscription")
public class Subscription {

    /**
     * The id of the subscription
     */
    @Id
    @GenericGenerator(
            name = "idSubscriptionSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "SUBSCRIPTION_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "3"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idSubscriptionSequenceGenerator")
    private int id;

    /**
     * The name of the subscription
     */
    @Column(name = "name")
    public String name;

    /**
     * The discount of the subscription
     */
    @Column(name = "discount")
    public int discount;

    public Subscription() {
    }

    /**
     * Returns the id of the subscription
     *
     * @return    the subscription id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the subscription
     *
     * @param     id the value we want be the id of the subscription.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the subscription
     *
     * @return    the subscription name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name to the subscription
     *
     * @param     name the value we want be the name of the subscription.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the discount of the subscription
     *
     * @return    the subscription discount.
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * Sets the discount to the subscription
     *
     * @param     discount the value we want be the discount of the subscription.
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
