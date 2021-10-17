package entities;

import java.util.List;

public class Airport {
    private String city;
    private String iataCode;

    public Airport(String city, String iataCode){
        this.city = city;
        this.iataCode = iataCode;
    }

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
}
