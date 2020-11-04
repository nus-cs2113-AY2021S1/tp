package seedu.zoomaster;

import com.google.gson.JsonParseException;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.slot.Module;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.slot.Timetable;

import java.io.File;
import java.io.IOException;

public class Zoomaster {

    private Storage<BookmarkList> bookmarkStorage;
    private Storage<Timetable> timetableStorage;
    private BookmarkList bookmarks;
    private Timetable timetable;
    private Timetable planner;
    private Ui ui;

    /**
     * Constructs a new Zoomaster instance.
     * Pass the filepath of the txt file to set up storage.
     *
     * @param bookmarkFilePath The filepath of the bookmark txt file.
     * @param timetableFilePath The filepath of the slot txt file.
     */
    public Zoomaster(String bookmarkFilePath, String timetableFilePath) {
        ui = new Ui();
      
        bookmarkStorage = new Storage<>(getJarFilepath() + bookmarkFilePath, BookmarkList.class);
        timetableStorage = new Storage<>(getJarFilepath() + timetableFilePath, Timetable.class);
      
        try {
            bookmarks = bookmarkStorage.load();
            timetable = timetableStorage.load();
            planner = new Timetable();
            Module.setModuleList(timetableStorage.loadModuleList());
        } catch (ZoomasterException e) {
            ui.showErrorMessage(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonParseException | ClassCastException e) {
            ui.showParsingErrorAndExit();
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

            } catch (ZoomasterException e) {
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
        new Zoomaster("./data/bookmarks.txt", "./data/timetable.txt").run();
    }


    /**
     * Returns path of jar file during execution to allow
     * app to create txt file in the same location.
     */
    public static String getJarFilepath() {
        return new File(Zoomaster.class.getProtectionDomain().getCodeSource().getLocation()
                .getPath()).getParent().replace("%20", " ");
    }

}