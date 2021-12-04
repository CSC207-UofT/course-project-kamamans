package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="airports")
@Table
//    @Id
//    @SequenceGenerator(
//            name = "airport_sequence",
//            sequenceName = "airport_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "airport_sequence"
//    )

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
}
