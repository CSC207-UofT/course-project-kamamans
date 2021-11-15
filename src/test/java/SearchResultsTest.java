import entities.Route;
import entities.Airport;
import entities.SearchResults;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;
import usecases.InteractDatabase;

import java.util.List;

public class SearchResultsTest {

    public Route r1;
    public Route r2;
    public Route r3;
    public ArrayList<Route> routes = new ArrayList<>();
    public SearchResults sq;
    InteractDatabase db = new InteractDatabase();

    @Before
    public void setUpRoutes(){
        r1 = db.getRoutes().get(0);
        r2 = db.getRoutes().get(1);
        r3 = db.getRoutes().get(2);
        routes.add(r1);
        routes.add(r2);
        routes.add(r3);
    }

    @Before
    public void setUpSearchQueries(){
        sq = new SearchResults(routes);
    }

    @Test(timeout = 50)
    public void TestFlightsSortedByPrice() {
        sq.sortByPrice();

        ArrayList<Route> routesa = new ArrayList<>();
        routesa.add(r3);
        routesa.add(r1);
        routesa.add(r2);
        SearchResults sqa = new SearchResults(routesa);

        double a = sq.getPotentialRoutes().get(0).getPriceofFlights();
        double b = sq.getPotentialRoutes().get(1).getPriceofFlights();
        double c = sq.getPotentialRoutes().get(2).getPriceofFlights();

        assertTrue((a <= b) && (b <= c));
    }

    @Test(timeout = 50)
    public void TestFlightsSortedByDuration() {
        sq.sortByDuration();

        ArrayList<Route> routesa = new ArrayList<>();
        routesa.add(r3);
        routesa.add(r1);
        routesa.add(r2);
        SearchResults sqa = new SearchResults(routesa);

        assertEquals(sq.getPotentialRoutes().get(0), sqa.getPotentialRoutes().get(0));
        assertEquals(sq.getPotentialRoutes().get(1), sqa.getPotentialRoutes().get(1));
        assertEquals(sq.getPotentialRoutes().get(2), sqa.getPotentialRoutes().get(2));
    }


}
