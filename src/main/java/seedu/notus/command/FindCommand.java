package seedu.notus.command;

import seedu.notus.data.notebook.Note;
import seedu.notus.ui.Formatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//@@author R-Ramana
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

        ArrayList<Note> filteredNotes = notebook.findNotes(keywords);

        LOGGER.log(Level.INFO, "Filtered Notes into an ArrayList of notes. "
                + "Filtered Notes only has notes with title containing the keyword.");

        if (filteredNotes.isEmpty()) {
            LOGGER.log(Level.INFO, "Filtered notes is empty.");
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
        }
        LOGGER.log(Level.INFO, "Filtered notes will be returned.");
        return Formatter.formatNotes(COMMAND_SUCCESSFUL_MESSAGE, filteredNotes);
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
