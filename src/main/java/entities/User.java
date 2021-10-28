package entities;

public abstract class User {
    public String ID;
    public String username;
    public String password;
    public String email;
    public String phoneNumber;
    public String userType;

    abstract String getID();

    abstract void setUsername(String username);
    abstract String getUsername();

    abstract void setPassword(String password);
    abstract String getPassword();

    abstract void setEmail(String email);
    abstract String getEmail();

    abstract void setPhoneNumber(String phoneNumber);
    abstract String getPhoneNumber();

    abstract void setUserType(String userType);
    abstract String getUserType();
}
