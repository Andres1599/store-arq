package unis.stores.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "rol")
public class Rol {

    /**
     * The id of the rol
     */
    @Id
    @GenericGenerator(
            name = "idRolSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "ROL_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "3"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idRolSequenceGenerator")
    private int id;

    /**
     * The name of the rol
     */
    @Column(name = "name")
    private String name;

    public Rol() {
    }

    /**
     * Returns the id of the rol
     *
     * @return    the rol id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the rol
     *
     * @param     id the value we want be the id of the rol.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the rol
     *
     * @return    the rol name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name to the rol
     *
     * @param     name the value we want be the name of the rol.
     */
    public void setName(String name) {
        this.name = name;
    }
}
