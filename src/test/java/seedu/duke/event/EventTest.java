package seedu.duke.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EventTest {
    Event event = new Event("PR meeting", "2000-06-30", "8pm");
    Event event2 = new Event("Autodesk course","2020-12-20","8-10.30pm");

    @Test
    void testPrintEvent() {
        assertEquals("[E][Up-coming]" + "\nEvent Name: " + "PR meeting" + "\nDate: "
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
                + "\nTime: 8pm" +"\n" + "*".repeat(50) + "\n";
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
                + "\nTime: 8pm\n"
                + "Now you have 0 event in the list.";
        assertEquals(expected2,EventList.deleteEvent(0));

        EventList.addEvent(event2);

        String expected5 = "1.[E][Up-coming]\n"
                + "Event Name: Autodesk course\n"
                + "Date: Dec 20 2020\n"
                + "Time: 8-10.30pm\n"
                + "Number of day(s) left: 61" + "\n"+ "*".repeat(50)+"\n";
        assertEquals(expected5,EventList.countdownView());

        String expected6 = "1.[E][Up-coming]\n"
                + "Event Name: Autodesk course\n"
                + "Date: Dec 20 2020\n"
                + "Time: 8-10.30pm" + "\n"+ "*".repeat(50)+"\n";
        assertEquals(expected6,EventList.searchEvents("autodesk"));

    }

}