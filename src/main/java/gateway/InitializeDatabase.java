package gateway;

import entities.Airport;
import entities.Flight;
import entities.Plane;
import entities.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import usecases.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

// API stuff
// base url: https://api.aviationstack.com/v1/
// flight, flight_status, flight_date, dep_iata, arr_iata, airline_name, airline_iata, flight_iata
// routes, airports(city?), airlines airplanes or aircrafts?
// key: 8a0423ec6b7b5e44ae6bab41e07f150b
// https://www.baeldung.com/java-http-url-connection

public class InitializeDatabase {

    /**
     * Resets all program data.
     * This is irreversible and idempotent.
     *
     * Sets database to a testing state
     */
    public static void resetTestData() {
        // Reset Files to blanks
        InteractDatabase.reset(UserList.class);
        InteractDatabase.reset(Airport.class);
        InteractDatabase.reset(Plane.class);
        InteractDatabase.reset(Flight.class);

        ArrayList<Airport> airports = new ArrayList<Airport>();
        ArrayList<Plane> planes = new ArrayList<Plane>();
        ArrayList<Flight> flights = new ArrayList<Flight>();

        // UserList Data Creation
        UserList userData = new UserList();
        userData.addUser(new User("user1", "111", "test1@email.ca", "(416)-000-0001"));
        userData.addUser(new User("user2", "222", "test2@email.com", "(416)-000-0002"));

        // Write User Data
        InteractDatabase.post(userData, UserList.class);

        // Airport Data Creation
        airports.add(new Airport("Toronto", "000"));
        airports.add(new Airport("Montreal", "001"));
        airports.add(new Airport("Vancouver", "002"));
        airports.add(new Airport("London", "003"));
        airports.add(new Airport("Paris", "004"));
        airports.add(new Airport("Hong Kong", "005"));

        // Write Airport Data
        for (Airport airport : airports) {
            InteractDatabase.post(airport, Airport.class);
        }

        // Plane Data Creation
        planes.add(new Plane("Boeing 747", 223, 7, 223 - 7, true));
        planes.add(new Plane("Apollo 11", 1738, 12, 1738 - 12, true));
        planes.add(new Plane("Falcon 1", 1337, 15, 1337 - 15, true));

        // Write Plane Data
        for (Plane plane : planes) {
            InteractDatabase.post(plane, Plane.class);
        }

        // Flight Data Creation
        GregorianCalendar date = new GregorianCalendar(2021, Calendar.DECEMBER, 6);
        flights.add(new Flight(date, planes.get(0), 10,2, airports.get(0), airports.get(1)));
        flights.add(new Flight(date, planes.get(1), 3, 7, airports.get(0), airports.get(2)));
        flights.add(new Flight(date, planes.get(2), 5, 3, airports.get(0), airports.get(3)));

        flights.add(new Flight(date, planes.get(0), 1, 2, airports.get(1), airports.get(0)));
        flights.add(new Flight(date, planes.get(1), 3, 4, airports.get(1), airports.get(2)));
        flights.add(new Flight(date, planes.get(2), 2, 2, airports.get(1), airports.get(4)));

        flights.add(new Flight(date, planes.get(0), 1, 2, airports.get(2), airports.get(0)));
        flights.add(new Flight(date, planes.get(1), 6, 4, airports.get(2), airports.get(1)));
        flights.add(new Flight(date, planes.get(2), 5, 5, airports.get(2), airports.get(5)));

        flights.add(new Flight(date, planes.get(0), 1, 2, airports.get(3), airports.get(1)));
        flights.add(new Flight(date, planes.get(1), 2, 4, airports.get(3), airports.get(4)));
        flights.add(new Flight(date, planes.get(2), 5, 1, airports.get(3), airports.get(5)));

        flights.add(new Flight(date, planes.get(0), 7, 2, airports.get(4), airports.get(0)));
        flights.add(new Flight(date, planes.get(1), 3, 4, airports.get(4), airports.get(3)));
        flights.add(new Flight(date, planes.get(2), 2, 2, airports.get(4), airports.get(5)));

        flights.add(new Flight(date, planes.get(0), 1, 2, airports.get(5), airports.get(2)));
        flights.add(new Flight(date, planes.get(1), 3, 4, airports.get(5), airports.get(3)));
        flights.add(new Flight(date, planes.get(2), 5, 6, airports.get(5), airports.get(4)));

        // Write Flight Data
        for (Flight flight : flights) {
            InteractDatabase.post(flight, Flight.class);
        }
    }

    /**
     * Fetches live flight data
     * @throws IOException
     * @throws JSONException
     * @throws ClassNotFoundException
     *
     * Adds real-life Flight, Airport, and Plane data into program
     */
    public static void updateDB() throws IOException, JSONException, ClassNotFoundException {
        String key = "8a0423ec6b7b5e44ae6bab41e07f150b";
        // Airports
        JSONObject allAirports = new JSONObject(getEndpoint("http://api.aviationstack.com/v1/airports", key));
        int a_quantity = allAirports.getJSONObject("pagination").getInt("total");
        JSONArray j_airports = allAirports.getJSONArray("data");
        for (int i = 0; i < a_quantity; i = i + 1) {
            AirportReadWriter.postAirport(new Airport(j_airports.getJSONObject(i).getString("airport_name"), j_airports.getJSONObject(i).getString("iata_code")));
        }
        // Planes
        JSONObject allPlanes = new JSONObject(getEndpoint("http://api.aviationstack.com/v1/airplanes", key));
        int p_quantity = allPlanes.getJSONObject("pagination").getInt("total");
        JSONArray j_planes = allPlanes.getJSONArray("data");
        for (int i = 0; i < p_quantity; i = i + 1) {
            PlaneReadWriter.postPlane(new Plane(j_planes.getJSONObject(i).getString("production_line"), 300, 50, 250, true));
//            Plane p1 = new Plane(j_planes.getJSONObject(i).getString("production_line"), 300, 50, 250, true);
//            postPlane(p1);
        }
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

    public static void main(String[] args) {
//        resetTestData();
//        AirportReadWriter.printAirports();
//        PlaneReadWriter.printPlanes();
        FlightReadWriter.printFlights();
    }

}
