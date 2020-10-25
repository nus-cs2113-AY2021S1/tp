package anichan.anime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import anichan.exception.AniException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnimeDataTest {
    private static AnimeData animeData;
    private static final Integer NEGATIVE_INTEGER = -1;
    private static final Integer NULL_PARAM = null;
    private static final String FIRST_INDEX_ANIME_INFO = "Index: 1" + System.lineSeparator()
            + "Name: Cowboy Bebop" + System.lineSeparator()
            + "Episodes: 26" + System.lineSeparator()
            + "Release Date: 03/Apr/1998" + System.lineSeparator()
            + "Rating: 86" + System.lineSeparator()
            + "Genre: [Action, Adventure, Drama, Sci-Fi]";

    @BeforeAll
    static void setUp() throws AniException {
        animeData = new AnimeData();
    }

    @Test
    void returnAnimeInfo_firstInteger_expectAnimeInfo() {
        assertEquals(animeData.returnAnimeInfo(0), FIRST_INDEX_ANIME_INFO);
    }

    @Test
    void getAnime_negativeInteger_expectException() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            animeData.getAnime(NEGATIVE_INTEGER);
        });
    }

    @Test
    void getAnime_nullInput_expectException() {
        assertThrows(NullPointerException.class, () -> {
            animeData.getAnime(NULL_PARAM);
        });
    }

    @Test
    void getAnimeByID_negativeInteger_expectException() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            animeData.getAnime(NEGATIVE_INTEGER);
        });
    }

    @Test
    void getAnimeByID_nullInput_expectException() {
        assertThrows(NullPointerException.class, () -> {
            animeData.getAnime(NULL_PARAM);
        });
    }
}
