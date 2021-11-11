package controller;

import entities.UserList;
import entities.UserManager;
import usecases.UserSettings;

public class UserController {
    UserSettings userSettings;

    public UserController(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public void createAccount(String username, String password, String email, String phoneNumber) {
        userSettings.createAccount(username, password, email, phoneNumber);
    }

    public boolean login(String username, String password) {
        return userSettings.loginAttempt(username, password);
    }

    public static void main(String[] args) {
        UserManager test1 = new UserManager("001", "pass1", "email1", "123");
        UserManager test2 = new UserManager("002", "pass2", "email2", "234");
        UserManager test3 = new UserManager("003", "pass3", "email3", "345");
        UserList userList = new UserList();
        userList.addUser(test1);
        userList.addUser(test2);
        userList.addUser(test3);
        UserSettings us = new UserSettings(userList);
        System.out.println(us.loginAttempt("001", "pass2"));
        System.out.println(us.createAccount("004", "pass4", "email4", "456"));
        System.out.println(us.loginAttempt("004", "pass4"));
        us.deleteAccount("004");
    }
}
