package entities;
import java.io.Serializable;
import usecases.InteractDatabase;

import java.lang.reflect.Array;
import java.util.*;

public class Route implements Serializable{
    private Airport departureAirport;
    private Airport destinationAirport;
    private Calendar departureDate;
    private List<Flight> flights;

    public Route (Airport departureAirport, Airport destinationAirport, Calendar departureDate, List<Flight> flights) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
        this.flights = flights;
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
}
