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
    private UserData data;
    private Ui ui;
    private Command com;
    private Parser currentParse;

    //Location refers to the "data" directory stored in the folder of this application
    private static final String FILELOCATION = "data";


    /**
     * Constructor for the Schedule components.
     *
     * @param filePath is a string containing the location of where the data text files are to be stored
     */
    public Duke(String filePath) {
        ui = new Ui();

        storage = new Storage(filePath);

        data = new UserData();

        currentParse = new Parser();

    }

    /**
     * Main run function. Loops until the bye command is entered.
     */
    public void run() {

        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.receiveCommand();
            ui.printDividerLine();
            Command c = currentParse.parse(userInput);
            isExit = c.isExit();
            c.execute(data, ui, storage);
        }
        ui.printByeMessage();

    }

    /**
     * Main function where the program starts.
     *
     * @param args No arguments entered so far
     */
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());*/
        Duke duke = new Duke(FILELOCATION);
        duke.run();
    }


}
