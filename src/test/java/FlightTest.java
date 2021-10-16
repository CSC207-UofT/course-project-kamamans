import entities.Flight;
import entities.Plane;
import entities.Route;

import java.util.Calendar;
import java.util.Date;

import org.junit.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FlightTest {
    Flight f;
    Route r;
    Plane p;


    @Before
    public void setup() throws Exception {
        // create an arraylist
        ArrayList<String> passengers = new ArrayList<String>();
        passengers.add("Majda");
        passengers.add("Salwa");
        passengers.add("Nathan");
        passengers.add("Kevin");
        passengers.add("Albert");
        passengers.add("Marian");
        passengers.add("Andrew");
        passengers.add("Andrei");

        // create an instance of Plane

        p = new Plane("Airbus A321", 220, 20,
                200, true, passengers);

        // create an instance of Flight
        //Generate a date for Jan. 9, 2021, 10:11:12 AM
        Calendar cal = Calendar.getInstance();
        cal.set(2021, Calendar.JANUARY, 9, 10, 11, 12); //Year, month, day of month, hours, minutes and seconds
        Date date = cal.getTime();
        f = new Flight(date, p, r, 1 );
    }

    @Test(timeout = 50)

    public void TestGetPlane(){
        assertEquals(p, f.getPlane());
    }

    @Test(timeout = 50)
    public void TestGetDate(){
        //Generate a date for Jan. 9, 2021, 10:11:12 AM
        Calendar cal = Calendar.getInstance();
        cal.set(2021, Calendar.JANUARY, 9, 10, 11, 12); //Year, month, day of month, hours, minutes and seconds
        Date date = cal.getTime();

        assertEquals(date, f.getDate());
    }

    @Test(timeout = 50)
    public void TestGetDuration(){
        assert(1 == f.getDuration());
    }

    //TODO: Create a unit test for Route, when Route Class is implemented

}
