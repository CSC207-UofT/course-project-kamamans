package entities;

import javax.persistence.*;

@Entity
@Table
public class Airport {
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
@Id
    private String iataCode;
    private String city;

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
