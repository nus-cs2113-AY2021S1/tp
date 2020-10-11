package seedu.duke.event;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testPrintEvent() {
        Event event = new Event("PR meeting", "2000-06-30", "8pm");
        assertEquals("Event Name: " + "PR meeting" + "\nDate: "
                + "Jun 30 2000" + "\nTime: " + "8pm" + "\n", event.printEvent());
    }

}