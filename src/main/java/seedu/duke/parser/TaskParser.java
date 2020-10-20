package seedu.duke.parser;

import seedu.duke.command.task.TaskCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;

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
    public void parseSingleCommandsExceptions(Hashtable<String, String> parameters) throws DukeException {
    }

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                ArrayList<Project> projectList)
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
                new TaskCommand().addTaskCommand(parameters, projectList);
            }
            break;
        case DELETE:
            if (parameters.get("0").isBlank() || !Parser.isStringContainsNumber(parameters.get("0"))) {
                throw new DukeException("please give a task number");
            } else {
                new TaskCommand().deleteTaskCommand(parameters, projectList);
            }
            break;
        case DONE:
            if (parameters.get("0").isBlank() || !Parser.isStringContainsNumber(parameters.get("0"))) {
                throw new DukeException("please give a task number");
            } else {
                new TaskCommand().doneTaskCommand(parameters, projectList);
            }
            break;
        case VIEW:
            new TaskCommand().viewTaskCommand(parameters, projectList);
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
                new TaskCommand().changeTaskPriorityCommand(parameters, projectList);
            }
            break;
        default:
            throw new DukeException("Invalid action!");
        }
    }
}
