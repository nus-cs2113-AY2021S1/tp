package storage;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataFileWriterTest extends DataFileTest {

    String fileInput = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"
            + System.lineSeparator()
            + "<main>"
            + System.lineSeparator()
            + "    <favourite>No</favourite>"
            + System.lineSeparator()
            + "    <subject>Test</subject>"
            + System.lineSeparator()
            + "    <contents>Test Success!</contents>"
            + System.lineSeparator()
            + "</main>"
            + System.lineSeparator();

    /*
    @Test
    void writeFileExists_sampleCheatsheet_success() {
        CheatSheetList.clear();

        CheatSheet testCheatSheet = new CheatSheet(sample,
                "Test",
                "Test Success!");
        CheatSheetList.add(testCheatSheet);

        testWriter.executeFunction();
        boolean isSampleAdded = sampleTest.toFile().exists();
        if (isSampleAdded) {
            eraseFile(sampleTest);
        }

        CheatSheetList.clear();
        assertTrue(isSampleAdded);
    }

    @Test
    void writeFileContents_sampleCheatsheet_success() {
        CheatSheetList.clear();

        CheatSheet testCheatSheet = new CheatSheet(sample,
                "Test",
                "Test Success!");
        CheatSheetList.add(testCheatSheet);

        testWriter.executeFunction();

        String writtenFile = empty;
        try {
            writtenFile = Files.readString(sampleTest);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sampleTest.toFile().exists()) {
                eraseFile(sampleTest);
            }
            CheatSheetList.clear();
            assertEquals(fileInput, writtenFile);
        }
    }

    @Test
    public void writeDataFiles_emptyCheatSheetList_success() {
        createDataDir();
        CheatSheetList.clear();
        testWriter.executeFunction();

        String[] userDirectoryFiles = dataDir.toFile().list();
        eraseFile(dataDir);

        int directoryFiles = userDirectoryFiles != null ? userDirectoryFiles.length : 0;
        assertEquals(0, directoryFiles);
    }
    */
}
