package usecases;

import entities.*;

import java.io.IOException;
import java.util.*;

public class AllPossibleFlights {
    private Calendar departureDate;
    private Hashtable<Integer, Airport> airportData;
    private Hashtable<String, Integer> reverseAirport;
    private ArrayList<Flight> flightData;
    private Flight[][] reverseFlight;
    private Graph graph;


    public AllPossibleFlights(Calendar departureDate) {
        this.departureDate = departureDate;
        this.flightData = new ArrayList<Flight>();
        this.airportData = new Hashtable<Integer, Airport>();
        this.reverseAirport = new Hashtable<String, Integer>();

        // Pull in airport data from the file
        int airportCount = 0;
        for (Airport airport : InteractDatabase.getAirportList()) {
            this.airportData.put(airportCount, airport);
            this.reverseAirport.put(airport.getIataCode(), airportCount);
            airportCount++;
        }

        // Pull in the Flight data from the file
        this.flightData = InteractDatabase.getFlightList();

        // Filter Flights
        // remove flights that violate departure date

        // Construct reverseFlight and graph
        this.graph = new Graph(airportCount);
        this.reverseFlight = new Flight[airportCount][airportCount];
        for (Flight flight : this.flightData) {
            int src = this.reverseAirport.get(flight.getSourceAirport().getIataCode());
            int dest = this.reverseAirport.get(flight.getDestinationAirport().getIataCode());
            this.reverseFlight[src][dest] = flight;
            this.graph.addEdge(src, dest);
        }
    }

    public SearchResults getRoutes(Airport source, Airport destination) {
        // Find respective integer keys for source and destination
        int sourceKey = this.reverseAirport.get(source.getIataCode());
        int destinationKey = this.reverseAirport.get(destination.getIataCode());

        // Call graph solution
        ArrayList<ArrayList<Integer>> integerPaths = this.graph.allPaths(sourceKey, destinationKey);

        // Convert integer results into routes
        ArrayList<ArrayList<Flight>> flightPaths = new ArrayList<ArrayList<Flight>>();
        for (ArrayList<Integer> intPath : integerPaths) {
            flightPaths.add(flightFromInt(intPath));
        }

        ArrayList<Route> routePaths = new ArrayList<Route>();
        for (ArrayList<Flight> flightPath : flightPaths) {
            routePaths.add(new Route(source, destination, this.departureDate, flightPath));
        }

        return new SearchResults(routePaths);
    }

    private ArrayList<Flight> flightFromInt (ArrayList<Integer> integerPaths) {
        ArrayList<Flight> output = new ArrayList<Flight>();
        for (int i = 1; i < integerPaths.size(); i++) {
            output.add(this.reverseFlight[integerPaths.get(i-1)][integerPaths.get(i)]);
        }
        return output;
    }
}

