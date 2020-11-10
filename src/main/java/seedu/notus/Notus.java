package seedu.notus;

import org.fusesource.jansi.AnsiConsole;
import seedu.notus.command.Command;
import seedu.notus.command.ExitCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.notebook.Notebook;
import seedu.notus.data.tag.TagManager;
import seedu.notus.data.timetable.Timetable;
import seedu.notus.storage.StorageManager;
import seedu.notus.ui.AsciiArt;
import seedu.notus.ui.InterfaceManager;
import seedu.notus.ui.Formatter;
import seedu.notus.util.parser.ParserManager;

import java.io.IOException;

import static seedu.notus.storage.StorageManager.LOGS_DIR;

/**
 * Entry point of the NotUS application.
 */
public class Notus {
    private InterfaceManager interfaceManager;
    private StorageManager storageManager;
    private Notebook notebook;
    private Timetable timetable;
    private TagManager tagManager;
    private ParserManager parserManager;

    private static final String WELCOME_MSG_STRING = "Welcome to NotUS! "
            + AsciiArt.getNotusLogo() + Formatter.LS
            + "Type \"help\" if you need to see a list of commands and their usages.";
    private static final String ENTER_COMMAND_MSG = "Enter command:";

    /**
     * Initializes the required managers.
     */
    private void init() {
        StorageManager.createDirectory(LOGS_DIR);

        this.interfaceManager = new InterfaceManager();
        this.notebook = new Notebook();
        this.timetable = new Timetable();
        this.tagManager = new TagManager();
        this.parserManager = new ParserManager();
        this.storageManager = new StorageManager(timetable, parserManager, notebook, tagManager);

        try {
            storageManager.createFiles();

            storageManager.loadAllNotes(false);
            storageManager.loadAllNotes(true);
            storageManager.saveAllNoteDetails(false);
            storageManager.saveAllNoteDetails(true);
            storageManager.loadTimetable();

        } catch (SystemException exception) {
            interfaceManager.prints(exception.getMessage());
        } catch (IOException exception) {
            interfaceManager.prints("Unable to save to a file");
        }
        interfaceManager.prints(WELCOME_MSG_STRING);
    }

    /** Reads the user command and executes it until the user exits the program. */
    private void runCommandLoop() {
        Command command;

        do {
            interfaceManager.prints(ENTER_COMMAND_MSG);
            String userCommandText = interfaceManager.getUserCommandInput();
            command = parserManager.parseCommand(userCommandText);
            String result = executeCommand(command);
            interfaceManager.prints(result);
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Exits the application.
     */
    private void exit() {
        try {
            storageManager.saveAll();
        } catch (IOException exception) {
            interfaceManager.prints("Unable to save to a file");
        }
    }

    /** Runs the program until termination. */
    private void run() {
        init();
        runCommandLoop();
        exit();
    }

    /**
     * Calls the execute function of the command.
     *
     * @param command The command to be executed.
     * @return String of the executed command.
     */
    private String executeCommand(Command command) {
        command.setData(notebook, timetable, tagManager, storageManager);
        return command.execute();
    }

    /**
     * Main entry-point for the application.
     */
    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        new Notus().run();
        AnsiConsole.systemUninstall();
    }
}
