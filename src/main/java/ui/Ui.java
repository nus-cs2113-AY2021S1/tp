package ui;

import java.util.Scanner;

// This class manages the input of CheatLogs.
public class Ui {
    Scanner in = new Scanner(System.in);

    /**
     * Gets the input in the terminal after the user hits Enter.
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        in.close();
    }
}
