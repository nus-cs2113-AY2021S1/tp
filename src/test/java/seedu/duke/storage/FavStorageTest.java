package seedu.duke.storage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;
import seedu.duke.model.favorite.Fav;
import seedu.duke.model.favorite.FavList;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FavStorageTest {
    private static String CORRUPTED_DIR = "src/test/StorageTestCases/FavStorageTestTXT/CorruptedFavStorage.txt";
    private static String VALID_DIR = "src/test/StorageTestCases/FavStorageTestTXT/ValidFavStorage.txt";
    private static FavStorage corruptedFile;
    private static FavStorage validFile;

    @BeforeAll
    public static void makeFile() {
        corruptedFile = new FavStorage(CORRUPTED_DIR);
        validFile = new FavStorage(VALID_DIR);
    }

    @Test
    void corruptedFileReadFile_expectFavListSize3() throws FileNotFoundException, CustomException {
        new FavList();
        corruptedFile.readFile();
        assertEquals(3, FavList.getSize());
        assertEquals("test1", FavList.getFav(0).getDesc());
        assertEquals("test2", FavList.getFav(1).getDesc());
        assertEquals("test3", FavList.getFav(2).getDesc());
    }

    @Test
    void validFileReadFile_expectFavListSize4() throws FileNotFoundException, CustomException {
        new FavList();
        validFile.readFile();
        assertEquals(4, FavList.getSize());
        assertEquals("test1", FavList.getFav(0).getDesc());
        assertEquals("test2", FavList.getFav(1).getDesc());
        assertEquals("test3", FavList.getFav(2).getDesc());
        assertEquals("test4", FavList.getFav(3).getDesc());
    }

    @Test
    void validFileupdateFile_expectFavListSize5() throws IOException, CustomException {
        Fav fav = new Fav("/dineinfo business","test5");
        new FavList();
        validFile.readFile();
        FavList.addFav(fav);
        validFile.updateFile();
        new FavList();
        validFile.readFile();
        assertEquals(5, FavList.getSize());
        assertEquals("test1", FavList.getFav(0).getDesc());
        assertEquals("test2", FavList.getFav(1).getDesc());
        assertEquals("test3", FavList.getFav(2).getDesc());
        assertEquals("test4", FavList.getFav(3).getDesc());
        assertEquals("test5", FavList.getFav(4).getDesc());
        FavList.deleteFav(5);
        validFile.updateFile();
    }

}
