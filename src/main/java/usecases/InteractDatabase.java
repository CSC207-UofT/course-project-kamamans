package usecases;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.*;

import com.fasterxml.jackson.databind.util.JSONPObject;
import entities.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// API stuff
// base url: https://api.aviationstack.com/v1/
// flight, flight_status, flight_date, dep_iata, arr_iata, airline_name, airline_iata, flight_iata
// routes, airports(city?), airlines airplanes or aircrafts?
// key: 8a0423ec6b7b5e44ae6bab41e07f150b
// https://www.baeldung.com/java-http-url-connection

// Notes and Questions
// idk how to write tests since I dont have access to <BasicUser>, <Flight>, <Airport> implementations
// ---> do we just verify on the Pull Request?
// Have to put constructor parameters for Makeshift Data
// For now <getFlightData> and <getAirportData> just retrieve by the id
// I'm unclear on what <getHistory> is supposed to do
// Should id's be strings?
public class InteractDatabase {

    private Hashtable<String, Flight> flightData;
    private Hashtable<String, Airport> airportData;
    public InteractDatabase() {
        this.userData = new Hashtable<String, User>();
        this.flightData = new Hashtable<String, Flight>();
        this.airportData = new Hashtable<String, Airport>();
        // Makeshift Data

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
    public boolean addUser(String id, User toAdd) {
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

    public Hashtable<String, User> getUsers() {
        return this.userData;
    }

    // get a User by username if possible
    public User getUser(String username, String password) {
        if (this.userData.containsKey(username)) {
            if (this.userData.get(username).getPassword().equals(password)) {
                return this.userData.get(username);
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

    public static String getEndpoint(String endpoint, String key) throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

//        byte[] encodedKey = Base64.encodeBase64(key.getBytes(StandardCharsets.UTF_8));
//        String keyHeaderValue = "Basic " + new String(encodedKey);

//        One of these two:
        // Encoded
        connection.setRequestProperty("Authorization", key);
        // Not Encoded
//        connection.setRequestProperty("Authorization", keyHeaderValue);

        // Request Setup
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(2500);
        connection.setReadTimeout(2500);

        int status = connection.getResponseCode();

        if (status > 299) {
            // connection is not successful
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//            System.exit();
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

    // Airport Database:
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
    public static Airport getAirportByName(String name) throws IOException, ClassNotFoundException {
        ArrayList<Airport> airportList = getAirportList();
        try {
            assert airportList != null;
            for (Airport airport : airportList) {
                if (airport.getCity().toLowerCase().contains(name.toLowerCase())) {
                    return airport;
                }
            }
        } catch (NullPointerException | AssertionError e) {
            return null;
        }
        return null;
    }

    // Plane Database:
    public static void postPlane(Plane toStore) throws IOException, ClassNotFoundException {
        // Serializes <toStore>
        ArrayList<Plane> db = getPlaneList();
        db.add(toStore);

        try {
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/plane.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(db);

            oos.close();
            fos.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static ArrayList<Plane> getPlaneList() throws IOException, ClassNotFoundException {
        // Returns list of Object
        ArrayList<Plane> outputList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("src/main/java/backend/database/plane.bin");
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

    public static Plane getPlane(String brandName) throws IOException, ClassNotFoundException  {
        ArrayList<Plane> planeList = getPlaneList();

        for (Plane plane:planeList) {
            if (plane.getBrandName().equals(brandName)) {
                return plane;
            }
        }
        return null;
    }


    public static void postRoute(Route routeToStore) throws IOException, ClassNotFoundException {
        // Serializes <toStore>
        ArrayList<Route> dbRoute = getRouteList();
        dbRoute.add(routeToStore);

        try {
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/airport.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(dbRoute);

            oos.close();
            fos.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public static ArrayList<Route> getRouteList() throws IOException, ClassNotFoundException {
        // Returns list of Object
        ArrayList<Route> outputList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("src/main/java/backend/database/route.bin");
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

    public static Route getRoute(Airport departure, Airport destination) throws IOException, ClassNotFoundException  {
        ArrayList<Route> routeList = getRouteList();

        assert routeList != null;
        for (Route route:routeList) {
            if (route.getDepartureAirport().equals(departure) & route.getDestinationAirport().equals(destination)) {
                return route;
            }
        }
        return null;
    }

    public static boolean initializeDatabase() {
        // Sets the database files for ArrayList
        // Only need to run this function once to setup your "server"

        ArrayList<Airport> base = new ArrayList<>();

        try {

            ArrayList<Plane> base2 = new ArrayList<>();

            // For Airport
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/airport.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(base);

            oos.close();
            fos.close();

            // For Plane
            FileOutputStream fos2 = new FileOutputStream("src/main/java/backend/database/plane.bin");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);

            oos2.writeObject(base2);

            oos2.close();
            fos2.close();

            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        }
    }

    public static void updateDB() throws IOException, JSONException, ClassNotFoundException {
        String key = "8a0423ec6b7b5e44ae6bab41e07f150b";
        // Airports
        JSONObject allAirports = new JSONObject(getEndpoint("https://api.aviationstack.com/v1/airports", key));
        int a_quantity = allAirports.getJSONObject("pagination").getInt("total");
        JSONArray j_airports = allAirports.getJSONArray("data");
        for (int i = 0; i < a_quantity; i = i + 1) {
            postAirport(new Airport(j_airports.getJSONObject(i).getString("airport_name"), j_airports.getJSONObject(i).getString("iata_code")));
        }
        // Planes
        JSONObject allPlanes = new JSONObject(getEndpoint("https://api.aviationstack.com/v1/airplanes", key));
        int p_quantity = allPlanes.getJSONObject("pagination").getInt("total");
        JSONArray j_planes = allPlanes.getJSONArray("data");
        for (int i = 0; i < p_quantity; i = i + 1) {
            postPlane(new Plane(j_planes.getJSONObject(i).getString("production_line"), 300, 50, 250, true));
//            Plane p1 = new Plane(j_planes.getJSONObject(i).getString("production_line"), 300, 50, 250, true);
//            postPlane(p1);
        }
    }

    public static void main(String[] args) throws IOException, JSONException, ClassNotFoundException {
//                System.out.println(getEndpoint("https://www.reddit.com/r/javascript.json"));
//
//                //Initialize
//                if (initializeDatabase()) {
//                    System.out.println("Server Initialized");
//                } else {
//                    System.out.println("Server Failed to Initialize");
//                }
//
//                //Write Data
//                Airport test1 = new Airport("toronto", "6ix");
//                Airport test2 = new Airport("vancouver", "lacroix");
//                postAirport(test1);
//                postAirport(test2);

        //        Read Data
        //        ArrayList<Airport> airportList = getAirport();
        //        for (Airport temp: airportList) {
        //            System.out.println(temp.getCity());
        //        }
//        System.out.println(getAirport("6ix").getCity());


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
//        ArrayList<Plane> planeList = getPlaneList();
//        for (Plane temp: planeList) {
//            System.out.println(temp.getBrandName());
//        }
//
//        System.out.println(getAirport("6ix").getCity());
        if (initializeDatabase()) { System.out.println("Server Initialized"); }
        else { System.out.println("Server Failed to Initialize"); }
        updateDB();
    }
}