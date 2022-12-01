package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EventLogTest {
    private Event event1;
    private Event event2;
    private Event event3;
    private Profile profile;
    private ProfileList profileList;

    @BeforeEach
    void setup() {
        EventLog log = EventLog.getInstance();
        log.clear();

        profile = new Profile("Test", "Science", 49, "Hello!");
        profileList = new ProfileList();
    }

    @Test
    void testLogEvent() {
        List<Event> list = new ArrayList<>();

        event1 = new Event("Event 1");
        event2 = new Event("Event 2");
        event3 = new Event("Event 3");

        EventLog eventLog = EventLog.getInstance();

        eventLog.logEvent(event1);
        eventLog.logEvent(event2);
        eventLog.logEvent(event3);

        for (Event event : eventLog) {
            list.add(event);
        }

        assertTrue(list.contains(event1));
        assertTrue(list.contains(event2));
        assertTrue(list.contains(event3));
    }

    @Test
    void testClear() {
        EventLog log = EventLog.getInstance();
        log.clear();
        Iterator<Event> itr = log.iterator();
        assertTrue(itr.hasNext());   // After log is cleared, the clear log event is added
        assertEquals("Event log cleared.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }

    @Test
    void testAddProfileEventLog() {
        profileList.addProfile(profile);

        Event profileEvent = new Event(profile.getName() + " was added to your list! \n");
        EventLog log = EventLog.getInstance();
        List<Event> list = new ArrayList<>();

        for (Event event : log) {
            list.add(event);
        }

        assertEquals(profile.getName() + " was added to your list! \n", list.get(1).getDescription());
        assertEquals(2, list.size());
        assertTrue(list.contains(profileEvent));
    }

    @Test
    void testRemoveProfileEventLog() {
        profileList.addProfile(profile);
        profileList.removeProfile(profile);

        Event addedEvent = new Event(profile.getName() + " was added to your list! \n");
        Event removedEvent = new Event(profile.getName() + " was removed from your list. \n");
        EventLog log = EventLog.getInstance();
        List<Event> list = new ArrayList<>();

        for (Event event : log) {
            list.add(event);
        }

        assertEquals(profile.getName() + " was added to your list! \n", list.get(1).getDescription());
        assertTrue(list.contains(addedEvent));
        assertEquals(profile.getName() + " was removed from your list. \n", list.get(2).getDescription());
        assertTrue(list.contains(removedEvent));
        assertEquals(3, list.size());
    }

    @Test
    void testSortProfilesByRouteEventLog() {
        profileList.sortProfileByRoute(99);

        Event sortedEvent = new Event("Profiles sorted by route. \n");
        EventLog log = EventLog.getInstance();
        List<Event> list = new ArrayList<>();

        for (Event event : log) {
            list.add(event);
        }

        assertEquals("Profiles sorted by route. \n", list.get(1).getDescription());
        assertTrue(list.contains(sortedEvent));
        assertEquals(2, list.size());
    }



}
