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
     * @param t modlist
     * @param name name entered by the user
     */
    public static void parse(String input, ModuleList t, String name, Storage storage) {
        String[] command = input.trim().split(" ");

        switch (command[0].toLowerCase()) {
        case "addmod":
            t.addMod(input);
            storage.appendToFile(input);
            break;
        case "addtime":
            //methods
            //storage.appendToFile(input);
            break;
        case "addexp":
            t.addExp(input);
            storage.appendToFile(input);
            break;
        case "deletemod":
            //methods
            //storage.appendToFile(input);
            break;
        case "deleteexp":
            //methods
            //storage.appendToFile(input);
            break;
        case "minus":
            //methods
            //storage.appendToFile(input);
            break;
        case "compare":
            //methods
            break;
        case "list":
            //Ui.printList(t.getData());
            Ui.printTable(t.getData(), Integer.parseInt(command[1]));
            break;
        case "help":
            Ui.printHelpList();
            break;
        case "exit":
            if (input.trim().length() > 4) {
                Ui.printInvalidCommand();
            } else {
                Ui.printExitScreen(name);
                exit = true;
            }
            break;
        default:
            Ui.printInvalidCommand();
        }
    }

    /**
     * Checks for exit status.
     *
     * @return status of exit
     */
    public static boolean isExit() {
        return exit;
    }

}
