package controller;
import entities.BaseUser;
import entities.UserManager;
import usecases.UserLogin;
import entities.UserList;
import usecases.UserReadWriter;

public class UserController {
    UserLogin userLogin;

    public UserController(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public static void createAccount() {

    }

    public boolean login(String username, String password) {
        return userLogin.loginAttempt(username, password);
    }

    public static void changeUserType(BaseUser user){
    }

    public static void main(String[] args) {
        UserList users = new UserList();
        users.addUser(new UserManager("edgar", "wright", "lnis@example.com", "1234567890"));
        users.addUser(new UserManager("steven", "spielberg", "gucci@example.com", "1112223333"));
        users.addUser(new UserManager("chloe", "zhao", "eternals@example.com", "4445556666"));

        UserLogin userLogin = new UserLogin(users);
        System.out.println(userLogin.loginAttempt("edgar", "wright"));
        System.out.println(!userLogin.loginAttempt("edgar", "right"));

    }
}
