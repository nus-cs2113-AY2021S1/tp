package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.anime.Anime;
import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.parser.AddWorkspaceParser;
import seedu.duke.storage.StorageManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class AddWorkspaceCommandTest {
    AnimeData animeData;
    User user;
    StorageManager storageManager;

    @BeforeEach
    void setUp() throws AniException {
        ArrayList<Anime> testList = new ArrayList<Anime>();
        Anime testAnime1 = new Anime();
        testList.add(testAnime1);
        animeData = new AnimeData(testList);
        storageManager = new StorageManager("test");
        user = new User("Tom", "Male");
    }

    @Test
    void execute_validName_ThrowsAniException() throws AniException {
        AddWorkspaceParser testParse = new AddWorkspaceParser();

        AddWorkspaceCommand testAddWorkspace = testParse.parse("-n Crunchy rail 12345 [];'%@&");
        assertDoesNotThrow(() -> testAddWorkspace.execute(animeData, storageManager, user));
    }
}
