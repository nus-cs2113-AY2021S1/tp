package seedu.duke.Command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Task.Deadline;
import seedu.duke.Task.Event;
import seedu.duke.Task.TaskList;
import seedu.duke.Task.Todo;
import seedu.duke.Ui;

/**
 * Adds a task to the task list, depending on what kind of task (event, deadline, todo) it is.
 */
public class AddCommand extends Command {

    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    public AddCommand(String userInput) {
        super(userInput);
    }

    /**
     * Creates a task in the task list after determining what type of task (event, deadline, todo).
     *
     * @param taskList the task list to add the new task to.
     * @param storage  not required.
     * @throws DukeException if the add command input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        String[] command;

        command = userInput.split(" ", 2);
        String commandType = command[0];
        String taskDescription;
        String taskDate;

        switch (commandType) {
        case TODO:
            try {
                taskDescription = command[1];
                taskList.addTask(new Todo(taskDescription));
            } catch (Exception e) {
                throw new DukeException("todo");
            }
            break;
        case DEADLINE:
            try {
                command = command[1].split("/by");
                taskDescription = command[0];
                taskDate = command[1];
                taskList.addTask(new Deadline(taskDescription, taskDate));
            } catch (Exception e) {
                throw new DukeException("deadline");
            }
            break;
        case EVENT:
            try {
                command = command[1].split("/at");
                taskDescription = command[0];
                taskDate = command[1];
                taskList.addTask(new Event(taskDescription, taskDate));
            } catch (Exception e) {
                throw new DukeException("event");
            }
            break;
        default:
            throw new DukeException("invalid command");
        }

        Ui.printAddTaskMessage(taskList);
    }
}
