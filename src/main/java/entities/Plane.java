package entities;

import java.io.Serializable;

/**
 * Creates a plane object that stores the brand name of the plane, total seat count, number of first class seats,
 * number of economy seats, and the number of vacant seats on the plane.
 */

public class Plane implements Serializable {
    private final String brandName;
    private final int seatCount;
    private final int firstClassSeats;
    private final int economySeats;
    private final boolean hasVacantSeats;


    public Plane(String brandName, int seatCount, int firstClassSeats,
                 int economySeats, boolean hasVacantSeats) {

        this.brandName = brandName;
        this.seatCount = seatCount;
        this.firstClassSeats = firstClassSeats;
        this.economySeats = economySeats;
        this.hasVacantSeats = hasVacantSeats;


    }

    public String getBrandName() {
        return this.brandName;
    }

    public int getSeatCount() {
        return this.seatCount;
    }

    public int getFirstClassSeats() {
        return this.firstClassSeats;
    }

    public int getEconomySeats() {
        return this.economySeats;
    }

    public boolean getHasVacantSeats() {
        return this.hasVacantSeats;
    }

    public String toString(){

        StringBuilder returnString = new StringBuilder();

        //Adding plane details
        returnString.append("{");
        returnString.append("\"brandName\": \"").append(getBrandName()).append("\", ");
        returnString.append("\"seatCount\": ").append(getSeatCount()).append(", ");
        returnString.append("\"firstClassSeats\": ").append(getFirstClassSeats()).append(", ");
        returnString.append("\"economySeats\": ").append(getEconomySeats()).append(", ");
        returnString.append("\"hasVacantSeats\": ").append(getHasVacantSeats());
        returnString.append("}");

        return new String(returnString);
    }
}
