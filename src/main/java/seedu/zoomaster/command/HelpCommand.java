package seedu.zoomaster.command;

import seedu.zoomaster.Parser;
import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.bookmark.AddBookmarkCommand;
import seedu.zoomaster.command.bookmark.DeleteBookmarkCommand;
import seedu.zoomaster.command.bookmark.FindBookmarkCommand;
import seedu.zoomaster.command.bookmark.LaunchBookmarkCommand;
import seedu.zoomaster.command.bookmark.ShowBookmarkCommand;
import seedu.zoomaster.command.planner.LoadPlannerCommand;
import seedu.zoomaster.command.planner.SavePlannerCommand;
import seedu.zoomaster.command.timetable.AddSlotCommand;
import seedu.zoomaster.command.timetable.DeleteSlotCommand;
import seedu.zoomaster.command.timetable.ShowTimetableCommand;
import seedu.zoomaster.command.timetable.EditSlotCommand;
import seedu.zoomaster.command.timetable.LaunchModuleAndSlotBookmark;
import seedu.zoomaster.command.planner.AddMeetingCommand;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Timetable;

public class HelpCommand extends Command {
    public static final String HELP_KW = "help";
    public String details;

    /**
     * Constructs a new HelpCommand instance and sets isExitCommand to true.
     */
    public HelpCommand(String command) throws ZoomasterException {
        assert command.startsWith(HELP_KW) : "input should always start with \"help\"";
        details = command.substring(HELP_KW.length()).trim();
        if (!details.isEmpty()) {
            if (!isValidCommand(details)) {
                throw new ZoomasterException(ZoomasterExceptionType.UNKNOWN_HELP_COMMAND, details);
            }
        }
    }

    /**
     * Prints the commands available.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) {
        if (details.isEmpty()) {   // Simple "help" command
            ui.printHelpMessage();
        } else {
            ui.printHelpMessage(details);
        }
    }


    private boolean isValidCommand(String details) {
        if (details.compareToIgnoreCase(ClearCommand.CLEAR_KW) == 0
            || details.compareToIgnoreCase(ChangeModeCommand.MODE_KW) == 0
            || details.compareToIgnoreCase(ExitCommand.EXIT_KW) == 0
            || details.compareToIgnoreCase(LaunchNowCommand.LAUNCH_NOW_KW) == 0) {
            return true;
        }
        if (Parser.getProgramMode() == 1) {
            if (details.compareToIgnoreCase(AddBookmarkCommand.ADD_KW) == 0
                    || details.compareToIgnoreCase(DeleteBookmarkCommand.DEL_KW) == 0
                    || details.compareToIgnoreCase(ShowBookmarkCommand.SHOW_KW) == 0
                    || details.compareToIgnoreCase(FindBookmarkCommand.FIND_KW) == 0
                    || details.compareToIgnoreCase(LaunchBookmarkCommand.LAUNCH_KW) == 0) {
                return true;
            }
        } else if (Parser.getProgramMode() == 2) {
            if (details.compareToIgnoreCase(AddSlotCommand.ADD_KW) == 0
                    || details.compareToIgnoreCase(DeleteSlotCommand.DEL_KW) == 0
                    || details.compareToIgnoreCase(ShowTimetableCommand.SHOW_KW) == 0
                    || details.compareToIgnoreCase(EditSlotCommand.EDIT_KW) == 0
                    || details.compareToIgnoreCase(LaunchModuleAndSlotBookmark.LAUNCH_KW) == 0) {
                return true;
            }
        } else if (Parser.getProgramMode() == 3) {
            if (details.compareToIgnoreCase(AddMeetingCommand.ADD_KW) == 0
                    || details.compareToIgnoreCase(LoadPlannerCommand.LOAD_KW) == 0
                    || details.compareToIgnoreCase(ShowTimetableCommand.SHOW_KW) == 0
                    || details.compareToIgnoreCase(SavePlannerCommand.SAVE_KW) == 0) {
                return true;
            }
        }
        return false;
    }
}
