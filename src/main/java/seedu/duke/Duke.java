package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.data.UserData;
import seedu.duke.event.EventList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.nio.file.Path;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private UserData tasks;
    private Ui ui;
    private Command com;
    private Parser parse;

    //Location refers to the "data" directory stored in the folder of this application
    private final String FILELOCATION = "data";

    /**
     * Main entry-point for the java.duke.Duke application.
     */


    public Duke(String filePath) {
        ui = new Ui();

        storage = new Storage(filePath);

        tasks = new UserData();

        parse = new Parser();

    }

    public void run() {
        

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
    }


}
