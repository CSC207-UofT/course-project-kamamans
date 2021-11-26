package controller;

import usecases.UserManager;

public class UserController {
    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    public void createAccount(String username, String password, String email, String phoneNumber) {
        userManager.createAccount(username, password, email, phoneNumber);
    }

    public boolean login(String username, String password) {
        try {
            return userManager.loginAttempt(username, password);
        }
        catch (NullPointerException e){
            System.out.println(e);
            return false;
        }
    }

    public void logout() {
        userManager.logout();
    }

    public void changePassword(String password) { userManager.setPassword(password); }

    public void changeEmail(String email) {
        userManager.setEmail(email);
    }

    public void changePhoneNumber(String phoneNumber) {
        userManager.setPhoneNumber(phoneNumber);
    }

    public void changeAppRating (int appRating) {
        userManager.setAppRating(appRating);
    }
}