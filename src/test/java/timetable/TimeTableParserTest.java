package timetable;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableParserTest {


    @Test
    void getDateTime_invalidDateTimeFormat() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            TimeTableParser.getDateTime("add class /CS2113T / online /http//:zoom.com/hsfufs  "
                    + "/Wednesday 2-4pm, Friday 5-8pm /5 weeks from 13/10");
        });
    }

    void getDateTime_validInput() {
        LocalDateTime expected = LocalDateTime.of(2020,10,13,0,0);
        LocalDateTime actual = TimeTableParser.getDateTime("add class /CS2113T / online /http//:zoom.com/hsfufs  "
                + "/Wednesday 2-4pm, Friday 5-8pm /5 weeks from 13/10/2020");
        assertEquals(expected, actual);
    }
}