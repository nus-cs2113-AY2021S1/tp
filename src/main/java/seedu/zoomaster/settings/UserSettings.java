package seedu.zoomaster.settings;

import seedu.zoomaster.command.Mode;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Day;

public class UserSettings {
    public static final String MODE_MAINMENU = "mainmenu";
    public static final String MODE_TIMETABLE = "timetable";
    public static final String MODE_BOOKMARK = "bookmark";

    public static final String DEFAULT_MODE_FIELD = "mode";
    private static final String DEFAULT_MODE_DESC = "Default mode at start up";
    private static final String[] DEFAULT_MODE_OPTIONS = {MODE_MAINMENU, MODE_TIMETABLE, MODE_BOOKMARK};

    public static final String FIRST_DAY_FIELD = "startday";
    private static final String FIRST_DAY_DESC = "First day of the week";
    private static final Day[] FIRST_DAY_OPTIONS = {Day.MON, Day.SUN};

    public static final String AUTO_SAVE_FIELD = "autosave";
    private static final String AUTO_SAVE_DESC = "Autosave after each command (only saves on exit if OFF)";
    private static final String[] AUTO_SAVE_OPTIONS = {SettingsVariable.ON, SettingsVariable.OFF};

    private SettingsVariable[] variables = {
            new SettingsVariable<String>(DEFAULT_MODE_FIELD, DEFAULT_MODE_DESC, DEFAULT_MODE_OPTIONS),
            new SettingsVariable<Day>(FIRST_DAY_FIELD, FIRST_DAY_DESC, FIRST_DAY_OPTIONS),
            new SettingsVariable<String>(AUTO_SAVE_FIELD, AUTO_SAVE_DESC, AUTO_SAVE_OPTIONS),
    };

    public SettingsVariable[] getVariables() {
        return variables;
    }

    public SettingsVariable getSettingsVariable(String fieldName) throws ZoomasterException {
        for (SettingsVariable variable: variables) {
            if (variable.getFieldName().equals(fieldName)) {
                return variable;
            }
        }

        throw new ZoomasterException(ZoomasterExceptionType.INVALID_SETTING_FIELD);
    }
}
