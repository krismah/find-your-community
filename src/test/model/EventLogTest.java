package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class EventLogTest {
    private Event event1;
    private Event event2;
    private Event event3;

    @BeforeEach
    void setup() {
        event1 = new Event("Event 1");
        event2 = new Event("Event 2");
        event3 = new Event("Event 3");

        EventLog log = EventLog.getInstance();

        log.logEvent(event1);
        log.logEvent(event2);
        log.logEvent(event3);
    }

    @Test
    void testLogEvent() {
        List<Event> list = new ArrayList<>();

        EventLog eventLog = EventLog.getInstance();

        for (Event event : eventLog) {
            list.add(event);
        }

        assertTrue(list.contains(event1));
        assertTrue(list.contains(event2));
        assertTrue(list.contains(event3));
    }



}
