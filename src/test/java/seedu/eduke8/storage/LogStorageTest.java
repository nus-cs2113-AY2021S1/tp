package seedu.eduke8.storage;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogStorageTest {
    private static final LocalDateTime DATE_TIME_NOW = LocalDateTime.now();
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    private static final String LOG_PATH = "data/logs/LOG_TEST_" + DATE_TIME_NOW.format(DATE_TIME_FORMATTER) + ".log";


    @Test
    void save_newLogFile_newLogFileCreated() throws IOException {
        LogStorage logStorage = new LogStorage(LOG_PATH);

        File logFile = logStorage.save();

        assertTrue(logFile.exists());

        String logData = getSecondLineOfLogs(logFile);

        assertEquals(logData, "INFO: Logging to file started");
    }

    String getSecondLineOfLogs(File logFile) throws FileNotFoundException {
        Scanner logReader = new Scanner(logFile);
        logReader.nextLine(); // Skip first line with datetime info
        String logData = logReader.nextLine();
        logReader.close();
        return logData;
    }
}
