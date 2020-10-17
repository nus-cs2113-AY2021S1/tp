package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.AniException;
import seedu.duke.parser.BrowseParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BrowseCommandTest {

    protected static final String LARGE_PAGE_NUM = "-p 9999";
    protected static final String NEGATIVE_PAGE_NUM = "-p -1";
    protected static final String ZERO_PAGE_NUM = "-p 0";

    @Test
    void parse_invalidPageNum_ThrowsAniException() throws AniException {
        BrowseParser testParse = new BrowseParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(LARGE_PAGE_NUM);
        });

        BrowseParser testParse2 = new BrowseParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(NEGATIVE_PAGE_NUM);
        });

        BrowseParser testParse3 = new BrowseParser();
        BrowseCommand testBrowse = testParse3.parse(ZERO_PAGE_NUM);
        assertEquals(testBrowse.getPage(), 1);
    }
}
