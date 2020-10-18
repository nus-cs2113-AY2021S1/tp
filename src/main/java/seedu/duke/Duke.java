package seedu.duke;

import com.sun.jna.Function;
import com.sun.jna.platform.win32.Wincon;
import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.data.notebook.Notebook;
import seedu.duke.data.notebook.TagManager;
import seedu.duke.data.timetable.Timetable;
import seedu.duke.storage.StorageManager;
import seedu.duke.ui.AsciiArt;
import seedu.duke.ui.InterfaceManager;
import seedu.duke.ui.Formatter;
import seedu.duke.util.Parser;

import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;

/**
 * Entry point of the NotUS application.
 */
public class Duke {

    private static final int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 4;

    private InterfaceManager interfaceManager;
    private StorageManager storageManager;
    private Notebook notebook;
    private Timetable timetable;
    private TagManager tagManager;

    private static final String WELCOME_MSG_STRING = "Welcome to NotUS! "
            + AsciiArt.getNotusLogo() + Formatter.LS
            + "Type \"help\" if you need to see a list of commands and their usages.";
    private static final String ENTER_COMMAND_MSG = "Enter command:";

    /**
     * Initializes the required managers.
     */
    private void init() {
        this.interfaceManager = new InterfaceManager();
        this.storageManager = new StorageManager();
        this.notebook = new Notebook();
        this.timetable = new Timetable();
        this.tagManager = new TagManager();

        interfaceManager.prints(WELCOME_MSG_STRING);
    }

    /** Reads the user command and executes it until the user exits the program. */
    private void runCommandLoop() {
        Command command;

        do {
            interfaceManager.prints(ENTER_COMMAND_MSG);
            String userCommandText = interfaceManager.getUserCommandInput();
            command = new Parser().parseCommand(userCommandText);
            String result = executeCommand(command);
            interfaceManager.prints(result);
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Exits the application.
     */
    private void exit() {
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
     * Sets windows console mode to enable virtual terminal processing to allow printing of ansi escape code.
     */
    private static void enableVirtualTerminalProcessing() {
        Function getStdHandleFunc = Function.getFunction("kernel32", "GetStdHandle");
        WinDef.DWORD stdOutputHandle = new WinDef.DWORD(Wincon.STD_OUTPUT_HANDLE);
        WinNT.HANDLE outputHandle = (WinNT.HANDLE) getStdHandleFunc.invoke(
                WinNT.HANDLE.class, new Object[]{stdOutputHandle});

        WinDef.DWORDByReference doubleWord = new WinDef.DWORDByReference(new WinDef.DWORD(0));
        Function getConsoleModeFunc = Function.getFunction("kernel32", "GetConsoleMode");
        getConsoleModeFunc.invoke(WinDef.BOOL.class, new Object[]{outputHandle, doubleWord});

        WinDef.DWORD dwMode = doubleWord.getValue();
        dwMode.setValue(dwMode.intValue() | ENABLE_VIRTUAL_TERMINAL_PROCESSING);
        Function setConsoleModeFunc = Function.getFunction("kernel32", "SetConsoleMode");
        setConsoleModeFunc.invoke(WinDef.BOOL.class, new Object[]{outputHandle, dwMode});
    }

    /**
     * Main entry-point for the application.
     */
    public static void main(String[] args) {
        if (System.getProperty("os.name").startsWith("Windows")) {
            enableVirtualTerminalProcessing();
        }
        new Duke().run();
    }
}
