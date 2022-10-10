package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileDatabaseTest {
    ProfileDatabase testDatabase;
    Profile testProfile1;
    Profile testProfile2;
    Profile testProfile3;

    @BeforeEach
    void setUp() {
        testDatabase = new ProfileDatabase();
        testProfile1 = new Profile("Chad", "Arts", 33, "Howdy!");
        testProfile2 = new Profile("Karen", "Applied Science", 49, "My message!");
        testProfile3 = new Profile("Sandy", "Arts", 49, "I love Arts!");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testDatabase.getSet().size());
    }

    @Test
    void testAddProfileOneProfile() {
        testDatabase.addProfile(testProfile1);
        assertTrue(testDatabase.getSet().contains(testProfile1));
    }

    @Test
    void testAddProfileMultipleProfiles() {
        testDatabase.addProfile(testProfile1);
        testDatabase.addProfile(testProfile2);
        assertTrue(testDatabase.getSet().contains(testProfile1));
        assertTrue(testDatabase.getSet().contains(testProfile2));
    }

    @Test
    void testRemoveProfileOneProfile() {
        testDatabase.addProfile(testProfile1);
        testDatabase.addProfile(testProfile2);
        testDatabase.addProfile(testProfile3);
        testDatabase.removeProfile(testProfile1);
        assertFalse(testDatabase.getSet().contains(testProfile1));
        assertTrue(testDatabase.getSet().contains(testProfile2));
        assertTrue(testDatabase.getSet().contains(testProfile3));
        assertEquals(2, testDatabase.getSet().size());
    }

    @Test
    void testRemoveProfileMultipleProfiles() {
        testDatabase.addProfile(testProfile1);
        testDatabase.addProfile(testProfile2);
        testDatabase.addProfile(testProfile3);
        testDatabase.removeProfile(testProfile1);
        testDatabase.removeProfile(testProfile3);
        assertFalse(testDatabase.getSet().contains(testProfile1));
        assertFalse(testDatabase.getSet().contains(testProfile3));
        assertTrue(testDatabase.getSet().contains(testProfile2));
        assertEquals(1, testDatabase.getSet().size());
    }

    @Test
    void testSortProfileByFacultyExistsOneProfile() {
        testDatabase.addProfile(testProfile1);
        testDatabase.addProfile(testProfile2);
        testDatabase.addProfile(testProfile3);
        Set<Profile> sorted = new HashSet<>();
        sorted.add(testProfile2);
        assertEquals(sorted, testDatabase.sortProfileByFaculty("Applied Science"));
    }

    @Test
    void testSortProfileByFacultyExistsMultipleProfiles() {
        testDatabase.addProfile(testProfile1);
        testDatabase.addProfile(testProfile2);
        testDatabase.addProfile(testProfile3);
        Set<Profile> sorted = new HashSet<>();
        sorted.add(testProfile1);
        sorted.add(testProfile3);
        assertEquals(sorted, testDatabase.sortProfileByFaculty("Arts"));
    }

    @Test
    void testSortProfileByFacultyDoesNotExistAProfile() {
        testDatabase.addProfile(testProfile1);
        testDatabase.addProfile(testProfile2);
        testDatabase.addProfile(testProfile3);
        Set<Profile> sorted = new HashSet<>();
        assertEquals(sorted, testDatabase.sortProfileByFaculty("Science"));
        assertEquals(0, testDatabase.sortProfileByFaculty("Science").size());
    }

    @Test
    void testSortProfileByRouteExistsOneProfile() {
        testDatabase.addProfile(testProfile1);
        testDatabase.addProfile(testProfile2);
        testDatabase.addProfile(testProfile3);
        Set<Profile> sorted = new HashSet<>();
        sorted.add(testProfile1);
        assertEquals(sorted, testDatabase.sortProfileByRoute(33));
    }

    @Test
    void testSortProfileByRouteExistsMultipleProfiles() {
        testDatabase.addProfile(testProfile1);
        testDatabase.addProfile(testProfile2);
        testDatabase.addProfile(testProfile3);
        Set<Profile> sorted = new HashSet<>();
        sorted.add(testProfile2);
        sorted.add(testProfile3);
        assertEquals(sorted, testDatabase.sortProfileByRoute(49));
    }

    @Test
    void testSortProfileByRouteDoesNotExistAProfile() {
        testDatabase.addProfile(testProfile1);
        testDatabase.addProfile(testProfile2);
        testDatabase.addProfile(testProfile3);
        Set<Profile> sorted = new HashSet<>();
        assertEquals(sorted, testDatabase.sortProfileByRoute(99));
        assertEquals(0, testDatabase.sortProfileByRoute(99).size());
    }
}
