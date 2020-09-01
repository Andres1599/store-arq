package unis.stores.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "client")
public class Client {

    /**
     * The id of the client
     */
    @Id
    @GenericGenerator(
            name = "idClientSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "CLIENT_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idClientSequenceGenerator")
    private int id;

    /**
     * The name of the client
     */
    @Column(name = "name")
    private String name;

    /**
     * The nit of the client
     */
    @Column(name = "nil")
    private String nit;

    /**
     * The email of the client
     */
    @Column(name = "email")
    private String email;

    /**
     * The phone of the client
     */
    @Column(name = "phone")
    private String phone;

    /**
     * The image of the client
     */
    @Column(name = "image")
    @Lob
    private String image;

    /**
     * The subExpireDate of the client
     */
    @Column(name = "sub_expire_date")
    private Date subExpireDate;

    /**
     * The subscription of the client
     */
    @OneToOne
    private Subscription subscription;

    public Client() {
    }

    /**
     * Returns the id of the client
     *
     * @return    the client id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the client
     *
     * @param     id the value we want be the id of the client.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the client
     *
     * @return    the client name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name to the client
     *
     * @param     name the value we want be the name of the client.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the nit of the client
     *
     * @return    the client nit.
     */
    public String getNit() {
        return nit;
    }

    /**
     * Sets the nit to the client
     *
     * @param     nit the value we want be the nit of the client.
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * Returns the email of the client
     *
     * @return    the client email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email to the client
     *
     * @param     email the value we want be the email of the client.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the phone of the client
     *
     * @return    the client phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone to the client
     *
     * @param     phone the value we want be the phone of the client.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the image of the client
     *
     * @return    the client image.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image to the client
     *
     * @param     image the value we want be the image of the client.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Returns the subscription of the client
     *
     * @return    the client subscription.
     */
    public Subscription getSubscription() {
        return subscription;
    }

    /**
     * Sets the subscription to the client
     *
     * @param     subscription the value we want be the subscription of the client.
     */
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    /**
     * Returns the subExpireDate of the client
     *
     * @return    the client subExpireDate.
     */
    public Date getSubExpireDate() {
        return subExpireDate;
    }

    /**
     * Sets the subExpireDate to the client
     *
     * @param     subExpireDate the value we want be the subExpireDate of the client.
     */
    public void setSubExpireDate(Date subExpireDate) {
        this.subExpireDate = subExpireDate;
    }
}
