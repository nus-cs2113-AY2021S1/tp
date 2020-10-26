package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import parser.CommandFlag;
import ui.Printer;

public abstract class FinderCommand extends Command {
    protected CheatSheetList cheatSheetList;

    public FinderCommand(Printer printer, CheatSheetList cheatSheetList) {
        super(printer);
        this.cheatSheetList = cheatSheetList;
    }

    protected CheatSheet getCheatSheetFromNameOrIndex() throws CommandException {
        CheatSheet desiredCheatSheet = null;
        if (flagsToDescriptions.get(CommandFlag.NAME) != null) {
            String name = flagsToDescriptions.get(CommandFlag.NAME);
            desiredCheatSheet = cheatSheetList.get(name);
        } else if (flagsToDescriptions.get(CommandFlag.INDEX) != null) {
            int index = Integer.parseInt(flagsToDescriptions.get(CommandFlag.INDEX));
            desiredCheatSheet = cheatSheetList.get(index);
        }

        if (desiredCheatSheet == null) {
            throw new CommandException("Please enter a valid name or an index");
        }

        return desiredCheatSheet;
    }
}

