package entities;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

/**
 * An object that returns search results and information on results.
 *
 * Includes returning potential routes sorted by price, duration, location etc.
 */

public class SearchResults {

    private List<Route> potentialRoutes;

    public SearchResults(List<Route> potentialRoutes) {
        this.potentialRoutes = potentialRoutes;
    }

    public List<Route> getPotentialRoutes() {
        return potentialRoutes;
    }

    public void setPotentialRoutes(List<Route> potentialRoutes) {
        this.potentialRoutes = potentialRoutes;
    }

    public String getPrice(Route route) {
        return route.toString();
    }

    public void sortByPrice() {
        potentialRoutes.sort(Comparator.comparingDouble(Route::getPriceofFlights));
    }

    public void sortByDuration() {
        potentialRoutes.sort(Comparator.comparingDouble(Route::getTotalDuration));
    }

    /**
     * Returns a StringBuilder object that is parseable and contains all the information for every route
     * in its list of routes.
     */
    public StringBuilder routesToString(User u) {
        if (this.potentialRoutes.isEmpty()) {
            return new StringBuilder("");
        }
        StringBuilder returnString = new StringBuilder("[");
        int idCounter = 0;
        for (Route route : potentialRoutes) {

            returnString.append("{");

            // Adding departure airport
            returnString.append("\"departureAirport\": {\"city\": \"" + route.getDepartureAirport().getCity() + "\", \"iataCode\": \"" +
                    route.getDepartureAirport().getIataCode() + "\"}, ");

            // Adding destination airport
            returnString.append("\"destinationAirport\": {\"city\": \"" + route.getDestinationAirport().getCity() + "\", \"iataCode\": \"" +
                    route.getDestinationAirport().getIataCode() + "\"}, ");

            // Adding departure date
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            returnString.append("\"departureDate\": \"" + sdf.format(route.getDepartureDate().getTime()) + "\", ");

            // Adding flights
            returnString.append("\"flights\": [");
            for (Flight flight : route.getFlights()) {

                returnString.append("{");

                // Adding departure date
                returnString.append("\"departureDate\": \"" + sdf.format(flight.getDate().getTime()) + "\", ");

                //Adding plane details
                returnString.append("\"plane\": {");
                returnString.append("\"brandName\": \"" + flight.getPlane().getBrandName() + "\", ");
                returnString.append("\"seatCount\": " + flight.getPlane().getSeatCount() + ", ");
                returnString.append("\"firstClassSeats\": " + flight.getPlane().getFirstClassSeats() + ", ");
                returnString.append("\"economySeats\": " + flight.getPlane().getEconomySeats() + ", ");
                returnString.append("\"hasVacantSeats\": " + flight.getPlane().getHasVacantSeats());
                returnString.append("}, ");

                // Adding price
                returnString.append("\"price\": " + flight.getPrice() + ", ");

                // Adding duration
                returnString.append("\"duration\": " + flight.getDuration() + ", ");

                // Adding source airport
                returnString.append("\"sourceAirport\": {");
                returnString.append("\"city\": \"" + flight.getSourceAirport().getCity() + "\", \"iataCode\": \"" +
                        flight.getSourceAirport().getIataCode());
                returnString.append("\"}, \"destinationAirport\": {");
                returnString.append("\"city\": \"" + flight.getDestinationAirport().getCity() + "\", \"iataCode\": \"" +
                        flight.getDestinationAirport().getIataCode());
                returnString.append("\"} ");
                returnString.append("},");
            }
            returnString.setLength(returnString.length() - 1);
            returnString.append("], ");

            // Adding price
            returnString.append("\"price\": " + route.getPriceofFlights() + ", ");

            // Adding duration
            returnString.append("\"duration\": " + route.getTotalDuration() + ", ");

            // Adding id
            while (route.getRouteID() == -1) {
                if (!u.existingRouteId.contains(idCounter)) {
                    route.setRouteID(idCounter);
                }
                idCounter++;
            }


            returnString.append("\"id\": \"" + route.getRouteID() + "\"");


            returnString.append("},");
        }
        returnString.setLength(returnString.length() - 1);
        returnString.append("]");

        return returnString;
    }
}