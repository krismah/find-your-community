package persistence;

import model.Profile;

import static org.junit.jupiter.api.Assertions.*;

// Based on JSON Demo from CPSC 210
public class JsonTest {
    protected void checkProfile(String name, String faculty, int route, String message, Profile profile) {
        assertEquals(name, profile.getName());
        assertEquals(faculty, profile.getFaculty());
        assertEquals(route, profile.getRoute());
        assertEquals(message, profile.getMessage());
    }

}
