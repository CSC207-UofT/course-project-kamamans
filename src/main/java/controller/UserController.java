package controller;
import java.io.Console;
import java.util.*;

public class UserController {
    public static void createAccount() {
    };

    public static void login(){
            Console console = System.console();
            if (console == null) {
                System.out.println("Couldn't get Console instance");
                System.exit(0);
            }

            console.printf("Welcome to your Flight Planner! \n");
            Scanner sc = new Scanner(System.in); // System.in is a standard input stream
            System.out.print("Username: ");
            String username = sc.nextLine();              // reads string as username

            char[] passwordArray = console.readPassword("Password: ");

            // add code to check if user exists, if not then call createAccount
        };
}
