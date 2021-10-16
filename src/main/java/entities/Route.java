package entities;

import java.util.List;

public class Route {
    private String departureAirport;
    private String destinationAirport;
    private List<Flight> flights;

    public Route(String departureAirport, String destinationAirport, List<Flight> flights) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.flights = flights;
    }

    public String getDepartureAirport() {
        return this.departureAirport;
    }

    public String getDestinationAirport() {
        return this.destinationAirport;
    }

    public void setDepartureAirport(String newDepartureAirport){
        this.departureAirport = newDepartureAirport;
    }

    public void setDestinationAirport(String newDestinationAirport) {
        this.destinationAirport = newDestinationAirport;
    }

    public double getPriceofFlights(){
        double p = 0;
        for(Flight f: this.flights) {
            p = p + f.price;
        }
        return p;
    }

    public double getTotalDuration(){
        double d = 0;
        for(Flight f: this.flights) {
            d = d + f.duration;
        }
        return d;
    }
}
