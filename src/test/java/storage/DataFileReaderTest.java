package storage;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataFileReaderTest extends DataFileTest {

    String fileInput = "<main>"
            + System.lineSeparator()
            + "<favourite>Yes</favourite>"
            + System.lineSeparator()
            + "<subject>Test</subject>"
            + System.lineSeparator()
            + "<contents>Test Success!</contents>"
            + System.lineSeparator()
            + "</main>";
    String invalidFileInput  = "garble garble garble ";
    Path sampleTest4 = Paths.get(userDir, data, test, "sample4.xml");
    String sample4 = "sample4";

    @Test
    void readFile_name_success() {
        final boolean isDataDirPresent = checkDataDirectoryExistence();
        testCheatSheetList.clear();

        createDirectory(sampleTestDir);
        createSampleFile(sampleTest4, fileInput);

        testReader.executeFunction();
        eraseFile(sampleTest4);
        eraseFile(sampleTestDir);
        restoreDataDir(isDataDirPresent);

        boolean isCheatSheetListEmpty = testCheatSheetList.getSize() == 0;
        String testName = !isCheatSheetListEmpty
                ? testCheatSheetList.getList().get(0).getName()
                : empty;
        testCheatSheetList.clear();

        assertEquals(sample4, testName);
    }

    @Test
    void readFile_subject_success() {
        final boolean isDataDirPresent = checkDataDirectoryExistence();

        testCheatSheetList.clear();
        createDirectory(sampleTestDir);
        createSampleFile(sampleTest4, fileInput);

        testReader.executeFunction();
        eraseFile(sampleTest4);
        eraseFile(sampleTestDir);
        restoreDataDir(isDataDirPresent);

        boolean isCheatSheetListEmpty = testCheatSheetList.getSize() == 0;
        String testSubject = !isCheatSheetListEmpty
                ? testCheatSheetList.getList().get(0).getSubject() :
                empty;
        testCheatSheetList.clear();
        assertEquals("Test", testSubject);
    }

    @Test
    void readFile_details_success() {
        final boolean isDataDirPresent = checkDataDirectoryExistence();

        testCheatSheetList.clear();
        createDirectory(sampleTestDir);
        createSampleFile(sampleTest4, fileInput);

        testReader.executeFunction();
        eraseFile(sampleTest4);
        eraseFile(sampleTestDir);
        restoreDataDir(isDataDirPresent);

        boolean isCheatSheetListEmpty = testCheatSheetList.getSize() == 0;
        String testSubject = !isCheatSheetListEmpty
                ? testCheatSheetList.getList().get(0).getDetails() :
                empty;
        testCheatSheetList.clear();
        assertEquals("Test Success!", testSubject);
    }

    @Test
    void readFile_favourite_success() {
        final boolean isDataDirPresent = checkDataDirectoryExistence();

        testCheatSheetList.clear();
        createDirectory(sampleTestDir);
        createSampleFile(sampleTest4, fileInput);

        testReader.executeFunction();
        eraseFile(sampleTest4);
        eraseFile(sampleTestDir);
        restoreDataDir(isDataDirPresent);

        boolean isCheatSheetListEmpty = testCheatSheetList.getSize() == 0;
        boolean testFavourite = !isCheatSheetListEmpty && testCheatSheetList.getList().get(0).getIsFavourite();
        testCheatSheetList.clear();
        assertTrue(testFavourite);
    }

    @Test
    void readFile_invalidFile_notAdded() {
        final boolean isDataDirPresent = checkDataDirectoryExistence();

        testCheatSheetList.clear();

        createDirectory(sampleTestDir);
        createSampleFile(sampleTest4, invalidFileInput);

        testReader.executeFunction();
        eraseFile(sampleTest4);
        eraseFile(sampleTestDir);

        restoreDataDir(isDataDirPresent);

        int finalListSize = testCheatSheetList.getSize();
        assertEquals(0, finalListSize);
    }

    @Test
    void readFile_jarFile_success() {
        final boolean isDataDirPresent = checkDataDirectoryExistence();
        testCheatSheetList.clear();

        testReader.jarDirectory = sampleJarFile;
        testReader.extractPreloadedCheatSheets();
        testReader.executeFunction();
        final int finalListSize = testCheatSheetList.getSize();

        eraseFile(Paths.get(userDir, data, "preloaded", "C", "string.xml"));
        eraseFile(Paths.get(userDir, data, "preloaded", "C"));
        eraseFile(Paths.get(userDir, data, "preloaded"));

        restoreDataDir(isDataDirPresent);
        testCheatSheetList.clear();

        assertEquals(1, finalListSize);
    }
    
}