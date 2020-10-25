package seedu.eduke8.command;

import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.question.Question;
import seedu.eduke8.ui.Ui;

import java.util.ArrayList;

public class BookmarkCommand extends Command {

    private BookmarkList bookmarks;
    String typeOfBookmarkCommand = "";

    public BookmarkCommand(Question question, String typeOfBookmarkCommand, BookmarkList bookmarks) {
        super();
        assert bookmarks != null;
        bookmarks.add(question);
        this.bookmarks = bookmarks;
        this.typeOfBookmarkCommand = typeOfBookmarkCommand;
    }

    public BookmarkCommand(String typeOfBookmarkCommand, BookmarkList bookmarks) {
        this.typeOfBookmarkCommand = typeOfBookmarkCommand;
        this.bookmarks = bookmarks;
    }


    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        if (typeOfBookmarkCommand.equals("listing")) {
            ui.printListOfBookmarkedQuestions(bookmarks);
        } else if (typeOfBookmarkCommand.equals("storing")) {
            ui.printBookmarkedIndicator();
        }
    }
}
