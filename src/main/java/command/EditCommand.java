package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;
import exception.EditorException;
import parser.CommandFlag;
import ui.Printer;

/**
 * Command to edit an existing cheatsheet in the list of cheatsheets.
 */
public class EditCommand extends FinderCommand {
    private final Editor editor;

    public static final String invoker = "/edit";

    /**
     * Constructor for EditCommand.
     * Required argument: either NAME or INDEX.
     *
     * @param printer        The printer object handles user interaction
     * @param cheatSheetList The cheatSheetList object holds the current list of cheatsheets
     * @param editor         The editor object opens the text editor
     */
    public EditCommand(Printer printer, CheatSheetList cheatSheetList, Editor editor) {
        super(printer, cheatSheetList);
        this.editor = editor;
        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.INDEX, null);
        alternativeFlags.add(CommandFlag.NAME);
        alternativeFlags.add(CommandFlag.INDEX);
    }

    /**
     * Checks whether the EditCommand contains required flags as stated in constructor.
     *
     * @return A boolean on whether the EditCommand contains the required flag
     */
    @Override
    public boolean hasRequiredFlags() {
        return flagsToDescriptions.get(CommandFlag.NAME) != null
                || flagsToDescriptions.get(CommandFlag.INDEX) != null;
    }

    /**
     * Gets a cheatsheet from cheatSheetList according to name or index, whichever the user entered.
     * Opens the cheatsheet via the editor object for user to edit.
     * Outputs the updated cheatsheet for verification
     *
     * @throws CommandException Thrown if for index, user enters non-numerical characters or is out of bound.
     */
    @Override
    public void execute() throws CommandException {
        try {
            CheatSheet desiredCheatSheet = getCheatSheetFromNameOrIndex();
            callContentEditor(desiredCheatSheet);
            printer.printViewCheatSheetMessage(desiredCheatSheet);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CommandException("Please enter a valid index");
        }
    }

    /**
     * Resets the editor, then opens it.
     *
     * @param desiredCheatSheet The cheatsheet specified by the user
     */
    private void callContentEditor(CheatSheet desiredCheatSheet) {
        editor.open();
        editor.setContent(desiredCheatSheet);
        editor.waitForClose();

        try {
            desiredCheatSheet.setDetails(editor.getContent());
        } catch (EditorException e) {
            printer.print(e.getMessage());
        }
    }
}
