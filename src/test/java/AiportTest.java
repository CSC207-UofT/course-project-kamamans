
import entities.Airport;
import entities.Route;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AiportTest {
    Airport a;
    private Route r;

    @Before
    public void setUp() throws Exception {
        a = new Airport("Toronto", "YYZ");
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
