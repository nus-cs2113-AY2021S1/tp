package seedu.duke;

import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.slot.SlotList;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.slot.Timetable;

public class Duke {

    private Storage<BookmarkList> bookmarkStorage;
    private Storage<Timetable> timetableStorage;
    private BookmarkList bookmarks;
    private Timetable timetable;
    private Ui ui;

    /**
     * Constructs a new Duke instance.
     * Pass the filepath of the txt file to set up storage.
     *
     * @param bookmarkFilePath The filepath of the bookmark txt file.
     * @param timetableFilePath The filepath of the slot txt file.
     */
    public Duke(String bookmarkFilePath, String timetableFilePath) {
        ui = new Ui();

        bookmarkStorage = new Storage<>(bookmarkFilePath, BookmarkList.class);
        timetableStorage = new Storage<>(timetableFilePath, Timetable.class);

        try {
            bookmarks = bookmarkStorage.load();
            timetable = timetableStorage.load();
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
                c.execute(bookmarks, timetable, ui, bookmarkStorage, timetableStorage);
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
        new Duke("./data/bookmarks.txt", "./data/slots.txt").run();
    }
}