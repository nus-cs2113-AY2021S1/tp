package seedu.duke.event;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EventTest {
    Event event = new Event("PR meeting", "2000-06-30", "8pm");

    @Test
    void testPrintEvent() {
        assertEquals("Event Name: " + "PR meeting" + "\nDate: "
                + "Jun 30 2000" + "\nTime: " + "8pm" + "\n", event.printEvent());
    }

    @Test
    void testEventAdd() {
        String expected1 = "Got it. I've added this Event:\n"
                + "Event Name: PR meeting"
                + "\nDate: Jun 30 2000"
                + "\nTime: 8pm\n"
                + "\nNow you have 1 event in the list.\n";
        assertEquals(expected1,EventList.addEvent(event));

        String expected3 = "Here are the current events in your list:\n"
                + "1.Event Name: PR meeting"
                + "\nDate: Jun 30 2000"
                + "\nTime: 8pm\n";
        assertEquals(expected3, EventList.printEventList());

        String expected2 = "I'll remove this Event:\n"
                + "Event Name: PR meeting"
                + "\nDate: Jun 30 2000"
                + "\nTime: 8pm\n"
                + "\nNow you have 0 event in the list.";
        assertEquals(expected2,EventList.deleteEvent(0));
    }

}