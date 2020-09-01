package unis.stores.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity(name = "product_sale")
public class ProductSale {

    /**
     * The id of the product sale
     */
    @Id
    @GenericGenerator(
            name = "idProductSaleSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "PRODUCT_SALE_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idProductSaleSequenceGenerator")
    @JsonProperty("id")
    private int id;

    /**
     * The product of the product sale
     */
    @OneToOne
    @JsonProperty("product")
    private Product product;

    /**
     * The sale of the product sale
     */
    @OneToOne
    private Sale sale;

    /**
     * The salePrice of the product sale
     */
    @Column(name = "sale_price")
    @JsonProperty("priceSale")
    private double salePrice;

    /**
     * The soldStock of the product sale
     */
    @Column(name = "sold_stock")
    @JsonProperty("stockSale")
    private int soldStock;

    public ProductSale() {
    }

    /**
     * Returns the id of the product sale
     *
     * @return    the product sale id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the product sale
     *
     * @param     id the value we want be the id of the product sale.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the product of the product sale
     *
     * @return    the product sale product.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product to the product sale
     *
     * @param     product the value we want be the product of the product sale.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Returns the sale of the product sale
     *
     * @return    the product sale sale.
     */
    public Sale getSale() {
        return sale;
    }

    /**
     * Sets the sale to the product sale
     *
     * @param     sale the value we want be the sale of the product sale.
     */
    public void setSale(Sale sale) {
        this.sale = sale;
    }

    /**
     * Returns the salePrice of the product sale
     *
     * @return    the product sale salePrice.
     */
    public double getSalePrice() {
        return salePrice;
    }

    /**
     * Sets the salePrice to the product sale
     *
     * @param     salePrice the value we want be the salePrice of the product sale.
     */
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * Returns the soldStock of the product sale
     *
     * @return    the product sale soldStock.
     */
    public int getSoldStock() {
        return soldStock;
    }

    /**
     * Sets the soldStock to the product sale
     *
     * @param     soldStock the value we want be the soldStock of the product sale.
     */
    public void setSoldStock(int soldStock) {
        this.soldStock = soldStock;
    }
}
