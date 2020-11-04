package seedu.zoomaster.command.settings;

import seedu.zoomaster.Ui;
import seedu.zoomaster.Zoomaster;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.settings.SettingsVariable;
import seedu.zoomaster.slot.Timetable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetSettingsCommand extends Command {
    public static final String SET_KW = "set";

    private int settingsIndex;
    private String newValue;

    public SetSettingsCommand(String command) throws ZoomasterException {
        Pattern pattern = Pattern.compile(SET_KW + "\\s+(?<settingsIndex>[0-9]+)\\s+(?<newValue>\\w+)");
        Matcher matcher = pattern.matcher(command);

        if (!matcher.matches()) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_EDIT_INPUT);
        }

        settingsIndex = Integer.parseInt(matcher.group("settingsIndex"));
        newValue = matcher.group("newValue");
    }

    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        SettingsVariable[] variable = Zoomaster.userSettings.getVariables();
        SettingsVariable settingsVariable;
        try {
            settingsVariable = variable[settingsIndex - 1];
        } catch (IndexOutOfBoundsException e) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_SETTING_NUMBER);
        }
        settingsVariable.setChosenOption(newValue);

        new ShowSettingsCommand().execute(bookmarks, timetable, ui);
        ui.print("Settings updated!");
    }
}
