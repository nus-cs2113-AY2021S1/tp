package seedu.duke;

import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.slot.Module;
import seedu.duke.slot.SlotList;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.slot.Timetable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

        bookmarkStorage = new Storage<>(getJarFilepath() + bookmarkFilePath, BookmarkList.class);
        timetableStorage = new Storage<>(getJarFilepath() + timetableFilePath, Timetable.class);

        try {
            bookmarks = bookmarkStorage.load();
            timetable = timetableStorage.load();
            Module.setModuleList(timetableStorage.loadModuleList());
        } catch (DukeException e) {
            ui.showErrorMessage(e);
        } catch (IOException e) {
            e.printStackTrace();
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
                c.execute(bookmarks, timetable, ui);
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
        new Duke("./data/bookmarks.txt", "./data/timetable.txt").run();
    }


    /**
     * Returns path of jar file during execution to allow
     * app to create txt file in the same location.
     */
    private static String getJarFilepath() {
        return new File(Duke.class.getProtectionDomain().getCodeSource().getLocation()
                .getPath()).getParent().replace("%20", " ");
    }

}