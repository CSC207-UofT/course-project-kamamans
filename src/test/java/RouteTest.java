import entities.Airport;
import entities.Flight;
import entities.Plane;
import entities.Route;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class RouteTest {
    public Route r;

    @Before
    public void setUp() throws Exception {
        List<Flight> flights = new ArrayList<>();

        flights.add(new Flight(new GregorianCalendar(2021, Calendar.DECEMBER, 30), new Plane("sdfinweo", 50, 50, 50, true), 9000, 30, new Airport("grse", "5235"), new Airport("gadgd", "235")));

        Airport t = new Airport("Toronto", "84681");

        Airport m = new Airport("Montreal", "235346");

        r = new Route(t, m, flights.get(0).getDate(), flights);
    }

    @Test(timeout = 50)
    public void TestgetDepartureAirport(){
        Airport sampleDeparture = new Airport("Toronto", "84681");
        sampleDeparture.equals(r.getDepartureAirport());
    }

    @Test(timeout = 50)
    public void TestgetDestinationAirport(){
        Airport sampleDestination = new Airport("Montreal", "235346");
        sampleDestination.equals(r.getDestinationAirport());
    }

    @Test(timeout = 50)
    public void TestsetDepartureAirport(){
        Airport sampleDeparture2 = new Airport("Edmonton", "696969");
        r.setDepartureAirport(sampleDeparture2);
        assertEquals(sampleDeparture2, r.getDepartureAirport());
    }

    @Test(timeout = 50)
    public void TestsetDestinationAirport(){
        Airport sampleDestination2 = new Airport("Calgary", "969696");
        r.setDestinationAirport(sampleDestination2);
        assertEquals(sampleDestination2, r.getDestinationAirport());
    }

    @Test(timeout = 50)
    public void TestgetDepartureDate() {
        Calendar sampleDate = new GregorianCalendar(2021, Calendar.DECEMBER, 30);
        assertEquals(sampleDate, r.getDepartureDate());
    }

    @Test(timeout = 50)
    public void TestsetDepartureDate() {
        Calendar sampleDate2 = new GregorianCalendar(2022, Calendar.JANUARY, 6);
        r.setDepartureDate(sampleDate2);
        assertEquals(sampleDate2, r.getDepartureDate());
    }

    @Test(timeout = 50)
    public void TestgetFlights() {
        List<Flight> sampleflights = new ArrayList<>();
        sampleflights.add(new Flight(new GregorianCalendar(2021, Calendar.DECEMBER, 30), new Plane("sdfinweo", 50, 50, 50, true), 9000, 30, new Airport("grse", "5235"), new Airport("gadgd", "235")));
        sampleflights.equals(r.getFlights());
    }

    @Test(timeout = 50)
    public void TestgetPriceofFlights(){
        assertEquals(9000, r.getPriceofFlights(), 1);
    }

    @Test(timeout = 50)
    public void TestTotalDuration(){
        assertEquals(30, r.getTotalDuration(), 1);
    }

    @Test(timeout = 50)
    public void TestrouteToString(){
        String s = "[{\"departureAirport\": {\"city\": \"Toronto\", \"iataCode\": \"84681\"}, \"destinationAirport\": {\"city\": \"Montreal\", \"iataCode\": \"235346\"}, \"departureDate\": \"12/30/2021\", \"flights\": [{\"departureDate\": \"12/30/2021\", \"plane\": {\"brandName\": \"sdfinweo\", \"seatCount\": 50, \"firstClassSeats\": 50, \"economySeats\": 50, \"hasVacantSeats\": true}, \"price\": 9000.0, \"duration\": 30.0, \"sourceAirport\": {\"city\": \"grse\", \"iataCode\": \"5235\"}, \"destinationAirport\": {\"city\": \"gadgd\", \"iataCode\": \"235\"}}], \"price\": 9000.0, \"duration\": 30.0, \"id\": \"-1\"}]";
        r.routeToString().equals(s);
    }
}
