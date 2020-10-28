package anichan.commands;

import anichan.anime.AnimeData;
import anichan.exception.AniException;
import anichan.human.User;
import anichan.storage.StorageManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SearchCommandTest {
    private static AnimeData animeData;
    private static StorageManager storageManager;
    private User user;

    private static final String SEARCH_ANIME_1 = "fate";
    private static final String SEARCH_ANIME_NONEXISTENT = "CS2113T The Great Refactoring!";
    private static final String NO_RESULTS_FOUND = "No results found!";
    private static final int NAME_SEARCH = 0;

    protected static final int ABOVE_RANGE = 9999;
    protected static final int BELOW_RANGE = -1;

    @BeforeAll
    static void setUp() throws AniException {
        animeData = new AnimeData();
        storageManager = new StorageManager("");
    }

    @Test
    void execute_emptySearch_throwsAssertionError() {
        SearchCommand testSearch = new SearchCommand();
        assertThrows(AniException.class, () -> {
            testSearch.execute(animeData, storageManager, user);
        });
    }

    @Test
    void execute_searchForNonExistingAnime_returnNoResultFound() throws AniException {
        SearchCommand testSearch = new SearchCommand();
        testSearch.setSearchTerm(SEARCH_ANIME_NONEXISTENT);
        testSearch.setSearchType(NAME_SEARCH);
        String result = testSearch.execute(animeData, storageManager, user);
        assertEquals(NO_RESULTS_FOUND, result);
    }

    @Test
    void execute_invalidSearchType_throwsAniException() {
        SearchCommand testSearch = new SearchCommand();
        testSearch.setSearchTerm(SEARCH_ANIME_1);
        testSearch.setSearchType(ABOVE_RANGE);
        assertThrows(AniException.class, () -> {
            testSearch.execute(animeData, storageManager, user);
        });

        testSearch.setSearchType(BELOW_RANGE);
        assertThrows(AniException.class, () -> {
            testSearch.execute(animeData, storageManager, user);
        });
    }
}
