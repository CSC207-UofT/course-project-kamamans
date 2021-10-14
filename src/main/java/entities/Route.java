package entities;

public class Route<String> {
    private String departureAirport;
    private String destinationAirport;

    public Route(String departureAirport, String destinationAirport) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
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

    public void setDestinationAirport(String newDestinationAirport){
        this.destinationAirport = newDestinationAirport;
    }
}
