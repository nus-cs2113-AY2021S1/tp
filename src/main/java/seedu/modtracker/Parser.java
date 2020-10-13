package seedu.modtracker;

/**
 * Parses user input.
 */
public class Parser {
    protected static boolean exit = false;
    public static final String COMMAND_ADDMOD = "addmod";
    public static final String COMMAND_ADDTIME = "addtime";
    public static final String COMMAND_ADDEXP = "addexp";
    public static final String COMMAND_DELETEMOD = "deletemod";
    public static final String COMMAND_DELETEEXP = "deleteexp";
    public static final String COMMAND_MINUS = "minus";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_EXIT = "exit";
    public static final int COMMAND_EXIT_LENGTH = 4;

    /**
     * Parses user inputs.
     *
     * @param input user input
     * @param modList module list
     * @param name name entered by the user
     * @param storage storage object to load and store data
     * @param toPrint whether the UI should print the output
     */
    public void parse(String input, ModuleList modList, String name, Storage storage, boolean toPrint) {
        Ui ui = new Ui();
        String[] command = input.trim().split(" ");

        switch (command[0].toLowerCase()) {
        case COMMAND_ADDMOD:
            modList.addMod(input, toPrint, storage);
            break;
        case COMMAND_ADDTIME:
            modList.addTime(input, toPrint, storage);
            break;
        case COMMAND_ADDEXP:
            modList.addExp(input, toPrint, storage);
            break;
        case COMMAND_DELETEMOD:
            modList.deleteMod(input, toPrint, storage);
            break;
        case COMMAND_DELETEEXP:
            modList.deleteExp(input, toPrint, storage);
            break;
        case COMMAND_MINUS:
            modList.minusTime(input, toPrint, storage);
            break;
        case COMMAND_LIST:
            ui.printTable(modList, Integer.parseInt(command[1]));
            break;
        case COMMAND_HELP:
            ui.printHelpList();
            break;
        case COMMAND_EXIT:
            if (input.trim().length() > COMMAND_EXIT_LENGTH) {
                ui.printInvalidCommand();
            } else {
                ui.printExitScreen(name);
                exit = true;
            }
            break;
        default:
            ui.printInvalidCommand();
        }
    }

    /**
     * Checks for exit status.
     *
     * @return status of exit
     */
    public boolean isExit() {
        return exit;
    }

}
