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
     * @param modules modlist
     * @param name name entered by the user
     */
    public void parse(String input, ModuleList modules, String name) {
        Ui ui = new Ui();
        String[] command = input.trim().split(" ");

        switch (command[0].toLowerCase()) {
        case "addmod":
            modules.addMod(input);
            break;
        case "addtime":
            //methods
            break;
        case "addexp":
            modules.addExp(input);
            break;
        case "deletemod":
            //methods
            break;
        case "deleteexp":
            //methods
            break;
        case "minus":
            //methods
            break;
        case "compare":
            //methods
            break;
        case "list":
            ui.printTable(modules.getData(), Integer.parseInt(command[1]));
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
