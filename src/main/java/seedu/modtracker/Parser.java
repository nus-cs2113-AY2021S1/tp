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
     * @param isSetup true if data is being loaded
     */
    public void parse(String input, ModuleList modList, String name, Storage storage, boolean isSetup) {
        Ui ui = new Ui();
        String[] command = input.trim().split(" ");

        switch (command[0].toLowerCase()) {
        case "addmod":
            modList.addMod(input, !isSetup);
            if (!isSetup) {
                storage.appendToFile(input);
            }
            break;
        case "addtime":
            modList.addTime(input, !isSetup);
            if (!isSetup) {
                storage.appendToFile(input);
            }
            break;
        case "addexp":
            modList.addExp(input, !isSetup);
            if (!isSetup) {
                storage.appendToFile(input);
            }
            break;
        case "deletemod":
            modList.deleteMod(input, !isSetup);
            if (!isSetup) {
                storage.appendToFile(input);
            }
            break;
        case "deleteexp":
            modList.deleteExp(input, !isSetup);
            if (!isSetup) {
                storage.appendToFile(input);
            }
            break;
        case "minus":
            modList.minusTime(input, !isSetup);
            if (!isSetup) {
                storage.appendToFile(input);
            }
            break;
        case "compare":
            //methods
            break;
        case "list":
            ui.printTable(modList.getData(), Integer.parseInt(command[1]));
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
