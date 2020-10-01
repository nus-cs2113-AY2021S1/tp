package seedu.duke.command;


import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.Ui;

/**
 * Searches the task list for tasks that contains the keyword specified by the user and prints them.
 */
public class FindCommand extends Command {
    private String keyword;


    public FindCommand(String userInput) {
        super(userInput);
    }

    /**
     * Searches the task list for tasks that contains the keyword specified by the user and prints them.
     *
     * @param taskList the task list to search from.
     * @param storage not required.
     * @throws DukeException if the keyword is not found.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {

        keyword = userInput.replace("find", "").trim();
        try {
            Ui.printFindTaskMessage(taskList, keyword);
        } catch (Exception e) {
            throw new DukeException("keyword not found");
        }
    }

}
