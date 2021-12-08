package usecases;

import entities.Plane;
import gateway.InitializeDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlaneReadWriterTest {

    @Before
    public void setUp() {
        InitializeDatabase.resetTestData();
    }

    @Test
    void getPlaneList() {
        ArrayList<Plane> list = PlaneReadWriter.getPlaneList();
        Plane test = list.get(2);
        assertEquals(test.getBrandName(), "Falcon 1");
        assertTrue(test.getHasVacantSeats());
        assertEquals(test.getIataCode(), "003");
        assertEquals(test.getSeatCount(), 1337);
    }

    @Test
    void getPlaneByIata() {
        Plane toAdd = new Plane("AirBus", 50, 5, 45, true, "445");
        PlaneReadWriter.postPlane(toAdd);
        Plane test = PlaneReadWriter.getPlaneByIata("445");
        assertEquals(test.getBrandName(), "AirBus");
        assertEquals(test.getIataCode(), "445");
        assertEquals(test.getSeatCount(), 50);
        assertEquals(test.getFirstClassSeats(), 5);
        assertTrue(test.getHasVacantSeats());
    }

    @Test
    void postPlane() {
        Plane toAdd = new Plane("Pirate Ship", 100, 1, 99, true, "223");
        PlaneReadWriter.postPlane(toAdd);
        Plane test = PlaneReadWriter.getPlaneByIata("223");
        assertEquals(test.getBrandName(), "Pirate Ship");
        assertEquals(test.getIataCode(), "223");
        assertEquals(test.getSeatCount(), 100);
        assertEquals(test.getFirstClassSeats(), 1);
        assertTrue(test.getHasVacantSeats());
    }

    @After
    public void terminate() {
        InitializeDatabase.resetTestData();
    }
}