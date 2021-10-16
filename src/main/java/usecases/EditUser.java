package usecases;

public class EditUser {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private boolean premium;

    public EditUser(String username, String password, String email, String phoneNumber, String c, Boolean premium){
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.premium = premium;
    }

    public void upgradeAccount(){
        if (this.premium){
            System.out.print("Account is already Premium");
        }
        else {
            this.premium = true;
            System.out.print("Account has been upgraded to Premium");
        }
    }

    public void changeUsername(String newUsername){
        this.username = newUsername;
        System.out.print("Username changed");
    }

    public void changePassword(String newPassword){
        this.password = newPassword;
        System.out.print("Password changed");
    }

}
