import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import entities.UserManager;
import entities.Route;
import usecases.InteractDatabase;

public class TerminalInterface {

    public static UserManager runDemoLogin(){

        InteractDatabase db = new InteractDatabase();

        UserManager currentUser = null;

        System.out.println("Welcome to your Flight Planner! \n");
        Scanner sc = new Scanner(System.in); // System.in is a standard input stream

        System.out.println("Example User: keshi, password");

        while(currentUser == null){
            System.out.println("Username: ");
            String username = sc.nextLine();              // reads string as username
            System.out.println("Password: ");
            String password = sc.nextLine();              // reads string as password
            currentUser = db.getUser(username, password);
            if (currentUser == null){
                System.out.println("Incorrect username/password. Please try again.");
            }
        }

        System.out.println("Successfully logged in!");

        return currentUser;

        // add code to check if user exists, if not then call createAccount
    };

    public static Route runDemoFindRoute(){

        // The actual program will have users enter a search query where the database returns appropriate routes
        InteractDatabase db = new InteractDatabase();

        Scanner sc = new Scanner(System.in); // System.in is a standard input stream

        System.out.println("Select one of the following routes (#): ");

        List<Route> routes = db.getRoutes();

        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");

        for (int i = 0; i < routes.size(); i++){
            System.out.println(i + ": " + routes.get(i).getDepartureAirport().getCity() + ", " +
                    routes.get(i).getDestinationAirport().getCity() + ", " +
                    fmt.format(routes.get(i).getDepartureDate().getTime()));
        }

        String selection = "";
        boolean validSelection = false;

        while (!validSelection){
            System.out.println("Enter route #: ");
            selection = sc.nextLine();
            try {
                if (Integer.parseInt(selection) >= 0 && Integer.parseInt(selection) < routes.size()) {
                    validSelection = true;
                }
            } catch (Exception ignored){

            }
        }

        // Database will store the flight in the user's flight history and a URL will be given to the user to book the
        // flight.
        System.out.println("Route selected!");

        return routes.get(Integer.parseInt(selection));

    }

    public static void main(String[] args) {

        UserManager currentUser = runDemoLogin();
        Route selectedRoute = runDemoFindRoute();
    }

}
