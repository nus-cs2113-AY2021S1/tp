package seedu.zoomaster.settings;

import seedu.zoomaster.slot.Day;

public class UserSettings {
    public static final String DEFAULT_MODE_KW = "mode";
    private static final String DEFAULT_MODE_DESC = "Default mode at start up";
    private static final Integer[] DEFAULT_MODE_OPTIONS = {0, 1, 2, 3};

    public static final String FIRST_DAY_KW = "startday";
    private static final String FIRST_DAY_DESC = "First day of the week";
    private static final Day[] FIRST_DAY_OPTIONS = {Day.MON, Day.SUN};

    public static final String AUTO_SAVE_KW = "autosave";
    private static final String AUTO_SAVE_DESC = "Autosave after each command (only saves on exit if OFF)";
    private static final String[] AUTO_SAVE_OPTIONS = {SettingsVariable.ON, SettingsVariable.OFF};

    private SettingsVariable[] variables = {
            new SettingsVariable<Integer>(DEFAULT_MODE_KW, DEFAULT_MODE_KW, DEFAULT_MODE_DESC, DEFAULT_MODE_OPTIONS),
            new SettingsVariable<Day>(FIRST_DAY_KW, FIRST_DAY_KW, FIRST_DAY_DESC, FIRST_DAY_OPTIONS),
            new SettingsVariable<String>(AUTO_SAVE_KW, AUTO_SAVE_KW, AUTO_SAVE_DESC, AUTO_SAVE_OPTIONS),
    };

    public SettingsVariable[] getVariables() {
        return variables;
    }
}
