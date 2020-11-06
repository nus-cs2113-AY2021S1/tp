package commands;

import access.Access;
import exception.StorageDataException;
import exception.InvalidInputException;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

/**
 * Represents an EditCommand which contains methods used in the various EditCommand classes.
 */
public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MODULE_PARAMETERS = " MODULE_INDEX MODULE_NAME";

    public static final String CHAPTER_PARAMETERS = " CHAPTER_INDEX CHAPTER_NAME";

    public static final String CARD_PARAMETERS = " FLASHCARD_INDEX q:QUESTION | a:ANSWER";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit the module name / chapter name / flashcard content.\n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "           " + CHAPTER_PARAMETERS + "\n"
            + "           " + CARD_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " 1 CS2113T\n"
            + "         " + COMMAND_WORD + " 2 Chapter 2\n"
            + "         " + COMMAND_WORD + " 3 q:What is the result of one plus one | a:two\n";

    public static final String MESSAGE_BEFORE_EDIT = "The following %1$s will be edited:\n";
    public static final String MESSAGE_AFTER_EDIT = "Edited %1$s:\n";

    @Override
    public abstract void execute(Ui ui, Access access, Storage storage)
            throws InvalidInputException, IOException, StorageDataException;

    /**
     * Constructs the result of the command execution before editing the content.
     *
     * @param type module, chapter or card type
     * @param content content to be edited
     * @return result to be displayed
     */
    protected String prepareBeforeEdit(String type, String content) {
        StringBuilder result = new StringBuilder();
        result.append(String.format(MESSAGE_BEFORE_EDIT, type));
        result.append(content).append("\n");
        return result.toString();
    }

    /**
     * Constructs the result of the command execution after editing the content.
     *
     * @param type module, chapter or card type
     * @param content content it is edited to
     * @return result to be displayed
     */
    protected String prepareAfterEdit(String type, String content) {
        StringBuilder result = new StringBuilder();
        result.append(String.format(MESSAGE_AFTER_EDIT, type));
        result.append(content);
        return result.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
