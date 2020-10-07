package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;

public class HelpCommand extends Command {

    public HelpCommand(String userInput) {
        super(userInput);
    }

    /**
     * Lists all available commands to the user.
     *
     * @param taskList not required.
     * @param storage  not required.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        Ui.printHelpCommand();
    }
}
