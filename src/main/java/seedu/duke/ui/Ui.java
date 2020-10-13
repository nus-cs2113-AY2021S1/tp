package seedu.duke.ui;

import java.util.Scanner;

public class Ui {
    private final String dividerLine = "____________________________________________________________";
    private final String logo = "__________                              ___________                  .___      \n"
            + "\\______   \\_____  ______   ___________  \\__    ___/___________     __| _/____  \n"
            + " |     ___/\\__  \\ \\____ \\_/ __ \\_  __ \\   |    |  \\_  __ \\__  \\   / __ |/ __ \\ \n"
            + " |    |     / __ \\|  |_> >  ___/|  | \\/   |    |   |  | \\// __ \\_/ /_/ \\  ___/ \n"
            + " |____|    (____  /   __/ \\___  >__|      |____|   |__|  (____  /\\____ |\\___  >\n"
            + "                \\/|__|        \\/                              \\/      \\/    \\/ ";

    public void greetUser() {
        print(logo);
        printWithDivider("Welcome to the Command Line Paper Trading App!");
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void printWithDivider(String... messages) {
        print(dividerLine);
        for (String message: messages) {
            print(message);
        }
        print(dividerLine);
    }

    public String getUserInput() {
        print("What would you like to do today?");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        return userInput;
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public boolean sayBye(String userInput) {
        if (userInput.contains("bye")) {
            return true;
        }
        return false;
    }
}
