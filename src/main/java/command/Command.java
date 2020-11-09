package command;

import cheatsheet.CheatSheetList;
import exception.CommandException;

import parser.CommandFlag;
import ui.Printer;

import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * The base class for all Commands.
 */
public abstract class Command {
    protected Printer printer;
    protected CheatSheetList cheatSheetList;

    protected ArrayList<CommandFlag> alternativeFlags; // At least one of these must be filled
    protected ArrayList<CommandFlag> necessaryFlags; // All of these must be filled

    protected LinkedHashMap<CommandFlag, String> flagsToDescriptions;
    public boolean isExitCommand;

    public Command() {
    }

    /**
     * Constructor for Command.
     *
     * @param printer The printer object handles the user interaction
     */
    public Command(Printer printer) {
        this.printer = printer;
        this.alternativeFlags = new ArrayList<>();
        this.necessaryFlags = new ArrayList<>();
        this.flagsToDescriptions = new LinkedHashMap<>();
        isExitCommand = false;
    }

    public LinkedHashMap<CommandFlag, String> getFlagsToDescriptionsMap() {
        return flagsToDescriptions;
    }

    public void setFlagsToDescriptionsMap(HashMap<CommandFlag, String> flagsToDescriptions) {
        this.flagsToDescriptions.putAll(flagsToDescriptions);
    }

    public ArrayList<CommandFlag> getAlternativeFlags() {
        return alternativeFlags;
    }

    public ArrayList<CommandFlag> getNecessaryFlags() {
        return necessaryFlags;
    }


    public boolean hasRequiredFlags() {
        return hasNecessaryFlags() && hasAlternativeFlags();
    }

    /**
     * Checks if the command has all of the necessary flags.
     *
     * @return A boolean of whether the command contains all the necessary flags
     */
    public boolean hasNecessaryFlags() {
        for (CommandFlag arg : necessaryFlags) {
            if (flagsToDescriptions.get(arg) == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the command has at least one of the alternative flags.
     *
     * @return A boolean of whether the command has at least one of the alternative flags
     */
    public boolean hasAlternativeFlags() {
        if (alternativeFlags.size() == 0) {
            return true;
        }

        for (CommandFlag arg : alternativeFlags) {
            if (flagsToDescriptions.get(arg) != null) {
                return true;
            }
        }
        return false;
    }
    
    public abstract void execute() throws CommandException, InterruptedException, IOException;
}
