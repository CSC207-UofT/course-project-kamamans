
import entities.basicUser;
import org.junit.*;

import static org.junit.Assert.*;

public class basicUserTest {
    basicUser b;

    @Before
    public void setUp() throws Exception {
        b = new basicUser("CSC207TEST", "csc207@mail.utoronto.ca", "4169782011", "economy");
        b.setUsername("testusername");
        b.setPassword("testpassword");
    }

    @Test(timeout = 50)
    public void TestGetUsername(){
        assertEquals("testusername", b.getUsername());
    }

    @Test(timeout = 50)
    public void TestSetUsername(){
        b.setUsername("testusername2");
        assertEquals("testusername2", b.getUsername());
    }

    @Test(timeout = 50)
    public void TestGetPassword(){
        assertEquals("testpassword", b.getPassword());
    }

    @Test(timeout = 50)
    public void TestSetPassword(){
        b.setPassword("testpassword2");
        assertEquals("testpassword2", b.getPassword());
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

    @Test(timeout = 50)
    public void TestGetEmail(){
        assertEquals("csc207@mail.utoronto.ca", b.getEmail());
    }

    @Test(timeout = 50)
    public void TestSetEmail(){
        b.setEmail("csc207test2@mail.utoronto.ca");
        assertEquals("csc207test2@mail.utoronto.ca", b.getEmail());
    }

    @Test(timeout = 50)
    public void TestGetPhoneNumber(){
        assertEquals("4169782011", b.getPhoneNumber());
    }

    @Test(timeout = 50)
    public void TestSetPhoneNumber(){
        b.setPhoneNumber("4169786360");
        assertEquals("4169786360", b.getPhoneNumber());
    }

    @Test(timeout = 50)
    public void TestGetClassType(){
        assertEquals("economy", b.getClassType());
    }

    @Test(timeout = 50)
    public void TestSetClassType(){
        b.setClassType("business");
        assertEquals("business", b.getClassType());
    }

}