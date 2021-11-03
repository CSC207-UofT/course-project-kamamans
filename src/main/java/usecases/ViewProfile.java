package usecases;

import entities.BasicUser;

public class ViewProfile {
    private BasicUser userAccount;

    public ViewProfile(BasicUser userAccount){
        this.userAccount = userAccount;
    }

    public void upgradeAccount(){
        if (this.userAccount.isUpgraded()){
            this.userAccount.setUpgraded(true);
            System.out.print("Account is already Premium. Expiry date refreshed");
        }
        else {
            this.userAccount.setUpgraded(true);
            System.out.print("Account has been upgraded to Premium");
        }
    }

    public void changeUsername(String newUsername){
        this.userAccount.setUsername(newUsername);
        System.out.print("Username changed");
    }

    public void changePassword(String newPassword){
        this.userAccount.setPassword(newPassword);
        System.out.print("Password changed");
    }

    public void viewProfileScreen(){

        // This is where the user makes changes to their account
    }

}
