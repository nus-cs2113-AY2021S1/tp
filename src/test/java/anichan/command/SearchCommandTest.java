package anichan.command;

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
    User user;

    private static final String SEARCH_ANIME_1 = "fate";
    private static final String SEARCH_GENRE_MUSIC = "Music";
    private static final String OUTPUT_ANIME_1 = "[ID:323] Fate/stay night";
    private static final String OUTPUT_ANIME_2 = "[ID:39] Beck: Mongolian Chop Squad";
    private static final String SEARCH_ANIME_NONEXISTENT = "CS2113T The Great Refactoring!";
    private static final String NO_RESULTS_FOUND = "No results found!";
    private static final int NAME_SEARCH = 0;
    private static final int GENRE_SEARCH = 1;

    protected static final int ABOVE_RANGE = 9999;
    protected static final int BELOW_RANGE = -1;

    @BeforeAll
    static void setUp() throws AniException {
        animeData = new AnimeData();
        storageManager = new StorageManager("");
    }


    @Test
    void execute_searchByName_returnActualResult() throws AniException {
        SearchCommand testSearch = new SearchCommand();
        testSearch.setSearchTerm(SEARCH_ANIME_1);
        testSearch.setSearchType(NAME_SEARCH);
        String result = testSearch.execute(animeData, storageManager, user);
        assertEquals(OUTPUT_ANIME_1 + System.lineSeparator(), result);
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
    void execute_searchByGenre_returnActualResult() throws AniException {
        SearchCommand testSearch = new SearchCommand();
        testSearch.setSearchGenre(SEARCH_GENRE_MUSIC);
        testSearch.setSearchType(GENRE_SEARCH);
        String result = testSearch.execute(animeData, storageManager, user);
        result = result.substring(0,34);
        assertEquals(OUTPUT_ANIME_2, result);

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
