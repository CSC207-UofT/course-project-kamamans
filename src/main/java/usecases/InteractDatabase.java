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
    // Airport Database:
    public static ArrayList<Airport> getAirportList() throws IOException, ClassNotFoundException {
        // Returns list of Airports
        try {
            FileInputStream fis = new FileInputStream("src/main/java/backend/database/airport.ser");
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
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/airport.ser");
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
            FileInputStream fis = new FileInputStream("src/main/java/backend/database/plane.ser");
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
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/plane.ser");
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

    // Flight Database:
    public static ArrayList<Flight> getFlightList() throws IOException, ClassNotFoundException {
        // Returns list of Flights
        try {
            FileInputStream fis = new FileInputStream("src/main/java/backend/database/flight.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<Flight> outputList = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
            return outputList;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        }
    }

    public static void postFlight(Flight toStore) throws IOException, ClassNotFoundException {
        // Serializes <toStore>
        ArrayList<Flight> flightList = getFlightList();
        flightList.add(toStore);

        try {
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/flight.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(flightList);

            oos.close();
            fos.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // Database Utility:
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

    public static void initializeDatabase() {
        // Sets the database files for ArrayList
        // Only need to run this function once to setup your "server"

        ArrayList<Airport> airports = new ArrayList<Airport>();
        ArrayList<Plane> planes = new ArrayList<Plane>();
        ArrayList<Flight> flights = new ArrayList<Flight>();
        try {
            // Airport Data Creation
            Airport a1 = new Airport("Toronto", "001");
            Airport a2 = new Airport("Montreal", "002");
            Airport a3 = new Airport("Vancouver", "003");
            airports.add(a1);
            airports.add(a2);
            airports.add(a3);

            // Write Airport Data
            FileOutputStream fos = new FileOutputStream("src/main/java/backend/database/airport.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(airports);
            oos.close();
            fos.close();
            System.out.println("Successfully reset Airports");

            // Plane Data Creation
            Plane p1 = new Plane("Boeing 747", 223, 7, 223-7, true);
            Plane p2 = new Plane("Apollo 11", 1738, 12, 1738-12, true);
            Plane p3 = new Plane("Falcon 1", 1337, 15, 1337-15, true);
            planes.add(p1);
            planes.add(p2);
            planes.add(p3);

            // Write Plane Data
            FileOutputStream fos2 = new FileOutputStream("src/main/java/backend/database/plane.ser");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(planes);
            oos2.close();
            fos2.close();
            System.out.println("Successfully reset Planes");

            // Flight Data Creation
            Flight f1 = new Flight(new GregorianCalendar(2021, Calendar.DECEMBER, 30), p1, 1, 2, a1, a2);
            Flight f2 = new Flight(new GregorianCalendar(2022, Calendar.JUNE, 5), p2, 3, 4, a2, a3);
            Flight f3 = new Flight(new GregorianCalendar(2022, Calendar.APRIL, 4), p3, 5, 6, a3, a1);
            flights.add(f1);
            flights.add(f2);
            flights.add(f3);

            // Write Flight Data
            FileOutputStream fos3 = new FileOutputStream("src/main/java/backend/database/flight.ser");
            ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
            oos3.writeObject(flights);
            oos3.close();
            fos3.close();
            System.out.println("Successfully reset Flights");


        } catch (IOException i) {
            i.printStackTrace();
            System.out.println("Something went wrong :/");
        }
    }

    private static void printAirports() throws IOException, ClassNotFoundException {
        System.out.println("Airport List:");
        for (Airport port : getAirportList()) {
            System.out.println("-----");
            System.out.println("city: " + port.getCity());
            System.out.println("iata: " + port.getIataCode());
        }
    }

    private static void printPlanes() throws IOException, ClassNotFoundException {
        System.out.println("Plane List:");
        for (Plane plane : getPlaneList()) {
            System.out.println("-----");
            System.out.println("brand name: " + plane.getBrandName());
            System.out.println("total seats: " + plane.getSeatCount());
            System.out.println("vacant: " + plane.getHasVacantSeats());
        }
    }

    private static void printFlights() throws  IOException, ClassNotFoundException {
        System.out.println("Flight List");
        for (Flight flight : getFlightList()) {
            System.out.println("-----");
            System.out.println("source: " + flight.getSourceAirport().getCity());
            System.out.println("dest: " + flight.getDestinationAirport().getCity());
            Calendar date = flight.getDate();
            String month = "" + (date.get(Calendar.MONTH)+1);
            String day = "" + date.get(Calendar.DAY_OF_MONTH);
            String year = "" + date.get(Calendar.YEAR);
            System.out.println("date: " + month + "/" + day + "/" + year);
        }
    }

    public static void main(String[] args) throws IOException, JSONException, ClassNotFoundException {
        initializeDatabase();
//        printAirports();
//        printPlanes();
//        printFlights();
    }
}