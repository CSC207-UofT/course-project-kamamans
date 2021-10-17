package entities;
import java.util.Date;

public class Flight {
    private Date date;
    private Plane plane;
    private double price;
    private double duration; //TODO: Assuming this will be in hours?
    private Airport sourceAirport;
    private Airport destinationAirport;

    public Flight(Date date, Plane plane, double price, double duration, Airport source, Airport destination){
        this.date = date;
        this.plane = plane;
        this.price = price;
        this.duration = duration;
        this.sourceAirport = source;
        this.destinationAirport = destination;
    }

    public Date getDate(){
        return date;
    }
    public Plane getPlane(){
        return this.plane;
    }
    public double getPrice(){
        return this.price;
    }
    public double getDuration(){
        return this.duration;
    }
    public Airport getSourceAirport(){
        return this.sourceAirport;
    }
    public Airport getDestinationAirport(){return this.destinationAirport;}

}
