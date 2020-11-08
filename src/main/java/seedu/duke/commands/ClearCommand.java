package seedu.duke.commands;

import seedu.duke.DukeException;
import seedu.duke.common.Messages;
import seedu.duke.model.Model;
import seedu.duke.model.ListType;
import seedu.duke.model.itemlist.BookList;
import seedu.duke.model.itemlist.LinkList;
import seedu.duke.model.itemlist.ModuleList;
import seedu.duke.model.itemlist.TaskList;
import seedu.duke.ui.Ui;

// @@author MuhammadHoze

/**
 * Clears all tasks in the task list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all tasks in the task list.\n"
            + "     Example: " + COMMAND_WORD;
    private String description = "";

    public ClearCommand(String command) {
        this.description = command;
    }

    public void execute(Model model) throws DukeException {
        if (description.isEmpty() | !description.equals("all")) {
            throw new DukeException(Messages.EXCEPTION_INVALID_CLEAR);
        }

        model.clear();
    }
}
