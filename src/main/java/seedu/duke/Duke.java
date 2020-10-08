package seedu.duke;

import seedu.duke.bookmark.BookmarkList;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.slot.SlotList;

public class Duke {

    private Storage storage;
    private BookmarkList bookmarks;
    private SlotList slotList;
    private Ui ui;

    /**
     * Constructs a new Duke instance.
     * Pass the filepath of the txt file to set up storage.
     *
     * @param filePath The filepath of the txt file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            bookmarks = new BookmarkList(storage.load());
            // create slot list
        } catch (DukeException e) {
            ui.showLoadingError();
            bookmarks = new BookmarkList();
            //create new slot list
        }
        //try {
        //    load timetable here
        //    if use another text file to save timetable, then pass another filepath to Duke constructor
        //    and create another Storage object
        //} catch (DukeException e) {

        //}
    }

    /**
     * This method is used run the Duke program.
     */
    public void run() {
        boolean isExit = false;

        ui.showWelcomeScreen();

        do {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(bookmarks, slotList, ui, storage);  // pass timetable here
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        } while (!isExit);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("./data/data.txt").run();
    }
}