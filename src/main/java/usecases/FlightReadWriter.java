package usecases;

import entities.Flight;
import gateway.InteractDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class FlightReadWriter {
    public static ArrayList<Flight> getFlightList() {
        // Returns list of Airports
        return InteractDatabase.fetchList(Flight.class);
    }

    public static void postFlight(Flight toStore) {
        // Serializes <toStore>
        InteractDatabase.post(toStore, Flight.class);
    }

    public static void printFlights() {
        System.out.println("Flight List");
        for (Flight flight : getFlightList()) {
            System.out.println("-----");
            System.out.println(flight.getSourceAirport().getCity() + " --> " + flight.getDestinationAirport().getCity());
            Calendar date = flight.getDate();
            String month = "" + (date.get(Calendar.MONTH) + 1);
            String day = "" + date.get(Calendar.DAY_OF_MONTH);
            String year = "" + date.get(Calendar.YEAR);
            System.out.println("date: " + month + "/" + day + "/" + year);
        }
    }
}
