package controller;

import entities.*;
import usecases.InteractDatabase;

import java.util.*;

public class PlanFlight {
    private Flight selectedFlight;
    private final String user;

    public PlanFlight(String user){
        this.user = user;
    }

    public static SearchResults EnterSearchRequirements(Calendar departureDate, Airport departure, Airport destination ) {
//    public static ArrayList<Flight> EnterSearchRequirements(Calendar departureDate, Airport departure, Airport destination ) {
        // ArrayList<Flight> flights = InteractDatabase.getFlight();
        // ^^ waiting for majda's implementation

        // Creating example flights for development
        ArrayList<Flight> flights = new ArrayList<Flight>();

        Plane basic = new Plane("boeing", 100, 10,80, true);
        Airport toronto = new Airport("Toronto", "code1");
        Airport vancouver = new Airport("Vancouver", "code2");
        Airport nyc = new Airport("New York", "code3");

        Calendar day0 = Calendar.getInstance();
        day0.set(2020, 5, 5);
        Calendar day1 = Calendar.getInstance();
        day1.set(2021, 11, 18);
        Calendar day2 = Calendar.getInstance();
        day2.set(2021, 11, 19);
        Calendar day3 = Calendar.getInstance();
        day3.set(2021, 11, 20);

        flights.add(new Flight(day0, basic, 1, 5, nyc, vancouver));
        flights.add(new Flight(day1, basic, 2, 5, toronto, vancouver));
        flights.add(new Flight(day1, basic, 3, 5, vancouver, toronto));
        flights.add(new Flight(day1, basic, 4, 5, toronto, nyc));
        flights.add(new Flight(day2, basic, 5, 5, vancouver, toronto));
        flights.add(new Flight(day3, basic, 6, 5, nyc, toronto));
        flights.add(new Flight(day3, basic, 7, 5, nyc, vancouver));
        // ^^^ all this is to generate a flight list


        // Filter to only have flights after given date
//        flights.removeIf(flight -> flight.getDate().before(departureDate));

        // Sort by date
//        Collections.sort(flights, (f1, f2)-> f1.getDate().compareTo(f2.getDate()));


        // Shallow Copy
//        ArrayList<Flight> tempFlights = (ArrayList<Flight>) flights.clone(); // <- shallow copy

        flights.removeIf(flight -> flight.getSourceAirport().getIataCode().equals(departure.getIataCode()));
        flights.removeIf(flight -> flight.getDestinationAirport().getIataCode().equals(destination.getIataCode()));

        ArrayList<Route> routeList = new ArrayList<Route>();

        for (Flight obj: flights) {
            // Create a new Route for every flight
            ArrayList<Flight> flightsToAdd = new ArrayList<Flight>();
            flightsToAdd.add(obj);
            Route routeToAdd = new Route(departure, destination, departureDate, flightsToAdd);
            if (routeToAdd.getFlights().get(0).getSourceAirport().getCity().equalsIgnoreCase(departure.getCity()) && routeToAdd.getFlights().get(0).getDestinationAirport().getCity().equalsIgnoreCase(destination.getCity())) {
                routeList.add(routeToAdd);
            }
        }

        return new SearchResults(routeList);
    }

    private static ArrayList<Flight> availableFlights(ArrayList<Flight> flightList, Airport src, Calendar time) {
        // Shallow Copy
        ArrayList<Flight> output = (ArrayList<Flight>) flightList.clone(); // <- shallow copy

        // remove flights with different <src>, and before given <time>
        output.removeIf(flight -> (!flight.getSourceAirport().getIataCode().equals(src.getIataCode())));
        output.removeIf(flight -> flight.getDate().compareTo(time) < 0);

        // remove flights with same destination
        Collections.sort(output, (f1, f2)-> f1.getDate().compareTo(f2.getDate()));
        int i = 0;
        while (i < output.size() - 1){
            String iataCode = output.get(i).getDestinationAirport().getIataCode();
            int j = i + 1;
            while (j < output.size()) {
                if (output.get(j).getDestinationAirport().getIataCode().equals(iataCode)) {
                    output.remove(j);
                    j--;
                }
                j++;
            }
            i++;
        }

        return output;
    }

    public void selectFlight(Flight flightToBeSelected){

    }

    public Flight getSelectedFlight(){
        return this.selectedFlight;
    }

    public String getUser() {
        return this.user;
    }

    public static void main(String[] args) {
        // Suppose you wanted to generate the routes from vancouver ->  rift
        // you would expect to go vancouver -> toronto -> rift

        // examples of routes
        // vancouver -> rift: vancouver -> toronto -> rift, day 2
        // toronto -> vancouver: toronto -> vancouver,  day 1


        Calendar today = Calendar.getInstance();
        today.set(2021, 11, 10);
        Airport source = new Airport("Toronto", "code1");
        Airport destination = new Airport("Vancouver", "");
        SearchResults output = EnterSearchRequirements(today, source, destination);
    }
}