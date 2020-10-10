import java.util.Scanner;

public class Ui {
    public static final String LINE_DIVIDER = "==================================================================";

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
}
