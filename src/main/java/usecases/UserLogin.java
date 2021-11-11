package usecases;

import entities.UserList;
import entities.UserManager;

import java.io.IOException;

public class UserLogin {
    private final UserList users;
    UserReadWriter userReadWriter = new UserReadWriter();

    public UserLogin(UserList users) {
        this.users = users;
        try {
            userReadWriter.saveToFile("src/main/java/backend/database/users.ser", users);
        } catch (IOException e) {
            System.out.println("Unable to save user list.");
        }
    }

    /**
     * runs a login attempt with username and password
     * @param username username of this user
     * @param password password attempt for this login
     * @return whether password matches stored password for this user
     */
    public boolean loginAttempt(String username, String password) {
        UserManager user = users.getUser(username);
        return user.passwordMatches(password);
    }

    /**
     * re-saves the username and logs them out
     * @param username username of this user
     */
    public void logout(String username) {
        UserManager savedUser = users.getUser(username);
        users.removeUser(users.getUser(username));
        users.addUser(savedUser);
    }
}
