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

public class InteractDatabase {


    // constructor
    public InteractDatabase() {

//        this.flightData.put("moist", new Flight(
//                new GregorianCalendar(2021, Calendar.DECEMBER, 30),
//                new Plane("Boeing 747", 223, 7, 223-7, true),
//                300, 8, airportData.get("pearson"), airportData.get("jfk")));
//        this.flightData.put("delectable", new Flight(
//                new GregorianCalendar(2022, Calendar.JUNE, 5),
//                new Plane("Apollo 11", 1738, 12, 1738-12, true),
//                600, 13, airportData.get("heathrow"), airportData.get("arnold")));
//        this.flightData.put("succulent", new Flight(
//                new GregorianCalendar(2022, Calendar.APRIL, 4),
//                new Plane("Falcon 1", 1337, 15, 1337-15, true),
//                1200, 5, airportData.get("jim"), airportData.get("heartthrob")));
    }

    // add a Flight, returns true if successful, returns false otherwise
//    public boolean addFlight(String id, Flight toAdd) {
//        if (this.flightData.containsKey(id)) {
//            return false;
//        }
//        this.flightData.put(id, toAdd);
//        return true;
//    }

    // get a Flight by ID if possible
//    public Flight getFlight(String id) {
//        if (this.flightData.containsKey(id)) {
//            return this.flightData.get(id);
//        }
//        return null;
//    }


    // get all routes
//    public ArrayList<Route> getRoutes() {
//        // to create a Route instance, we search for a combination of Flights
//        // that link the source to the destination
//        // generate different possible routes
//        // (vary in cost, duration, or connecting flights)
//        // for now, we return all routes
//        ArrayList<Route> output = new ArrayList<>();
//        ArrayList<Flight> flights1 = new ArrayList<>();
//        flights1.add(this.flightData.get("moist"));
//        ArrayList<Flight> flights2 = new ArrayList<>();
//        flights2.add(this.flightData.get("delectable"));
//        ArrayList<Flight> flights3 = new ArrayList<>();
//        flights3.add(this.flightData.get("succulent"));
//        output.add(new Route(this.airportData.get("pearson"), this.airportData.get("jfk"), flights1.get(0).getDate(), flights1));
//        output.add(new Route(this.airportData.get("heathrow"), this.airportData.get("arnold"), flights2.get(0).getDate(), flights2));
//        output.add(new Route(this.airportData.get("jim"), this.airportData.get("heartthrob"), flights3.get(0).getDate(), flights3));
//        return output;
//    }

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
    public static ArrayList<Airport> getAirportList() throws IOException, ClassNotFoundException {
        // Returns list of Airports
        try {
            FileInputStream fis = new FileInputStream("src/main/java/backend/database/airport.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<Airport> outputList = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
            return outputList;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        }
    }

    public static void postAirport(Airport toStore) throws IOException, ClassNotFoundException {
        // Serializes <toStore>
        ArrayList<Airport> airportList = getAirportList();
        airportList.add(toStore);

        try {
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/airport.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(airportList);

            oos.close();
            fos.close();
            System.out.println("Success: Airport POSTed");
        } catch (IOException i) {
            i.printStackTrace();
            System.out.println("Error: Could not POST given airport");
        }
    }

    public static Airport getAirportByIata(String iataCode) throws IOException, ClassNotFoundException  {
        ArrayList<Airport> airportList = getAirportList();

        for (Airport airport:airportList) {
            if (airport.getIataCode().equals(iataCode)) {
                return airport;
            }
        }
        System.out.println("airport doesnt exist");
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
    public static ArrayList<Plane> getPlaneList() throws IOException, ClassNotFoundException {
        // Returns list of Planes
        try {
            FileInputStream fis = new FileInputStream("src/main/java/backend/database/plane.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<Plane> outputList = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
            return outputList;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        }
    }

    public static void postPlane(Plane toStore) throws IOException, ClassNotFoundException {
        // Serializes <toStore>
        ArrayList<Plane> planeList = getPlaneList();
        planeList.add(toStore);

        try {
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/plane.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(planeList);

            oos.close();
            fos.close();
        } catch (IOException i) {
            i.printStackTrace();
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

    // Route Database?:
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

    // Database Utility:
    public static void initializeDatabase() {
        // Sets the database files for ArrayList
        // Only need to run this function once to setup your "server"

        ArrayList<Airport> airports = new ArrayList<Airport>();
        ArrayList<Plane> planes = new ArrayList<Plane>();
        try {
            // For Airport
            // Data Creation
            airports.add(new Airport("Toronto", "001"));
            airports.add(new Airport("Montreal", "002"));
            airports.add(new Airport("Vancouver", "003"));

            // Write Data
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/airport.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(airports);
            oos.close();
            fos.close();
            System.out.println("Successfully reset Airports");

            // For Plane
            FileOutputStream fos2 = new FileOutputStream("src/main/java/backend/database/plane.bin");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(planes);
            oos2.close();
            fos2.close();
            System.out.println("Successfully reset Planes");

        } catch (IOException i) {
            i.printStackTrace();
            System.out.println("Something went wrong :/");
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

    private static void printAirports() throws IOException, ClassNotFoundException {
        for (Airport port : getAirportList()) {
            System.out.println("city: " + port.getCity());
            System.out.println("iata: " + port.getIataCode());
        }
    }

    public static void main(String[] args) throws IOException, JSONException, ClassNotFoundException {
//        InteractDatabase db = new InteractDatabase();
        printAirports();
//        Airport x = InteractDatabase.getAirportByName("Toronto");
//        System.out.println(x.getCity());

//        InteractDatabase.initializeDatabase()

    }
}