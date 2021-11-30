package usecases;

import entities.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A map of user id -> User objects.
 */
public class UserList implements Serializable {
    private final Map<String, User> userList = new HashMap<>();

    /**
     * Add a user to the user list.
     * @param user the UserManager to be added
     */
    public void addUser(User user) {
        userList.put(user.getUsername(), user);
    }

    /**
     * Remove a user from the user list.
     * @param user the UserManager to be removed
     */
    public void removeUser(User user) { userList.remove(user.getUsername()); }

    /**
     * Get the UserManager by username
     * @param username the username of the user to get
     */
    public User getUser(String username) { return userList.get(username); }
}
