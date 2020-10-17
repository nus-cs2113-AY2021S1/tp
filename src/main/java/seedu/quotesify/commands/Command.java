package seedu.quotesify.commands;

import seedu.quotesify.ui.TextUi;

public abstract class Command {

    public static final String TAG_BOOK = "-b";
    public static final String TAG_QUOTE = "-q";
    public static final String TAG_RATING = "-r";
    public static final String TAG_CATEGORY = "-c";
    public static final String TAG_TODO = "-t";
    public static final String TAG_BOOKMARK = "-bm";

    public static final String ERROR_INVALID_BOOK_NUM = "Invalid book number specified!";
    public static final String ERROR_INVALID_QUOTE_NUM = "Invalid quote number specified!";
    public static final String ERROR_INVALID_TODO_NUM = "Invalid task number specified!";
    public static final String ERROR_NO_BOOK_FOUND = "There is no such book!";
    public static final String ERROR_BOOK_TITLE_MISSING = "There is no book title specified!";
    public static final String ERROR_BOOK_ALREADY_EXISTS = "The book already exists!";
    public static final String ERROR_NO_QUOTE_FOUND = "There is no such quote!";
    public static final String ERROR_NO_BOOKS_IN_LIST = "There are no books in the list!";
    public static final String ERROR_NO_BOOKS_BY_AUTHOR = "There are no books by this author!";
    public static final String LIST_SPECIFIED_RATING_NOT_FOUND_MESSAGE = "I can't find any books rated %d star!";
    public static final String ERROR_NO_AUTHOR_NAME = "Please type in the author name.";
    public static final String ERROR_NO_PAGE_NUM = "There is no page number provided!";
    public static final String ERROR_NO_TASK_NAME = "There is no task name provided!";
    public static final String ERROR_RATING_EXIST = "This book has already been rated!";
    public static final String ERROR_BOOK_TO_RATE_NOT_FOUND = "I can't find this book to rate!";
    public static final String ERROR_RATING_NOT_FOUND = "I can't find ratings for this book!";
    public static final String ERROR_RATING_MISSING_BOOK_TITLE_OR_RATING_SCORE
            = "Both book title and rating score cannot be empty.";
    public static final String ERROR_RATING_MISSING_BOOK_TITLE = "Book title cannot be empty.";
    public static final String ERROR_TODO_NOT_FOUND = "There is no such task!";
    public static final String ERROR_BOOKMARK_NOT_FOUND = "There is no such bookmark!";
    public static final String ERROR_LIST_UNKNOWN_COMMAND = "I do not understand that command. Try again";

    public static final String FLAG_AUTHOR = "/by";
    public static final String FLAG_REFERENCE = "/from";
    public static final String FLAG_DELIMITER = "/";
    public static final String FLAG_EDIT = "/to";
    public static final String REFERENCE_KEYWORD = "reference";
    public static final String AUTHORNAME_KEYWORD = "authorName";

    public abstract void execute(TextUi ui);

    public abstract boolean isExit();
}
