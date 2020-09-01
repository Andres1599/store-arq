package unis.stores.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Line {

    /**
     * The id of the line
     */
    @Id
    @GenericGenerator(
            name = "idLineSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "LINE_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idLineSequenceGenerator")
    private int id;

    /**
     * The name of the line
     */
    @Column(name = "name")
    private String name;

    public Line() {
    }

    /**
     * Returns the id of the line
     *
     * @return    the line id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the line
     *
     * @param     id the value we want be the id of the line.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the line
     *
     * @return    the line name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name to the line
     *
     * @param     name the value we want be the name of the line.
     */
    public void setName(String name) {
        this.name = name;
    }
}
