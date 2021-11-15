package controller;

import entities.BaseUser;
import entities.Route;
import entities.UserManager;
import usecases.UserSettings;

public class UserController {
    private final UserSettings userSettings;


    public UserController(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public void createAccount(String username, String password, String email, String phoneNumber) {
        userSettings.createAccount(username, password, email, phoneNumber);
    }

    public boolean login(String username, String password) {
        try{
            return userSettings.loginAttempt(username, password);
        }
        catch (NullPointerException e){
            System.out.println(e);
            return(false);
        }

    }


}