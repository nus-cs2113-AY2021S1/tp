package seedu.duke.event;

import org.junit.jupiter.api.Test;
import seedu.duke.hr.Member;
import seedu.duke.hr.MemberList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EventListTest {
    Event event = new Event("PR meeting", "2030-06-30", "08-00");
    Event event2 = new Event("Autodesk course","2030-10-20","08-00");
    Event event1 = new Event("PR meeting", "2010-06-30", "08-00");
    Member member1 = new Member("James Gosling", 11111111,
            "111111@gmail.com", "member");

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

    @Test
    void test_EventAttendance_executesNormally() {
        EventList.addEvent(event2);
        MemberList.addToList(member1);
        String expected1 = "Noted. I have added this participant to this event:\n"
                + "James Gosling\n"
                + "Now you have 1 member participated in Autodesk course.\n";
        assertEquals(expected1, EventList.addAttendance(event2.getEventName(), member1.getMemberName()));

        String expected2 = "The following member has participated in this event:\n"
                + "1. James Gosling\n"
                + "Now you have 1 member attended Autodesk course.\n";
        assertEquals(expected2, EventList.listAttendance(event2.getEventName()));

        String expected3 = "Got it! I'll remove this member from the event attendance:\n"
                + "James Gosling\n"
                + "Now you have 0 members attended Autodesk course.\n";
        assertEquals(expected3, EventList.deleteAttendance(event2.getEventName(), member1.getMemberName()));
        EventList.deleteEvent(0);
        MemberList.deleteFromList(0);
    }

    @Test
    void test_EventAttendance_executeUnsuccessfully() {
        EventList.addEvent(event2);
        MemberList.addToList(member1);

        //member does not exist
        String expected1 = "Member does not exist!\n";
        assertEquals(expected1, EventList.addAttendance(event2.getEventName(),"Draco Malfoy"));

        //event does not exist
        String expected2 = "OOPS!!! The event does not exist.\n";
        assertEquals(expected2, EventList.addAttendance("Arduino course", member1.getMemberName()));

        EventList.addAttendance(event2.getEventName(), member1.getMemberName());
        //member attendance already taken
        String expected3 = "Member attendance had already been taken!\n";
        assertEquals(expected3, EventList.addAttendance(event2.getEventName(),member1.getMemberName()));

        //event does not exist
        String expected4 = "OOPS!!! The event does not exist.\n";
        assertEquals(expected4, EventList.listAttendance("Arduino course"));

        //event does not exist
        String expected5 = "OOPS!!! The event does not exist.\n";
        assertEquals(expected5, EventList.deleteAttendance("Arduino course", member1.getMemberName()));

        EventList.deleteAttendance(event2.getEventName(), member1.getMemberName());
        //member attendance does not exist
        String expected6 = "Member attendance for this event has not been taken!\n";
        assertEquals(expected6, EventList.deleteAttendance(event2.getEventName(), "Draco Malfoy"));
        EventList.deleteEvent(0);
        MemberList.deleteFromList(0);
    }

}