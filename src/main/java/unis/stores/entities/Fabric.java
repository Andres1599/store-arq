package unis.stores.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Fabric {

    /**
     * The id of the fabric
     */
    @Id
    @GenericGenerator(
            name = "idFabricSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "FABRIC_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idFabricSequenceGenerator")
    @JsonProperty("id")
    private int id;

    /**
     * The name of the fabric
     */
    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    /**
     * The ip of the fabric
     */
    @Column(name = "ip")
    @JsonProperty("ip")
    private String ip;

    /**
     * The servicePassword of the fabric
     */
    @Column(name = "service_password")
    @JsonProperty("servicePassword")
    private String servicePassword;

    /**
     * The lastDateRequest of the fabric
     */
    @Column(name = "last_date_request")
    @JsonProperty("lastDateRequest")
    private Date lastDateRequest;

    public Fabric() {
    }

    /**
     * Returns the id of the fabric
     *
     * @return    the fabric id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the fabric
     *
     * @param     id the value we want be the id of the fabric.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the fabric
     *
     * @return    the fabric name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name to the fabric
     *
     * @param     name the value we want be the name of the fabric.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the ip of the fabric
     *
     * @return    the fabric ip.
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets the ip to the fabric
     *
     * @param     ip the value we want be the ip of the fabric.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Returns the servicePassword of the fabric
     *
     * @return    the fabric servicePassword.
     */
    public String getServicePassword() {
        return servicePassword;
    }

    /**
     * Sets the servicePassword to the fabric
     *
     * @param     servicePassword the value we want be the servicePassword of the fabric.
     */
    public void setServicePassword(String servicePassword) {
        this.servicePassword = servicePassword;
    }

    /**
     * Returns the lastDateRequest of the fabric
     *
     * @return    the fabric lastDateRequest.
     */
    public Date getLastDateRequest() {
        return lastDateRequest;
    }

    /**
     * Sets the lastDateRequest to the fabric
     *
     * @param     lastDateRequest the value we want be the lastDateRequest of the fabric.
     */
    public void setLastDateRequest(Date lastDateRequest) {
        this.lastDateRequest = lastDateRequest;
    }
}
