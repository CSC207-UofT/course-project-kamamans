package entities;
import java.io.Serializable;


import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.json.*;

/**
 * An object representing the path which a traveller takes to a certain destination.
 *
 * Storing the departure and destination airports, the departure date of the first flight, and a list of one or
 * more flights to the final destination.
 */
public class Route implements Serializable{
    private Airport departureAirport;
    private Airport destinationAirport;
    private Calendar departureDate;
    private List<Flight> flights;
    private int routeID = -1;

    public Route (Airport departureAirport, Airport destinationAirport, Calendar departureDate, List<Flight> flights) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
        this.flights = flights;
    }

    public Route(String routeJSON) throws JSONException, ParseException {
        System.out.println("hi: " + routeJSON);
        JSONObject obj = new JSONObject(routeJSON);
        departureAirport = new Airport(obj.getString("departureAirport"));
        destinationAirport = new Airport(obj.getString("destinationAirport"));
        System.out.println(departureAirport);
        departureDate = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        departureDate.setTime(sdf.parse(obj.getString("departureDate")));
        System.out.println(departureDate);
        JSONArray flightsArray = obj.getJSONArray("flights");
        List<Flight> flightsList = new ArrayList<>();
        for (int i = 0; i < flightsArray.length(); i++) {
            Flight flight = new Flight(flightsArray.getString(i));
            flightsList.add(flight);
        }
    }

    public Airport getDepartureAirport() {
        return this.departureAirport;
    }

    public Airport getDestinationAirport() {
        return this.destinationAirport;
    }

    public void setDepartureAirport(Airport newDepartureAirport){
        this.departureAirport = newDepartureAirport;
    }

    public void setDestinationAirport(Airport newDestinationAirport) {
        this.destinationAirport = newDestinationAirport;
    }

    public Calendar getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(Calendar departureDate) {
        this.departureDate = departureDate;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public double getPriceofFlights(){
        double p = 0;
        for(Flight f: this.flights) {
            p = p + f.getPrice();
        }
        return p;
    }

    public double getTotalDuration(){
        double d = 0;
        for(Flight f: this.flights) {
            d = d + f.getDuration();
        }
        return d;
    }
    public int getRouteID() {
        return this.routeID;
    }
    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    /**
     * returns a Hashmap that contains information of the entire route.
     */
    public HashMap<String, Object> getInformation(Route r){
        HashMap<String, Object> info = new HashMap<String,Object>();

        HashMap<String, Object> departureAirport = new HashMap<String,Object>();
        departureAirport.put("city", r.departureAirport.getCity());
        departureAirport.put("iataCode", r.departureAirport.getIataCode());
        info.put("departureAirport", departureAirport);

        HashMap<String, Object> destinationAirport = new HashMap<String,Object>();
        destinationAirport.put("city", r.destinationAirport.getCity());
        destinationAirport.put("iataCode", r.destinationAirport.getIataCode());
        info.put("destinationAirport", destinationAirport);

        info.put("departureDate", r.departureDate);

        info.put("flights", r.flights);

        info.put("price", r.getPriceofFlights());

        info.put("duration", r.getTotalDuration());
        return info;
    }
    public StringBuilder routeToString() {
        StringBuilder returnString = new StringBuilder("[");


        returnString.append("{");

        // Adding departure airport
        returnString.append("\"departureAirport\": {\"city\": \"" + getDepartureAirport().getCity() + "\", \"iataCode\": \"" +
                getDepartureAirport().getIataCode() + "\"}, ");

        // Adding destination airport
        returnString.append("\"destinationAirport\": {\"city\": \"" + getDestinationAirport().getCity() + "\", \"iataCode\": \"" +
                getDestinationAirport().getIataCode() + "\"}, ");

        // Adding departure date
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        returnString.append("\"departureDate\": \"" + sdf.format(getDepartureDate().getTime()) + "\", ");

        // Adding flights
        returnString.append("\"flights\": [");
        for (Flight flight : getFlights()) {

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
        returnString.append("\"price\": " + getPriceofFlights() + ", ");

        // Adding duration
        returnString.append("\"duration\": " + getTotalDuration() + ", ");

        // Adding id
        returnString.append("\"id\": \"" + getRouteID() + "\"");



        returnString.append("},");

        returnString.setLength(returnString.length() - 1);
        returnString.append("]");

        return returnString;

    }
}
