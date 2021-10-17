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

    public Route<Airport> r1;
    public Route<Airport> r2;
    public Route<Airport> r3;
    public ArrayList<Route<Airport>> routes = new ArrayList<>();
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

        ArrayList<Route<Airport>> routesa = new ArrayList<>();
        routesa.add(r3);
        routesa.add(r1);
        routesa.add(r2);
        SearchResults sqa = new SearchResults(routesa);

        assertEquals(sq.getPotentialRoutes().get(0), sqa.getPotentialRoutes().get(0));
        assertEquals(sq.getPotentialRoutes().get(1), sqa.getPotentialRoutes().get(1));
        assertEquals(sq.getPotentialRoutes().get(2), sqa.getPotentialRoutes().get(2));
    }

    @Test(timeout = 50)
    public void TestFlightsSortedByDuration() {
        sq.sortByDuration();

        ArrayList<Route<Airport>> routesa = new ArrayList<>();
        routesa.add(r3);
        routesa.add(r1);
        routesa.add(r2);
        SearchResults sqa = new SearchResults(routesa);

        assertEquals(sq.getPotentialRoutes().get(0), sqa.getPotentialRoutes().get(0));
        assertEquals(sq.getPotentialRoutes().get(1), sqa.getPotentialRoutes().get(1));
        assertEquals(sq.getPotentialRoutes().get(2), sqa.getPotentialRoutes().get(2));
    }


}
