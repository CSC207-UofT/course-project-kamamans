package entities;

public class Route<String> {
    private String departureAirport;
    private String destinationAirport;
    private double priceofFlights;
    private double totalDuration;

    public Route(String departureAirport, String destinationAirport, double priceofFlights, double totalDuration) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.priceofFlights = priceofFlights;
        this.totalDuration = totalDuration;
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
        return this.priceofFlights;
    }

    public double getTotalDuration(){
        return this.totalDuration;
    }
}
