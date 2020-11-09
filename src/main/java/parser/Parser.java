package parser;

import cheatsheet.CheatSheetList;
import command.Command;
import command.AddCommand;
import command.ClearCommand;
import command.DeleteCommand;
import command.EditCommand;
import command.ExitCommand;
import command.FavouriteCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.SettingsCommand;
import command.ViewCommand;
import editor.Editor;
import exception.CommandException;
import settings.Settings;
import storage.DataFileDestroyer;
import storage.DataFileReader;
import ui.Ui;
import ui.Printer;

import java.util.LinkedHashMap;


/**
 * Parser for user input to create Command objects that can be executed at a later time.
 */
public class Parser {
    private final CheatSheetList cheatSheetList;
    private final DataFileDestroyer fileDestroyer;
    private final DataFileReader fileReader;
    private final Editor editor;
    private final Printer printer;
    private final Ui ui;
    private final Settings settings;

    private static final String FLAG_REGEX = "(?=(/[a-z]))";

    /**
     * Constructor for the Parser. Specific objects are passed to the commands
     * that need them to execute.
     *
     * @param cheatSheetList The current list of cheat sheets
     * @param editor         The editor object to open the text editor
     * @param fileDestroyer  Destroys existing files
     * @param fileReader     Reads existing files
     * @param printer        The printer object to handle user interaction
     * @param ui             Takes input, used to get missing command flags
     * @param settings       Settings of CheatLogs affect, change the way that the same
     *                       commands will execute
     */
    public Parser(CheatSheetList cheatSheetList, Editor editor,
                  DataFileDestroyer fileDestroyer, Printer printer,
                  Ui ui, Settings settings, DataFileReader fileReader) {
        this.cheatSheetList = cheatSheetList;
        this.editor = editor;
        this.fileReader = fileReader;
        this.fileDestroyer = fileDestroyer;
        this.printer = printer;
        this.ui = ui;
        this.settings = settings;
    }

    /**
     * Parses the user input to return an executable command.
     *
     * @param userInput      Input that is used to construct a command
     *
     * @return               The constructed command
     */
    public Command parse(String userInput) throws CommandException {
        Command commandToBeExecuted = parseCommandType(userInput);

        LinkedHashMap<CommandFlag, String> flagsToDescriptions = parseFlagDescriptions(commandToBeExecuted, userInput);
        commandToBeExecuted.setFlagsToDescriptionsMap(flagsToDescriptions);

        setMissingDescriptions(commandToBeExecuted);
        return commandToBeExecuted;
    }

    /**
     * Parses the user input to return a command injected with all the objects
     * it needs to execute.
     *
     * @param userInput            Input that is used to construct a command
     *
     * @return                     The constructed command
     *
     * @throws CommandException    When the command keyword is not recognized
     */
    private Command parseCommandType(String userInput) throws CommandException {
        String parsedInput = userInput.split(" ")[0];
        switch (parsedInput) {
        case AddCommand.invoker:
            return new AddCommand(printer, cheatSheetList, editor);
        case ClearCommand.invoker:
            return new ClearCommand(printer, cheatSheetList, fileDestroyer, fileReader, ui);
        case DeleteCommand.invoker:
            return new DeleteCommand(printer, cheatSheetList, fileDestroyer, ui);
        case EditCommand.invoker:
            return new EditCommand(printer, cheatSheetList, editor);
        case ExitCommand.invoker:
            return new ExitCommand(printer);
        case FavouriteCommand.invoker:
            return new FavouriteCommand(printer, cheatSheetList);
        case FindCommand.invoker:
            return new FindCommand(printer, cheatSheetList);
        case HelpCommand.invoker:
            return new HelpCommand(printer);
        case ListCommand.invoker:
            return new ListCommand(printer, cheatSheetList);
        case SettingsCommand.invoker:
            return new SettingsCommand(printer, settings);
        case ViewCommand.invoker:
            return new ViewCommand(printer, cheatSheetList);
        default:
            throw new CommandException("Please enter a valid command");
        }
    }

    /**
     * Parses the user input for flags with their accompanying descriptions
     * such as "/n NAME". These flag and description pairs will be placed in a
     * hash map where:
     * key : the flag represented as a CommandFlag object. (e.g. /n is represented as
     *                                                      a CommandFlag.NAME object)
     * value : the description of the flag (e.g. "if else" in "/n if else")
     *
     * @param commandToBeExecuted       The command to get the flag and descriptions for.
     * @param userInput                 The input used to create the flag and description hash map
     *
     * @return                          The flag and description hash map
     *
     * @throws CommandException         One of the flags inputted isn't required by the command
     */
    private LinkedHashMap<CommandFlag, String> parseFlagDescriptions(Command commandToBeExecuted, String userInput)
            throws CommandException {
        LinkedHashMap<CommandFlag, String> flagsToDescriptions = new LinkedHashMap<>();
        try {
            // This split causes each element in details to follow "/flag description"
            String[] details = userInput.split(FLAG_REGEX);

            /* The first element at index 0 is the command itself, not the flags
                so we skip it */
            for (int i = 1; i < details.length; i++) {
                int descriptionStartIdx = getDescriptionStartIdx(details[i]);
                String flag = getFlag(details[i], descriptionStartIdx);
                String flagDescription = getFlagDescription(details[i], descriptionStartIdx);

                CommandFlag validatedCommandFlag = validateCommandFlag(commandToBeExecuted, flag);

                // One of the flags inputted isn't needed by the command
                if (validatedCommandFlag == null) {
                    throw new CommandException("Please input the correct flags");
                // Duplicate flag
                } else if (flagsToDescriptions.get(validatedCommandFlag) != null) {
                    throw new CommandException("Duplicated flags are not allowed");
                }
                flagsToDescriptions.put(validatedCommandFlag, flagDescription);
            }
        } catch (IndexOutOfBoundsException i) {
            throw new CommandException("Flag indexing error");
        }

        return flagsToDescriptions;
    }

    /**
     * Validate if the command actually needs the inputted flag, or if it is irrelevant to execution.
     *
     * @param commandToBeExecuted       The command who is accepting the flag
     * @param flag                      The flag we are validating if we need.
     *
     * @return                          The CommandFlag Enum that matches the valid flag
     *                                  null if the command doesn't need the flag
     */
    private CommandFlag validateCommandFlag(Command commandToBeExecuted, String flag) {
        for (CommandFlag c : commandToBeExecuted.getFlagsToDescriptionsMap().keySet()) {
            if (c.getFlag().equals(flag)) {
                return c;
            }
        }
        return null;
    }

    /**
     * The user input may have missed certain required flags needed for
     * command execution, this method requests the user to input those missing flags.
     *
     * @param commandToBeExecuted       The command to get the missing flag and descriptions for.
     */
    private void setMissingDescriptions(Command commandToBeExecuted) {
        LinkedHashMap<CommandFlag, String> map = commandToBeExecuted.getFlagsToDescriptionsMap();

        /* If the user has set the help message ON, help message for the
           current command are printed as a reference */
        if (settings.getDisplayingHelpMessages()
                && (!commandToBeExecuted.hasRequiredFlags()
                || commandToBeExecuted instanceof ListCommand
                || commandToBeExecuted instanceof ClearCommand)) {
            printer.printCommandHelpMessage(commandToBeExecuted.getClass());
        }

        // Ask the user for the missing flags until the command can execute
        while (!commandToBeExecuted.hasRequiredFlags()) {
            if (!commandToBeExecuted.hasAlternativeFlags()) {
                printer.printAlternativeArgumentPrompt(commandToBeExecuted);
            }

            /* commands initialize the required keys they need with value null. We
               thus look for keys with a null value as they haven't been filled yet*/
            for (CommandFlag key : map.keySet()) {
                /* If one of the alternative flags have already been filled,
                   skip this repeated alternative argument*/
                if (commandToBeExecuted.getAlternativeFlags().contains(key)
                    && commandToBeExecuted.hasAlternativeFlags()) {
                    continue;
                } else if ((map.get(key) == null || map.get(key).isBlank()) && key != CommandFlag.DELETE) {
                    printer.printMissingArgument(key);

                    String newArgVal = ui.getUserInput().trim();
                    if (newArgVal.isBlank()) {
                        newArgVal = null;
                    }

                    map.replace(key, newArgVal);
                }
            }
        }
    }

    /**
     * Looks for the first white space in the input which marks the start
     * of the description for a flag. (e.g. index 2 in "/n NAME")
     *
     * @param input     input from the user
     *
     * @return          the index where the first whitespace is at
     *                  or null if no whitespace is found
     */
    private int getDescriptionStartIdx(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (Character.isWhitespace(input.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the flag from a flag and description pair using the splitIdx
     * to decide where to split. (e.g."/n" in "/n NAME")
     *
     * @param details     parsed input from the user taking the form of "/flag description"
     * @param splitIdx    Where to split the details to get the flag
     *
     * @return            the flag from the details
     */
    private String getFlag(String details, int splitIdx) {
        if (splitIdx == -1) {
            return details;
        } else {
            return details.substring(0, splitIdx).trim();
        }
    }

    /**
     * Gets the description from a flag and description pair using the splitIdx
     * to decide where to split. (e.g."/n" in "/n NAME")
     *
     * @param details     parsed input from the user taking the form of "/flag description"
     * @param splitIdx    Where to split the details to get the flag
     *
     * @return            the description from the details
     */
    private String getFlagDescription(String details, int splitIdx) {
        if (splitIdx == -1) {
            return "";
        } else {
            return details.substring(splitIdx).trim();
        }
    }
}
