package seedu.duke.command.timetable;

import seedu.duke.command.ChangeModeCommand;
import seedu.duke.command.timetable.ShowTimetableCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowTimetableCommandTest {

    @Test
    public void testShowAllSlots() {
        assertEquals("ALL", new ShowTimetableCommand("show").day);
    }

    @Test
    public void testMondaySlots() {
        assertEquals("mon", new ShowTimetableCommand("show mon").day);
    }

    @Test
    public void testTuesdaySlots() {
        assertEquals("tue", new ShowTimetableCommand("show tue").day);
    }

    @Test
    public void testWednesdaySlots() {
        assertEquals("wed", new ShowTimetableCommand("show wed").day);
    }

    @Test
    public void testThursdaySlots() {
        assertEquals("thu", new ShowTimetableCommand("show thu").day);
    }

    @Test
    public void testFridaySlots() {
        assertEquals("fri", new ShowTimetableCommand("show fri").day);
    }

    @Test
    public void testSatudaySlots() {
        assertEquals("sat", new ShowTimetableCommand("show sat").day);
    }

    @Test
    public void testSundaySlots() {
        assertEquals("sun", new ShowTimetableCommand("show sun").day);
    }

    @Test
    public void testInvalidSlots1() {
        assertEquals(null, new ShowTimetableCommand("show1").day);
    }

    @Test
    public void testInvalidSlots2() {
        assertEquals(null, new ShowTimetableCommand("show abc").day);
    }

}
