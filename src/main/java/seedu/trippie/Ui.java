package seedu.trippie;

import java.util.Scanner;

public class Ui {

    Scanner in = new Scanner(System.in);

    /**
     * Prints message when startup.
     */
    public void greetUser() {
        printLogo();
        printLine();
    }

    /**
     * Prints message when program is closed.
     */
    public void showFarewells() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    private void printLogo() {
        String logo = "  _            _   ____   ____   _            __T___T__   \n"
                + " _| |_   ____ |_| | __ \\ | __ \\ |_|   ____   /         \\ \n"
                + "|_   _| /  __\\ _  | |_| || |_| | _   / __ \\ | |_|   |_| |  \n"
                + "  | | _ | |   | | |  __/ |  __/ | | | |__|_||    ___    |\n"
                + "  | |/ || |   | | | |    | |    | | | \\____  \\  \\WWW/  /\n"
                + "   \\__/ |_|   |_| |_|    |_|    |_|  \\____/   \\_______/\n";
        System.out.println("Welcome to\n" + logo
                + "\n\t\t\t\t\t\t\t" + "Travel made easy");
    }

    public void printLine() {
        System.out.println("_________________________________________________________________________");
    }

    /**
     * Takes in user input for parsing.
     *
     * @return String for parsing.
     */
    public String readCommand() {
        System.out.print(">> ");
        return in.nextLine();
    }

    public String getLine() {
        return in.nextLine();
    }

}
