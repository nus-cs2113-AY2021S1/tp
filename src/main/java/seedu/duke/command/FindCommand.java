package seedu.duke.command;

import seedu.duke.data.notebook.Note;
import seedu.duke.ui.Formatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

import static seedu.duke.util.PrefixSyntax.SUFFIX_INDEX;

/**
 * Finds Notes in the Notebook.(Possible to add find in event too)
 */
public class FindCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("FindCommand");

    public static final String COMMAND_WORD = "find-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Finds a note. Parameters: KEYWORDS";

    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "There are no matching notes. "
            + "Please try another search query.";
    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Here are the matching notes in your list:";

    private String keywords;

    /**
     * Constructs a FindCommand to find Notes in the Notebook given the keyword.
     *
     * @param keywords to look for in the Notebook.
     */
    public FindCommand(String keywords) {
        this.keywords = keywords;
    }

    /**
     * Filters and finds notes that has the title containing the user inputted keyword.
     * Stores the filtered notes in an ArrayList of Note
     *
     * @return String containing the filtered list of notes
     */
    @Override
    public String execute() {
        setupLogger();
        LOGGER.log(Level.INFO, "Logger Setup, will proceed to execute FindCommand.");

        StringBuilder notes = new StringBuilder();

        ArrayList<Note> filteredNotes = (ArrayList<Note>) notebook.getNotes().stream()
                .filter((s) -> s.getTitle().toLowerCase().contains(keywords.toLowerCase()))
                .collect(Collectors.toList());

        LOGGER.log(Level.INFO, "Filtered Notes into an ArrayList of notes. "
                + "Filtered Notes only has notes with title containing the keyword.");


        for (int i = 0; i < filteredNotes.size(); i++) {
            notes.append(i + 1)
                    .append(SUFFIX_INDEX)
                    .append(filteredNotes.get(i).getTitle())
                    .append(" ")
                    .append(filteredNotes.get(i).getTagsName())
                    .append(Formatter.LS);
        }

        LOGGER.log(Level.INFO, "Filtered notes stored as a string to be returned.");

        if (filteredNotes.isEmpty()) {
            LOGGER.log(Level.INFO, "Filtered notes is empty.");
            return COMMAND_UNSUCCESSFUL_MESSAGE;
        }
        LOGGER.log(Level.INFO, "Filtered notes will be returned.");
        return COMMAND_SUCCESSFUL_MESSAGE + Formatter.LS + notes;
    }

    public void setupLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.INFO);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        LOGGER.addHandler(consoleHandler);

        try {
            FileHandler fileHandler = new FileHandler("FindCommand.log");
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            LOGGER.addHandler(fileHandler);
        } catch (IOException error) {
            LOGGER.log(Level.SEVERE, "File logger not working.", error);
        }
    }
}
