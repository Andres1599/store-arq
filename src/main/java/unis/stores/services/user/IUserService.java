package unis.stores.services.user;

import unis.stores.entities.User;

import java.util.List;

public interface IUserService {

    /**
     * Returns the users of the system
     *
     * @return    the list of the users from the database.
     */
    List<User> getUsers();
    /**
     * Returns a user searched by the id
     *
     * @param     id this is the id of the user that we are looking for
     * @return    the user retrieved from the database
     */
    User getUserById(int id);
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
    User createUser(String firstName, String lastName, String username, String password, int rolId);
    /**
     * Updates the param of a user
     *
     * @param     id this is the id of the user that we want to update
     * @param     firstName this is the firstName of the user we want to update
     * @param     lastName this is the lastName of the user we want to update
     * @param     username this is the username of the user we want to update
     * @return    the updated user
     */
    User updateUser(int id, String firstName, String lastName, String username);
    /**
     * Updates the rol of a user
     *
     * @param     id this is the id of the user that we want to update
     * @param     idRol this is the idRol of the rol we want to update
     * @return    the updated user
     */
    User updateUserRol(int id, int idRol);
    /**
     * Updates the password of a user
     *
     * @param     id this is the id of the user that we want to update
     * @param     newPassword this is the newPassword of the user we want to update
     * @return    the updated user
     */
    boolean updatePassword(int id, String newPassword);
    /**
     * Deletes a user
     *
     * @param     id this is the id of the user that we are deleting
     * @return    a boolean specifying true if success and false in case of error
     */
    boolean deleteUser(int id);
    /**
     * Searches if a user with that username and password exists
     *
     * @param     username this is the username of the user that we are looking for
     * @param     password this is the password of the user that we are looking for
     * @return    the user retrieved from the database
     */
    User login(String username, String password);
    /**
     * Returns a user searched by the username
     *
     * @param     username this is the username of the user that we are looking for
     * @return    the user retrieved from the database
     */
    boolean checkExistUser(String username);
}
