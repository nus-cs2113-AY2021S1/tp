package seedu.duke.tool;

import java.util.Scanner;

public class Ui {
    public static final String lineSplit = "    ____________________________________________________________";
    public static final String  logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints line for splitting contents.
     */
    public void showLine() {
        System.out.println(lineSplit);
    }

    /**
     * Read the command entered by the user.
     * Returns the command entered by the user.
     *
     * @return String of command entered by the user.
     */
    public String readCommand() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

}
