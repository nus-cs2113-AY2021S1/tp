package seedu.duke.command;

import seedu.duke.data.notebook.Note;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static seedu.duke.ui.InterfaceManager.printFindNotesMessage;

/**
 * Finds Notes in the Notebook.(Possible to add find in event too)
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Finds a note. Parameters: ";

    private String keywords;

    /**
     * Constructs a FindCommand to find Notes in the Notebook given the keyword.
     *
     * @param keywords to look for in the Notebook.
     */
    public FindCommand(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String execute() {
        ArrayList<Note> filteredNotes = (ArrayList<Note>) notebook.getNotes().stream()
                .filter((s) -> s.getTitle().contains(keywords))
                .collect(Collectors.toList());

        printFindNotesMessage(filteredNotes);
        return null;
    }
}
