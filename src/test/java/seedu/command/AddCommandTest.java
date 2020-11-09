package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.task.Event;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AddCommandTest {

    String name = "CS2040C";
    String location = "LT23";
    String timeStart = "1600";
    String timeEnd = "1900";
    String locationTest = "/LT23";
    
    @Test
    void testAddCommand() {
        Event e = new Event(name, location,timeStart,timeEnd);

        assertEquals("CS2040C LT23 1600-1900",e.toString());
    }
    
    @Test
    void testLocation() {
        Event e1 = new Event(name, locationTest,timeStart,timeEnd);

        assertNotEquals(e1.toString(),"CS2040C LT23 1600-1900");
    }

}