package seedu.eduke8.storage;

import org.junit.jupiter.api.Test;

import java.io.File;
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
    public void save_newLogFile_newLogFileCreated() throws IOException {
        LogStorage logStorage = new LogStorage(LOG_PATH);
        logStorage.save();

        String filePath = new File("").getAbsolutePath();
        String[] logPathSplit = LOG_PATH.split("/");
        for (String path: logPathSplit) {
            filePath += File.separator + path;
        }

        File logFile = new File(filePath);
        assertTrue(logFile.exists());

        Scanner logReader = new Scanner(logFile);
        logReader.nextLine(); // Skip first line with datetime info
        String logData = logReader.nextLine();
        logReader.close();

        assertEquals(logData, "INFO: Logging to file started");
    }
}
