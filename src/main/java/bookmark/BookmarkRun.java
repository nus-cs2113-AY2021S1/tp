package bookmark;

import bookmark.commands.BookmarkCommand;
import exceptions.InvalidCommandException;
import studyit.StudyItLog;


import java.io.IOException;
import java.util.ArrayList;

public class BookmarkRun {
    private ArrayList<BookmarkCategory> bookmarkCategories;
    private BookmarkUi bookmarkUi;
    private BookmarkParser bookmarkParser;
    private BookmarkStorage bookmarkStorage;
    private int mode = 0;

    public BookmarkRun() {
        bookmarkUi = new BookmarkUi();
        bookmarkParser = new BookmarkParser();
        bookmarkStorage = new BookmarkStorage("data/bookmark.txt");
        try {
            bookmarkCategories = bookmarkStorage.loadFile();
        } catch (IOException e) {
            System.out.println("An error occured: " + e.getMessage());
        }

        StudyItLog.logger.info("Bookmark mode initialized");
    }

    public void run(String command) {
        try {
            BookmarkCommand c = bookmarkParser.evaluateInput(command,mode);
            c.executeCommand(bookmarkUi,bookmarkCategories,bookmarkStorage);
            mode = c.getCategoryNumber();
        } catch (InvalidCommandException e) {
            bookmarkUi.showInvalidError("Bookmark Command");
            StudyItLog.logger.warning("Invalid bookmark command: Command unidentifiable");
        }
    }
}
