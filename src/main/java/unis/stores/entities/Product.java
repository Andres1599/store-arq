package unis.stores.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "product")
public class Product {

    /**
     * The id of the product
     */
    @Id
    @GenericGenerator(
            name = "idProductSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "PRODUCT_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idProductSequenceGenerator")
    @JsonProperty("id")
    private int id;

    /**
     * The name of the product
     */
    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    /**
     * The description of the product
     */
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    /**
     * The partNo of the product
     */
    @Column(name = "part_no")
    @JsonProperty("partNo")
    private String partNo;

    /**
     * The price of the product
     */
    @Column(name = "price")
    @JsonProperty("price")
    private double price;

    /**
     * The stock of the product
     */
    @Column(name = "stock")
    @JsonProperty("stock")
    private int stock;

    /**
     * The vehicles assigned to the product
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "vehicle_products", joinColumns = {@JoinColumn(referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(referencedColumnName = "universal_code")})
    private List<Vehicle> vehicles;

    /**
     * The fabric of the product
     */
    @ManyToOne
    private Fabric fabric;

    public Product() {
    }

    /**
     * Returns the id of the product
     *
     * @return    the product id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the product
     *
     * @param     id the value we want be the id of the product.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the product
     *
     * @return    the product name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name to the product
     *
     * @param     name the value we want be the name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the product
     *
     * @return    the product description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description to the product
     *
     * @param     description the value we want be the description of the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the part number of the product
     *
     * @return    the product part number.
     */
    public String getPartNo() {
        return partNo;
    }

    /**
     * Sets the partNo to the product
     *
     * @param     partNo the value we want be the part number of the product.
     */
    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    /**
     * Returns the price of the product
     *
     * @return    the product price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price to the product
     *
     * @param     price the value we want be the price of the product.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the stock of the product
     *
     * @return    the product stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock to the product
     *
     * @param     stock the value we want be the stock of the product.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Returns the vehicles assigned to the product
     *
     * @return    the product vehicles list.
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Sets the vehicles to the product
     *
     * @param     vehicles the list of vehicles we want to assign to the product.
     */
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * Returns the fabric of the product
     *
     * @return    the product fabric.
     */
    public Fabric getFabric() {
        return fabric;
    }

    /**
     * Sets the fabric to the product
     *
     * @param     fabric the fabric we want to assign to the product
     */
    public void setFabric(Fabric fabric) {
        this.fabric = fabric;
    }

    /**
     * Returns the sale price of the product, based in the purchase price
     *
     * @return    the product sale price.
     */
    public double getSalePrice() {
        return round(this.price * 1.90, 2);
    }

    /**
     * Returns the sale price of the product, based in the purchase price
     *
     * @return    the product sale price.
     */
    public double getValueWithoutIVA() {
        return round(getSalePrice() / 1.12, 2);
    }

    /**
     * Returns a rounded number by an specific number of decimals
     *
     * @param     value the value we want to round to certain number of decimals.
     * @param     places the number of decimals we want in the final rounded number.
     * @return    the rounded value.
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
