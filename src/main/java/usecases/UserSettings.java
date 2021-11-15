package usecases;

import entities.UserList;
import entities.UserManager;

import java.io.IOException;

public class UserSettings {
    private UserList users;
    UserReadWriter userReadWriter = new UserReadWriter();

    public UserSettings() {
        try {
            this.users = userReadWriter.readFromFile("src/main/java/backend/database/users.ser");
        } catch (IOException e) {
            System.out.println("Unable to read user list.");
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid class.");
        }
    }

    public void serializeUsers() {
        try {
            userReadWriter.saveToFile("src/main/java/backend/database/users.ser", users);
        } catch (IOException e) {
            System.out.println("Unable to save user list.");
        }
    }

    /**
     * create a new UserManager with username, password, email, and phoneNumber and add them to users
     * @param username username of new user
     * @param password password of new user
     * @param email email address of user
     * @param phoneNumber phone number of new user
     * @return UserManager representing this new user
     */
    public UserManager createAccount(String username, String password, String email, String phoneNumber) {
        UserManager newUser = new UserManager(username, password, email, phoneNumber);
        users.addUser(newUser);
        return newUser;
    }

    public void deleteAccount(String username) {
        users.removeUser(users.getUser(username));
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

    public String getUserType(String username) {
        return users.getUser(username).getUserType();
    }

}
