import entities.Route;
import entities.SearchQueries;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

import java.util.List;

public class SearchQueriesTest {

    public Route<String> r1;
    public Route<String> r2;
    public Route<String> r3;
    public ArrayList<Route<String>> routes = new ArrayList<>();
    public SearchQueries<List<Route<String>>> sq;


    @Before
    public void setUpRoutes(){
        r1 = new Route<>("Toronto", "Edmonton", 900, 2);
        r2 = new Route<>("Montreal", "Quebec City", 600, 20);
        r3 = new Route<>("China", "United States", 1200, 3);
        routes.add(r1);
        routes.add(r2);
        routes.add(r3);
    }

    @Before
    public void setUpSearchQueries(){
        sq = new SearchQueries<>(routes);
    }

    @Test(timeout = 50)
    public void TestFlightsSortedByPrice() {
        sq.sortByPrice();

        ArrayList<Route<String>> routesa = new ArrayList<>();
        routesa.add(r2);
        routesa.add(r1);
        routesa.add(r3);
        SearchQueries<List<Route<String>>> sqa = new SearchQueries<>(routesa);

        assertEquals(sq.getPotentialRoutes().get(0), sqa.getPotentialRoutes().get(0));
        assertEquals(sq.getPotentialRoutes().get(1), sqa.getPotentialRoutes().get(1));
        assertEquals(sq.getPotentialRoutes().get(2), sqa.getPotentialRoutes().get(2));
    }

    @Test(timeout = 50)
    public void TestFlightsSortedByDuration() {
        sq.sortByDuration();

        ArrayList<Route<String>> routesa = new ArrayList<>();
        routesa.add(r1);
        routesa.add(r3);
        routesa.add(r2);
        SearchQueries<List<Route<String>>> sqa = new SearchQueries<>(routesa);

        assertEquals(sq.getPotentialRoutes().get(0), sqa.getPotentialRoutes().get(0));
        assertEquals(sq.getPotentialRoutes().get(1), sqa.getPotentialRoutes().get(1));
        assertEquals(sq.getPotentialRoutes().get(2), sqa.getPotentialRoutes().get(2));
    }


}
