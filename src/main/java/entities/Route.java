package entities;

public class Route {
    private String departureAirport;
    private String destinationAirport;
    private String priceofFlights;
    private String totalDuration;

    public Route(String departureAirport, String destinationAirport, String priceofFlights, String totalDuration) {
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

    public String getPriceofFlights(){
        return this.priceofFlights;
    }

    public String getTotalDuration(){
        return this.totalDuration;
    }
}
