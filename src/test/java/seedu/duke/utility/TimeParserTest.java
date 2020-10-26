package seedu.duke.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeParserTest {
    private TimeParser parser = new TimeParser();

    @org.junit.jupiter.api.Test
    public void timeParseTime() {
        final String input = "2h45m";
        assertEquals(165, parser.parseTime(input));

    }
}
