package seedu.zoomaster.command;

import seedu.zoomaster.Ui;
import seedu.zoomaster.Parser;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Timetable;

public class ChangeModeCommand extends Command {
    public static final String MODE_KW = "mode";
    public int setToMode;

    /**
     * Constructs a new ChangeModeCommand instance.
     */
    public ChangeModeCommand(String command) {
        setToMode = getModeFromCommand(command);
    }


    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui)
            throws ZoomasterException {
        if (setToMode == 1) {
            Parser.programMode = 1;
            ui.print("Changing to bookmark mode" + System.lineSeparator());
        } else if (setToMode == 2) {
            Parser.programMode = 2;
            ui.print("Changing to timetable mode" + System.lineSeparator());
        } else if (setToMode == 3) {
            Parser.programMode = 3;
            ui.print("Changing to planner mode" + System.lineSeparator());
        } else {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_MODE);
        }
    }

    public int getModeFromCommand(String input) {
        int outputData;

        assert input.startsWith(ChangeModeCommand.MODE_KW) : "input should always start with \"mode\"";
        if (input.length() <= 5) {
            return 0;
        }

        String modeData = input.substring(5);

        if (modeData.compareTo("bookmark") == 0) {
            outputData = 1;
        } else if (modeData.compareTo("timetable") == 0) {
            outputData = 2;
        } else if (modeData.compareTo("planner") == 0) {
            outputData = 3;
        } else {
            outputData = 0;
        }

        return outputData;
    }
}
