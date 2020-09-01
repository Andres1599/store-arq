package unis.stores.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Rol;
import unis.stores.entities.User;
import unis.stores.repositories.RolRepository;
import unis.stores.repositories.UserRepository;

import java.util.List;

@Service
public class UserService implements IUserService {

    /**
     * The user repository to connect to the database
     */
    private final UserRepository userRepository;

    /**
     * The rol repository to connect to the database
     */
    private final RolRepository rolRepository;

    @Autowired
    public UserService(UserRepository userRepository, RolRepository rolRepository) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
    }

    /**
     * Returns the users of the system
     *
     * @return    the list of the users from the database.
     */
    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Returns a user searched by the id
     *
     * @param     id this is the id of the user that we are looking for
     * @return    the user retrieved from the database
     */
    @Override
    public User getUserById(int id) {
        return userRepository.findOne(id);
    }

    /**
     * Creates a user in the system
     *
     * @param     firstName this is the firstName of the user we want to create
     * @param     lastName this is the lastName of the user we want to create
     * @param     username this is the username of the user we want to create
     * @param     password this is the password of the user we want to create
     * @param     rolId this is the rolId of the user we want to create
     * @return    the created user
     */
    @Override
    public User createUser(String firstName, String lastName, String username, String password, int rolId) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || username == null
            || username.isEmpty() || password == null || password.isEmpty() || rolId == 0)
            return null;

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setUsername(username);
        newUser.setPassword(password);

        Rol userRol = rolRepository.findOne(rolId);
        newUser.setRol(userRol);

        return userRepository.save(newUser);
    }

    /**
     * Updates the param of a user
     *
     * @param     id this is the id of the user that we want to update
     * @param     firstName this is the firstName of the user we want to update
     * @param     lastName this is the lastName of the user we want to update
     * @param     username this is the username of the user we want to update
     * @return    the updated user
     */
    @Override
    public User updateUser(int id, String firstName, String lastName, String username) {
        if (!userRepository.exists(id)) {
            return null;
        }

        User updateUser = userRepository.findOne(id);

        updateUser.setFirstName(firstName);
        updateUser.setLastName(lastName);
        updateUser.setUsername(username);

        return userRepository.save(updateUser);
    }

    /**
     * Updates the rol of a user
     *
     * @param     id this is the id of the user that we want to update
     * @param     idRol this is the idRol of the rol we want to update
     * @return    the updated user
     */
    @Override
    public User updateUserRol(int id, int idRol) {
        if (!userRepository.exists(id) || !rolRepository.exists(idRol))
            return null;

        User updateUser = userRepository.findOne(id);
        Rol updateRol = rolRepository.findOne(idRol);

        updateUser.setRol(updateRol);

        return userRepository.save(updateUser);
    }

    /**
     * Updates the password of a user
     *
     * @param     id this is the id of the user that we want to update
     * @param     newPassword this is the newPassword of the user we want to update
     * @return    the updated user
     */
    @Override
    public boolean updatePassword(int id, String newPassword) {
        if (!userRepository.exists(id))
            return false;

        User updateUser = userRepository.findOne(id);

        updateUser.setPassword(newPassword);

        return userRepository.save(updateUser) != null;
    }

    /**
     * Deletes a user
     *
     * @param     id this is the id of the user that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    @Override
    public boolean deleteUser(int id) {
        if (!userRepository.exists(id))
            return false;

        userRepository.delete(id);
        return true;
    }

    /**
     * Searches if a user with that username and password exists
     *
     * @param     username this is the username of the user that we are looking for
     * @param     password this is the password of the user that we are looking for
     * @return    the user retrieved from the database
     */
    @Override
    public User login(String username, String password) {
        User login = userRepository.findByUsernameAndPassword(username, password);

        if (login != null)
            login.setPassword(null);

        return login;
    }

    /**
     * Returns a user searched by the username
     *
     * @param     username this is the username of the user that we are looking for
     * @return    the user retrieved from the database
     */
    @Override
    public boolean checkExistUser(String username) {
        return userRepository.findByUsername(username) != null;
    }
}
