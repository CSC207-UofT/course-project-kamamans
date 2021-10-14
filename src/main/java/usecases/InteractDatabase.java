package usecases;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import entities.BasicUser;
import entities.Flight;
import entities.Airport;
import entities.Route;
import entities.Plane;
import usecases.SearchQueries;

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
    private Hashtable<String, Route> routeData; // do we store routes?

    public InteractDatabase() {
        this.userData = new Hashtable<String, BasicUser>();
        this.flightData = new Hashtable<String, Flight>();
        this.airportData = new Hashtable<String, Airport>();
        this.routeData = new Hashtable<String, Route>();


        // Makeshift Data
        this.userData.put("keshi", new BasicUser("keshi", "right@here.com", "5551231234", "business"));
        this.userData.put("twice", new BasicUser("twice", "feel@special.kr", "2129212921", "first"));
        this.userData.put("mxmtoon", new BasicUser("mxmtoon", "dawn@dusk.com", "6473334444", "economy"));

        this.flightData.put("moist", Flight(
                new Date(1584102896),
                new Plane("Boeing 747", 223, 7, 223-7, true, new ArrayList<String>(0)),
                new Route(), 3.141));
        this.flightData.put("delectable", Flight(
                new Date(1000293071)),
                new Plane("Apollo 11", 1738, 12, 1738-12, true, new ArrayList<String>(0)),
                new Route(), 1.6180);
        this.flightData.put("succulent", Flight(
                new Date(1426325213)),
                new Plane("Falcon 1", 1337, 15, 1337-15, true, new ArrayList<String>(0)),
                new Route(), 2.788);

        this.airportData.put("pearson", new Airport());
        this.airportData.put("jfk", new Airport());
        this.airportData.put("heathrow", new Airport());

        this.routeData.put("red", new Route());
        this.routeData.put("light", new Route());
        this.routeData.put("green", new Route());


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

    // add a Route, returns true if successful, returns false otherwise
    public boolean addRoute(String id, Route toAdd) {
        if (this.routeData.containsKey(id)) {
            return false
        }
        this.routeData.put(id, toAdd);
        return true;
    }

    // get a User by ID if possible
    public BasicUser getUser(String id) {
        if (this.userData.containsKey(id)) {
            return this.userData.get(id);
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
    public Flight[] flightByRoutes(Route route) {
        Flight[] output = new Flight[0];
        Flight item;
        for (String key : this.flightData.keySet()) {
            item = this.flightData.get(key);
            if (item.getRoute() == route) {
                output.append(item);
            }
        }
        return output;
    }

    // get an Airport by ID if possible
    public Airport getAirport(String id) {
        if (this.airportData.containsKey(id)) {
            return this.airportData.get(id);
        }
        return null;
    }

    // get a Route by ID if possible
    public Route getRoute(String id) {
        if (this.routeData.containsKey(id)) {
            return this.routeData.get(id);
        }
        return null;
    }

    // get a Route by SearchQueries
    public Route[] routeByQuery(SearchQueries query) {
        // to create a Route instance, we search for a combination of Flights
        // that link the source to the destination

        // generate different possible routes
        // (vary in cost, duration, or connecting flights)

        // currently, there is no Route implementation so
        output = [new Route(), new Route(), new Route()];
        return output;
    }

}
