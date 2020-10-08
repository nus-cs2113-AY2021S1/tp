package seedu.duke;

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
    public static void parse(String input, ModuleList t, String name) {
        String[] command = input.trim().split(" ");

        switch (command[0].toLowerCase()) {
        case "addmod":
            t.addMod(input);
            break;
        case "addtime":
            //methods
            break;
        case "addexp":
            t.addExp(input);
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
