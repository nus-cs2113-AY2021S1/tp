package seedu.quotesify.category;

import org.junit.jupiter.api.Test;
import seedu.quotesify.exception.QuotesifyException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryParserTest {
    @Test
    public void getRequiredParameters_validCommand() {
        String param1 = "action -b 1";
        String param2 = "romance -q 1";
        String param3 = "fantasy -b 1 -q 1";
        try {
            assertArrayEquals(new String[]{"action", "1", ""}, CategoryParser.getRequiredParameters(param1.split(" ")));
            assertArrayEquals(new String[]{"romance", "", "1"}, CategoryParser.getRequiredParameters(param2.split(" ")));
            assertArrayEquals(new String[]{"fantasy", "1", "1"}, CategoryParser.getRequiredParameters(param3.split(" ")));
        } catch (QuotesifyException e) {

        }
    }

    @Test
    public void getRequiredParameters_throwsQuotesifyException() {
        String param = "action -b 1 -b 1";
        Throwable exception = assertThrows(QuotesifyException.class, () -> CategoryParser.getRequiredParameters(param.split(" ")));
        assertEquals("Invalid parameters!", exception.getMessage());
    }
}
