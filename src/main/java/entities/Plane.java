package entities;

import java.util.ArrayList;

public class Plane {
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
