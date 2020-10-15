package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.addcommand.AddModuleCommand;
import seedu.duke.command.addcommand.AddTaskCommand;
import seedu.duke.command.filtercommand.FilterCommand;
import seedu.duke.command.filtercommand.deletecommand.DeleteModuleCommand;
import seedu.duke.command.filtercommand.listcommand.ListModuleCommand;
import seedu.duke.command.filtercommand.listcommand.ListTaskCommand;
import seedu.duke.command.misc.ChangeDirectoryCommand;
import seedu.duke.exception.InvalidFormatException;
import seedu.duke.util.DateTime;
import seedu.duke.util.DateTimeFormat;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.util.ExceptionMessage.MESSAGE_DUPLICATE_PREFIX_FOUND;
import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_DATETIME_FORMAT;
import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_PARAMETERS;
import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_PRIORITY;
import static seedu.duke.util.ExceptionMessage.MESSAGE_MISSING_DIRECTORY_NAME;
import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_EMPTY_INPUT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_MISSING_MODULE_CODE;
import static seedu.duke.util.Message.MESSAGE_MISSING_TASK_DESCRIPTION;
import static seedu.duke.util.Message.MESSAGE_NO_EDIT_MODULE;

public class Parser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<parameters>.*)");
    private static final String COMMAND_WORD_GROUP = "commandWord";
    private static final String PARAMETERS_GROUP = "parameters";
    private static final String IDENTIFIER_GROUP = "identifier";
    private static final String MODULE_GROUP = "moduleCode";
    private static final String DEADLINE_GROUP = "deadline";
    private static final String PRIORITY_GROUP = "priority";
    private static final String NONE = "";
    private static final String INVALID_GROUP = "invalid";
    public static final String MODULE_PREFIX = "-m";
    public static final String DEADLINE_PREFIX = "-d";

    /**
     * Parses the input string read by the <b>UI</b> and converts the string into a specific <b>Command</b>, which is
     * to be executed by the <b>Nuke</b> program.
     * <p></p>
     * <b>Note</b>: The user input has to start with a certain keyword (i.e. <i>command word</i>), otherwise an
     * <i>Invalid Command Exception</i> will be thrown.
     *
     * @param input The user input read by the <b>UI</b>
     * @return The <b>corresponding</b> command to be executed
     * @see Command
     */
    public Command parseCommand(String input) {
        if (input.isBlank()) {
            return new IncorrectCommand(MESSAGE_EMPTY_INPUT);
        }

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        String commandWord = matcher.group(COMMAND_WORD_GROUP).toLowerCase().trim();
        String parameters = matcher.group(PARAMETERS_GROUP);

        try {
            switch (commandWord){
            case AddModuleCommand.COMMAND_WORD:
                return prepareAddModuleCommand(parameters);
            case AddTaskCommand.COMMAND_WORD:
                return prepareAddTaskCommand(parameters);
            case ListModuleCommand.COMMAND_WORD:
                return prepareListModuleCommand(parameters);
            case ListTaskCommand.COMMAND_WORD:
                return new ListTaskCommand();
            case DeleteModuleCommand.COMMAND_WORD:
                return prepareDeleteModuleCommand(parameters);

            case ChangeDirectoryCommand.COMMAND_WORD:
                return prepareChangeDirectoryCommand(parameters);

            default:
                return new IncorrectCommand("Wrong!");
            }
        } catch (InvalidParameterException e) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        } catch (DuplicatePrefixException e) {
            return new IncorrectCommand(String.format("%s%s\n", MESSAGE_DUPLICATE_PREFIX_FOUND, e.getMessage()));
        }
    }

    /**
     * Prepares the command to add a module.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to add a module
     */
    private Command prepareAddModuleCommand(String parameters)
            throws InvalidParameterException, DuplicatePrefixException {
        Matcher matcher = AddModuleCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher);

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, AddModuleCommand.FORMAT));
        }

        String moduleCode = matcher.group(IDENTIFIER_GROUP).trim();
        if (moduleCode.isEmpty()) {
            return new IncorrectCommand(MESSAGE_MISSING_MODULE_CODE);
        }

        return new AddModuleCommand(moduleCode);
    }

    /**
     * Prepares the command to add a task.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to add a task
     */
    private Command prepareAddTaskCommand(String parameters)
            throws InvalidParameterException {
        Matcher matcher = AddTaskCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher,
                DEADLINE_PREFIX);

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT));
        }

        String taskDescription = matcher.group(IDENTIFIER_GROUP).trim();
        if (taskDescription.isEmpty()) {
            return new IncorrectCommand(MESSAGE_MISSING_TASK_DESCRIPTION);
        }

        String deadline = matcher.group(DEADLINE_GROUP).replace(DEADLINE_PREFIX, NONE).trim();

        DateTime deadlineToSet;
        try {
            deadlineToSet = DateTimeFormat.stringToDateTime(deadline);
        } catch (DateTimeFormat.InvalidDateTimeException e) {
            return new IncorrectCommand(MESSAGE_INVALID_DATETIME_FORMAT);
        }

        try {
            return new AddTaskCommand(taskDescription, deadlineToSet);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(MESSAGE_INVALID_PRIORITY);
        }
    }


    /* Prepare Delete and List Commands */

    /**
     * Prepares the command to delete modules or show filtered modules.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to delete modules or show filtered modules
     */
    private Command prepareListModuleCommand(String parameters)
            throws InvalidParameterException {
        return new ListModuleCommand(parameters, false);
    }

    /**
     * Return the arguments in the context of the delete person command
     *
     * @param parameters full command args string
     * @return the prepared command
     */
    private static Command prepareDeleteModuleCommand(String parameters) {
        try {
            final String moduleCode = parameters.trim();
            return new DeleteModuleCommand(moduleCode);
        } catch (NumberFormatException pe) {
            return new IncorrectCommand(MESSAGE_MISSING_MODULE_CODE);
        }
    }

    /**
     * Prepares the command to change the current directory.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to change the current directory
     */
    private Command prepareChangeDirectoryCommand(String parameters) {
        if (parameters.isBlank()) {
            return new IncorrectCommand(MESSAGE_MISSING_DIRECTORY_NAME);
        } else if (parameters.trim().equals("..")) {
            return new ChangeDirectoryCommand();
        } else {
            return new ChangeDirectoryCommand(parameters.trim());
        }
    }

    /**
     * Validate the parameters given by the user for the respective command.
     *
     * @param parameters
     *  The parameters given by the user
     * @param matcher
     *  The matcher to match for attributes and check for validity
     * @param parameterPrefixes
     *  The prefixes used for the respective command
     * @throws InvalidParameterException
     *  If an invalid parameter is found in the parameters or the parameters do not match the expected format
     */
    private void validateParameters(String parameters, Matcher matcher, String... parameterPrefixes)
            throws InvalidParameterException {

        if (!matcher.matches()) {
            throw new InvalidParameterException();
        }
    }

    /**
     * Checks if there is anything to edit from the input given by the user.
     *
     * @param attributes
     *  The attributes to be edited
     * @return
     *  <code>TRUE</code> if there is nothing to edit, or <code>FALSE</code> otherwise
     */
    private boolean isNothingToEdit(String... attributes) {
        for (String attribute : attributes) {
            if (!attribute.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static class DuplicatePrefixException extends InvalidFormatException {
        private String message;

        public DuplicatePrefixException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }
}
