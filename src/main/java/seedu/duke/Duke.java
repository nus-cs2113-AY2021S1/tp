package seedu.duke;

import java.util.Scanner;
import seedu.duke.constants.Logos;
import seedu.duke.settings.Settings;
import seedu.duke.ui.UI;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Settings userSettings = new Settings();
        System.out.println("Take a quiz with\n" + Logos.DOTTED_CLICKER_LOGO);
        System.out.println("What is your name?");
        userSettings.setName(SCANNER.nextLine());
        System.out.println("Hello " + userSettings.getName());
        System.out.println("What would you like me to do for you today?");
        String command = SCANNER.next();
        if(command.equals("settings")) {
            if(SCANNER.hasNextInt()) {
               userSettings.setDividerNum(SCANNER.nextInt());
            } else {
                userSettings.setName(SCANNER.nextLine());
            }
        } else if(command.equals("help")) {
            String next = SCANNER.next();
            UI.printHelpMessage(next);
        }
    }
}
