
import entities.Airport;
import entities.Route;
import entities.basicUser;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AiportTest {
    Airport a;
    private Route r;
    //TODO : need to complete Route before testing
    List<Route> listofRoute = new ArrayList<Route>(List.of(r));

    @Before
    public void setUp() throws Exception {
        a = new Airport("Toronto", "YYZ", listofRoute);
    }

    @Test(timeout = 50)
    public void TestGetCity(){
        assertEquals("Toronto", a.getCity());
    }

    @Test(timeout = 50)
    public void TestSetCity(){
        a.setCity("GTA");
        assertEquals("GTA", a.getCity());
    }

    @Test(timeout = 50)
    public void TestGetIataCode(){
        assertEquals("YYZ", a.getIataCode());
    }

    @Test(timeout = 50)
    public void TestSetIataCode(){
        a.setIataCode("YTZ");
        assertEquals("YTZ", a.getIataCode());
    }
    //TODO: Once Routes is created complete Unit Test Cases

}
