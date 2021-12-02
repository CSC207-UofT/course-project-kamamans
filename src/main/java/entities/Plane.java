package entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creates a plane object that stores the brand name of the plane, total seat count, number of first class seats,
 * number of economy seats, and the number of vacant seats on the plane.
 */

public class Plane implements Serializable {
    private String brandName;
    private int seatCount;
    private int firstClassSeats;
    private int economySeats;
    private boolean hasVacantSeats;



    public Plane(String brandName, int seatCount, int firstClassSeats,
                 int economySeats, boolean hasVacantSeats){

        this.brandName = brandName;
        this.seatCount = seatCount;
        this.firstClassSeats = firstClassSeats;
        this.economySeats = economySeats;
        this.hasVacantSeats = hasVacantSeats;


    }

    public String getBrandName(){
        return this.brandName;
    }

    public int getSeatCount(){
        return this.seatCount;
    }

    public int getFirstClassSeats(){
        return this.firstClassSeats;
    }

    public int getEconomySeats(){
        return this.economySeats;
    }

    public boolean getHasVacantSeats(){
        return this.hasVacantSeats;
    }

}
