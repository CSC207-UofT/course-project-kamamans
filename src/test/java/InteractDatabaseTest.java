import entities.Airport;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import usecases.InteractDatabase;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InteractDatabaseTest {
    @Test(timeout = 50)
    public static void main(String[] args) throws JSONException, IOException, ClassNotFoundException {
        String[] strings = new String[] {"a"};
        InteractDatabase.main(strings);
        assert InteractDatabase.getAirportList().size() > 1;
        assert InteractDatabase.getPlaneList().size() > 1;
        assert InteractDatabase.getAirportByIata("AAA").getIataCode() == "AAA";
    }

}