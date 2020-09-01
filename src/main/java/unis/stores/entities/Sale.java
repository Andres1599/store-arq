package unis.stores.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "sale")
public class Sale {

    /**
     * The id of the sale
     */
    @Id
    @GenericGenerator(
            name = "idSaleSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "SALE_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idSaleSequenceGenerator")
    @JsonProperty("id")
    private int id;

    /**
     * The date of the sale
     */
    @Column(name = "sale_date")
    @JsonProperty("date")
    private Date date;

    /**
     * The total of the sale
     */
    @Column(name = "total")
    @JsonProperty("total")
    private double total;

    /**
     * The client of the sale
     */
    @OneToOne
    @JsonProperty("client")
    private Client client;

    /**
     * The orderState of the sale
     */
    @OneToOne
    private OrderState orderState;

    /**
     * The request of the sale
     */
    @ManyToOne
    private Request request;

    /**
     * The productSales of the sale
     */
    @OneToMany
    @JsonProperty("productSale")
    private List<ProductSale> productSales;

    /**
     * The isCredit of the sale
     */
    @JsonProperty("isCredit")
    private boolean isCredit;

    public Sale() {
    }

    /**
     * Returns the id of the sale
     *
     * @return    the sale id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the sale
     *
     * @param     id the value we want be the id of the sale.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the date of the sale
     *
     * @return    the sale date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date to the sale
     *
     * @param     date the value we want be the date of the sale.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns the total of the sale
     *
     * @return    the sale total.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Sets the total to the sale
     *
     * @param     total the value we want be the total of the sale.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Returns the client of the sale
     *
     * @return    the sale client.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets the client to the sale
     *
     * @param     client the value we want be the client of the sale.
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Returns the orderState of the sale
     *
     * @return    the sale orderState.
     */
    public OrderState getOrderState() {
        return orderState;
    }

    /**
     * Sets the orderState to the sale
     *
     * @param     orderState the value we want be the orderState of the sale.
     */
    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    /**
     * Returns the productSales of the sale
     *
     * @return    the sale productSales.
     */
    public List<ProductSale> getProductSales() {
        return productSales;
    }

    /**
     * Sets the productSales to the sale
     *
     * @param     productSales the value we want be the productSales of the sale.
     */
    public void setProductSales(List<ProductSale> productSales) {
        this.productSales = productSales;
    }

    /**
     * Returns the sale is the credit type
     *
     * @return    a boolean of the state.
     */
    public boolean isCredit() {
        return isCredit;
    }

    /**
     * Sets the credit to the sale
     *
     * @param     credit the value we want be the credit of the sale.
     */
    public void setCredit(boolean credit) {
        isCredit = credit;
    }

    /**
     * Returns the request of the sale
     *
     * @return    the sale request.
     */
    public Request getRequest() {
        return request;
    }

    /**
     * Sets the request to the sale
     *
     * @param     request the value we want be the request of the sale.
     */
    public void setRequest(Request request) {
        this.request = request;
    }
}
