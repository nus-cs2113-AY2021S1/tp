package com.scrumptious.parser;

import com.scrumptious.command.Command;
import com.scrumptious.command.task.AddTaskCommand;
import com.scrumptious.command.task.ChangeTaskPriorityCommand;
import com.scrumptious.command.task.DeleteTaskCommand;
import com.scrumptious.command.task.DoneTaskCommand;
import com.scrumptious.command.task.PriorityViewCommand;
import com.scrumptious.command.task.ViewTaskCommand;
import com.scrumptious.exception.ScrumptiousException;
import com.scrumptious.model.project.ProjectManager;

import java.util.Hashtable;

import static com.scrumptious.command.CommandSummary.ADD;
import static com.scrumptious.command.CommandSummary.DELETE;
import static com.scrumptious.command.CommandSummary.DESCRIPTION;
import static com.scrumptious.command.CommandSummary.DONE;
import static com.scrumptious.command.CommandSummary.PRIORITY;
import static com.scrumptious.command.CommandSummary.PRIORITYVIEW;
import static com.scrumptious.command.CommandSummary.TASK_ID;
import static com.scrumptious.command.CommandSummary.TITLE;
import static com.scrumptious.command.CommandSummary.VIEW;

public class TaskParser implements ExceptionsParser {

    @Override
    public Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                   ProjectManager projectListManager)
            throws ScrumptiousException {

        switch (action.toLowerCase()) {
        case ADD:
            if (!parameters.containsKey(TITLE) || !parameters.containsKey(DESCRIPTION)
                    || !parameters.containsKey(PRIORITY)) {
                throw new ScrumptiousException("Missing parameters.");
            }
            if (parameters.get(TITLE).isBlank()) {
                throw new ScrumptiousException("Please enter a title!");
            }
            if (parameters.get(DESCRIPTION).isBlank()) {
                throw new ScrumptiousException("Please enter a description!");
            }
            if (parameters.get(PRIORITY).isBlank()) {
                throw new ScrumptiousException("Please enter a priority!");
            } else {
                return new AddTaskCommand(parameters, projectListManager);
            }
        case DELETE:
            if (parameters.get("0") == null) {
                throw new ScrumptiousException("Missing parameters or dashes were input. "
                        + "Please do not enter dashes for this command.");
            }
            if (parameters.get("0").isBlank() || !ParserManager.isStringIntParsable(parameters.get("0"))) {
                throw new ScrumptiousException("Please enter a task number!");
            } else {
                return new DeleteTaskCommand(parameters, projectListManager);
            }
        case DONE:
            if (parameters.get("0") == null) {
                throw new ScrumptiousException("Missing parameters or dashes were input. "
                        + "Please do not enter dashes for this command.");
            }
            if (parameters.get("0").isBlank() || !ParserManager.isStringIntParsable(parameters.get("0"))) {
                throw new ScrumptiousException("Please enter a task number!");
            } else {
                return new DoneTaskCommand(parameters, projectListManager);
            }
        case VIEW:
            return new ViewTaskCommand(parameters, projectListManager);
        case PRIORITY:
            if (!parameters.containsKey(TASK_ID) || !parameters.containsKey(PRIORITY)) {
                throw new ScrumptiousException("Missing parameters.");
            }
            if (parameters.get(TASK_ID).isBlank() || !ParserManager.isStringIntParsable(parameters.get(TASK_ID))) {
                throw new ScrumptiousException("Task ID entered is invalid!");
            }
            if (parameters.get(PRIORITY).isBlank()) {
                throw new ScrumptiousException("Please enter a priority!");
            } else {
                return new ChangeTaskPriorityCommand(parameters, projectListManager);
            }
        case PRIORITYVIEW:
            if (!parameters.isEmpty()) {
                throw new ScrumptiousException("Invalid action!");
            } else {
                return new PriorityViewCommand(parameters, projectListManager);
            }
        default:
            throw new ScrumptiousException("Invalid action!");
        }
    }
}
