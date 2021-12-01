package controller;

import entities.*;
import usecases.InteractDatabase;
import usecases.AllPossibleFlights;

import java.util.*;

/**
 * A class responsible for booking a user a flight
 */
public class PlanFlight {
    private Flight selectedFlight;
    private final String user;

    public PlanFlight(String user){
        this.user = user;
    }

    public static SearchResults EnterSearchRequirements(Calendar departureDate, Airport departure, Airport destination ) {
        // Create a sample graph
        AllPossibleFlights.Graph_dfs g = new AllPossibleFlights.Graph_dfs(7); // specify the number of verticies
        // In our case the number of vertices will be: SearchRoutePOC.len = obj.airportData.size();

        AllPossibleFlights obj = new AllPossibleFlights();
        Enumeration<String> enu = obj.getAirportData().keys();

        ArrayList<String> keys = new ArrayList<String>();

        // Creating an Array List of airport names
        while (enu.hasMoreElements()) {keys.add(enu.nextElement());}

        HashMap<String, Integer> NodeID = new HashMap<String, Integer>();

        Hashtable<String, Airport> airports = obj.getAirportData();

        // In Graph, Airports are Nodes/Vertices:
        //NodeID is used for changing iatacode to int (for use in graph)
        int node = 0;
        for (String key : keys) {
            NodeID.put(key, node);
            node++;
        }

        Hashtable <String, Flight> flights = obj.getFlightData();

        // In Graph, Flights are Paths:
        //gets names of all airports to use for NodeID
        Enumeration<String> enuflight = flights.keys();
        ArrayList<String> flight_keys = new ArrayList<String>();

        while (enuflight.hasMoreElements()) {
            flight_keys.add(enuflight.nextElement());
        }

        for(String key2: flight_keys) {
            Flight flight_object = (Flight) flights.get(key2);
            // Arrival and Destination Airports for each flight
            String end = flight_object.getSourceAirport().getIataCode();
            String arrival = flight_object.getDestinationAirport().getIataCode();
            //System.out.println(departure);
            //System.out.println(arrival);

            // let u, v be NodeID.get(source, dest)
            int u = NodeID.get(arrival);
            int v = NodeID.get(end);
            g.addEdge(v, u);
        }

        List<Integer> output = g.printRoutes(departure, destination);
        return output;

    }

    public void selectFlight(Flight flightToBeSelected){
        this.selectedFlight = flightToBeSelected;
    }

    public Flight getSelectedFlight(){
        return this.selectedFlight;
    }

    public String getUser() {
        return this.user;
    }

}