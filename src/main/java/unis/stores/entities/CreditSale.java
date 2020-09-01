package unis.stores.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity(name = "credit_sale")
public class CreditSale {

    /**
     * The id of the credit sale
     */
    @Id
    @GenericGenerator(
            name = "idCreditSaleSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "CREDIT_SALE_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idCreditSaleSequenceGenerator")
    @JsonProperty("id")
    private int id;

    /**
     * The sale of the credit sale
     */
    @ManyToOne
    private Sale sale;

    /**
     * The client of the credit sale
     */
    @ManyToOne
    private Client client;

    public CreditSale() {
    }

    /**
     * Returns the id of the credit sale
     *
     * @return    the credit sale id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the credit sale
     *
     * @param     id the value we want be the id of the credit sale.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the sale of the credit sale
     *
     * @return    the credit sale sale.
     */
    public Sale getSale() {
        return sale;
    }

    /**
     * Sets the sale to the credit sale
     *
     * @param     sale the value we want be the sale of the credit sale.
     */
    public void setSale(Sale sale) {
        this.sale = sale;
    }

    /**
     * Returns the client of the credit sale
     *
     * @return    the credit sale client.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets the id to the credit sale
     *
     * @param     client the value we want be the client of the credit sale.
     */
    public void setClient(Client client) {
        this.client = client;
    }
}
