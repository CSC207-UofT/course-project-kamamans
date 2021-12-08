import org.junit.Test;
import usecases.LoginHandler;
import entities.User;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

public class LoginHandlerTest {
    public static void main(String[] args) {
        User u1 = new User("u1",  "123", "123@mail.com", "1231231234");
        User u2 = new User("u2",  "124", "124@mail.com", "1231231235");
        LoginHandler log = new LoginHandler();
        String[] empty = new String[] {};
        assertFalse(log.createAccount("u1", "123", "123@mail.com", "1231231234").isEmpty());
    }
}
