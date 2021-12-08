import java.text.SimpleDateFormat;
import java.util.*;

import controller.UserController;
import entities.*;

import usecases.LoginHandler;

/**
 * Class that represented Presenter
 * Currently replaced with GUI of HTML and JS code
 * Kept for ease of testing
 */

public class TerminalInterface {

    public static void runDemoLogin() {
        LoginHandler us = new LoginHandler();
        UserController uc = new UserController(us);

        Boolean loggedIn = false;

        System.out.println("Welcome to your Flight Planner! \n");
        Scanner sc = new Scanner(System.in); // System.in is a standard input stream

        System.out.println("Example User: user1, 111");

        while (!loggedIn) {
            System.out.println("Username: ");
            String username = sc.nextLine();              // reads string as username
            System.out.println("Password: ");
            String password = sc.nextLine();              // reads string as password

            if (!uc.login(username, password)) {
                // login failed
                System.out.println("Incorrect username/password. Please try again.");
            } else {
                // login succeeded
                loggedIn = true;
            }
        }
        System.out.println("Successfully logged in!");
    }

    ;

    public static void runDemoFindRoute() {
        // The actual program will have users enter a search query where the database returns appropriate routes

        Scanner sc = new Scanner(System.in); // System.in is a standard input stream
        System.out.println("Select one of the following routes (#): ");

        List<Route> routes = new ArrayList<Route>();

        // Airport Data
        Airport a1 = new Airport("Toronto", "001");
        Airport a2 = new Airport("Montreal", "002");
        Airport a3 = new Airport("Vancouver", "003");

        // Plane Data
        Plane p1 = new Plane("Boeing 747", 223, 7, 223 - 7, true);
        Plane p2 = new Plane("Apollo 11", 1738, 12, 1738 - 12, true);
        Plane p3 = new Plane("Falcon 1", 1337, 15, 1337 - 15, true);

        // Flight Data
        GregorianCalendar feb14 = new GregorianCalendar(2022, Calendar.FEBRUARY, 14);
        Flight f1 = new Flight(feb14, p1, 1, 2, a1, a2);
        Flight f2 = new Flight(feb14, p2, 3, 4, a2, a3);
        Flight f3 = new Flight(feb14, p3, 5, 6, a3, a1);

        // Route Data
        ArrayList<Flight> list1 = new ArrayList<>();
        list1.add(f1);
        list1.add(f2);
        Route r1 = new Route(a1, a3, feb14, list1); // Toronto --> Montreal --> Vancouver

        ArrayList<Flight> list2 = new ArrayList<>();
        list2.add(f2);
        list2.add(f3);
        Route r2 = new Route(a2, a1, feb14, list2); // Montreal --> Vancouver --> Toronto

        ArrayList<Flight> list3 = new ArrayList<>();
        list3.add(f3);
        list3.add(f1);
        Route r3 = new Route(a3, a2, feb14, list3); // Vancouver --> Toronto --> Montreal

        // Add routes to list
        routes.add(r1);
        routes.add(r2);
        routes.add(r3);

        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");

        for (int i = 0; i < routes.size(); i++) {
            System.out.println(i + ": " + routes.get(i).getDepartureAirport().getCity() + ", " +
                    routes.get(i).getDestinationAirport().getCity() + ", " +
                    fmt.format(routes.get(i).getDepartureDate().getTime()));
        }

        String selection = "";
        boolean validSelection = false;

        while (!validSelection) {
            System.out.println("Enter route #: ");
            selection = sc.nextLine();
            try {
                if (Integer.parseInt(selection) >= 0 && Integer.parseInt(selection) < routes.size()) {
                    validSelection = true;
                }
            } catch (Exception ignored) {

            }
        }

        // Database will store the flight in the user's flight history and a URL will be given to the user to book the
        // flight.
        System.out.println("Route selected!");

    }

    public static void main(String[] args) {
        runDemoLogin();
        runDemoFindRoute();
    }

}
