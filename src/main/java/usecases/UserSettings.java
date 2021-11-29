package usecases;

import entities.BaseUser;
import entities.UserList;
import entities.UserManager;

import java.io.IOException;

public class UserSettings {
    private UserList users;
    private String currentUser;
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
            userReadWriter.saveToFile("src/main/java/backend/database/users.ser", this.users);
        } catch (IOException e) {
            System.out.println("Unable to save user list.");
        }
    }
  
    public UserList deserializeUsers() {
        try {
            return userReadWriter.readFromFile("src/main/java/backend/database/users.ser");
        } catch (IOException e) {
            System.out.println("Unable to read user list.");
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid class.");
            return null;
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

        // Verify the given info checks out
        boolean error = false;
        if (users.getUser(username) != null) {
            // username is already taken
            error = true;
        }
        if (!phoneNumber.matches("^\\(\\d\\d\\d\\)-\\d\\d\\d-\\d\\d\\d$")) {
            // phone number fails regex of form: (416)-123-4567
            error = true;
        }
        if (!email.matches("^\\w+@\\w+\\.((com)|(ca))$")) {
            // email fails regex
            error = true;
        }
        if (!users.phoneExists(phoneNumber)) {
            // phone number already exists
            error = true;
        }
        if (!users.emailExists(email)) {
            // email already exists
            error = true;
        }

        if (error) {
            // Something went wrong
            return null;
            // i think we want to throw errors back to the frontend
            // which specify what went wrong. but idk how to do this
        }

        // All info checks out!
        this.users.addUser(newUser);
        this.serializeUsers();
        this.users = deserializeUsers();
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
        this.currentUser = user.getUsername();
        return user.passwordMatches(password);
    }

    public String getUserType(String username) {
        return users.getUser(username).getUserType();
    }

    public String getCurrentUser(){
        return this.currentUser;
    }
}
