package usecases;
import entities.*;

public class UserManager {
    private String id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    public BaseUser user;

    public UserManager() {
        this.user = new BasicUser(this);
    }

    public UserManager(String id, String username, String password, String email, String phoneNumber) {
        this.user = new BasicUser(this);
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId() { return this.id; }

    public void setUsername(String username) { this.username = username; }

    public String getUsername() { return this.username; }

    public void setPassword(String password) { this.password = password; }

    public String getPassword() { return this.password; }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return this.email; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void changeUserType(BaseUser user){
        this.user = user;
    }

    public String upgradeUserType() { return this.user.upgradeUserType(); }

    public String downgradeUserType() { return this.user.downgradeUserType(); }

}
