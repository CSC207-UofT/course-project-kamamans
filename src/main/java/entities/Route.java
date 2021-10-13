package entities;

public class Route<String> {
    private String DepartureAirport;
    private String DestinationAirport;

    public Route(String DepartureAirport, String DestinationAirport) {
        this.DepartureAirport = DepartureAirport;
        this.DestinationAirport = DestinationAirport;
    }

    public String getDepartureAirport() {
        return this.DepartureAirport;
    }

    public String getDestinationAirport() {
        return this.DestinationAirport;
    }

    public void setDepartureAirport(String newDepartureAirport){
        this.DepartureAirport = newDepartureAirport;
    }

    public void setDestinationAirport(String newDestinationAirport){
        this.DestinationAirport = newDestinationAirport;
    }
}
