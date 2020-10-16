package seedu.duke;

import com.sun.jna.Function;
import com.sun.jna.platform.win32.WinDef;
import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.data.notebook.Notebook;
import seedu.duke.data.notebook.TagManager;
import seedu.duke.data.timetable.Timetable;
import seedu.duke.storage.StorageManager;
import seedu.duke.ui.InterfaceManager;
import seedu.duke.util.Parser;

import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.Wincon;
import static com.sun.jna.platform.win32.Kernel32.INSTANCE;
import static com.sun.jna.platform.win32.WinBase.INVALID_HANDLE_VALUE;
import static com.sun.jna.platform.win32.Wincon.STD_OUTPUT_HANDLE;

/**
 * Entry point of the NotUS application.
 */
public class Duke {

    private static final int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 0x0200;

    private InterfaceManager interfaceManager;
    private StorageManager storageManager;
    private Notebook notebook;
    private Timetable timetable;
    private TagManager tagManager;

    private static final String WELCOME_MSG_STRING = "Welcome to NotUS! "
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

    private String executeCommand(Command command) {
        command.setData(notebook, timetable, tagManager, storageManager);
        return command.execute();
    }

    /**
     * Main entry-point for the application.
     */
    public static void main(String[] args) {
        /*WinNT.HANDLE handle = INSTANCE.GetStdHandle(Wincon.STD_OUTPUT_HANDLE);

        if (handle == INVALID_HANDLE_VALUE) {
            System.out.println(42);
        }

        boolean result = INSTANCE.SetConsoleMode(handle, 4);*/

        Function GetStdHandleFunc = Function.getFunction("kernel32", "GetStdHandle");
        WinDef.DWORD STD_OUTPUT_HANDLE = new WinDef.DWORD(Wincon.STD_OUTPUT_HANDLE);
        WinNT.HANDLE hOut = (WinNT.HANDLE)GetStdHandleFunc.invoke(WinNT.HANDLE.class, new Object[]{STD_OUTPUT_HANDLE});

        WinDef.DWORDByReference p_dwMode = new WinDef.DWORDByReference(new WinDef.DWORD(0));
        Function GetConsoleModeFunc = Function.getFunction("kernel32", "GetConsoleMode");
        GetConsoleModeFunc.invoke(WinDef.BOOL.class, new Object[]{hOut, p_dwMode});

        int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 4;
        WinDef.DWORD dwMode = p_dwMode.getValue();
        dwMode.setValue(dwMode.intValue() | ENABLE_VIRTUAL_TERMINAL_PROCESSING);
        Function SetConsoleModeFunc = Function.getFunction("kernel32", "SetConsoleMode");
        SetConsoleModeFunc.invoke(WinDef.BOOL.class, new Object[]{hOut, dwMode});

        new Duke().run();
    }
}
