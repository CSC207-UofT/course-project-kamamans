package entities;
import javax.persistence.Basic;
import java.io.Serializable;

/**
 * UserManager is responsible for common methods and attributes across all users.
 */

public class UserManager implements Serializable {
    private final String username;
    private String password;
    private String email;
    private String phoneNumber;
    private int appRating;
    public BaseUser user;

    public UserManager(String username, String password, String email, String phoneNumber) {
        this.user = new BasicUser(this);
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    /**
     * Change the type of this User by passing in a new BaseUser (see Premium and Basic User for example usage).
     * @param user a new BaseUser
     */
    public void changeUserType(BaseUser user){
        this.user = user;
    }

    public String upgradeUserType() { return this.user.upgradeUserType(); }

    public String downgradeUserType() { return this.user.downgradeUserType(); }

    public String getUserType() {
        if (this.user instanceof BasicUser) {
            return "Basic";
        } else if (this.user instanceof PremiumUser) {
            return "Premium";
        } else {
            return null;
        }
    }

}
