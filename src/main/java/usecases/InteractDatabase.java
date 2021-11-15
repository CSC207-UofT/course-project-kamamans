package usecases;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.sql.Array;
main
import java.sql.SQLOutput;
import java.util.*;

import entities.BasicUser;
import entities.Flight;
import entities.Airport;
import entities.Route;
import entities.Plane;
routeBackend




// Notes and Questions
// idk how to write tests since I dont have access to <BasicUser>, <Flight>, <Airport> implementations
// ---> do we just verify on the Pull Request?
// Have to put constructor parameters for Makeshift Data
// For now <getFlightData> and <getAirportData> just retrieve by the id
// I'm unclear on what <getHistory> is supposed to do
// Should id's be strings?
public class InteractDatabase {
routeBackend
    private Hashtable<String, BasicUser> userData;


    private Hashtable<String, UserManager> userData;
main
    private Hashtable<String, Flight> flightData;
    private Hashtable<String, Airport> airportData;
    public InteractDatabase() {
        this.userData = new Hashtable<String, UserManager>();
        this.flightData = new Hashtable<String, Flight>();
        this.airportData = new Hashtable<String, Airport>();
        // Makeshift Data

main
        this.airportData.put("pearson", new Airport("Montreal", "252"));
        this.airportData.put("jfk", new Airport("Toronto", "76"));
        this.airportData.put("heathrow", new Airport("Vancouver", "251256342"));
        this.airportData.put("arnold", new Airport("Quebec City", "12443"));
        this.airportData.put("jim", new Airport("Mumbai", "457"));
        this.airportData.put("heartthrob", new Airport("Paris", "9856"));
        this.flightData.put("moist", new Flight(
                new GregorianCalendar(2021, Calendar.DECEMBER, 30),
                new Plane("Boeing 747", 223, 7, 223-7, true),
                300, 8, airportData.get("pearson"), airportData.get("jfk")));
        this.flightData.put("delectable", new Flight(
                new GregorianCalendar(2022, Calendar.JUNE, 5),
                new Plane("Apollo 11", 1738, 12, 1738-12, true),
                600, 13, airportData.get("heathrow"), airportData.get("arnold")));
        this.flightData.put("succulent", new Flight(
                new GregorianCalendar(2022, Calendar.APRIL, 4),
                new Plane("Falcon 1", 1337, 15, 1337-15, true),
                1200, 5, airportData.get("jim"), airportData.get("heartthrob")));
    }
    // add a User, returns true if successful, returns false otherwise
    public boolean addUser(String id, UserManager toAdd) {
        if (this.userData.containsKey(id)) {
            return false;
        }
        this.userData.put(id, toAdd);
        return true;
    }
    // add a Flight, returns true if successful, returns false otherwise
    public boolean addFlight(String id, Flight toAdd) {
        if (this.flightData.containsKey(id)) {
            return false;
        }
        this.flightData.put(id, toAdd);
        return true;
    }
    // add an Airport, returns true if successful, returns false otherwise
    public boolean addAirport(String id, Airport toAdd) {
        if (this.airportData.containsKey(id)) {
            return false;
        }
        this.airportData.put(id, toAdd);
        return true;
    }
routeBackend
    public Hashtable<String, BasicUser> getUsers() {


    public Hashtable<String, UserManager> getUsers() {
main
        return this.userData;
    }
            }
        }
        return null;
    }
    // get a Flight by ID if possible
    public Flight getFlight(String id) {
        if (this.flightData.containsKey(id)) {
            return this.flightData.get(id);
        }
        return null;
    }
    // get a list of Flights by Route
    public List<Flight> flightByRoutes(Route route) {
        return route.getFlights();
    }
routeBackend


main
    // get all routes
    public ArrayList<Route> getRoutes() {
        // to create a Route instance, we search for a combination of Flights
        // that link the source to the destination
        // generate different possible routes
        // (vary in cost, duration, or connecting flights)
        // for now, we return all routes
        ArrayList<Route> output = new ArrayList<>();
        ArrayList<Flight> flights1 = new ArrayList<>();
        flights1.add(this.flightData.get("moist"));
        ArrayList<Flight> flights2 = new ArrayList<>();
        flights2.add(this.flightData.get("delectable"));
        ArrayList<Flight> flights3 = new ArrayList<>();
        flights3.add(this.flightData.get("succulent"));
        output.add(new Route(this.airportData.get("pearson"), this.airportData.get("jfk"), flights1.get(0).getDate(), flights1));
        output.add(new Route(this.airportData.get("heathrow"), this.airportData.get("arnold"), flights2.get(0).getDate(), flights2));
        output.add(new Route(this.airportData.get("jim"), this.airportData.get("heartthrob"), flights3.get(0).getDate(), flights3));
        return output;
    }

    public static String getEndpoint(String endpoint) throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Request Setup
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(2500);
        connection.setReadTimeout(2500);

        int status = connection.getResponseCode();

        if (status > 299) {
            // connection is not successful
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        } else {
            // connection is successful
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        while ((line = reader.readLine()) != null) {
            responseContent.append(line);
        }
        reader.close();

        return responseContent.toString();
    }

routeBackend

    // Airport Database:

main
    public static void postAirport(Airport toStore) throws IOException, ClassNotFoundException {
        // Serializes <toStore>
        ArrayList<Airport> db = getAirportList();
        db.add(toStore);

        try {
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/airport.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(db);

            oos.close();
            fos.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static ArrayList<Airport> getAirportList() throws IOException, ClassNotFoundException {
        // Returns list of Object
        ArrayList<Airport> outputList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("src/main/java/backend/database/airport.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            outputList = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
            return outputList;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        }
    }

    public static Airport getAirport(String iataCode) throws IOException, ClassNotFoundException  {
        ArrayList<Airport> airportList = getAirportList();

        for (Airport airport:airportList) {
            if (airport.getIataCode().equals(iataCode)) {
                return airport;
            }
        }
        return null;
    }

routeBackend
    public static void postRoute(Route routeToStore) throws IOException, ClassNotFoundException {
        // Serializes <toStore>
        ArrayList<Route> dbRoute = getRouteList();
        dbRoute.add(routeToStore);

        try {
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/airport.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(dbRoute);


    // Plane Database:
    public static void postPlane(Plane toStore) throws IOException, ClassNotFoundException {
        // Serializes <toStore>
        ArrayList<Plane> db = getPlaneList();
        db.add(toStore);

        try {
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/plane.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(db);
 main

            oos.close();
            fos.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

routeBackend
    public static ArrayList<Route> getRouteList() throws IOException, ClassNotFoundException {
        // Returns list of Object
        ArrayList<Route> outputList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("src/main/java/backend/database/route.bin");

    public static ArrayList<Plane> getPlaneList() throws IOException, ClassNotFoundException {
        // Returns list of Object
        ArrayList<Plane> outputList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("src/main/java/backend/database/plane.bin");
main
            ObjectInputStream ois = new ObjectInputStream(fis);

            outputList = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
            return outputList;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        }
    }

routeBackend
    public static Route getRoute(Airport departure, Airport destination) throws IOException, ClassNotFoundException  {
        ArrayList<Route> routeList = getRouteList();

        for (Route route:routeList) {
            if (route.getDepartureAirport().equals(departure) & route.getDestinationAirport().equals(destination)) {
                return route;

    public static Plane getPlane(String brandName) throws IOException, ClassNotFoundException  {
        ArrayList<Plane> planeList = getPlaneList();

        for (Plane plane:planeList) {
            if (plane.getBrandName().equals(brandName)) {
                return plane;
main
            }
        }
        return null;
    }

routeBackend
=======

main
    public static boolean initializeDatabase() {
        // Sets the database files for ArrayList
        // Only need to run this function once to setup your "server"

        ArrayList<Airport> base = new ArrayList<>();
routeBackend

        try {

        ArrayList<Plane> base2 = new ArrayList<>();

        try {

            // For Airport
main
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/airport.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(base);

            oos.close();
            fos.close();

routeBackend

            // For Plane
            FileOutputStream fos2 = new FileOutputStream("src/main/java/backend/database/plane.bin");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);

            oos2.writeObject(base2);

            oos2.close();
            fos2.close();

main
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
routeBackend
        //        System.out.println(getEndpoint("https://www.reddit.com/r/javascript.json"));

        //        Initialize
        //        if (initializeDatabase()) {
        //            System.out.println("Server Initialized");
        //        } else {
        //            System.out.println("Server Failed to Initialize");
        //        }

        //        Write Data
        //        Airport test1 = new Airport("toronto", "6ix");
        //        Airport test2 = new Airport("vancouver", "lacroix");
        //        postAirport(test1);
        //        postAirport(test2);

        //        Read Data
        //        ArrayList<Airport> airportList = getAirport();
        //        for (Airport temp: airportList) {
        //            System.out.println(temp.getCity());
        //        }

        System.out.println(getAirport("6ix").getCity());
    }

}

//        System.out.println(getEndpoint("https://www.reddit.com/r/javascript.json"));

        // Initialize
//        if (initializeDatabase()) {
//            System.out.println("Server Initialized");
//        } else {
//            System.out.println("Server Failed to Initialize");
//        }

//     Testing Airport Database:

//        Write Data
//        Airport test1 = new Airport("toronto", "6ix");
//        Airport test2 = new Airport("vancouver", "lacroix");
//        postAirport(test1);
//        postAirport(test2);

//        Read Data
//        ArrayList<Airport> airportList = getAirport();
//        for (Airport temp: airportList) {
//            System.out.println(temp.getCity());
//        }

//        Testing Plane Database:

        // Write Data
//        Plane test3 = new Plane("Airbus A321", 220, 20, 200, true);
//        Plane test4 = new Plane("Boeing 737", 189, 20, 169, true);
//        postPlane(test3);
//        postPlane(test4);

        // Read Data
        ArrayList<Plane> planeList = getPlaneList();
        for (Plane temp: planeList) {
            System.out.println(temp.getBrandName());
        }

        // System.out.println(getAirport("6ix").getCity());
    }
}
main
