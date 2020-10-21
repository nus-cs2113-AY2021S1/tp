package anichan.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import anichan.anime.Anime;
import anichan.anime.AnimeData;
import anichan.exception.AniException;
import anichan.human.User;
import anichan.parser.AddWorkspaceParser;
import anichan.storage.StorageManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class AddWorkspaceCommandTest {
    AnimeData animeData;
    User user;
    StorageManager storageManager;

    @BeforeEach
    void setUp() throws AniException {
        ArrayList<Anime> testList = new ArrayList<>();
        Anime testAnime1 = new Anime();
        testList.add(testAnime1);
        animeData = new AnimeData(testList);
        storageManager = new StorageManager("data");
        user = new User("Tom", "Male");
    }

    @Test
    void execute_validName_ThrowsAniException() throws AniException {
        AddWorkspaceParser testParse = new AddWorkspaceParser();

        AddWorkspaceCommand testAddWorkspace = testParse.parse("-n Crunchy rail 12345");
        Assertions.assertDoesNotThrow(() -> testAddWorkspace.execute(animeData, storageManager, user));
    }
}
