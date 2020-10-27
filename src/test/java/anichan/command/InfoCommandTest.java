package anichan.command;

import anichan.anime.Anime;
import anichan.anime.AnimeData;
import anichan.exception.AniException;
import anichan.human.User;
import anichan.parser.InfoParser;
import anichan.storage.StorageManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InfoCommandTest {
    AnimeData animeData;
    StorageManager storageManager;
    User user;

    protected static final String ZERO_ANIME_INDEX = "-a 0";
    protected static final String LARGE_ANIME_INDEX = "-a 3";
    protected static final String VALID_ANIME_INDEX = "-a 1";

    @BeforeEach
    void setUp() {
        ArrayList<Anime> testList = new ArrayList<>();
        String[] testAnime1ReleaseDate = {"2000", "12", "12"};
        String[] testAnime1Genre = {"Action", "Drama"};
        String[] testAnime2ReleaseDate = {"2020", "1", "1"};
        String[] testAnime2Genre = {"Thriller"};

        Anime testAnime1 = new Anime("testAnime1", testAnime1ReleaseDate, 80,
                testAnime1Genre, 20, 20);
        Anime testAnime2 = new Anime("testAnime2", testAnime2ReleaseDate, 65,
                testAnime2Genre, 45, 60);

        testList.add(testAnime1);
        testList.add(testAnime2);

        animeData = new AnimeData(testList);
    }

    @Test
    void execute_zeroInteger_throwsAniException() throws AniException {
        InfoParser testParser = new InfoParser();
        InfoCommand testInfo = testParser.parse(ZERO_ANIME_INDEX);
        assertThrows(AniException.class, () -> {
            testInfo.execute(animeData, storageManager, user);
        });
    }

    @Test
    void execute_indexLargerThanDataSize_throwsAniException() throws AniException {
        InfoParser testParser = new InfoParser();
        InfoCommand testInfo = testParser.parse(LARGE_ANIME_INDEX);
        assertThrows(AniException.class, () -> {
            testInfo.execute(animeData, storageManager, user);
        });
    }

    @Test
    void execute_correctAnimeIndex_returnAnimeInfo() throws AniException {
        StringBuilder expectedOutputBuild = new StringBuilder();
        expectedOutputBuild.append("Here is the information for the anime:").append(System.lineSeparator());
        expectedOutputBuild.append("Index: 1").append(System.lineSeparator());
        expectedOutputBuild.append("Name: testAnime1").append(System.lineSeparator());
        expectedOutputBuild.append("Episodes: 20").append(System.lineSeparator());
        expectedOutputBuild.append("Release Date: 12/Dec/2000").append(System.lineSeparator());
        expectedOutputBuild.append("Rating: 80").append(System.lineSeparator());
        expectedOutputBuild.append("Genre: [Action, Drama]");
        String expectedOutput = expectedOutputBuild.toString();

        InfoParser testParser = new InfoParser();
        InfoCommand testInfo = testParser.parse(VALID_ANIME_INDEX);
        
        String actualOutput = testInfo.execute(animeData, storageManager, user);
        assertEquals(expectedOutput, actualOutput);
    }
}
