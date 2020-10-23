package seedu.duke.parser;

import seedu.duke.command.task.AddTaskCommand;
import seedu.duke.command.task.ChangeTaskPriorityCommand;
import seedu.duke.command.task.DeleteTaskCommand;
import seedu.duke.command.task.DoneTaskCommand;
import seedu.duke.command.task.ViewTaskCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.TASK_ID;
import static seedu.duke.command.CommandSummary.ADD;
import static seedu.duke.command.CommandSummary.DELETE;
import static seedu.duke.command.CommandSummary.DONE;
import static seedu.duke.command.CommandSummary.VIEW;

public class TaskParser implements ExceptionsParser {

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                ProjectList projectListManager)
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
                new AddTaskCommand(parameters, projectListManager).execute();
            }
            break;
        case DELETE:
            assert parameters.get("0") != null : "Invalid Input";
            if (parameters.get("0") == null) {
                Ui.showError("Please do not enter dashes.");
                return;
            }
            if (parameters.get("0").isBlank() || !Parser.isStringContainsNumber(parameters.get("0"))) {
                throw new DukeException("please give a task number!");
            } else {
                new DeleteTaskCommand(parameters, projectListManager).execute();
            }
            break;
        case DONE:
            assert parameters.get("0") != null : "Invalid Input";
            if (parameters.get("0") == null) {
                Ui.showError("Please do not enter dashes.");
                return;
            }
            if (parameters.get("0").isBlank() || !Parser.isStringContainsNumber(parameters.get("0"))) {
                throw new DukeException("please give a task number!");
            } else {
                new DoneTaskCommand(parameters, projectListManager).execute();
            }
            break;
        case VIEW:
            new ViewTaskCommand(parameters, projectListManager).execute();
            break;
        case PRIORITY:
            if (!parameters.containsKey(TASK_ID) || !parameters.containsKey(PRIORITY)) {
                throw new DukeException("Missing parameters.");
            }
            if (parameters.get(TASK_ID).isBlank() || !Parser.isStringContainsNumber(parameters.get(TASK_ID))) {
                throw new DukeException("Task ID entered is invalid!");
            }
            if (parameters.get(PRIORITY).isBlank()) {
                throw new DukeException("Please enter a priority!");
            } else {
                new ChangeTaskPriorityCommand(parameters, projectListManager).execute();
            }
            break;
        default:
            throw new DukeException("Invalid action!");
        }
    }
}
