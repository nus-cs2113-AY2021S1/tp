package seedu.smarthomebot.logic.parser;

import org.apache.commons.lang3.StringUtils;
import seedu.smarthomebot.logic.commands.AddCommand;
import seedu.smarthomebot.logic.commands.Command;
import seedu.smarthomebot.logic.commands.CreateCommand;
import seedu.smarthomebot.logic.commands.DeleteCommand;
import seedu.smarthomebot.logic.commands.ExitCommand;
import seedu.smarthomebot.logic.commands.HelpCommand;
import seedu.smarthomebot.logic.commands.InvalidCommand;
import seedu.smarthomebot.logic.commands.ListCommand;
import seedu.smarthomebot.logic.commands.OffCommand;
import seedu.smarthomebot.logic.commands.OnCommand;
import seedu.smarthomebot.logic.commands.RemoveCommand;
import seedu.smarthomebot.logic.commands.ResetCommand;
import seedu.smarthomebot.logic.commands.UsageCommand;
import seedu.smarthomebot.logic.commands.exceptions.ParameterFoundException;
import seedu.smarthomebot.logic.parser.exceptions.EmptyParameterException;
import seedu.smarthomebot.logic.parser.exceptions.IllegalCharacterException;
import seedu.smarthomebot.logic.parser.exceptions.InvalidCommandException;
import seedu.smarthomebot.commons.exceptions.InvalidNumericalValueException;
import seedu.smarthomebot.logic.parser.exceptions.WattageExceedException;

import static seedu.smarthomebot.commons.Messages.MESSAGE_EMPTY_PARAMETER;
import static seedu.smarthomebot.commons.Messages.MESSAGE_ILLEGAL_CHARACTER;
import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_ADD_COMMAND;
import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smarthomebot.commons.Messages.MESSAGE_INVALID_LIST_COMMAND;
import static seedu.smarthomebot.commons.Messages.MESSAGE_POWER_EXCEEDED;
import static seedu.smarthomebot.commons.Messages.MESSAGE_VALUE_NOT_NUMBER;
import static seedu.smarthomebot.commons.Messages.MESSAGE_PARAMETER_INVALID;
import static seedu.smarthomebot.commons.Messages.MESSAGE_NO_PARAMETER_IN_ON_BY_LOCATION;
/**
 * Parses user input.
 */
public class Parser {

    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";

    //@@author zongxian-ctrl

    /**
     * Parses arguments into AddCommand format.
     *
     * @param arguments parameters of AddComand
     * @return the prepared AddCommand
     */
    private static Command prepareAddCommand(String arguments) {
        int indexLocation = arguments.indexOf("l/");
        int indexPower = arguments.indexOf("w/");
        int indexType = arguments.indexOf("t/");
        String name;
        String location;
        String wattage;
        String type;

        try {
            if (indexLocation < indexPower && indexPower < indexType) {
                name = arguments.substring(0, indexLocation).trim();
                location = arguments.substring(indexLocation + 2, indexPower).trim();
                wattage = arguments.substring(indexPower + 2, indexType).trim();
                type = arguments.substring(indexType + 2).toLowerCase().trim();

                if (isEmptyInput(name) | isEmptyInput(location)
                        | isEmptyInput(wattage) | isEmptyInput(type)) {
                    throw new EmptyParameterException();
                }
                if (hasIllegalCharacter(name)) {
                    throw new IllegalCharacterException();
                }
                testWattageValidity(wattage);
            } else {
                throw new InvalidCommandException();
            }
            return new AddCommand(name, location, wattage, type);

        } catch (InvalidCommandException | StringIndexOutOfBoundsException e) {
            return new InvalidCommand(MESSAGE_INVALID_ADD_COMMAND);
        } catch (InvalidNumericalValueException e) {
            return new InvalidCommand(MESSAGE_VALUE_NOT_NUMBER);
        } catch (WattageExceedException e) {
            return new InvalidCommand(MESSAGE_POWER_EXCEEDED);
        } catch (EmptyParameterException e) {
            return new InvalidCommand(MESSAGE_EMPTY_PARAMETER);
        } catch (IllegalCharacterException e) {
            return new InvalidCommand(MESSAGE_ILLEGAL_CHARACTER + " [APPLIANCE_NAME].");
        }

    }
    //@@author leonlowzd

    /**
     * Parses arguments into OnCommand format.
     *
     * @param arguments parameters of OnCommand
     * @return the prepared OnCommand
     */
    private static Command prepareOnCommand(String arguments) {
        int indexParameter = arguments.indexOf("p/");
        String name;
        String parameter;
        try {
            if (indexParameter < 1) {
                name = arguments;
                if (isEmptyInput(name)) {
                    throw new EmptyParameterException();
                }
                parameter = "";
            } else {
                name = arguments.substring(0, indexParameter).trim();
                parameter = arguments.substring(indexParameter + 2).toLowerCase().trim();
                if (isEmptyInput(name) || isEmptyInput(parameter)) {
                    throw new EmptyParameterException();
                }
                checkIfParameterIsInt(parameter);
            }
            return new OnCommand(name, parameter);
        } catch (EmptyParameterException e) {
            return new InvalidCommand(MESSAGE_EMPTY_PARAMETER);
        } catch (InvalidNumericalValueException e) {
            return new InvalidCommand(MESSAGE_PARAMETER_INVALID);
        }
    }

    //@@author leonlowzd

    /**
     * Parses arguments into OffCommand format.
     *
     * @param arguments parameters of OffCommand
     * @return the prepared OffCommand
     */
    private static Command prepareOffCommand(String arguments) {
        try {
            int indexParameter = arguments.indexOf("p/");
            if (!(indexParameter < 0)) {
                throw new ParameterFoundException();
            }
            if (isEmptyInput(arguments)) {
                throw new EmptyParameterException();
            }
            return new OffCommand(arguments);
        } catch (EmptyParameterException e) {
            return new InvalidCommand(MESSAGE_EMPTY_PARAMETER);
        } catch (ParameterFoundException e) {
            return new InvalidCommand(MESSAGE_NO_PARAMETER_IN_ON_BY_LOCATION);
        }
    }

    //@@author Ang_Cheng_Jun

    /**
     * Parses arguments into ListCommand format.
     *
     * @param arguments parameters of ListCommand
     * @return the prepared ListCommand
     */
    private static Command prepareListCommand(String arguments) {
        String[] str = arguments.split(" ");
        if (str[0].equals(LOCATION_TYPE)) {
            return new ListCommand(LOCATION_TYPE, "");
        } else if (str[0].equals(APPLIANCE_TYPE)) {
            if (arguments.equals(APPLIANCE_TYPE)) {
                return new ListCommand(APPLIANCE_TYPE, "");
            } else if (str[1].trim().startsWith("l/")) {
                return new ListCommand(APPLIANCE_TYPE, str[1].trim().substring(2));
            } else {
                return new InvalidCommand(MESSAGE_INVALID_LIST_COMMAND);
            }
        } else {
            return new InvalidCommand(MESSAGE_INVALID_LIST_COMMAND);
        }
    }

    /**
     * Checks if the parameter is numerical value.
     *
     * @param parameter parameter entered by user
     * @throws InvalidNumericalValueException if the parameter is not numerical value
     */
    private static void checkIfParameterIsInt(String parameter) throws InvalidNumericalValueException {
        try {
            Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            throw new InvalidNumericalValueException();
        }

    }

    //@@author

    /**
     * Checks if the wattage entered by the user is valid.
     *
     * @param wattage parameter entered by user.
     * @throws WattageExceedException         if the wattage is less 1 or more than 9999.
     * @throws InvalidNumericalValueException if the wattage is not an numerical value.
     */
    private static void testWattageValidity(String wattage) throws WattageExceedException,
            InvalidNumericalValueException {
        try {
            int wattageValue = Integer.parseInt(wattage);
            // Common appliance is between 1 to 9999 watts
            if ((wattageValue < 1) || (wattageValue > 9999)) {
                throw new WattageExceedException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidNumericalValueException();
        }
    }

    //@@author

    /**
     * Checks if the input is empty.
     *
     * @param input parameter entered by the user.
     * @return true if input is empty.
     */
    private static boolean isEmptyInput(String input) {
        return (input.isEmpty());
    }

    //@@author

    /**
     * Check if the input contains illegal character.
     *
     * @param input parameter entered by the user.
     * @return true if any illegal character is found.
     */
    private static boolean hasIllegalCharacter(String input) {
        return (input.contains(" ") || input.contains("/") || input.contains("|"));
    }

    //@@author

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string.
     * @return command based on the user input.
     */
    public Command parseCommand(String userInput) {
        String[] words = userInput.trim().split(" ", 2);
        final String commandWord = words[0];
        final String arguments = StringUtils.replaceOnce(userInput, commandWord, "").trim();

        switch (commandWord) {
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case CreateCommand.COMMAND_WORD:
            return prepareCreateCommand(arguments);
        case RemoveCommand.COMMAND_WORD:
            return prepareRemoveCommand(arguments);
        case AddCommand.COMMAND_WORD:
            return prepareAddCommand(arguments);
        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(arguments);
        case OnCommand.COMMAND_WORD:
            return prepareOnCommand(arguments);
        case OffCommand.COMMAND_WORD:
            return prepareOffCommand(arguments);
        case ListCommand.COMMAND_WORD:
            return prepareListCommand(arguments);
        case UsageCommand.COMMAND_WORD:
            return new UsageCommand();
        case ResetCommand.COMMAND_WORD:
            return new ResetCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

    }

    //@@author

    /**
     * Parses arguments for CreateCommand by checking if arguments is valid.
     *
     * @param arguments parameter of CreateCommand.
     * @return the prepared CreateCommand.
     */
    private Command prepareCreateCommand(String arguments) {
        try {
            if (isEmptyInput(arguments)) {
                throw new EmptyParameterException();
            }
            if (hasIllegalCharacter(arguments)) {
                throw new IllegalCharacterException();
            }
            return new CreateCommand(arguments);
        } catch (EmptyParameterException e) {
            return new InvalidCommand(MESSAGE_EMPTY_PARAMETER);
        } catch (IllegalCharacterException e) {
            return new InvalidCommand(MESSAGE_ILLEGAL_CHARACTER + " [LOCATION_NAME].");
        }

    }

    //@@author zongxian-ctrl

    /**
     * Parses arguments for RemoveCommand by checking if arguments is valid.
     *
     * @param arguments parameter of RemoveCommand.
     * @return the prepared RemoveCommand.
     */
    private Command prepareRemoveCommand(String arguments) {
        try {
            if (isEmptyInput(arguments)) {
                throw new EmptyParameterException();
            }
            return new RemoveCommand(arguments);
        } catch (EmptyParameterException e) {
            return new InvalidCommand(MESSAGE_EMPTY_PARAMETER);
        }

    }

    /**
     * Parses arguments for DeleteCommand by checking if arguments is valid.
     *
     * @param arguments parameter of DeleteCommand.
     * @return the prepared DeleteCommand.
     */
    private Command prepareDeleteCommand(String arguments) {
        try {
            if (isEmptyInput(arguments)) {
                throw new EmptyParameterException();
            }
            return new DeleteCommand(arguments);
        } catch (EmptyParameterException e) {
            return new InvalidCommand(MESSAGE_EMPTY_PARAMETER);
        }

    }

}

