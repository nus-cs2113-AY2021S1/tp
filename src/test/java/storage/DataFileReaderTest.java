package storage;

import cheatsheet.CheatSheetList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    /*
    @Test
    void readFile_name_success() {
        CheatSheetList.clear();
        createSampleFile(sampleTest, fileInput);

        testReader.executeFunction();
        eraseFile(sampleTest);

        String testName = CheatSheetList.getList().get(0).getName();
        CheatSheetList.clear();

        assertEquals(sample, testName);
    }

    @Test
    void readFile_subject_success() {
        CheatSheetList.clear();
        createSampleFile(sampleTest, fileInput);

        testReader.executeFunction();
        eraseFile(sampleTest);

        String testSubject = CheatSheetList.getList().get(0).getSubject();
        CheatSheetList.clear();

        assertEquals("Test", testSubject);
    }

    @Test
    void readFile_details_success() {
        CheatSheetList.clear();
        createSampleFile(sampleTest, fileInput);

        testReader.executeFunction();
        eraseFile(sampleTest);

        String testSubject = CheatSheetList.getList().get(0).getDetails();
        CheatSheetList.clear();

        assertEquals("Test Success!", testSubject);
    }
    */
}
