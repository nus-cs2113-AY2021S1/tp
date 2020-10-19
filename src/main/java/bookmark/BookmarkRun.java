package bookmark;

import bookmark.commands.BookmarkCommand;
import exceptions.InvalidCommandException;
import studyit.StudyItLog;


import java.util.ArrayList;

public class BookmarkRun {
    private ArrayList<BookmarkCategory> bookmarkCategories = new ArrayList<>();
    private BookmarkUi bookmarkUi;
    private BookmarkParser bookmarkParser;
    private BookmarkStorage bookmarkStorage;

    public BookmarkRun() {
        bookmarkCategories.add(new NusCategory());
        bookmarkCategories.add(new ZoomCategory());
        bookmarkUi = new BookmarkUi();
        bookmarkParser = new BookmarkParser();
        bookmarkStorage = new BookmarkStorage("data/timetable.txt");
    }

    public void run(String command) {
        try {
            BookmarkCommand c = bookmarkParser.evaluateInput(command);
            c.executeCommand(bookmarkUi,bookmarkCategories);
        } catch (InvalidCommandException e) {
            bookmarkUi.showInvalidBookmarkCommand();
            StudyItLog.logger.info("Cannot understand bookmark command");
        }
    }

    public ArrayList<BookmarkCategory> getBookmarkCategories(){
        return bookmarkCategories;
    }
}
