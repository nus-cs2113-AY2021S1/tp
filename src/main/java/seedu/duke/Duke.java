package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.level.Admin;
import seedu.duke.tool.Access;
import seedu.duke.tool.Parser;
import seedu.duke.tool.Ui;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private Ui ui;
    private Admin admin;
    private Access access;

    public Duke() {
        ui = new Ui();
        admin = new Admin();
        access = new Access();
    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse(fullCommand);
            //System.out.println(fullCommand);
            c.execute(access, ui, admin);
            isExit = c.isExit();
            ui.showLine();
            System.out.println(access.getLevel());
        }
    }

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

        new Duke().run();
    }
}
