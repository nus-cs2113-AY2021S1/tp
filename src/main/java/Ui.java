import java.util.Scanner;

public class Ui {
    public static final String LINE_DIVIDER = "=======================================================================";

    /**
     * Receive command input from the user via terminal.
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
     * Prints text with line divider above and below the text.
     *
     * @param text any String type text
     */
    public static void printLine(String text) {
        System.out.println(LINE_DIVIDER + "\n" + text + "\n" + LINE_DIVIDER);
    }

    public static void printExit() {
        System.out.println(LINE_DIVIDER + "\nSee you again soon!!!\n" + LINE_DIVIDER);
    }

    public static void printLocation() {
        if (StudyIt.getCurrentMode() == Mode.MENU) {
            printLine("You are currently at the main menu!");
        } else {
            printLine("Location not found, there's issues with the program"); //TODO: Change this
        }
    }

    public static void changeMode(String command) {
        try {
            Mode newMode = CommandParser.getDestinationMode(command);
            StudyIt.changeMode(newMode);
            printLine("You are now at: " + ModeNames.getCurrentModeName());
        } catch (InvalidModeException e) {
            printLine("Invalid mode name! Please try again.\n"
                    + "You are still at: " + ModeNames.getCurrentModeName());
        }
    }

    public static void exitMode() {
        System.out.println("Exited " + ModeNames.getCurrentModeName() + "!");
        changeMode("cd menu"); //TODO: Check UI
    }
}