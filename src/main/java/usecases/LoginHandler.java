package usecases;

import entities.User;
import java.io.IOException;

/**
 * LoginHandler handles login and account creation.
 */

public class LoginHandler {
    private UserList users;
    public ViewProfile currentUser;
    UserReadWriter userReadWriter = new UserReadWriter();

    /**
     * Initialize UserManager with users pointing to the current serialized user list.
     */
    public LoginHandler() {
        try {
            this.users = userReadWriter.readFromFile("src/main/java/backend/database/users.ser");
        } catch (IOException e) {
            System.out.println("Unable to read user list.");
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid class.");
        }
    }

    public void saveSettings() {
        serializeUsers();
        users = deserializeUsers();
    }

    /**
     * Writes the current users to users.ser.
     */
    public void serializeUsers() {
        try {
            userReadWriter.saveToFile("src/main/java/backend/database/users.ser", this.users);
        } catch (IOException e) {
            System.out.println("Unable to save user list.");
        }
    }

    /**
     * Reads the current user list and returns them in a UserList.
     * @return a UserList of the current users
     */
    public UserList deserializeUsers() {
        try {
            users = userReadWriter.readFromFile("src/main/java/backend/database/users.ser");
            return users;
        } catch (IOException e) {
            System.out.println("Unable to read user list.");
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid class.");
            return null;
        }
    }
  
    /**
     * Create a new UserManager with username, password, email, and phoneNumber and add them to users
     * @param username username of new user
     * @param password password of new user
     * @param email email address of user
     * @param phoneNumber phone number of new user
     */
    public boolean createAccount(String username, String password, String email, String phoneNumber) {
        User newUser = new User(username, password, email, phoneNumber);

        // Verify the given info checks out
        boolean error = false;
        if (users.getUser(username) != null) {
            // username is already taken
            error = true;
        }
        if (!phoneNumber.matches("^\\(\\d\\d\\d\\)-\\d\\d\\d-\\d\\d\\d\\d$")) {
            // phone number fails regex of form: (416)-123-4567
            error = true;
        }
        if (!email.matches("^\\w+@\\w+\\.((com)|(ca))$")) {
            // email fails regex
            error = true;
        }
        if (users.phoneExists(phoneNumber)) {
            // phone number already exists
            error = true;
        }
        if (users.emailExists(email)) {
            // email already exists
            error = true;
        }

        if (error) {
            // Something went wrong
            return false;
        } else {
            // everything behaving well
            this.users.addUser(newUser);
            saveSettings();
            return true;
        }
    }

    public void deleteAccount(String username) {
        users.removeUser(users.getUser(username));
    }

    /**
     * Runs a login attempt with username and password and sets currentUser to user specified by username.
     * @param username username of this user
     * @param password password attempt for this login
     * @return whether password matches stored password for this user
     */
    public boolean loginAttempt(String username, String password) {
        User user = users.getUser(username);
        currentUser = new ViewProfile(user); // currentUser points to a ViewProfile for the user who is logged in
        return user.passwordMatches(password);
    }

    /**
     * Runs a logout (does not exit the program) by setting currentUser to null.
     */
    public void logout() {
        this.currentUser = null; // currentUser no longer points to any specific ViewProfile for a user
    }

    // TODO: remove this and replace instances of it with the User itself?
    public String getCurrentUserUsername() { return this.currentUser.getUsername(); }

    public static void main(String[] args) {
        System.out.println("416-123-4567".matches("^\\d\\d\\d-\\d\\d\\d-\\d\\d\\d$"));
    }
}
