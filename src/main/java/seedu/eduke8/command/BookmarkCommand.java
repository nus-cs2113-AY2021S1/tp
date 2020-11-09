package seedu.eduke8.command;

import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.question.Question;
import seedu.eduke8.ui.Ui;

import static seedu.eduke8.exception.ExceptionMessages.ERROR_BOOKMARK_NONE;

/**
 * The specific command to manage bookmarked questions in E-Duke-8.
 */
public class BookmarkCommand extends Command {
    private static final String BOOKMARK_LIST = "listing";
    private static final String BOOKMARK_STORE = "storing";
    private static final String BOOKMARK_DELETE = "delete";

    private BookmarkList bookmarkList;
    private Question question;
    private boolean alreadyBookmarked = false;
    private int deleteIndex = 0;
    String typeOfBookmarkCommand = "";

    public BookmarkCommand(Question question, String typeOfBookmarkCommand, BookmarkList bookmarkList) {
        super();
        assert bookmarkList != null;
        this.question = question;
        if (question.isBookmarked()) {
            alreadyBookmarked = true;
        } else {
            bookmarkList.add(question);
        }
        this.bookmarkList = bookmarkList;
        this.typeOfBookmarkCommand = typeOfBookmarkCommand;
    }

    public BookmarkCommand(String typeOfBookmarkCommand, BookmarkList bookmarkList) throws Eduke8Exception {
        this.typeOfBookmarkCommand = typeOfBookmarkCommand;
        this.bookmarkList = bookmarkList;
        if (bookmarkList.getCount() == 0) {
            throw new Eduke8Exception(ERROR_BOOKMARK_NONE);
        }
    }

    public BookmarkCommand(int deleteIndex, String typeOfBookmarkCommand, BookmarkList bookmarkList)
            throws Eduke8Exception {
        if (bookmarkList.getCount() == 0) {
            throw new Eduke8Exception(ERROR_BOOKMARK_NONE);
        }
        this.typeOfBookmarkCommand = typeOfBookmarkCommand;
        this.bookmarkList = bookmarkList;
        this.deleteIndex = deleteIndex;

        int currentIndex = 0;
        for (Displayable question : bookmarkList.getInnerList()) {
            currentIndex++;
            if (currentIndex == deleteIndex) {
                Question castedQuestion = (Question) question;
                castedQuestion.markAsNotBookmarked();
            }
        }
        bookmarkList.delete(deleteIndex - 1);
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        if (typeOfBookmarkCommand.equals(BOOKMARK_LIST)) {
            ui.printListOfBookmarkedQuestions(bookmarkList);
        } else if (typeOfBookmarkCommand.equals(BOOKMARK_STORE)) {
            if (alreadyBookmarked) {
                ui.printAlreadyBookmarkedIndicator();
            } else {
                ui.printBookmarkedIndicator();
            }
        } else if (typeOfBookmarkCommand.equals(BOOKMARK_DELETE)) {
            ui.printDeletedBookmarkIndicator(deleteIndex);
        }
    }
}

