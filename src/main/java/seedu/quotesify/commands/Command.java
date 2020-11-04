package seedu.quotesify.commands;

import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

/**
 * Represents a user command.
 */
public abstract class Command {

    /** Tags used to identify the different features. */
    public static final String TAG_BOOK = "-b";
    public static final String TAG_QUOTE = "-q";
    public static final String TAG_QUOTE_REFLECTION = "-qr";
    public static final String TAG_RATING = "-r";
    public static final String TAG_CATEGORY = "-c";
    public static final String TAG_TODO = "-t";
    public static final String TAG_BOOKMARK = "-bm";

    /** Error messages for the different commands. */
    public static final String ERROR_INVALID_BOOK_NUM = "Invalid book number specified!";
    public static final String ERROR_INVALID_QUOTE_NUM = "Invalid quote number specified!";
    public static final String ERROR_DUPLICATE_QUOTE = "This quote has already been added!";
    public static final String ERROR_INVALID_TODO_NUM = "Invalid task number specified!";
    public static final String ERROR_INVALID_PAGE_NUM = "Invalid page number specified!";
    public static final String ERROR_INVALID_BOOKMARK_NUM = "Invalid bookmark number specified!";
    public static final String ERROR_INVALID_TAG = "Invalid / No tag provided";
    public static final String ERROR_NO_BOOK_FOUND = "There is no such book!";
    public static final String ERROR_BOOK_TITLE_MISSING = "There is no book title specified!";
    public static final String ERROR_BOOK_ALREADY_EXISTS = "The book already exists!";
    public static final String ERROR_CATEGORY_EXISTS_IN_BOOK = "Category [%s] already exists in book [%s]!";
    public static final String ERROR_CATEGORY_EXISTS_IN_QUOTE = "Category [%s] already exists in quote [\"%s\"]!";
    public static final String ERROR_CATEGORY_NOT_IN_BOOK = "Category [%s] not found in book [%s]!";
    public static final String ERROR_CATEGORY_NOT_IN_QUOTE = "Category [%s] not found in quote [\"%s\"]!";
    public static final String ERROR_NO_MATCHING_CATEGORY = "There are no matching categories!";
    public static final String ERROR_NO_QUOTE_FOUND = "There is no such quote!";
    public static final String ERROR_NO_QUOTE_NUMBER = "Quote number is missing";
    public static final String ERROR_FIND_KEYWORD_MISSING = "Keyword to search is missing";
    public static final String ERROR_NO_BOOKS_IN_LIST = "There are no books in the list!";
    public static final String ERROR_NO_BOOKS_BY_AUTHOR = "There are no books by this author!";
    public static final String ERROR_LIST_SPECIFIED_RATING_NOT_FOUND = "I can't find any books rated %d star!";
    public static final String ERROR_NO_AUTHOR_NAME = "Please type in the author name.";
    public static final String ERROR_NO_PAGE_NUM = "There is no page number provided!";
    public static final String ERROR_NO_TASK_NAME = "There is no task name provided!";
    public static final String ERROR_RATING_EXIST = "This book has already been rated!";
    public static final String ERROR_RATING_DO_NOT_EXIST = "This book has not been rated!";
    public static final String ERROR_RATING_NOT_FOUND = "I can't find any ratings related to [%s]!";
    public static final String ERROR_TODO_NOT_FOUND = "There is no such task!";
    public static final String ERROR_BOOKMARK_NOT_FOUND = "There is no such bookmark!";
    public static final String ERROR_LIST_UNKNOWN_COMMAND = "I do not understand that command. Try again";
    public static final String ERROR_MISSING_EDIT_FLAG = "Editing requires a \"/to\" flag";
    public static final String ERROR_MISSING_KEYWORD = "Please type in the keyword.";
    public static final String ERROR_MISSING_CATEGORY = "Missing category name";
    public static final String ERROR_MISSING_BOOK_OR_QUOTE = "Please specify a book title or quote number!";
    public static final String ERROR_MISSING_REFLECTION_FIELD = "Please specify your reflection!";
    public static final String ERROR_NO_REFLECTION = "The quote you selected does not have a reflection!";

    public static final String FLAG_AUTHOR = "/by";
    public static final String FLAG_REFERENCE = "/from";
    public static final String FLAG_DELIMITER = "/";
    public static final String FLAG_EDIT = "/to";
    public static final String FLAG_REFLECT = "/reflect";
    public static final String REFERENCE_KEYWORD = "reference";
    public static final String AUTHORNAME_KEYWORD = "authorName";
    public static final String DONE_KEYWORD = "done";

    /**
     * Executes the different commands.
     *
     * @param ui Ui of the program.
     * @param storage Storage of the program.
     */
    public abstract void execute(TextUi ui, Storage storage);

    /**
     * Decides if the program should be terminated.
     *
     * @return decision to terminate the program.
     */
    public abstract boolean isExit();
}
