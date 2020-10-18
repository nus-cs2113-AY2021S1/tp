package bookmark;

import bookmark.commands.BookmarkCommand;
import exceptions.InvalidCommandException;
import log.StudyItLog;


import java.util.ArrayList;

public class BookmarkRun {
    private ArrayList<BookmarkCategory> bookmarkCategories = new ArrayList<>();

    public BookmarkRun() {
        bookmarkCategories.add(new NusCategory());
        bookmarkCategories.add(new ZoomCategory());
    }

    public void run(String command) {
        BookmarkUi bookmarkUi = new BookmarkUi();
        BookmarkParser bookmarkParser = new BookmarkParser();
        try {
            BookmarkCommand c = bookmarkParser.evaluateInput(command);
            c.executeCommand(bookmarkUi,bookmarkCategories);
        } catch (InvalidCommandException e) {
            bookmarkUi.showInvalidBookmarkCommand();
            StudyItLog.logger.info("Cannot understand bookmark command");
        }
    }
}
