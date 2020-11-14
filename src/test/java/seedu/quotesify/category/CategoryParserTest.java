package seedu.quotesify.category;

import org.junit.jupiter.api.Test;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.ui.UiMessage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryParserTest {
    @Test
    public void getRequiredParameters_validCommand() throws QuotesifyException {
        String param1 = "action -b 1";
        String param2 = "romance -q 1";
        String param3 = "fantasy -b 1 -q 1";
        assertArrayEquals(new String[]{"action", "1", "", "1", "0"},
                CategoryParser.getRequiredParameters(param1));
        assertArrayEquals(new String[]{"romance", "", "1", "0", "1"},
                CategoryParser.getRequiredParameters(param2));
        assertArrayEquals(new String[]{"fantasy", "1", "1", "1", "1"},
                CategoryParser.getRequiredParameters(param3));
    }

    @Test
    public void getRequiredParameters_throwsQuotesifyException() {
        String param = "action -b 1 -b 1";
        Throwable exception = assertThrows(QuotesifyException.class, () ->
                CategoryParser.getRequiredParameters(param)
        );
        assertEquals("Invalid parameters!", exception.getMessage());
    }

    @Test
    public void validateParametersResult_validCommand() throws QuotesifyException {
        String param1 = "-b 1";
        String param2 = "romance";
        String param3 = "fantasy -b 1 -q 1";
        String[] params1 = CategoryParser.getRequiredParameters(param1);
        String[] params2 = CategoryParser.getRequiredParameters(param2);
        String[] params3 = CategoryParser.getRequiredParameters(param3);
        assertEquals(-1, CategoryParser.validateParametersResult(params1));
        assertEquals(0, CategoryParser.validateParametersResult(params2));
        assertEquals(1, CategoryParser.validateParametersResult(params3));
    }

    @Test
    public void getEditParameters_validCommand() throws QuotesifyException {
        String information = "love /to romance".trim();
        assertArrayEquals(new String[]{"love", "romance"},
                CategoryParser.getEditParameters(information));
    }

    @Test
    public void getEditParameters_invalidCommand() {
        String information = "love /to ".trim();
        Throwable exception = assertThrows(QuotesifyException.class, () ->
                CategoryParser.getEditParameters(information)
        );
        String error = "Invalid parameters!" + System.lineSeparator() + UiMessage.EDIT_CATEGORY_COMMAND;
        assertEquals(error, exception.getMessage());
    }

    @Test
    public void parseCategoriesToList_success() {
        String categories = "love romance";
        List<String> list = new ArrayList<>();
        list.add("love");
        list.add("romance");
        assertArrayEquals(list.toArray(), CategoryParser.parseCategoriesToList(categories).toArray());
    }
}
