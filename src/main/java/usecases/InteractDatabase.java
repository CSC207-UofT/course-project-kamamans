package usecases;

import java.util.Hashtable;

public class InteractDatabase {

    private Hashtable<String, BasicUser> userData;
    private Hashtable<String, Flight> flightData;
    private Hashtable<String, Airport> airportData;

    public InteractDatabase() {
        this.userData = new Hashtable<String, BasicUser>();
        this.flightData = new Hashtable<String, Flight>();
        this.airportData = new Hashtable<String, Airport>();
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

    // get an Airport by ID if possible
    public Airport getAirport(String id) {
        if (this.airportData.containsKey(id)) {
            return this.airportData.get(id);
        }
        return null;
    }

}
