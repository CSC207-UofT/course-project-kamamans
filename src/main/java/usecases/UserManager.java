package usecases;

import entities.Airport;
import entities.Flight;
import entities.User;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * UserManager handles editing user profiles and methods for all user types.
 */

public class UserManager {
    private UserList users;
    private User currentUser;
    UserReadWriter userReadWriter = new UserReadWriter();

    /**
     * Initialize UserManager with users pointing to the current list.
     */
    public UserManager() {
        try {
            this.users = userReadWriter.readFromFile("src/main/java/backend/database/users.ser");
        } catch (IOException e) {
            System.out.println("Unable to read user list.");
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid class.");
        }
    }

    /**
     * Saves the current users to users.ser.
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
     * @return UserManager representing this new user
     */
    public User createAccount(String username, String password, String email, String phoneNumber) {
        User newUser = new User(username, password, email, phoneNumber);
        users.addUser(newUser);
        serializeUsers();
        users = deserializeUsers();
        return newUser;
    }

    public void deleteAccount() {
        users.removeUser(currentUser);
    }

    /**
     * runs a login attempt with username and password and sets currentUser to user specified by username
     * @param username username of this user
     * @param password password attempt for this login
     * @return whether password matches stored password for this user
     */
    public boolean loginAttempt(String username, String password) {
        User user = users.getUser(username);
        currentUser = user; // currentUser points to the user who is logged in
        return user.passwordMatches(password);
    }

    /**
     * Runs a logout (does not exit the program) by setting currentUser to null.
     */
    public void logout() {
        this.currentUser = null; // currentUser no longer points to any specific user
    }

    public String getCurrentUserUsername(){
        return this.currentUser.getUsername();
    }

    public String getPassword() {
        return currentUser.getPassword();
    }

    public void setPassword(String password) {
        currentUser.setPassword(password);
    }

    public String getEmail() {
        return currentUser.getEmail();
    }

    public void setEmail(String email) {
        currentUser.setEmail(email);
    }

    public String getPhoneNumber() {
        return currentUser.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        currentUser.setPhoneNumber(phoneNumber);
    }

    public int getAppRating() {
        return currentUser.getAppRating();
    }

    public void setAppRating(int appRating) {
        currentUser.setAppRating(appRating);
    }

    public void addFlightToHistory(Flight flight) { currentUser.addFlightToHistory(flight); }

    public String upgradeUserType() {
        return currentUser.upgradeUserType();
    }

    public String downgradeUserType() {
        return currentUser.downgradeUserType();
    }

    public String getUserType() { return currentUser.getUserType(); }

    // methods past here are premium user functions, so they check user type
    public String getClassType() {
        return currentUser.user.getClassType();
    }

    public Date getRenewalDate() {
        return currentUser.user.getRenewalDate();
    }

    public String getColorScheme() {
        return currentUser.user.getColorScheme();
    }

    public List<Airport> getFavouriteAirports() {
        return currentUser.user.getFavouriteAirports();
    }

    public int getAutoLogoutTimer() {
        return currentUser.user.getAutoLogoutTimer();
    }

    public Airport getHomeAirport() {
        return currentUser.user.getHomeAirport();
    }
}