package seedu.duke.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeParserTest {
    private TimeParser parser = new TimeParser();

    @org.junit.jupiter.api.Test
    public void timeParseTest() {
        final String input = "2h45m";
        assertEquals(165, parser.parseTime(input));

    }

    @org.junit.jupiter.api.Test
    public void parseIntegerTest() {
        final String input = "45";
        assertEquals(45, parser.parseTime(input));
    }

    @org.junit.jupiter.api.Test
    public void parseInvalidInput() {
        final String input = "onehour";
        assertEquals(-1,parser.parseTime(input));
    }
}
