package seedu.smarthomebot;

import seedu.smarthomebot.commands.AddCommand;
import seedu.smarthomebot.commands.Command;
import seedu.smarthomebot.commands.CreateCommand;
import seedu.smarthomebot.commands.DeleteCommand;
import seedu.smarthomebot.commands.ExitCommand;
import seedu.smarthomebot.commands.HelpCommand;
import seedu.smarthomebot.commands.InvalidCommand;
import seedu.smarthomebot.commands.ListCommand;
import seedu.smarthomebot.commands.OffCommand;
import seedu.smarthomebot.commands.OnCommand;
import seedu.smarthomebot.commands.RemoveCommand;
import seedu.smarthomebot.commands.UsageCommand;
import seedu.smarthomebot.exceptions.EmptyParameterException;
import seedu.smarthomebot.exceptions.IllegalCharacterException;
import seedu.smarthomebot.exceptions.InvalidCommandException;
import seedu.smarthomebot.exceptions.InvalidValueException;
import seedu.smarthomebot.exceptions.PowerExceedException;

import static seedu.smarthomebot.common.Messages.MESSAGE_EMPTY_PARAMETER;
import static seedu.smarthomebot.common.Messages.MESSAGE_ILLEGAL_CHARACTER;
import static seedu.smarthomebot.common.Messages.MESSAGE_INVALID_ADD_COMMAND;
import static seedu.smarthomebot.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smarthomebot.common.Messages.MESSAGE_INVALID_LIST_COMMAND;
import static seedu.smarthomebot.common.Messages.MESSAGE_POWER_EXCEEDED;
import static seedu.smarthomebot.common.Messages.MESSAGE_POWER_NOT_NUMBER;


public class Parser {

    private static final String APPLIANCE_TYPE = "appliance";
    private static final String LOCATION_TYPE = "location";

    private static Command prepareOnCommand(String arguments) {
        int indexParameter = arguments.indexOf("p/");
        String name;
        String parameter;
        try {
            if (indexParameter < 1) {
                name = arguments;
                if (checkForEmptyInput(name)) {
                    throw new EmptyParameterException();
                }
                parameter = "";
            } else {
                name = arguments.substring(0, indexParameter).trim();
                parameter = arguments.substring(indexParameter + 2).toLowerCase().trim();
                if (checkForEmptyInput(name)
                        || checkForEmptyInput(parameter)) {
                    throw new EmptyParameterException();
                }
                convertParameterToInt(parameter);
            }
            return new OnCommand(name, parameter);
        } catch (EmptyParameterException e) {
            return new InvalidCommand(MESSAGE_EMPTY_PARAMETER);

        } catch (InvalidValueException e) {
            return new InvalidCommand(MESSAGE_POWER_NOT_NUMBER);

        }
    }

    private static Command prepareOffCommand(String arguments) {
        try {
            if (checkForEmptyInput(arguments)) {
                throw new EmptyParameterException();
            }
            return new OffCommand(arguments);
        } catch (EmptyParameterException e) {
            return new InvalidCommand(MESSAGE_EMPTY_PARAMETER);

        }
    }

    private static void convertParameterToInt(String parameter) throws InvalidValueException {
        try {
            Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            throw new InvalidValueException();
        }

    }

    private static Command prepareAddCommand(String arguments) {
        int indexLocation = arguments.indexOf("l/");
        int indexPower = arguments.indexOf("w/");
        int indexType = arguments.indexOf("t/");
        String name;
        String location;
        String power;
        String type;

        try {
            if (indexLocation < indexPower && indexPower < indexType) {
                name = arguments.substring(0, indexLocation).trim();
                location = arguments.substring(indexLocation + 2, indexPower).trim();
                power = arguments.substring(indexPower + 2, indexType).trim();
                type = arguments.substring(indexType + 2).toLowerCase().trim();

                if (checkForEmptyInput(name) | checkForEmptyInput(location)
                        | checkForEmptyInput(power) | checkForEmptyInput(type)) {
                    throw new EmptyParameterException();
                }
                if (checkForIllegalCharacter(name)) {
                    throw new IllegalCharacterException();
                }
                testPowerValidity(power);
            } else {
                throw new InvalidCommandException();
            }
            return new AddCommand(name, location, power, type);

        } catch (InvalidCommandException e) {
            return new InvalidCommand(MESSAGE_INVALID_ADD_COMMAND);
        } catch (InvalidValueException e) {
            return new InvalidCommand(MESSAGE_POWER_NOT_NUMBER);
        } catch (PowerExceedException e) {
            return new InvalidCommand(MESSAGE_POWER_EXCEEDED);
        } catch (EmptyParameterException e) {
            return new InvalidCommand(MESSAGE_EMPTY_PARAMETER);
        } catch (IllegalCharacterException e) {
            return new InvalidCommand(MESSAGE_ILLEGAL_CHARACTER + " [APPLIANCE_NAME].");
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(MESSAGE_INVALID_ADD_COMMAND);
        }

    }

    private static Command prepareListCommand(String arguments) {
        String[] str = arguments.split(" ");
        if (str[0].equals(LOCATION_TYPE)) {
            return new ListCommand(LOCATION_TYPE, "");
        } else if (str[0].equals(APPLIANCE_TYPE)) {
            if (arguments.equals(APPLIANCE_TYPE)) {
                return new ListCommand(APPLIANCE_TYPE, "");
            } else if (str[1].trim().substring(0, 2).equals("l/")) {
                return new ListCommand(APPLIANCE_TYPE, str[1].trim().substring(2));
            } else {
                return new InvalidCommand(MESSAGE_INVALID_LIST_COMMAND);
            }
        } else {
            return new InvalidCommand(MESSAGE_INVALID_LIST_COMMAND);
        }
    }

    private Command prepareCreateCommand(String arguments) {
        try {
            if (checkForEmptyInput(arguments)) {
                throw new EmptyParameterException();
            }
            if (checkForIllegalCharacter(arguments)) {
                throw new IllegalCharacterException();
            }
            return new CreateCommand(arguments);
        } catch (EmptyParameterException e) {
            return new InvalidCommand(MESSAGE_EMPTY_PARAMETER);
        } catch (IllegalCharacterException e) {
            return new InvalidCommand(MESSAGE_ILLEGAL_CHARACTER + " [LOCATION_NAME].");
        }

    }

    private Command prepareDeleteCommand(String arguments) {
        try {
            if (checkForEmptyInput(arguments)) {
                throw new EmptyParameterException();
            }
            return new DeleteCommand(arguments);
        } catch (EmptyParameterException e) {
            return new InvalidCommand(MESSAGE_EMPTY_PARAMETER);
        }

    }

    private Command prepareRemoveCommand(String arguments) {
        try {
            if (checkForEmptyInput(arguments)) {
                throw new EmptyParameterException();
            }
            return new RemoveCommand(arguments);
        } catch (EmptyParameterException e) {
            return new InvalidCommand(MESSAGE_EMPTY_PARAMETER);
        }

    }

    private static void testPowerValidity(String power) throws PowerExceedException, InvalidValueException {
        try {
            int powerValue = Integer.parseInt(power);
            // Common appliance should not exceed 9999 watts
            if ((powerValue < 1) || (powerValue > 9999)) {
                throw new PowerExceedException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidValueException();
        }
    }

    private static boolean checkForEmptyInput(String input) {
        return (input.isEmpty());
    }

    private static boolean checkForIllegalCharacter(String input) {
        return (input.contains(" ") | input.contains("/"));
    }

    public Command parseCommand(String userInput) {
        String[] words = userInput.trim().split(" ", 2);
        final String commandWord = words[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

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
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

    }

}

