package seedu.duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main entry-point for the java.duke.Duke application.
 */
public class Duke {
    private static ModuleList t;

    public static void main(String[] args) {
        Ui.printWelcomeScreen();
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        run(t);
    }

    public static void run(ModuleList t) {
        boolean isExit = false;
        while (!isExit) {
            String input = Ui.readCommand();
            Parser.parse(input, ModuleList.modList);
            isExit = Parser.isExit();
        }
    }
}
