package unis.stores.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Request {

    /**
     * The id of the request
     */
    @Id
    @GenericGenerator(
            name = "idRequestSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "REQUEST_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idRequestSequenceGenerator")
    @JsonProperty("id")
    private int id;

    /**
     * The date of the request
     */
    @Column(name = "request_date")
    private Date date;

    /**
     * The productRequests of the request
     */
    @OneToMany
    @JsonProperty("product")
    private List<ProductRequest> productRequests;

    /**
     * The fabric of the request
     */
    @OneToOne
    @JsonProperty("factory")
    private Fabric fabric;

    /**
     * The requestState of the request
     */
    @OneToOne
    @JsonProperty("status")
    private RequestState requestState;

    /**
     * The deliveryDate of the request
     */
    @Column(name = "delivery_date")
    private Date deliveryDate;

    /**
     * The password of the request
     */
    private String password;

    public Request() {
    }

    /**
     * Returns the id of the request
     *
     * @return    the request id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the request
     *
     * @param     id the value we want be the id of the request.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the date of the request
     *
     * @return    the request date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date to the request
     *
     * @param     date the value we want be the date of the request.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns the productRequests of the request
     *
     * @return    the request productRequests.
     */
    public List<ProductRequest> getProductRequests() {
        return productRequests;
    }

    /**
     * Sets the productRequests to the request
     *
     * @param     productRequests the value we want be the productRequests of the request.
     */
    public void setProductRequests(List<ProductRequest> productRequests) {
        this.productRequests = productRequests;
    }

    /**
     * Returns the fabric of the request
     *
     * @return    the request fabric.
     */
    public Fabric getFabric() {
        return fabric;
    }

    /**
     * Sets the fabric to the request
     *
     * @param     fabric the value we want be the fabric of the request.
     */
    public void setFabric(Fabric fabric) {
        this.fabric = fabric;
    }

    /**
     * Returns the requestState of the request
     *
     * @return    the request requestState.
     */
    public RequestState getRequestState() {
        return requestState;
    }

    /**
     * Sets the requestState to the request
     *
     * @param     requestState the value we want be the requestState of the request.
     */
    public void setRequestState(RequestState requestState) {
        this.requestState = requestState;
    }

    /**
     * Returns the deliveryDate of the request
     *
     * @return    the request deliveryDate.
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Sets the deliveryDate to the request
     *
     * @param     deliveryDate the value we want be the deliveryDate of the request.
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * Returns the password of the request
     *
     * @return    the request password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password to the request
     *
     * @param     password the value we want be the password of the request.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
