package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.EmptyCommand;
import seedu.duke.command.sprint.CreateSprintCommand;
import seedu.duke.command.sprint.AddSprintTaskCommand;
import seedu.duke.command.sprint.RemoveSprintTaskCommand;
import seedu.duke.command.sprint.ViewSprintCommand;
import seedu.duke.command.sprint.AllocateSprintTaskCommand;
import seedu.duke.command.sprint.EditSprintCommand;
import seedu.duke.command.sprint.DeallocateSprintTaskCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.CREATE;
import static seedu.duke.command.CommandSummary.EDIT;
import static seedu.duke.command.CommandSummary.ADDTASK;
import static seedu.duke.command.CommandSummary.REMOVETASK;
import static seedu.duke.command.CommandSummary.VIEW;
import static seedu.duke.command.CommandSummary.ALLOCATE;
import static seedu.duke.command.CommandSummary.DEALLOCATE;

public class SprintParser implements ExceptionsParser {

    @Override
    public Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                   ProjectManager projectListManager)
            throws DukeException {


        switch (action.toLowerCase()) {
        case CREATE:
            if (checkCreateSprintParams(parameters)) {
                return new CreateSprintCommand(parameters, projectListManager);
            }
            break;
        case EDIT:
            if (checkEditSprintParams(parameters)) {
                return new EditSprintCommand(parameters, projectListManager);
            }
            break;
        case ADDTASK:
            if (checkAddRemoveTaskParams(parameters)) {
                return new AddSprintTaskCommand(parameters, projectListManager);
            }
            break;
        case REMOVETASK:
            if (checkAddRemoveTaskParams(parameters)) {
                return new RemoveSprintTaskCommand(parameters, projectListManager);
            }
            break;
        case VIEW:
            if (checkViewSprintParams(parameters)) {
                return new ViewSprintCommand(parameters, projectListManager);
            }
            break;
        case ALLOCATE:
            if (checkAllocateDeallocateTaskParams(parameters)) {
                return new AllocateSprintTaskCommand(parameters, projectListManager);
            }
            break;
        case DEALLOCATE:
            if (checkAllocateDeallocateTaskParams(parameters)) {
                return new DeallocateSprintTaskCommand(parameters, projectListManager);
            }
            break;
        default:
            throw new DukeException("Invalid action!");
        }
        return new EmptyCommand(parameters);
    }


    /**
     * Validate parameters for CreateSprintCommand.
     */
    private boolean checkCreateSprintParams(Hashtable<String, String> parameters)
            throws DukeException {
        //Mandatory fields
        checkParamExist(parameters, "goal");
        //Optional fields.
        checkParamIntegerParsable(parameters, "project", "Project ID");
        checkStartParam(parameters);
        return true;
    }

    /**
     * Validate parameters for EditSprintCommand.
     */
    private boolean checkEditSprintParams(Hashtable<String, String> parameters)
            throws DukeException {
        //Mandatory fields
        checkParamExist(parameters, "goal");
        //Optional fields
        checkParamIntegerParsable(parameters, "project", "Project ID");
        checkParamIntegerParsable(parameters, "sprint", "Sprint ID");
        return true;
    }

    /**
     * Validate parameters for AddSprintTaskCommand and RemoveSprintTaskCommand.
     */
    private boolean checkAddRemoveTaskParams(Hashtable<String, String> parameters)
            throws DukeException {
        //Mandatory fields
        checkTaskParam(parameters);
        //Optional fields
        checkParamIntegerParsable(parameters, "project", "Project ID");
        checkParamIntegerParsable(parameters, "sprint", "Sprint ID");
        return true;
    }

    /**
     * Validate parameters for ViewSprintCommand.
     */
    private boolean checkViewSprintParams(Hashtable<String, String> parameters)
            throws DukeException {
        //Optional fields
        checkParamIntegerParsable(parameters, "project", "Project ID");
        checkParamIntegerParsable(parameters, "sprint", "Sprint ID");
        return true;
    }

    /**
     * Validate parameters for AllocateSprintTaskCommand and DeallocateSprintTaskCommand.
     */
    private boolean checkAllocateDeallocateTaskParams(Hashtable<String, String> parameters)
            throws DukeException {
        //Mandatory fields
        checkParamExist(parameters, "user");
        checkParamExist(parameters, "task");
        checkTaskParam(parameters);
        //Optional fields
        checkParamIntegerParsable(parameters, "project", "Project ID");
        checkParamIntegerParsable(parameters, "sprint", "Sprint ID");
        return true;
    }


    /**
     * Validate if parameter is Integer parsable and throws custom exception accordingly.
     */
    private boolean checkParamIntegerParsable(Hashtable<String, String> parameters, String paramName, String paramDesc)
            throws DukeException {
        if (parameters.containsKey(paramName)) {
            checkIntegerParsable(paramDesc, parameters.get(paramName));
        } else if (parameters.containsKey("0")) {
            checkIntegerParsable(paramDesc, parameters.get("0"));
        }
        return true;
    }

    /**
     * Validate if String is Integer parsable and throws custom exception accordingly.
     */
    private void checkIntegerParsable(String paramDesc, String... inputs)
            throws DukeException {
        for (String input : inputs) {
            try {
                Integer.parseInt(input);
            } catch (NumberFormatException error) {
                throw new DukeException(String.format("Please include a positive integer for %s.", paramDesc));
            }
        }
    }

    /**
     * Check if the "task" params is specified and if parsable into an Integer.
     */
    private void checkTaskParam(Hashtable<String, String> parameters)
            throws DukeException {

        if (parameters.containsKey("task")) {
            checkTaskParamTag(parameters, "task");
        } else if (parameters.containsKey("0")) {
            checkTaskParamTag(parameters, "0");
        } else {
            throw new DukeException("Please indicate task(s) to be allocated.");
        }
    }

    private void checkTaskParamTag(Hashtable<String, String> parameters, String tag)
            throws DukeException {
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
     * @parameters - All parameters supplied by user
     * @paramName - Parameter to be check if exist in @parameters
     * @throws DukeException if do not exist
     */
    private void checkParamExist(Hashtable<String, String> parameters, String paramName)
            throws DukeException {
        if (!parameters.containsKey(paramName)) {
            throw new DukeException("Missing parameter(s): " + paramName);
        }
    }

    /**
     * Check if a start parameter exist and DateTime parsable.
     * @parameters - All parameters supplied by user
     * @throws DukeException if not DateTime parsable
     */
    private void checkStartParam(Hashtable<String, String> parameters) throws DukeException {
        if (parameters.containsKey("start")) {
            try {
                DateTimeParser.parseDate(parameters.get("start"));
            } catch (DukeException e) {
                throw new DukeException("Please indicate your start date in this following format: YYYYMMDD");
            }
        }
    }
}