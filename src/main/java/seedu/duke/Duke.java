package seedu.duke;

import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.slot.SlotList;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.slot.Timetable;

import java.io.File;
import java.util.ArrayList;

public class Duke {

    private Storage<BookmarkList> bookmarkStorage;
    private Storage<Timetable> timetableStorage;
    private Storage<Timetable> plannerStorage;
    private BookmarkList bookmarks;
    private Timetable timetable;
    private Timetable planner;
    //private ArrayList<File> files;
    private Ui ui;

    /**
     * Constructs a new Duke instance.
     * Pass the filepath of the txt file to set up storage.
     *
     * @param bookmarkFilePath The filepath of the bookmark txt file.
     * @param timetableFilePath The filepath of the slot txt file.
     */
    public Duke(String bookmarkFilePath, String timetableFilePath, String plannerFilePath) {
        ui = new Ui();

        bookmarkStorage = new Storage<>(bookmarkFilePath, BookmarkList.class);
        timetableStorage = new Storage<>(timetableFilePath, Timetable.class);
        //plannerStorage = new Storage<>(plannerFilePath, Timetable.class);

        try {
            bookmarks = bookmarkStorage.load();
            timetable = timetableStorage.load();
            planner = new Timetable();
            //files = plannerStorage.loadFiles();
            //Timetable temp = plannerStorage.loadPlanner();
            //planner = Storage.initialiseEmptySlots(temp);

        } catch (DukeException e) {
            ui.showErrorMessage(e);
        }
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
                if (Parser.programMode == 3) {
                    c.execute(bookmarks, planner, ui);
                } else {
                    c.execute(bookmarks, timetable, ui);
                }
                isExit = c.isExit();
                bookmarkStorage.save(bookmarks);
                timetableStorage.save(timetable);

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
        new Duke("./data/bookmarks.txt", "./data/slots.txt", "./data/planner/").run();
    }
}