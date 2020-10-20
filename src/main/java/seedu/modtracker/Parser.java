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
        assert input != null;
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
        case COMMAND_DELETEEXP:
            modList.deleteExp(input, toPrint, storage);
            break;
        case COMMAND_ADDTIME:
            try {
                modList.addTime(input, toPrint, storage);
            } catch (Exception e) {
                ui.printErrorMessage(e.getMessage());
                System.out.println("");
            }
            break;
        case COMMAND_MINUS:
            try {
                modList.minusTime(input, toPrint, storage);
            } catch (Exception e) {
                ui.printErrorMessage(e.getMessage());
                System.out.println("");
            }
            break;
        case COMMAND_LIST:
            assert toPrint : "toPrint should be true";
            try {
                ui.printTable(modList, Integer.parseInt(command[1]));
            } catch (Exception e) {
                ui.printErrorMessage(e.getMessage());
                System.out.println("");
            }
            break;
        case COMMAND_HELP:
            assert toPrint : "toPrint should be true";
            if (!input.toLowerCase().trim().equals(COMMAND_HELP)) {
                ui.printInvalidCommand();
            } else {
                ui.printHelpList();
            }
            break;
        case COMMAND_EXIT:
            assert toPrint : "toPrint should be true";
            if (!input.toLowerCase().trim().equals(COMMAND_EXIT)) {
                ui.printInvalidCommand();
            } else {
                ui.printExitScreen(name);
                exit = true;
            }
            break;
       //Please update accordingly
        case "breakdown":
            ui.printBreakDownAnalysis(modList,1);
            break;
        default:
            assert toPrint : "toPrint should be true";
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
