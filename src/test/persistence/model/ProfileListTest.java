package persistence.model;

import model.Profile;
import model.ProfileList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileListTest {
    ProfileList testProfileList;
    Profile testProfile1;
    Profile testProfile2;
    Profile testProfile3;

    @BeforeEach
    void setUp() {
        testProfileList = new ProfileList();
        testProfile1 = new Profile("Chad", "Arts", 33, "Howdy!");
        testProfile2 = new Profile("Karen", "Applied Science", 49, "My message!");
        testProfile3 = new Profile("Sandy", "Arts", 49, "I love Arts!");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testProfileList.getList().size());
    }

    @Test
    void testAddProfileOnlyOne() {
        testProfileList.addProfile(testProfile1);
        assertEquals(testProfile1, testProfileList.getList().get(0));
        assertEquals(1, testProfileList.getList().size());
    }

    @Test
    void testAddProfilesMultiple() {
        testProfileList.addProfile(testProfile2);
        assertEquals(testProfile2, testProfileList.getList().get(0));
        assertEquals(1, testProfileList.getList().size());
        testProfileList.addProfile(testProfile1);
        assertEquals(testProfile1, testProfileList.getList().get(1));
        assertEquals(2, testProfileList.getList().size());
    }

    @Test
    void testRemoveProfileOnlyOne() {
        testProfileList.addProfile(testProfile1);
        testProfileList.removeProfile(testProfile1);
        assertEquals(0, testProfileList.getList().size());
        assertFalse(testProfileList.getList().contains(testProfile1));
    }

    @Test
    void testRemoveProfileMultiple() {
        testProfileList.addProfile(testProfile2);
        testProfileList.addProfile(testProfile1);
        testProfileList.removeProfile(testProfile1);
        assertEquals(1, testProfileList.getList().size());
        assertFalse(testProfileList.getList().contains(testProfile1));
        assertTrue(testProfileList.getList().contains(testProfile2));
        testProfileList.removeProfile(testProfile2);
        assertEquals(0, testProfileList.getList().size());
        assertFalse(testProfileList.getList().contains(testProfile2));
    }

    @Test
    void testSortProfileByFacultyExistsOneProfile() {
        testProfileList.addProfile(testProfile1);
        testProfileList.addProfile(testProfile2);
        testProfileList.addProfile(testProfile3);
        List<Profile> sorted = new ArrayList<>();
        sorted.add(testProfile2);
        assertEquals(sorted, testProfileList.sortProfileByFaculty("Applied Science"));
    }
    @Test
    void testSortProfileByFacultyExistsMultipleProfiles() {
        testProfileList.addProfile(testProfile1);
        testProfileList.addProfile(testProfile2);
        testProfileList.addProfile(testProfile3);
        List<Profile> sorted = new ArrayList<>();
        sorted.add(testProfile1);
        sorted.add(testProfile3);
        assertEquals(sorted, testProfileList.sortProfileByFaculty("Arts"));
    }

    @Test
    void testSortProfileByFacultyDoesNotExistAProfile() {
        testProfileList.addProfile(testProfile1);
        testProfileList .addProfile(testProfile2);
        List<Profile> sorted = new ArrayList<>();
        assertEquals(sorted, testProfileList.sortProfileByFaculty("Science"));
        assertEquals(0, testProfileList.sortProfileByFaculty("Science").size());
    }

    @Test
    void testSortProfileByRouteExistsOneProfile() {
        testProfileList.addProfile(testProfile1);
        testProfileList.addProfile(testProfile2);
        testProfileList.addProfile(testProfile3);
        List<Profile> sorted = new ArrayList<>();
        sorted.add(testProfile1);
        assertEquals(sorted, testProfileList.sortProfileByRoute(33));
    }

    @Test
    void testSortProfileByRouteExistsMultipleProfiles() {
        testProfileList.addProfile(testProfile1);
        testProfileList.addProfile(testProfile2);
        testProfileList.addProfile(testProfile3);
        List<Profile> sorted = new ArrayList<>();
        sorted.add(testProfile2);
        sorted.add(testProfile3);
        assertEquals(sorted, testProfileList.sortProfileByRoute(49));
    }

    @Test
    void testSortProfileByRouteDoesNotExistAProfile() {
        testProfileList.addProfile(testProfile1);
        testProfileList.addProfile(testProfile2);
        testProfileList.addProfile(testProfile3);
        List<Profile> sorted = new ArrayList<>();
        assertEquals(sorted, testProfileList.sortProfileByRoute(99));
        assertEquals(0, testProfileList.sortProfileByRoute(99).size());
    }

}
