package unis.stores.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity(name = "product_request")
public class ProductRequest {

    /**
     * The id of the product request
     */
    @Id
    @GenericGenerator(
            name = "idProductRequestSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "PRODUCT_REQUEST_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idProductRequestSequenceGenerator")
    @JsonProperty("id")
    private int id;

    /**
     * The product of the product request
     */
    @OneToOne
    @JsonProperty("product")
    private Product product;

    /**
     * The request of the product request
     */
    @ManyToOne
    private Request request;

    /**
     * The requestedStock of the product request
     */
    @Column(name = "requested_stock")
    @JsonProperty("stockSale")
    private int requestedStock;

    public ProductRequest() {
    }

    /**
     * Returns the id of the product request
     *
     * @return    the product request id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the product request
     *
     * @param     id the value we want be the id of the product request.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the product of the product request
     *
     * @return    the product request product.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product to the product request
     *
     * @param     product the value we want be the product of the product request.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Returns the request of the product request
     *
     * @return    the product request request.
     */
    public Request getRequest() {
        return request;
    }

    /**
     * Sets the request to the product request
     *
     * @param     request the value we want be the request of the product request.
     */
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * Returns the requestedStock of the product request
     *
     * @return    the product request requestedStock.
     */
    public int getRequestedStock() {
        return requestedStock;
    }

    /**
     * Sets the requestedStock to the product request
     *
     * @param     requestedStock the value we want be the requestedStock of the product request.
     */
    public void setRequestedStock(int requestedStock) {
        this.requestedStock = requestedStock;
    }
}
