package entities;
import java.io.Serializable;
import usecases.InteractDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Route<A> implements Serializable{
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

    public ArrayList<Object> getInformation(Route<A> r){
        ArrayList<Object> lst = new ArrayList<>();
        lst.set(0, r.departureAirport);
        lst.set(1, r.destinationAirport);
        lst.set(2, r.departureDate);
        lst.set(3, r.flights);
        lst.set(4, r.getPriceofFlights());
        lst.set(5, r.getTotalDuration());
        return lst;
    }
}
