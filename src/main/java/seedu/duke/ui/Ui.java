package seedu.duke.ui;

import java.util.Scanner;

public class Ui {

    private static Scanner in = new Scanner(System.in);

    public static String getCommand() {
        return in.nextLine();
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help?");
    }

    public static void showError(Exception error) {
        System.out.println(error);
    }

    public static void commandList() {
    }

    public static void printExitMessage() {
        System.out.println("So long buddy!");
    }
    public static void printRouteSelectionMessage()
    {
        System.out.println("What bus routes would you like to see?\nCurrently, we have two bus routes available for" +
                "your viewing \n1.AA1 \n2.AA2 \nType the name to view:");
    }
}
