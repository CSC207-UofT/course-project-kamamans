import entities.Route;
import org.junit.*;

import static org.junit.Assert.*;

public class RouteTest {
    public Route r;

    @Before
    public void setUp() throws Exception {
        r = new Route("Toronto", "Edmonton", "9000", "2");
    }

    @Test(timeout = 50)
    public void TestgetDepartureAirport(){
        assertEquals("Toronto", r.getDepartureAirport());
    }

    @Test(timeout = 50)
    public void TestgetDestinationAirport(){
        assertEquals("Edmonton", r.getDestinationAirport());
    }

    @Test(timeout = 50)
    public void TestsetDepartureAirport(){
        r.setDepartureAirport("Tampa Bay");
        assertEquals("Tampa Bay", r.getDepartureAirport());
    }

    @Test(timeout = 50)
    public void TestsetDestinationAirport(){
        r.setDestinationAirport("Tampa Bay");
        assertEquals("Tampa Bay", r.getDestinationAirport());
    }

    @Test(timeout = 50)
    public void TestgetPriceofFlights(){
        assertEquals("9000", r.getPriceofFlights());
    }

    @Test(timeout = 50)
    public void TestTotalDuration(){
        assertEquals("2", r.getTotalDuration());
    }
}
