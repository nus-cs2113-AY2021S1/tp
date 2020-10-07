package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.ui.InterfaceManager;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Finds Notes in the Notebook.(Possible to add find in event too)
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find-n";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": Finds a note. Parameters: KEYWORDS";

    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

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
        String notes = "";

        ArrayList<Note> filteredNotes = (ArrayList<Note>) notebook.getNotes().stream()
                .filter((s) -> s.getTitle().contains(keywords))
                .collect(Collectors.toList());

        for (int i = 0; i < filteredNotes.size(); i++) {
            notes += (i + 1) + "." + filteredNotes.get(i).toString();
        }

        if (filteredNotes.isEmpty()) {
            return "There are no matching notes. Please try another search query." + InterfaceManager.LS;
        }

        return "Here are the matching notes in your list:" + InterfaceManager.LS + notes;
    }
}
