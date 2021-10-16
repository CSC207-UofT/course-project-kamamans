import entities.Plane;
import entities.Route;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class PlaneTest {
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
    }

    @Test(timeout = 50)

    public void TestGetBrandName(){
        assertEquals("Airbus A321", p.getBrandName());
    }

    @Test(timeout = 50)
    public void TestGetSeatCount(){
        assertEquals(220, p.getSeatCount());
    }

    @Test(timeout = 50)
    public void TestGetFirstClassSeats(){
        assert(20 == p.getFirstClassSeats());
    }

    @Test(timeout = 50)
    public void TestGetEconomySeats(){
        assertEquals("Airbus A321", p.getBrandName());
    }

    @Test(timeout = 50)
    public void TestGetHasVacantSeats(){
        assertTrue(p.getHasVacantSeats());
    }

    @Test(timeout = 50)
    public void TestGetPassengerList(){
        //create an array
        ArrayList<String> passengers = new ArrayList<String>();
        passengers.add("Majda");
        passengers.add("Salwa");
        passengers.add("Nathan");
        passengers.add("Kevin");
        passengers.add("Albert");
        passengers.add("Marian");
        passengers.add("Andrew");
        passengers.add("Andrei");
        assertEquals(passengers, p.getPassengerList() );

    }

}
