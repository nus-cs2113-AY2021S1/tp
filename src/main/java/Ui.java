import java.util.Scanner;

public class Ui {
    public static final String LINE_DIVIDER = "=======================================================================";

    /**
     * Receive command input from the user via terminal
     *
     * @return the command input as a String
     */
    public static String inputCommand() {
        String command;
        Scanner in = new Scanner(System.in);

        command = in.nextLine();

        return command;
    }

    /**
     * Prints text with line divider above and below the text
     * @param text any String type text
     */
    public static void printLine(String text) {
        System.out.println(LINE_DIVIDER + "\n" + text + "\n" + LINE_DIVIDER);
    }

    public static void printExit() {
        System.out.println(LINE_DIVIDER + "\nSee you again soon!!!\n" + LINE_DIVIDER);
    }

    public static void printLocation() {
        if (StudyIt.mode == Mode.INITIAL) {
            printLine("You are currently at the main menu!");
        } else {
            printLine("Location not found, there's issues with the program"); //TODO: Change this
        }
    }
}
