package unis.stores.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unis.stores.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Gets a user searched by the username and the password, this method is used by the login
     *
     * @param     username is the value for the username we are searching for.
     * @param     password is the value for the password we are searching for.
     * @return    null if no user founded and a user if founded
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * Gets a user searched by the username.
     *
     * @param     username is the value for the username we are searching for.
     * @return    null if no user founded and a user if founded
     */
    User findByUsername(String username);
}
