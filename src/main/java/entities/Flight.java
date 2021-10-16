package entities;
import java.util.Date;

public class Flight {
    private Date date;
    private Plane plane;
    private Route route;
    private double duration; //TODO: Assuming this will be in hours?

    public Flight(Date date, Plane plane, Route route, double duration){
        this.date = date;
        this.plane = plane;
        this.route = route;
        this.duration = duration;
    }

    public Date getDate(){
        return date;
    }
    public Plane getPlane(){
        return this.plane;
    }
    public Route getRoute(){
        return this.route;
    }

    public double getDuration(){
        return this.duration;
    }

}
