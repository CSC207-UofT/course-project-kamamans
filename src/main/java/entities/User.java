package entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;

/**
 * User is responsible for common methods and attributes across all users.
 */

public class User implements Serializable {
    private final String username;
    private String password;
    private String email;
    private String phoneNumber;
    private int appRating;
    private List<Route> routeHistory;
    public BaseUserSettings user;

    public User(String username, String password, String email, String phoneNumber) {
        this.user = new BasicUserSettings(this);
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.routeHistory = new ArrayList<>();
    }

    public String getUsername() { return this.username; }

    public void setPassword(String password) { this.password = password; }

    public String getPassword() { return this.password; }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return this.email; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPhoneNumber() { return this.phoneNumber; }

    public void setAppRating(int appRating) { this.appRating = appRating; }

    public int getAppRating() { return this.appRating; }

    public boolean passwordMatches(String password) {
        return this.password.equals(password);
    }

    public void addRouteToHistory(Route route) {
        this.routeHistory.add(route);
    }

    public List<Route> getRouteHistory() { return this.routeHistory; }

    /**
     * Change the type of this User by passing in a new BaseUser (see Premium and Basic User for example usage).
     * @param user a new BaseUser
     */
    public void changeUserType(BaseUserSettings user){
        this.user = user;
    }

    public String upgradeUserType() { return this.user.upgradeUserType(); }

    public String downgradeUserType() { return this.user.downgradeUserType(); }

    /**
     * Return a String denoting UserType for functions that differ across users.
     * @return String representing the type of user
     */
    public String getUserType() {
        if (this.user instanceof BasicUserSettings) {
            return "Basic";
        } else if (this.user instanceof PremiumUserSettings) {
            return "Premium";
        } else {
            return null;
        }
    }

}
