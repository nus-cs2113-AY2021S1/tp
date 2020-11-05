package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.project.ViewProjectCommand;
import seedu.duke.command.task.AddTaskCommand;
import seedu.duke.command.task.ChangeTaskPriorityCommand;
import seedu.duke.command.task.DeleteTaskCommand;
import seedu.duke.command.task.DoneTaskCommand;
import seedu.duke.command.task.PriorityViewCommand;
import seedu.duke.command.task.ViewTaskCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.ui.Ui;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.ADD;
import static seedu.duke.command.CommandSummary.DELETE;
import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.DONE;
import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.PRIORITYVIEW;
import static seedu.duke.command.CommandSummary.TASK_ID;
import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.VIEW;

public class TaskParser implements ExceptionsParser {

    @Override
    public Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                   ProjectManager projectListManager)
            throws DukeException {

        switch (action.toLowerCase()) {
        case ADD:
            if (!parameters.containsKey(TITLE) || !parameters.containsKey(DESCRIPTION)
                    || !parameters.containsKey(PRIORITY)) {
                throw new DukeException("Missing parameters.");
            }
            if (parameters.get(TITLE).isBlank()) {
                throw new DukeException("Please enter a title!");
            }
            if (parameters.get(DESCRIPTION).isBlank()) {
                throw new DukeException("Please enter a description!");
            }
            if (parameters.get(PRIORITY).isBlank()) {
                throw new DukeException("Please enter a priority!");
            } else {
                return new AddTaskCommand(parameters, projectListManager);
            }
        case DELETE:
            assert parameters.get("0") != null : "Invalid Input";
            if (parameters.get("0") == null) {
                throw new DukeException("Missing parameters or dashes were input. "
                        + "Please do not enter dashes for this command.");
            }
            if (parameters.get("0").isBlank() || !ParserManager.isStringIntParsable(parameters.get("0"))) {
                throw new DukeException("Please enter a task number!");
            } else {
                return new DeleteTaskCommand(parameters, projectListManager);
            }
        case DONE:
            assert parameters.get("0") != null : "Invalid Input";
            if (parameters.get("0") == null) {
                throw new DukeException("Missing parameters or dashes were input. "
                        + "Please do not enter dashes for this command.");
            }
            if (parameters.get("0").isBlank() || !ParserManager.isStringIntParsable(parameters.get("0"))) {
                throw new DukeException("Please enter a task number!");
            } else {
                return new DoneTaskCommand(parameters, projectListManager);
            }
        case VIEW:
            return new ViewTaskCommand(parameters, projectListManager);
        case PRIORITY:
            if (!parameters.containsKey(TASK_ID) || !parameters.containsKey(PRIORITY)) {
                throw new DukeException("Missing parameters.");
            }
            if (parameters.get(TASK_ID).isBlank() || !ParserManager.isStringIntParsable(parameters.get(TASK_ID))) {
                throw new DukeException("Task ID entered is invalid!");
            }
            if (parameters.get(PRIORITY).isBlank()) {
                throw new DukeException("Please enter a priority!");
            } else {
                return new ChangeTaskPriorityCommand(parameters, projectListManager);
            }
        case PRIORITYVIEW:
            if (!parameters.isEmpty()) {
                throw new DukeException("Invalid action!");
            } else {
                return new PriorityViewCommand(parameters, projectListManager);
            }
        default:
            throw new DukeException("Invalid action!");
        }
    }
}
