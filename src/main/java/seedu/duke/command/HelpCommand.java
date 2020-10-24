package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.slot.Timetable;

public class HelpCommand extends Command {
    public static final String HELP_KW = "help";

    /**
     * Constructs a new HelpCommand instance and sets isExitCommand to true.
     */
    public HelpCommand() {

    }

    /**
     * Prints the commands available.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) {
        ui.printHelpMessage();
    }
}
