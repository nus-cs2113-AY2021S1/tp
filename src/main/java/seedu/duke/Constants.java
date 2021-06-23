package seedu.duke;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents class that contains all the constants.
 */
public class Constants {
    public static final String COMMAND_WORD_DIET = "diet";
    public static final String COMMAND_WORD_PROFILE = "profile";
    public static final String COMMAND_WORD_WORKOUT = "workout";

    public static final String COMMAND_WORD_ADD = "add";
    public static final String COMMAND_WORD_NEW = "new";
    public static final String COMMAND_WORD_DELETE = "delete";
    public static final String COMMAND_WORD_EDIT = "edit";
    public static final String COMMAND_WORD_END = "end";
    public static final String COMMAND_WORD_HELP = "help";
    public static final String COMMAND_WORD_VIEW = "view";
    public static final String COMMAND_WORD_CLEAR = "clear";
    public static final String COMMAND_WORD_SEARCH = "search";
    public static final String COMMAND_WORD_LIST = "list";
    public static final String COMMAND_WORD_WRONG = "wrong";

    public static final String PROJECT_ROOT = System.getProperty("user.dir");
    public static final String DATA_FOLDER = "saves";

    public static final Path PATH_TO_PROFILE_FILE = Paths.get(PROJECT_ROOT, DATA_FOLDER, "profile", "profile.json");
    public static final Path PATH_TO_PROFILE_FOLDER = Paths.get(PROJECT_ROOT, DATA_FOLDER, "profile");

    public static final String PATH_TO_WORKOUT_SESSION_HISTORY = Paths.get(PROJECT_ROOT, DATA_FOLDER, "workout",
            "history.json").toString();
    public static final String PATH_TO_WORKOUT_SESSION_FOLDER = Paths.get(PROJECT_ROOT, DATA_FOLDER, "workout",
            "workoutSession").toString();

    public static final String PATH_TO_DIET_FOLDER = "saves/diet/";

    public static final String PATH_TO_LOG_FILE = Paths.get(PROJECT_ROOT, "logs", "SchwarzeneggerLogs.log").toString();
}
