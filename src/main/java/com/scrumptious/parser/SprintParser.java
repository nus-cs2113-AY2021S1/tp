package com.scrumptious.parser;

import com.scrumptious.command.Command;
import com.scrumptious.command.sprint.CreateSprintCommand;
import com.scrumptious.command.sprint.AddSprintTaskCommand;
import com.scrumptious.command.sprint.RemoveSprintTaskCommand;
import com.scrumptious.command.sprint.ViewSprintCommand;
import com.scrumptious.command.sprint.AllocateSprintTaskCommand;
import com.scrumptious.command.sprint.EditSprintCommand;
import com.scrumptious.command.sprint.DeallocateSprintTaskCommand;
import com.scrumptious.exception.ScrumptiousException;
import com.scrumptious.model.project.ProjectManager;

import java.util.Hashtable;

import static com.scrumptious.command.CommandSummary.CREATE;
import static com.scrumptious.command.CommandSummary.EDIT;
import static com.scrumptious.command.CommandSummary.ADDTASK;
import static com.scrumptious.command.CommandSummary.REMOVETASK;
import static com.scrumptious.command.CommandSummary.VIEW;
import static com.scrumptious.command.CommandSummary.ALLOCATE;
import static com.scrumptious.command.CommandSummary.DEALLOCATE;

public class SprintParser implements ExceptionsParser {

    @Override
    public Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                   ProjectManager projectListManager)
            throws ScrumptiousException {


        switch (action.toLowerCase()) {
        case CREATE:
            checkCreateSprintParams(parameters);
            return new CreateSprintCommand(parameters, projectListManager);
        case EDIT:
            checkEditSprintParams(parameters);
            return new EditSprintCommand(parameters, projectListManager);
        case ADDTASK:
            checkAddRemoveTaskParams(parameters);
            return new AddSprintTaskCommand(parameters, projectListManager);
        case REMOVETASK:
            checkAddRemoveTaskParams(parameters);
            return new RemoveSprintTaskCommand(parameters, projectListManager);
        case VIEW:
            checkViewSprintParams(parameters);
            return new ViewSprintCommand(parameters, projectListManager);
        case ALLOCATE:
            checkAllocateDeallocateTaskParams(parameters);
            return new AllocateSprintTaskCommand(parameters, projectListManager);
        case DEALLOCATE:
            checkAllocateDeallocateTaskParams(parameters);
            return new DeallocateSprintTaskCommand(parameters, projectListManager);
        default:
            throw new ScrumptiousException("Invalid action!");
        }
    }


    /**
     * Validate parameters for CreateSprintCommand.
     */
    private void checkCreateSprintParams(Hashtable<String, String> parameters) throws ScrumptiousException {
        //Mandatory fields
        checkParamExist(parameters, "goal");
        //Optional fields.
        checkParamIntegerParsable(parameters, "project", "Project ID");
        checkStartParam(parameters);
    }

    /**
     * Validate parameters for EditSprintCommand.
     */
    private void checkEditSprintParams(Hashtable<String, String> parameters) throws ScrumptiousException {
        //Mandatory fields
        checkParamExist(parameters, "goal");
        //Optional fields
        checkParamIntegerParsable(parameters, "project", "Project ID");
        checkParamIntegerParsable(parameters, "sprint", "Sprint ID");
    }

    /**
     * Validate parameters for AddSprintTaskCommand and RemoveSprintTaskCommand.
     */
    private void checkAddRemoveTaskParams(Hashtable<String, String> parameters) throws ScrumptiousException {
        //Mandatory fields
        checkTaskParam(parameters);
        //Optional fields
        checkParamIntegerParsable(parameters, "project", "Project ID");
        checkParamIntegerParsable(parameters, "sprint", "Sprint ID");
    }

    /**
     * Validate parameters for ViewSprintCommand.
     */
    private void checkViewSprintParams(Hashtable<String, String> parameters) throws ScrumptiousException {
        //Optional fields
        checkParamIntegerParsable(parameters, "project", "Project ID");
        checkParamIntegerParsable(parameters, "sprint", "Sprint ID");
    }

    /**
     * Validate parameters for AllocateSprintTaskCommand and DeallocateSprintTaskCommand.
     */
    private void checkAllocateDeallocateTaskParams(Hashtable<String, String> parameters) throws ScrumptiousException {
        //Mandatory fields
        checkParamExist(parameters, "user");
        checkParamExist(parameters, "task");
        checkTaskParam(parameters);
        //Optional fields
        checkParamIntegerParsable(parameters, "project", "Project ID");
        checkParamIntegerParsable(parameters, "sprint", "Sprint ID");
    }


    /**
     * Validate if parameter is Integer parsable and throws custom exception accordingly.
     */
    private void checkParamIntegerParsable(Hashtable<String, String> parameters, String paramName, String paramDesc)
            throws ScrumptiousException {
        if (parameters.containsKey(paramName)) {
            checkIntegerParsable(paramDesc, parameters.get(paramName));
        } else if (parameters.containsKey("0")) {
            checkIntegerParsable(paramDesc, parameters.get("0"));
        }
    }

    /**
     * Validate if String is Integer parsable and throws custom exception accordingly.
     */
    private void checkIntegerParsable(String paramDesc, String... inputs)
            throws ScrumptiousException {
        for (String input : inputs) {
            try {
                Integer.parseInt(input);
            } catch (NumberFormatException error) {
                throw new ScrumptiousException(String.format("Please include a positive integer for %s.", paramDesc));
            }
        }
    }

    /**
     * Check if the "task" params is specified and if parsable into an Integer.
     */
    private void checkTaskParam(Hashtable<String, String> parameters)
            throws ScrumptiousException {

        if (parameters.containsKey("task")) {
            checkTaskParamTag(parameters, "task");
        } else if (parameters.containsKey("0")) {
            checkTaskParamTag(parameters, "0");
        } else {
            throw new ScrumptiousException("Please indicate the task(s) to be allocated/deallocated.");
        }
    }

    private void checkTaskParamTag(Hashtable<String, String> parameters, String tag)
            throws ScrumptiousException {
        String[] taskIds;
        if (tag.equals("task")) {
            taskIds = parameters.get(tag).split(" ");
        } else {
            taskIds = parameters.values().toArray(new String[0]);
        }
        checkIntegerParsable("Task IDs", taskIds);
    }

    /**
     * Check if a parameter exist.
     * @param parameters - All parameters supplied by user
     * @param paramName - Parameter to be check if exist in @parameters
     * @throws ScrumptiousException if do not exist
     */
    private void checkParamExist(Hashtable<String, String> parameters, String paramName)
            throws ScrumptiousException {
        if (!parameters.containsKey(paramName)) {
            throw new ScrumptiousException("Missing parameter(s): " + paramName);
        }
    }

    /**
     * Check if a start parameter exist and DateTime parsable.
     * @param parameters - All parameters supplied by user
     * @throws ScrumptiousException if not DateTime parsable
     */
    private void checkStartParam(Hashtable<String, String> parameters) throws ScrumptiousException {
        if (parameters.containsKey("start")) {
            try {
                String date = parameters.get("start");
                String correctDate = DateTimeParser.catchDateFormat(date);
                DateTimeParser.parseDate(correctDate);
            } catch (ScrumptiousException e) {
                throw new ScrumptiousException(e.getMessage());
            }
        }
    }
}