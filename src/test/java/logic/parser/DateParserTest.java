package logic.parser;

import exceptions.InvalidDateFormatException;
import org.junit.jupiter.api.Test;
import logic.parser.DateParser;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class DateParserTest {

    @Test
    void parseDate_correctFormat_returnParsedDate() {
        LocalDateTime dt = LocalDateTime.of(2020, 10, 22, 0, 0);

        try {
            String in1 = "20201022";
            assertEquals(dt, DateParser.parseDate(in1));

            String in2 = "2020-10-22";
            assertEquals(dt, DateParser.parseDate(in2));

            String in3 = "2020 10 22";
            assertEquals(dt, DateParser.parseDate(in3));
        } catch (InvalidDateFormatException e) {
            fail();
        }
    }

    @Test
    void parseDate_wrongFormat_returnNull() {
        assertThrows(InvalidDateFormatException.class, () -> DateParser.parseDate("2020:10:22"));
    }

    @Test
    void parseDate_invalidDate_throwsInvalidDateFormatException() {
        assertThrows(InvalidDateFormatException.class, () -> DateParser.parseDate("20202131"));
    }
}
