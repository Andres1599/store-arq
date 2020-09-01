package unis.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unis.stores.constants.Constants;
import unis.stores.entities.User;
import unis.stores.result.user.*;
import unis.stores.services.user.UserService;

import java.util.Map;

@CrossOrigin
@Controller
public class UserController {

    /**
     * The user service to connect to the database
     */
    @Autowired
    private UserService userService;

    @PostMapping(
            value = "/login",
            headers = "Accept=application/json")
    public ResponseEntity<Object> logIn(@RequestBody Map<String, String> body) {
        if (body.get(Constants.USERNAME_LABEL) == null || body.get(Constants.PASSWORD_LABEL) == null)
            return ResponseEntity.badRequest().body(new LoginResult(false, "No Credentials Provided!"));

        User loggedUser = userService.login(body.get(Constants.USERNAME_LABEL), body.get(Constants.PASSWORD_LABEL));

        if (loggedUser != null) {
            return ResponseEntity.ok().body(loggedUser);
        } else {
            return ResponseEntity.badRequest().body(new LoginResult(false, "Bad Credentials!"));
        }
    }

    /**
     * Signs Int a user in the system
     *
     * @param     body contains the information to create the user
     * @return    returns the result of the creation action
     */
    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.FIRST_NAME_LABEL) || !body.containsKey(Constants.LAST_NAME_LABEL) || !body.containsKey(Constants.USERNAME_LABEL)
            || !body.containsKey(Constants.PASSWORD_LABEL) || !body.containsKey(Constants.USER_ROL_ID_LABEL))
            return ResponseEntity.badRequest().body(new SignInResult(false, "Bad Request"));

        try {
            int rolId = Integer.parseInt(body.get(Constants.USER_ROL_ID_LABEL));

            if (userService.checkExistUser(body.get(Constants.USERNAME_LABEL)))
                return ResponseEntity.badRequest().body(new SignInResult(false, "User Already Exists!"));

            User createdUser = userService.createUser(body.get(Constants.FIRST_NAME_LABEL), body.get(Constants.LAST_NAME_LABEL),
                    body.get(Constants.USERNAME_LABEL), body.get(Constants.PASSWORD_LABEL), rolId);

            if (createdUser == null) {
                return ResponseEntity.badRequest().body(new SignInResult(false, "Error creating the user!"));
            } else {
                return ResponseEntity.ok().body(createdUser);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new SignInResult(false, "Bad Request"));
        }
    }

    /**
     * Update a user in the system
     *
     * @param     body contains the information to update the user
     * @return    returns the result of the update action
     */
    @PutMapping("/user")
    public ResponseEntity<Object> update(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.USER_ID_LABEL) || !body.containsKey(Constants.FIRST_NAME_LABEL) ||
                !body.containsKey(Constants.LAST_NAME_LABEL) || !body.containsKey(Constants.USERNAME_LABEL))
            return ResponseEntity.badRequest().body(new UpdateUserResult(false, "Bad Request"));

        try {
            int userId = Integer.parseInt(body.get(Constants.USER_ID_LABEL));

            if (userService.getUserById(userId) == null)
                return ResponseEntity.badRequest().body(new SignInResult(false, "User doesn't Exists!"));

            if (body.containsKey(Constants.USER_ROL_ID_LABEL)){
                int rolId = Integer.parseInt(body.get(Constants.USER_ROL_ID_LABEL));

                userService.updateUserRol(userId, rolId);
            }

            User userUpdated = userService.updateUser(userId, body.get(Constants.FIRST_NAME_LABEL), body.get(Constants.LAST_NAME_LABEL),
                    body.get(Constants.USERNAME_LABEL));

            if (userUpdated == null) {
                return ResponseEntity.badRequest().body(new SignInResult(false, "Error updating the user!"));
            } else {
                return ResponseEntity.ok().body(userUpdated);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new SignInResult(false, "Bad Request"));
        }
    }

    /**
     * Update the rol of a user
     *
     * @param     body contains the information to update the user rol
     * @return    returns the result of the update action
     */
    @PutMapping("/user/rol")
    public ResponseEntity<Object> updateRol(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.USER_ID_LABEL) || !body.containsKey(Constants.USER_ROL_ID_LABEL))
            return ResponseEntity.badRequest().body(new UpdateUserRolResult(false, "Bad Request"));

        try {
            int userId = Integer.parseInt(body.get(Constants.USER_ID_LABEL));
            int rolId = Integer.parseInt(body.get(Constants.USER_ROL_ID_LABEL));

            User userUpdated = userService.updateUserRol(userId, rolId);

            if (userUpdated == null) {
                return ResponseEntity.badRequest().body(new UpdateUserRolResult(false, "Error updating the user!"));
            } else {
                return ResponseEntity.ok().body(userUpdated);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateUserRolResult(false, "Bad Request"));
        }
    }

    /**
     * Update the password of a user
     *
     * @param     body contains the information to update the user password
     * @return    returns the result of the update action
     */
    @PutMapping("/user/password")
    public ResponseEntity<Object> updatePassword(@RequestBody Map<String, String> body) {
        if (!body.containsKey(Constants.USER_ID_LABEL) || !body.containsKey(Constants.PASSWORD_LABEL))
            return ResponseEntity.badRequest().body(new UpdateUserPasswordResult(false, "Bad Request"));

        try {
            int userId = Integer.parseInt(body.get(Constants.USER_ID_LABEL));

            if (userService.updatePassword(userId, body.get(Constants.PASSWORD_LABEL))) {
                return ResponseEntity.ok().body(new UpdateUserPasswordResult(true, "Password updated successfully"));
            } else {
                return ResponseEntity.badRequest().body(new UpdateUserPasswordResult(false, "Error updating the password"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UpdateUserPasswordResult(false, "Bad Request"));
        }
    }

    /**
     * Delete a user in the system
     *
     * @param     id the id user we want to delete
     * @return    returns the result of the deletion action
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new DeleteUserResult(false, "Bad Request"));

        try {
            int userId = Integer.parseInt(id);

            if (userService.deleteUser(userId)) {
                return ResponseEntity.ok().body(new DeleteUserResult(true, "User deleted successfully"));
            } else {
                return ResponseEntity.badRequest().body(new DeleteUserResult(false, "Error deleting user"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DeleteUserResult(false, "Bad Request"));
        }
    }

    /**
     * Gets the system users
     *
     * @return    returns the list of users in the system
     */
    @GetMapping("/user")
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    /**
     * Gets a user
     *
     * @param     id the id of the user we want to get
     * @return    returns the founded user
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id) {
        try {
            int userId = Integer.parseInt(id);

            User user = userService.getUserById(userId);

            if (user != null)
                return ResponseEntity.ok().body(user);
            else
                return ResponseEntity.badRequest().body(new GetUserResult(false, "User doesn't exists!"));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GetUserResult(false, "Bad Request"));
        }
    }
}
