package anichan.parser;

import anichan.anime.AnimeData;
import anichan.commands.BrowseCommand;
import anichan.exception.AniException;
import anichan.human.User;
import anichan.storage.StorageManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BrowseParserTest {
    private static AnimeData animeData;
    private static StorageManager storageManager;
    User user;

    private static final String EMPTY_STRING = "";
    private static final String NON_INT_PAGE_NUM = "-p twenty";
    private static final String FIRST_ANIME = "1.   Cowboy Bebop                                        [Id: 1  ]";
    private static final String BROWSING_PAGE_1 = "Browsing Page: 1";
    private static final String INVALID_ORDER_TEST = "-o whateverOrder";
    private static final String INVALID_PARAMETERS_TEST1 = "-n name";
    private static final String INVALID_PARAMETERS_TEST2 = "-sort name";
    private static final String INVALID_FIELD_TEST1 = "-s   ";
    private static final String INVALID_FIELD_TEST2 = "-s beepboopbeep";
    private static final String INVALID_FIELD_TEST3 = "-s -o -p";
    private static final String INVALID_FIELD_TEST4 = "- ";
    private static final String DIFF_ORDER_TEST = "-p 1 -s rating -o asc";
    private static final String DIFF_ORDER_TEST2 = "-s rating -o asc -p 1";
    private static final String INT_OVERFLOW_FIELD_TEST = "-p 999999999999999";

    @BeforeAll
    static void setUp() throws AniException {
        animeData = new AnimeData();
        storageManager = new StorageManager(EMPTY_STRING);
    }

    @Test
    void parse_invalidParameter_ThrowsAniException() {
        BrowseParser testParse = new BrowseParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(INVALID_PARAMETERS_TEST1);
        });

        assertThrows(AniException.class, () -> {
            testParse.parse(INVALID_PARAMETERS_TEST2);
        });
    }

    @Test
    void parse_edgeCaseDashBlank_defaultExecution() throws AniException {
        BrowseParser testParse = new BrowseParser();
        BrowseCommand testBrowse = testParse.parse(INVALID_FIELD_TEST4);
        testBrowse.setAnimePerPage(1);
        String result = testBrowse.execute(animeData, storageManager, user);
        assertEquals(FIRST_ANIME + System.lineSeparator() + BROWSING_PAGE_1, result);
    }

    @Test
    void parse_invalidPageNum_throwsAniException() {
        BrowseParser testParse = new BrowseParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(NON_INT_PAGE_NUM);
        });
    }

    @Test
    void parse_invalidOrderType_throwsAniException() {
        BrowseParser testParse = new BrowseParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(INVALID_ORDER_TEST);
        });
    }

    @Test
    void parse_invalidField_throwsAniException() {
        BrowseParser testParse = new BrowseParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(INVALID_FIELD_TEST1);
        });

        BrowseParser testParse2 = new BrowseParser();
        assertThrows(AniException.class, () -> {
            testParse2.parse(INVALID_FIELD_TEST2);
        });

        BrowseParser testParse3 = new BrowseParser();
        assertThrows(AniException.class, () -> {
            testParse3.parse(INVALID_FIELD_TEST3);
        });
    }

    @Test
    void parse_differentParameterOrder_identicalBrowseSettings() throws AniException {
        BrowseParser testParse = new BrowseParser();
        BrowseCommand testBrowse = testParse.parse(DIFF_ORDER_TEST);
        BrowseCommand testBrowse2 = testParse.parse(DIFF_ORDER_TEST2);

        assertEquals(testBrowse.getPage(), testBrowse2.getPage());
        assertEquals(testBrowse.getSortType(), testBrowse2.getSortType());
        assertEquals(testBrowse.getOrder(), testBrowse2.getOrder());
    }

    @Test
    void parse_setPageToIntOverflow_throwsAniException() {
        BrowseParser testParse = new BrowseParser();
        assertThrows(AniException.class, () -> {
            testParse.parse(INT_OVERFLOW_FIELD_TEST);
        });
    }

}
