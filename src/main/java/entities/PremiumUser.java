package entities;

import java.util.Date;

public class PremiumUser extends User {
    private String classType;

    public void PremiumUser(String id, String username, String password, String email, String phoneNumber, String c){
        this.ID = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.classType = c;
        this.userType = "Premium";
    }

    public String getID() { return this.ID; }

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

    public void setUserType(String userType) { this.userType = userType; }

    public String getUserType() { return this.userType; }

    public void setClassType(String classType) { this.classType = classType; }

    public String getClassType() { return this.classType; }
}
