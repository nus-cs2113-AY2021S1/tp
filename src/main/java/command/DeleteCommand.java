package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import parser.CommandFlag;
import storage.DataFileDestroyer;
import ui.Printer;

public class DeleteCommand extends FinderCommand {
    protected DataFileDestroyer fileDestroyer;
    public static final String invoker = "/delete";

    public DeleteCommand(Printer printer, CheatSheetList cheatSheetList, DataFileDestroyer fileDestroyer) {
        super(printer, cheatSheetList);
        this.fileDestroyer = fileDestroyer;

        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.INDEX, null);
        alternativeArguments.add(CommandFlag.NAME);
        alternativeArguments.add(CommandFlag.INDEX);
    }

    @Override
    public void execute() throws CommandException {
        try {
            CheatSheet cheatSheetToDelete = getCheatSheetFromNameOrIndex();
            cheatSheetList.remove(cheatSheetToDelete.getName());
            fileDestroyer.executeFunction(cheatSheetToDelete.getName());
            printer.printDeleteCheatSheetMessage(cheatSheetToDelete, cheatSheetList);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new CommandException("Please enter a valid name or index");
        } catch (NumberFormatException n) {
            throw new CommandException("Please enter a valid index");
        }
    }
}
