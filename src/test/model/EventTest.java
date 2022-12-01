package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event event;
    private Date date;


    @BeforeEach
    void setup() {
        event = new Event("A fun event!");
        date = Calendar.getInstance().getTime();
    }

    @Test
    void testEvent() {
        assertEquals("A fun event!", event.getDescription());
        assertEquals(date, event.getDate());
    }

    @Test
    void testToString() {
        assertEquals(date.toString() + "\n" + "A fun event!", event.toString());
    }

    @Test
    void testEqualsOtherIsNull() {
        assertFalse(event.equals(null));
    }

    @Test
    void testEqualsDifferentClass() {
        assertFalse(event.equals(date));
    }


}
