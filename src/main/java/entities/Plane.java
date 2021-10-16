package entities;

import java.util.ArrayList;

public class Plane {
    private String brandName;
    private int seatCount;
    private int firstClassSeats;
    private int economySeats;
    private boolean hasVacantSeats;
    private ArrayList<String> passengerList; //TODO:Assumption is that passengerList is a list of all the passengers.


    public Plane(String brandName, int seatCount, int firstClassSeats,
                 int economySeats, boolean hasVacantSeats, ArrayList<String> passengers){

        this.brandName = brandName;
        this.seatCount = seatCount;
        this.firstClassSeats = firstClassSeats;
        this.economySeats = economySeats;
        this.hasVacantSeats = hasVacantSeats;
        this.passengerList = passengers;

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

    public ArrayList<String> getPassengerList(){
        return this.passengerList;
    }

}
