package seedu.duke;

import java.util.Scanner;
import seedu.duke.tysPackage.tysMain;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        int check = 1;
        if (check==1) {
            System.out.println("Testin for forking workflow");
        }
        if (1 == 1)
        {
            System.out.println("true");
        }

        tysMain.testShowFunction();
    }
}

// jusufn