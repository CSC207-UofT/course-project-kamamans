import entities.Graph;
import org.junit.*;
//import

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GraphTest {
    Graph g = new Graph(4);

    @Before
    public void setUp() {
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
    }

    @Test(timeout = 50)
    public void testAllPaths() {
        System.out.println(g.allPaths(0, 3));
        ArrayList<Integer> a1 = new ArrayList<Integer>(4);
        a1.add(0);
        a1.add(1);
        a1.add(2);
        a1.add(3);
        ArrayList<Integer> a2 = new ArrayList<Integer>(3);
        a2.add(0);
        a2.add(2);
        a2.add(3);
        ArrayList<ArrayList<Integer>> aList = new ArrayList<ArrayList<Integer>>(2);
        aList.add(a1);
        aList.add(a2);
        assertEquals(aList, g.allPaths(0, 3));
    }
}
