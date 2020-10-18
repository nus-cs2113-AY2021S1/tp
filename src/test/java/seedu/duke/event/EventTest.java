package seedu.duke.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EventTest {
    Event event = new Event("PR meeting", "2000-06-30", "8pm");

    @Test
    void testPrintEvent() {
        assertEquals("[E][Up-coming]\n" + "Event Name: " + "PR meeting" + "\nDate: "
                + "Jun 30 2000" + "\nTime: " + "8pm" , event.printEvent());
    }

    @Test
    void testEventList() {
        String expected1 = "Got it. I've added this Event:\n"
                + "[E][Up-coming]\n"
                + "Event Name: PR meeting"
                + "\nDate: Jun 30 2000"
                + "\nTime: 8pm"
                + "\nNow you have 1 event in the list.\n";
        assertEquals(expected1,EventList.addEvent(event));

        String expected3 = "Here are the current events in your list:\n"
                + "1.[E][Up-coming]\n"
                + "Event Name: PR meeting"
                + "\nDate: Jun 30 2000"
                + "\nTime: 8pm";
        assertEquals(expected3, EventList.printEventList());
        String expected4 = "Nice! I've marked this task as done:\n"
                +  "1.[E][Done]\n"
                +  "Event Name: PR meeting\n"
                + "Date: Jun 30 2000\n"
                + "Time: 8pm";
        assertEquals(expected4, EventList.isCompleted(0));

        String expected2 = "I'll remove this Event:\n"
                + "[E][Done]\n"
                + "Event Name: PR meeting"
                + "\nDate: Jun 30 2000"
                + "\nTime: 8pm"
                + "\nNow you have 0 event in the list.";
        assertEquals(expected2,EventList.deleteEvent(0));


    }

}