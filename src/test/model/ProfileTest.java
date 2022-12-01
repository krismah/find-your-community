package model;

import model.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {
    Profile testProfile;

    @BeforeEach
    void setUp() {
        testProfile = new Profile("Amy", "Science", 99, "Hello!");
    }

    @Test
    void testConstructor() {
        assertEquals("Amy", testProfile.getName());
        assertEquals("Science", testProfile.getFaculty());
        assertEquals(99, testProfile.getRoute());
        assertEquals("Hello!", testProfile.getMessage());
    }

    @Test
    void testToString() {
        assertEquals("Amy" + "\n\t" + "Science" + "\n\t" + 99 + "\n\t" + "Hello!", testProfile.toString());
    }
}
