package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;
import seedu.duke.model.bus.BusStops;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FreqStorageTest {
    @Test
    public void loadFreqList_validData_success() throws CustomException {
        FreqStorage freqFile = new FreqStorage("src/test/StorageTestCases/ValidFreqList");
        freqFile.readFile();
        assertEquals("PGP", BusStops.mostSearchedBusStop().getName());
    }

    @Test
    public void loadFreqList_missingEntry_customExceptionThrown() throws CustomException, IOException {
        FreqStorage freqFile = new FreqStorage("src/test/StorageTestCases/InvalidFreqList_MissingEntry");
        assertThrows(CustomException.class, freqFile::readFile);
        resetTestCase_missingEntry();
    }

    @Test
    public void loadFreqList_corruptedData_customExceptionThrown() throws CustomException, IOException {
        FreqStorage freqFile = new FreqStorage("src/test/StorageTestCases/InvalidFreqList_CorruptedData");
        assertThrows(CustomException.class, freqFile::readFile);
        resetTestCase_corruptedData();
    }

    public void resetTestCase_missingEntry() throws IOException {
        File savedFile = new File("src/test/StorageTestCases/InvalidFreqList_missingEntry");
        FileWriter writer = new FileWriter(savedFile);
        for (int i = 0; i < 25; i++) {
            writer.write("0" + System.lineSeparator());
        }
        writer.close();
    }

    public void resetTestCase_corruptedData() throws IOException {
        File savedFile = new File("src/test/StorageTestCases/InvalidFreqList_CorruptedData");
        FileWriter writer = new FileWriter(savedFile);
        writer.write("asdasdcxzczxc");
        writer.close();
    }
}