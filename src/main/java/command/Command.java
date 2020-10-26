package command;

import cheatsheet.CheatSheetList;
import exception.CommandException;

import parser.CommandFlag;
import ui.Printer;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * The base class for all Commands.
 */
public abstract class Command {
    protected Printer printer;
    protected CheatSheetList cheatSheetList;

    protected ArrayList<CommandFlag> alternativeArguments;
    protected LinkedHashMap<CommandFlag, String> flagsToDescriptions;
    public boolean isExitCommand;

    public Command() {
    }

    public Command(Printer printer) {
        this.printer = printer;
        this.alternativeArguments = new ArrayList<>();
        this.flagsToDescriptions = new LinkedHashMap<>();
        isExitCommand = false;
    }

    public LinkedHashMap<CommandFlag, String> getFlagstodescriptionsMap() {
        return flagsToDescriptions;
    }

    public void setFlagstodescriptionsMap(HashMap<CommandFlag, String> flagstodescriptions) {
        this.flagsToDescriptions.putAll(flagstodescriptions);
    }

    public ArrayList<CommandFlag> getAlternativeArguments() {
        return alternativeArguments;
    }

    public boolean hasAlternativeArgument() {
        if (alternativeArguments.size() == 0) {
            return true;
        }

        for (CommandFlag arg : alternativeArguments) {
            if (flagsToDescriptions.get(arg) != null && !flagsToDescriptions.get(arg).isEmpty()) {
                return true;
            }
        }

        return false;
    }

    public abstract void execute() throws CommandException;
}
