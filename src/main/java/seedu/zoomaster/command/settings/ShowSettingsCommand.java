package seedu.zoomaster.command.settings;

import seedu.zoomaster.Ui;
import seedu.zoomaster.Zoomaster;
import seedu.zoomaster.settings.SettingsVariable;
import seedu.zoomaster.settings.UserSettings;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.slot.Timetable;

public class ShowSettingsCommand extends Command {
    public static final String SHOW_KW = "show";

    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        ui.print(generateSettingsListMessage());
    }

    private String generateSettingsListMessage() {
        StringBuilder message = new StringBuilder();
        SettingsVariable[] settingsVariables = Zoomaster.userSettings.getVariables();
        for (int i = 0; i < settingsVariables.length; i++) {
            String lineHeader = String.format(
                    "[%d] %s: ", i, settingsVariables[i].getDescription());
            message.append(lineHeader);

            message.append(settingsVariables[i].getOptionsList());
            message.append("\n");
        }

        return message.toString();
    }
}
