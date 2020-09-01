package unis.stores.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Brand {

    /**
     * The id of the brand
     */
    @Id
    @GenericGenerator(
            name = "idBrandSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "BRAND_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idBrandSequenceGenerator")
    private int id;

    /**
     * The name of the brand
     */
    @Column(name = "name")
    private String name;

    public Brand() {
    }

    /**
     * Returns the id of the brand
     *
     * @return    the brand id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the brand
     *
     * @param     id the value we want be the id of the brand.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the brand of the brand
     *
     * @return    the brand name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the id to the brand
     *
     * @param     name the value we want be the brand of the brand.
     */
    public void setName(String name) {
        this.name = name;
    }
}
