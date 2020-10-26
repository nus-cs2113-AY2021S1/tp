package seedu.duke.event;

import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventListTest {
    Event event = new Event("PR meeting", "2000-06-30", "8pm");
    Event event2 = new Event("Autodesk course","2020-10-20","8-10.30pm");

    @Test
    void testPrintEvent() {
        assertEquals("[E][Up-coming]" + "\nEvent Name: " + "PR meeting" + "\nDate: "
                + "Jun 30 2000" + "\nTime: " + "8pm", event.printEvent());
    }

    @Test
    void test_EventList() {
        String expected1 = "Got it. I've added this Event:\n"
                + "[E][Up-coming]\n"
                + "Event Name: PR meeting"
                + "\nDate: Jun 30 2000"
                + "\nTime: 8pm"
                + "\nNow you have 1 event in the list.\n";
        assertEquals(expected1, EventList.addEvent(event));

        assertEquals("PR meeting", event.getEventName());

        assertEquals(LocalDate.parse("2000-06-30"),event.getEventDate());

        assertEquals("8pm", event.getEventTime());

        assertEquals(event,EventList.getEvent(0));

        String expected3 = "Here are the current events in your list:\n"
                + "1.[E][Up-coming]\n"
                + "Event Name: PR meeting"
                + "\nDate: Jun 30 2000"
                + "\nTime: 8pm" + "\n" + "*".repeat(50) + "\n";
        assertEquals(expected3, EventList.printEventList());

        String expected4 = "Nice! I've marked this task as done:\n"
                + "1.[E][Done]\n"
                + "Event Name: PR meeting\n"
                + "Date: Jun 30 2000\n"
                + "Time: 8pm";
        assertEquals(expected4, EventList.isCompleted(0));

        String expected2 = "Got it! I'll remove this Event:\n"
                + "[E][Done]\n"
                + "Event Name: PR meeting"
                + "\nDate: Jun 30 2000"
                + "\nTime: 8pm\n"
                + "Now you have 0 event in the list.";
        assertEquals(expected2, EventList.deleteEvent(0));


        String expected7 = "OOPS!!! The event does not exist.Please try our help command!\n";
        assertEquals(expected7,EventList.deleteEvent(3));

        //test countdown
        EventList.addEvent(event2);
        String expected5 = "";
        assertEquals(expected5, EventList.countdownView());

        //test search events
        String expected6 = "1.[E][Up-coming]\n"
                + "Event Name: Autodesk course\n"
                + "Date: Oct 20 2020\n"
                + "Time: 8-10.30pm" + "\n" + "*".repeat(50) + "\n";
        assertEquals(expected6,EventList.searchEvents("autodesk"));
        assertEquals(expected6,EventList.searchEvents("2020-10-20"));

        EventList.deleteEvent(0);
        String expected8 = "Oops! The event list is empty!";
        assertEquals(expected8,EventList.clearEvents());

        String expected9 = "OOPS!!! The event does not exist.Please try our help command!\n";
        assertEquals(expected9,EventList.isCompleted(3));

        String expected10 = "Oops! The event list is empty!";
        assertEquals(expected10,EventList.printEventList());

        assertEquals("The list is empty",EventList.countdownView());


    }

}