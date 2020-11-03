package seedu.duke.event;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @@author  Varsha3006
 */
class EventListTest {
    Event event = new Event("PR meeting", "2030-06-30", "08-00");
    Event event2 = new Event("Autodesk course","2030-10-20","08-00");
    Event event1 = new Event("PR meeting", "2010-06-30", "08-00");

    @Test
    void testPrintEvent() {
        assertEquals("[E][Up-coming]" + "\nEvent Name: " + "PR meeting" + "\nDate: "
                + "Jun 30 2030" + "\nTime: " + "08-00", event.printEvent());
    }

    @Test
    void test_EventList() {

        String expected = "Oops! Seems like you have entered a past date. Please enter a valid date.";
        assertEquals(expected,EventList.addEvent(event1));

        String expected1 = "Got it. I've added this Event:\n"
                + "[E][Up-coming]\n"
                + "Event Name: PR meeting"
                + "\nDate: Jun 30 2030"
                + "\nTime: 08-00"
                + "\nNow you have 1 event in the list.\n";
        assertEquals(expected1, EventList.addEvent(event));

        assertEquals("PR meeting", event.getEventName());

        assertEquals(LocalDate.parse("2030-06-30"),event.getEventDate());

        assertEquals("08-00", event.getEventTime());

        assertEquals(event,EventList.getEvent(0));

        String expected2 = "Here are the current events in your list:\n"
                + "1.[E][Up-coming]\n"
                + "Event Name: PR meeting"
                + "\nDate: Jun 30 2030"
                + "\nTime: 08-00" + "\n" + "*".repeat(50) + "\n";
        assertEquals(expected2, EventList.printEventList());

        String expected3 = "Nice! I've marked this task as done:\n"
                + "1.[E][Done]\n"
                + "Event Name: PR meeting\n"
                + "Date: Jun 30 2030\n"
                + "Time: 08-00";
        assertEquals(expected3, EventList.isCompleted(0));

        String expected4 = "Got it! I'll remove this Event:\n"
                + "[E][Done]\n"
                + "Event Name: PR meeting"
                + "\nDate: Jun 30 2030"
                + "\nTime: 08-00\n"
                + "Now you have 0 event in the list.";
        assertEquals(expected4, EventList.deleteEvent(0));


        String expected5 = "Oops! The event list is empty!";
        assertEquals(expected5,EventList.deleteEvent(3));

        EventList.addEvent(event2);
        //test search events
        String expected6 = "1.[E][Up-coming]\n"
                + "Event Name: Autodesk course\n"
                + "Date: Oct 20 2030\n"
                + "Time: 08-00" + "\n" + "*".repeat(50) + "\n";
        assertEquals(expected6,EventList.searchEvents("autodesk"));
        assertEquals(expected6,EventList.searchEvents("2030-10-20"));
        assertEquals("No matching events found!",EventList.searchEvents("arduino"));

        EventList.deleteEvent(0);
        String expected7 = "Oops! The event list is empty!";
        assertEquals(expected7,EventList.clearEvents());


        String expected8 = "Oops! The event list is empty!";
        assertEquals(expected8,EventList.printEventList());

        assertEquals("Oops! The event list is empty!",EventList.countdownView());

        EventList.addEvent(event);

        String expected9 = "OOPS!!! The event does not exist.\n";
        assertEquals(expected9,EventList.isCompleted(3));

    }



}