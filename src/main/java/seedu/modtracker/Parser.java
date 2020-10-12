package seedu.modtracker;

/**
 * Parses user input.
 */
public class Parser {
    protected static boolean exit = false;

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
        case "addmod":
            modList.addMod(input, toPrint, storage);
            break;
        case "addtime":
            modList.addTime(input, toPrint, storage);
            break;
        case "addexp":
            modList.addExp(input, toPrint, storage);
            break;
        case "deletemod":
            modList.deleteMod(input, toPrint, storage);
            break;
        case "deleteexp":
            modList.deleteExp(input, toPrint, storage);
            break;
        case "minus":
            modList.minusTime(input, toPrint, storage);
            break;
        case "compare":
            //methods
            break;
        case "list":
            ui.printTable(modList, Integer.parseInt(command[1]));
            break;
        case "help":
            ui.printHelpList();
            break;
        case "exit":
            if (input.trim().length() > 4) {
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
