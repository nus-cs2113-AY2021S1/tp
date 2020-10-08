package seedu.duke;

import seedu.duke.classes.Storage;
import seedu.duke.utility.ShowList;
import seedu.duke.utility.Ui;
import seedu.duke.utility.InputParser;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private Storage storage;
    //public ShowList shows;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            new ShowList(/*storage.loadState()*/);
        } catch (Exception e) {
            //ui.showLoadingError();
            new ShowList();
        }
    }

    public void run() {
        //...
        /*ui.hello();
        Scanner scan = new Scanner(System.in);
        InputParser parseManager = new InputParser();
        while (!parseManager.isByeTime()) {
            Ui.printLineIcon();
            String input = scan.nextLine();
            parseManager.parseInput(input);
        }*/
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}

