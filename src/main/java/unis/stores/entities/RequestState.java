package unis.stores.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RequestState {

    /**
     * The id of the line
     */
    @Id
    @GenericGenerator(
            name = "idRequestStateSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "REQUEST_STATE_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idRequestStateSequenceGenerator")
    @JsonProperty("id")
    private int id;

    /**
     * The name of the line
     */
    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    public RequestState() {
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
