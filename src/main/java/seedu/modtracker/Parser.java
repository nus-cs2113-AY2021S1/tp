package seedu.modtracker;

/**
 * Parses user input.
 */
public class Parser {
    protected boolean exit = false;
    protected boolean restart = false;
    public static final String COMMAND_ADDMOD = "addmod";
    public static final String COMMAND_ADDEXP = "addexp";
    public static final String COMMAND_DELETEMOD = "deletemod";
    public static final String COMMAND_DELETEEXP = "deleteexp";
    public static final String COMMAND_OPEN_NOTIFICATION = "open";
    public static final String COMMAND_MINUSTIME = "minustime";
    public static final String COMMAND_EDITTIME = "edittime";
    public static final String COMMAND_DELETETIME = "deletetime";
    public static final String COMMAND_ADDTIME = "addtime";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_ANALYSIS = "analyse";
    public static final String COMMAND_ADDTASK = "addtask";
    public static final String COMMAND_DELETETASK = "deletetask";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_LISTTASK = "listtask";
    public static final String COMMAND_CLEAR = "clear";
    public static final String COMMAND_RESET = "reset";

    /**
     * Parses user inputs.
     *
     * @param input    user input
     * @param modList  module list
     * @param name     name entered by the user
     * @param storage  storage object to load and store data
     * @param toPrint  whether the UI should print the output
     * @param taskList task list
     */
    public void parse(String input, ModuleList modList, String name, Storage storage,
                      boolean toPrint, TaskList taskList) {
        Notification notification = new Notification();
        Ui ui = new Ui();
        assert input != null : "Input not null";

        String[] command = input.trim().split(" ");

        switch (command[0].toLowerCase()) {
        case COMMAND_ADDMOD:
            modList.addMod(input, toPrint, storage);
            break;
        case COMMAND_ADDEXP:
            modList.addExp(input, toPrint, storage);
            break;
        case COMMAND_DELETEMOD:
            modList.deleteMod(input, toPrint, storage);
            break;
        case COMMAND_EDITTIME:
            try {
                modList.editTime(input, toPrint, storage);
            } catch (IllegalArgumentException b) {
                ui.printInvalidTime();
            } catch (Exception e) {
                ui.printErrorMessage(COMMAND_EDITTIME);
            }
            break;
        case COMMAND_DELETEEXP:
            modList.deleteExp(input, toPrint, storage);
            break;
        case COMMAND_OPEN_NOTIFICATION:
            if (isToPrintTrue(toPrint, ui, input)) {
                input = input.toLowerCase().trim();
                if (input.equals(COMMAND_OPEN_NOTIFICATION)) {
                    notification.printNotification(modList);
                } else {
                    ui.printInvalidCommand();
                }
            }
            break;
        case COMMAND_ADDTIME:
            try {
                modList.addTime(input, toPrint, storage);
            } catch (IllegalArgumentException b) {
                ui.printInvalidTime();
            } catch (Exception e) {
                ui.printErrorMessage(COMMAND_ADDTIME);
            }
            break;
        case COMMAND_MINUSTIME:
            try {
                modList.minusTime(input, toPrint, storage);
            } catch (IllegalArgumentException b) {
                ui.printInvalidTime();
            } catch (Exception e) {
                ui.printErrorMessage(COMMAND_MINUSTIME);
            }
            break;
        case COMMAND_DELETETIME:
            try {
                modList.deleteTime(input, toPrint, storage);
            } catch (Exception e) {
                ui.printErrorMessage(COMMAND_DELETETIME);
            }
            break;
        case COMMAND_LIST:
            if (isToPrintTrue(toPrint, ui, input)) {
                try {
                    ui.printTable(modList, Integer.parseInt(command[1]));
                } catch (Exception e) {
                    ui.printErrorMessage(COMMAND_LIST);
                }
            }
            break;
        case COMMAND_ANALYSIS:
            if (isToPrintTrue(toPrint, ui, input)) {
                try {
                    ui.printBreakDownAnalysis(modList, Integer.parseInt(command[1]));
                } catch (Exception e) {
                    ui.printErrorMessage(COMMAND_ANALYSIS);
                }
            }
            break;
        case COMMAND_ADDTASK:
            taskList.addTask(input, toPrint, storage);
            break;
        case COMMAND_DELETETASK:
            taskList.deleteTasks(input, toPrint, storage);
            break;
        case COMMAND_DONE:
            taskList.setDone(input, toPrint, storage);
            break;
        case COMMAND_LISTTASK:
            if (isToPrintTrue(toPrint, ui, input)) {
                ui.printTaskList(taskList);
            }
            break;
        case COMMAND_HELP:
            if (isToPrintTrue(toPrint, ui, input)) {
                if (!input.toLowerCase().trim().equals(COMMAND_HELP)) {
                    ui.printInvalidCommand();
                } else {
                    ui.printHelpList();
                }
            }
            break;
        case COMMAND_EXIT:
            if (isToPrintTrue(toPrint, ui, input)) {
                if (!input.toLowerCase().trim().equals(COMMAND_EXIT)) {
                    ui.printInvalidCommand();
                } else {
                    ui.printExitScreen(name);
                    exit = true;
                }
            }
            break;
        case COMMAND_CLEAR:
            if (isToPrintTrue(toPrint, ui, input)) {
                if (ui.confirmClear()) {
                    storage.clearData();
                    modList.clear();
                    taskList.clear();
                }
            }
            break;
        case COMMAND_RESET:
            if (isToPrintTrue(toPrint, ui, input)) {
                if (ui.confirmReset()) {
                    storage.reset();
                    modList.clear();
                    taskList.clear();
                    restart = true;
                    exit = true;
                }
            }
            break;
        default:
            if (isToPrintTrue(toPrint, ui, input)) {
                ui.printInvalidCommand();
            }
            break;
        }
    }

    private boolean isToPrintTrue(boolean toPrint, Ui ui, String input) {
        if (!toPrint) {
            ui.printDataError(input);
            return false;
        }
        return true;
    }

    /**
     * Checks for exit status.
     *
     * @return status of exit.
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Checks for whether the program wants to restart.
     *
     * @return status of restart.
     */
    public boolean isRestart() {
        return restart;
    }
}
