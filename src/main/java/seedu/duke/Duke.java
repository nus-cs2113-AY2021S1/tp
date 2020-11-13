package seedu.duke;

import seedu.duke.classes.Storage;
import seedu.duke.classes.WatchTime;
import seedu.duke.utility.InputParser;
import seedu.duke.utility.ShowList;
import seedu.duke.utility.Ui;

import static seedu.duke.utility.Ui.SAVE_DIRECTORY;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private Storage storage;
    private ShowList shows;
    private Ui ui;
    private WatchTime watchTime;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.shows = storage.loadState();
        } catch (Exception e) {
            this.shows = new ShowList();
        }
        watchTime = new WatchTime(storage.getRecordedDate(),
                storage.getDurationWatchedToday(), storage.getDailyWatchLimit());

    }

    public void run() {

        ui.hello();
        InputParser parseManager = new InputParser();
        while (!parseManager.isByeTime()) {
            Ui.promptUser();
            try {
                storage.saveState();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            String input = ui.getUserCommand();
            parseManager.parseInput(input);
        }
    }

    public static void main(String[] args) {
        new Duke(SAVE_DIRECTORY).run();
    }
}

