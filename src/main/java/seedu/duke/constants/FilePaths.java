package seedu.duke.constants;

import java.io.File;

/**
 * Default file paths for save files.
 */
public class FilePaths {
    /**
     * Default path to file for saving settings.
     */
    public static final String DEFAULT_USER_SETTINGS_FILE_PATH = "/data" + File.separator + "userSettings.txt";

    /**
     * Test file path to see where the files save.
     */
    public static final String TEST_FILE_PATH = "testFile.txt";

    /**
     * Path to store user past history.
     */
    public static final String HISTORY_FILE_PATH = "data/history.txt";

    /**
     * The general path to store the statistics of the past quizzes.
     */
    public static final String STATS = "data/Stats/stats";

}
