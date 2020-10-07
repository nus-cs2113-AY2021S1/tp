package seedu.duke;

/**
 * Parses user input.
 */
public class Parser {
    protected static boolean exit = false;

    public static void parse(String input, ModuleList t) {
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
            //methods
            break;
        case "exit":
            if (input.trim().length() > 4) {
                Ui.printInvalidCommand();
            } else {
                Ui.printExitScreen();
                exit = true;
            }
            break;
        default:
            Ui.printInvalidCommand();
        }
    }

    /**
     * Prints the week number, module code, expected workload and actual time spent
     * in the specified week for all the modules taken.
     * GL testing stuff
     *
     * @return status of exit
     */
    public static boolean isExit() {
        return exit;
    }

}
