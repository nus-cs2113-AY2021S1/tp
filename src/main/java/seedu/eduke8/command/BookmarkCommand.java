package seedu.eduke8.command;

import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.question.Question;
import seedu.eduke8.ui.Ui;

import java.util.ArrayList;

public class BookmarkCommand extends Command {
    private static final String BOOKMARK_LIST = "listing";
    private static final String BOOKMARK_STORE = "storing";

    private BookmarkList bookmarks;
    private Question question;
    private boolean alreadyBookmarked = false;
    private int deleteIndex = 0;
    String typeOfBookmarkCommand = "";

    public BookmarkCommand(Question question, String typeOfBookmarkCommand, BookmarkList bookmarks) {
        super();
        assert bookmarks != null;
        this.question = question;
        if (question.isBookmarked()) {
            alreadyBookmarked = true;
        } else {
            bookmarks.add(question);
        }
        this.bookmarks = bookmarks;
        this.typeOfBookmarkCommand = typeOfBookmarkCommand;
    }

    public BookmarkCommand(String typeOfBookmarkCommand, BookmarkList bookmarks) {
        this.typeOfBookmarkCommand = typeOfBookmarkCommand;
        this.bookmarks = bookmarks;
    }

    public BookmarkCommand(int deleteIndex, String typeOfBookmarkCommand, BookmarkList bookmarks) {
        this.typeOfBookmarkCommand = typeOfBookmarkCommand;
        this.bookmarks = bookmarks;
        this.deleteIndex = deleteIndex;
        ArrayList<Displayable> bookmarkList = bookmarks.getInnerList();
        bookmarkList.remove(deleteIndex - 1);
    }


    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        if (typeOfBookmarkCommand.equals(BOOKMARK_LIST)) {
            ui.printListOfBookmarkedQuestions(bookmarks);
        } else if (typeOfBookmarkCommand.equals(BOOKMARK_STORE)) {
            if (alreadyBookmarked) {
                ui.printAlreadyBookmarkedIndicator();
            } else {
                ui.printBookmarkedIndicator();
            }
        } else if (typeOfBookmarkCommand.equals("delete")) {
            ui.printDeletedBookmarkIndicator(deleteIndex);
        }
    }
}

