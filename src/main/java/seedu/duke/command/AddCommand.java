package seedu.duke.command;

import seedu.duke.DateParser;
import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.TaskList;
import seedu.duke.task.Todo;
import seedu.duke.task.Lecture;
import seedu.duke.task.Tutorial;
import seedu.duke.task.Lab;

import java.time.LocalDate;


/**
 * Adds a task to the task list, depending on what kind of task (event, deadline, todo) it is.
 */
public class AddCommand extends Command {

    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String LECTURE = "lecture";
    public static final String TUTORIAL = "tutorial";
    public static final String LAB = "lab";

    public AddCommand(String userInput) {
        super(userInput);
    }

    /**
     * Creates a task in the task list after determining what type of task (event, deadline, todo).
     * Saves the updated task list in the storage after the new task is added.
     *
     * @param taskList the task list to add the new task to.
     * @param storage  the storage to be saved to.
     * @throws DukeException if the add command input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        String[] command;

        command = userInput.split(" ", 2);
        String commandType = command[0];
        String taskDescription;
        LocalDate taskDate;

        switch (commandType) {
        case TODO:
            try {
                taskDescription = command[1].trim();

                if (taskDescription.isEmpty()) {
                    throw new DukeException("todo");
                } else {
                    taskList.addTask(new Todo(taskDescription));
                }
            } catch (Exception e) {
                throw new DukeException("todo");
            }
            break;
        case DEADLINE:
            try {
                command = command[1].split("/by");
                taskDescription = command[0].trim();
                taskDate = DateParser.inputDateProcessor(command[1].trim());

                if (taskDescription.isEmpty()) {
                    throw new DukeException("deadline");
                } else {
                    taskList.addTask(new Deadline(taskDescription, taskDate));
                }
            } catch (Exception e) {
                throw new DukeException("deadline");
            }
            break;
        case EVENT:
            try {
                command = command[1].split("/at");
                taskDescription = command[0].trim();
                taskDate = DateParser.inputDateProcessor(command[1].trim());

                if (taskDescription.isEmpty()) {
                    throw new DukeException("event");
                } else {
                    taskList.addTask(new Event(taskDescription, taskDate));
                }
            } catch (Exception e) {
                throw new DukeException("event");
            }
            break;
        case LECTURE:
            try {
                command = command[1].split("/");
                taskDescription = command[0].trim();

                command = command[1].trim().split(" ");

                if (taskDescription.isEmpty()) {
                    throw new DukeException("lecture");
                } else {
                    taskList.addTask(new Lecture(taskDescription, command[0], command[1]));
                }
            } catch (Exception e) {
                throw new DukeException("lecture");
            }
            break;
        case TUTORIAL:
            try {
                command = command[1].split("/");
                taskDescription = command[0].trim();

                command = command[1].trim().split(" ");

                if (taskDescription.isEmpty()) {
                    throw new DukeException("tutorial");
                } else {
                    taskList.addTask(new Tutorial(taskDescription, command[0], command[1]));
                }
            } catch (Exception e) {
                throw new DukeException("tutorial");
            }
            break;
        case LAB:
            try {
                command = command[1].split("/");
                taskDescription = command[0].trim();

                command = command[1].trim().split(" ");

                if (taskDescription.isEmpty()) {
                    throw new DukeException("lab");
                } else {
                    taskList.addTask(new Lab(taskDescription, command[0], command[1]));
                }
            } catch (Exception e) {
                throw new DukeException("lab");
            }
            break;
        default:
            throw new DukeException("invalid command");
        }

        Ui.printAddTaskMessage(taskList);
        storage.saveData(taskList);
    }
}
