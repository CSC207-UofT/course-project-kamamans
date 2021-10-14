import entities.Route;

public class RouteTest {
    public Route r;

    @Before
    public void setUp() throws Exception {
        r = new Route("Toronto", "Edmonton", 9000, 2);
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
    public void TestsetDepartureAirport("Tampa Bay"){
        assertEquals("Tampa Bay", r.getDepartureAirport());
    }

    @Test(timeout = 50)
    public void TestsetDestinationAirport("Tampa Bay"){
        assertEquals("Tampa Bay", r.setDestinationAirport());
    }

    @Test(timeout = 50)
    public void TestgetPriceofFlights(){
        assertEquals(9000, r.getPriceofFlights());
    }

    @Test(timeout = 50)
    public void TestTotalDuration(){
        assertEquals(2, r.getTotalDuration());
    }
}
