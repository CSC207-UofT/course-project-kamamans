package entities;

import java.util.List;

public class Airport {
    private String city;
    private String iataCode;
    private List<Route> routes;

    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return this.city;
    }

    public void setIataCode(String IATA){
        this.iataCode = IATA;
    }

    public String getIataCode(){
        return this.iataCode;
    }

    public void setRoutes(List<Route> routes){
        this.routes = routes;
    }

    public void addRoute(Route route){
        this.routes.add(route);
    }

    public List<Route> getRoutes(){
        return this.routes;
    }
}
