package usecases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.*;

import entities.BasicUser;
import entities.Flight;
import entities.Airport;
import entities.Route;
import entities.Plane;

// Notes and Questions
// idk how to write tests since I dont have access to <BasicUser>, <Flight>, <Airport> implementations
// ---> do we just verify on the Pull Request?
// Have to put constructor parameters for Makeshift Data
// For now <getFlightData> and <getAirportData> just retrieve by the id
// I'm unclear on what <getHistory> is supposed to do
// Should id's be strings?

public class InteractDatabase {

    private Hashtable<String, BasicUser> userData;
    private Hashtable<String, Flight> flightData;
    private Hashtable<String, Airport> airportData;

    public InteractDatabase() {
        this.userData = new Hashtable<String, BasicUser>();
        this.flightData = new Hashtable<String, Flight>();
        this.airportData = new Hashtable<String, Airport>();


        // Makeshift Data
        this.userData.put("keshi", new BasicUser("keshi", "password", "right@here.com", "5551231234", "business"));
        this.userData.put("twice", new BasicUser("twice", "password", "feel@special.kr", "2129212921", "first"));
        this.userData.put("mxmtoon", new BasicUser("mxmtoon", "password", "dawn@dusk.com", "6473334444", "economy"));

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
    public boolean addUser(String id, BasicUser toAdd) {
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

    public Hashtable<String, BasicUser> getUsers() {
        return this.userData;
    }

    // get a User by ID if possible
    public BasicUser getUser(String id, String password) {
        if (this.userData.containsKey(id)) {
            if (this.userData.get(id).getPassword().equals(password)) {
                return this.userData.get(id);
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
    public List<Flight> flightByRoutes(Route<Airport> route) {
        return route.getFlights();
    }

    // get an Airport by ID if possible
    public Airport getAirport(String id) {
        if (this.airportData.containsKey(id)) {
            return this.airportData.get(id);
        }
        return null;
    }

    // get all routes
    public ArrayList<Route<Airport>> getRoutes() {
        // to create a Route instance, we search for a combination of Flights
        // that link the source to the destination

        // generate different possible routes
        // (vary in cost, duration, or connecting flights)

        // for now, we return all routes
        ArrayList<Route<Airport>> output = new ArrayList<>();
        ArrayList<Flight> flights1 = new ArrayList<>();
        flights1.add(this.flightData.get("moist"));
        ArrayList<Flight> flights2 = new ArrayList<>();
        flights2.add(this.flightData.get("delectable"));
        ArrayList<Flight> flights3 = new ArrayList<>();
        flights3.add(this.flightData.get("succulent"));
        output.add(new Route<Airport>(this.airportData.get("pearson"), this.airportData.get("jfk"), flights1.get(0).getDate(), flights1));
        output.add(new Route<Airport>(this.airportData.get("heathrow"), this.airportData.get("arnold"), flights2.get(0).getDate(), flights2));
        output.add(new Route<Airport>(this.airportData.get("jim"), this.airportData.get("heartthrob"), flights3.get(0).getDate(), flights3));
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

    public static void main(String[] args) throws IOException {
        System.out.println(getEndpoint("https://www.reddit.com/r/javascript.json"));
    }

}
