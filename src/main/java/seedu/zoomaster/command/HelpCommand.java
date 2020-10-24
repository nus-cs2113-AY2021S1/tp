package seedu.zoomaster.command;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.slot.Timetable;

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
