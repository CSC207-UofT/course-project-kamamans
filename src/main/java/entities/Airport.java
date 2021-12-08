package entities;

import org.json.*;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Represents an airport.
 *
 * Each airport object must include the city it is located in and its IATA code.
 */

public class Airport implements Serializable {
    private String city;
    private String iataCode;

    public Airport(String city, String iataCode){
        this.city = city;
        this.iataCode = iataCode;
    }

    public Airport() {
        this.city = "";
        this.iataCode = "";
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

    /**
     * Returns airports as a JSON parseable string.
     * @return airport as a JSON parseable string
     */
    public StringBuilder airportToString() {
        StringBuilder returnString = new StringBuilder("{");
        returnString.append("\"iataCode\": " + "\""+iataCode+"\"" + ",");
        returnString.append("\"city\":" + "\""+city+"\"");
        returnString.append("}");
        return returnString;
    }
}
