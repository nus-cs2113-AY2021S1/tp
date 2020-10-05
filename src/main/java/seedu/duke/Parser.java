package seedu.duke;

import java.util.ArrayList;

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
            Ui.printList(t.getData());
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
     * Returns true if user types in "bye".
     *
     * @return status of exit
     */
    public static boolean isExit() {
        return exit;
    }

}
