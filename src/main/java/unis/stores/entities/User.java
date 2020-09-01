package unis.stores.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity(name = "users")
public class User {

    /**
     * The id of the user
     */
    @Id
    @GenericGenerator(
            name = "idSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "USER_ID_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "3"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    @GeneratedValue(generator = "idSequenceGenerator")
    private int id;

    /**
     * The firstName of the user
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The lastName of the user
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The username of the user
     */
    @Column(name = "username")
    private String username;

    /**
     * The password of the user
     */
    @Column(name = "password")
    private String password;

    /**
     * The rol of the user
     */
    @OneToOne
    private Rol rol;

    public User() {
    }

    /**
     * Returns the id of the user
     *
     * @return    the user id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id to the user
     *
     * @param     id the value we want be the id of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the firstName of the user
     *
     * @return    the user firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the firstName to the user
     *
     * @param     firstName the value we want be the firstName of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the lastName of the user
     *
     * @return    the user lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the lastName to the user
     *
     * @param     lastName the value we want be the lastName of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the username of the user
     *
     * @return    the user username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username to the user
     *
     * @param     username the value we want be the username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user
     *
     * @return    the user password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password to the user
     *
     * @param     password the value we want be the password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the rol of the user
     *
     * @return    the user rol.
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Sets the rol to the user
     *
     * @param     rol the value we want be the rol of the user.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
