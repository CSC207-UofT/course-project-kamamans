package entities;
import java.util.Date;

public class Flight {
    private Date date;
    private Plane plane;
    private double duration; //TODO: Assuming this will be in hours?
    private Airport source;
    private Airport destination;

    public Flight(Date date, Plane plane, double duration, Airport source, Airport destination){
        this.date = date;
        this.plane = plane;
        this.duration = duration;
        this.source = source;
        this.destination = destination;
    }

    public Date getDate(){
        return date;
    }
    public Plane getPlane(){
        return this.plane;
    }
    public double getDuration(){
        return this.duration;
    }
    public Airport getSource(){
        return this.source;
    }
    public Airport getDestination(){return this.destination;}

}
