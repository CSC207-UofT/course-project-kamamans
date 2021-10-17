import entities.Airport;
import entities.Flight;
import entities.Plane;
import entities.Route;
import org.junit.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class RouteTest {
    public Route<Airport> r;

    @Before
    public void setUp() throws Exception {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(new Date(5235346), new Plane("sdfinweo", 50, 50, 50, true), 9000, 30, new Airport("grse", "5235"), new Airport("gadgd", "235")));
        r = new Route<Airport>(new Airport("Toronto", "84681"), new Airport("Montreal", "235346"), flights);
    }

    @Test(timeout = 50)
    public void TestgetPriceofFlights(){
        assertEquals(9000, r.getPriceofFlights(), 1);
    }

    @Test(timeout = 50)
    public void TestTotalDuration(){
        assertEquals(30, r.getTotalDuration(), 1);
    }
}
