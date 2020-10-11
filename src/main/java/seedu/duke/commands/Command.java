package seedu.duke.commands;

import seedu.duke.lists.ListManager;
import seedu.duke.ui.TextUi;

public abstract class Command {

    public static final String TAG_BOOK = "-b";
    public static final String TAG_QUOTE = "-q";
    public static final String TAG_RATING = "-r";
    public static final String TAG_CATEGORY = "-c";
    public static final String TAG_TODO = "-t";
    public static final String TAG_BOOKMARK = "-bm";

    public static final String ERROR_INVALID_QUOTE_NUM = "Invalid quote number specified!";
    public static final String ERROR_INVALID_TODO_NUM = "Invalid task number specified!";
    public static final String ERROR_NO_BOOK_FOUND = "There is no such book!";
    public static final String ERROR_NO_BOOKS_IN_LIST = "There are no books in the list!";
    public static final String ERROR_NO_BOOKS_BY_AUTHOR = "There are no books by this author!";
    public static final String ERROR_NO_AUTHOR_NAME = "Please type in the author name.";
    public static final String ERROR_RATING_EXIST = "This book has already been rated!";
    public static final String ERROR_BOOK_TO_RATE_NOT_FOUND = "I can't find this book to rate!";
    public static final String ERROR_RATING_NOT_FOUND = "This book has not been rated!";
    public static final String ERROR_TODO_NOT_FOUND = "There is no such task!";
    public static final String ERROR_BOOKMARK_NOT_FOUND = "There is no such bookmark!";
    public static final String ERROR_LIST_UNKNOWN_COMMAND = "I do not understand that command. Try again";
    public static final String ERROR_MISSING_BOOK_OR_QUOTE = "Please specify a book title or quote number!";

    public static final String FLAG_AUTHOR = "/by";
    public static final String FLAG_REFERENCE = "/from";
    public static final String FLAG_DELIMITER = "/";
    public static final String REFERENCE_KEYWORD = "reference";
    public static final String AUTHORNAME_KEYWORD = "authorName";

    public abstract void execute(TextUi ui);

    public abstract boolean isExit();
}
