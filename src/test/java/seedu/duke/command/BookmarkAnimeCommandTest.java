package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.anime.Anime;
import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BookmarkAnimeCommandTest {
    AnimeData animeData;

    protected static final String INVALID_FIRST_PARAMETERS_ADD_TEST1 = "1 -a 123";
    protected static final String INVALID_FIRST_PARAMETERS_ADD_TEST2 = "a -a 123";
    protected static final String INVALID_FIRST_PARAMETERS_DELETE_TEST1 = "1 -d 123";
    protected static final String INVALID_FIRST_PARAMETERS_DELETE_TEST2 = "a -d 123";
    protected static final String INVALID_FIELD_ADD_TEST1 = "-a   ";
    protected static final String INVALID_FIELD_ADD_TEST2 = "-a beepboopbeep";
    protected static final String INVALID_FIELD_ADD_TEST3 = "-a 123 123";
    protected static final String INVALID_FIELD_DELETE_TEST1 = "-d   ";
    protected static final String INVALID_FIELD_DELETE_TEST2 = "-d beepboopbeep";
    protected static final String INVALID_FIELD_DELETE_TEST3 = "-d 123 123";
    protected static final String INVALID_FIELD_EDIT_TEST1 = "1 -e   ";
    protected static final String INVALID_FIELD_EDIT_TEST2 = "1 -e beepboopbeep";
    protected static final String INVALID_FIELD_EDIT_TEST3 = "1 -e 123 123";
    protected static final String NEGATIVE_BOOKMARKID_DELETE_TEST = "-d -1";
    protected static final String NEGATIVE_ANIMEID_ADD_TEST = "-a -3";
    protected static final String NEGATIVE_BOOKMARKID_EDIT_TEST = "-1 -e 1";

    protected static final String PROPER_INPUT1 = "-a 410";
    protected static final String PROPER_INPUT2 = "-a 430";

    @BeforeEach
    void setUp() throws AniException {
        ArrayList<Anime> testList = new ArrayList<Anime>();
        Anime testAnime1 = new Anime();
        Anime testAnime2 = new Anime();
        testList.add(testAnime1);
        testList.add(testAnime2);
        animeData = new AnimeData(testList);
    }

    @Test
    void execute_invalidFirstParameter_ThrowsAniException() {
        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand testAdd1 = new BookmarkAnimeCommand(INVALID_FIRST_PARAMETERS_ADD_TEST1);
        });

        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand testAdd2 = new BookmarkAnimeCommand(INVALID_FIRST_PARAMETERS_ADD_TEST2);
        });

        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand testDelete1 = new BookmarkAnimeCommand(INVALID_FIRST_PARAMETERS_DELETE_TEST1);
        });

        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand testDelete2 = new BookmarkAnimeCommand(INVALID_FIRST_PARAMETERS_DELETE_TEST2);
        });
    }

    @Test
    void execute_invalidFieldForAdd_ThrowsAniException() {
        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand test1 = new BookmarkAnimeCommand(INVALID_FIELD_ADD_TEST1);
        });

        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand test2 = new BookmarkAnimeCommand(INVALID_FIELD_ADD_TEST2);
        });

        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand test3 = new BookmarkAnimeCommand(INVALID_FIELD_ADD_TEST3);
        });
    }

    @Test
    void execute_invalidFieldForDelete_ThrowsAniException() {
        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand test1 = new BookmarkAnimeCommand(INVALID_FIELD_DELETE_TEST1);
        });

        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand test2 = new BookmarkAnimeCommand(INVALID_FIELD_DELETE_TEST2);
        });

        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand test3 = new BookmarkAnimeCommand(INVALID_FIELD_DELETE_TEST3);
        });
    }

    @Test
    void execute_invalidFieldForEdit_ThrowsAniException() {
        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand test1 = new BookmarkAnimeCommand(INVALID_FIELD_EDIT_TEST1);
        });

        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand test2 = new BookmarkAnimeCommand(INVALID_FIELD_EDIT_TEST2);
        });

        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand test3 = new BookmarkAnimeCommand(INVALID_FIELD_EDIT_TEST3);
        });
    }

    @Test
    void execute_negativeBookmarkIdForDelete_ThrowsAniException() throws AniException {
        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand test1 = new BookmarkAnimeCommand(NEGATIVE_BOOKMARKID_DELETE_TEST);
        });
    }

    @Test
    void execute_negativeBookmarkIdForEdit_ThrowsAniException() throws AniException {
        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand test1 = new BookmarkAnimeCommand(NEGATIVE_BOOKMARKID_EDIT_TEST);
        });
    }

    @Test
    void execute_negativeAnimeIdForAdd_ThrowsAniException() throws AniException {
        assertThrows(AniException.class, () -> {
            BookmarkAnimeCommand test1 = new BookmarkAnimeCommand(NEGATIVE_ANIMEID_ADD_TEST);
        });
    }


}
