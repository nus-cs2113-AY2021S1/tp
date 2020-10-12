package seedu.duke.command;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;
import static seedu.duke.util.PrefixSyntax.PREFIX_LINE;
import static seedu.duke.util.PrefixSyntax.PREFIX_CONTENT;

public class EditNoteCommand extends Command {

    public static final String COMMAND_WORD = "edit-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Edits a note in the notebook. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE] "
            + "[" + PREFIX_DELIMITER + PREFIX_LINE + " LINE]"
            + "[" + PREFIX_DELIMITER + PREFIX_CONTENT + " CONTENT]"
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG TAG_COLOR"
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG1 TAG_COLOR...]";

    private int index;

    /**
     * Constructs an EditNoteCommand to edit a Note.
     *
     * @param index of the Note to be edited.
     */
    public EditNoteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
        return null;
    }
}
