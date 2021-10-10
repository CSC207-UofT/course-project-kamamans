
import entities.basicUser;
import org.junit.*;

import static org.junit.Assert.*;

public class basicUserTest {
    basicUser b;

    @Before
    public void setUp() throws Exception {
        b = new basicUser("CSC207TEST", "csc207@mail.utoronto.ca", "4169782011", "economy");
    }

    @Test(timeout = 50)
    public void TestGetID() {
        assertEquals("CSC207TEST", b.getID());
    }

    @Test(timeout = 50)
    public void TestSetID() {
        b.setID("CSC207TEST2");
        assertEquals("CSC207TEST2", b.getID());
    }

}