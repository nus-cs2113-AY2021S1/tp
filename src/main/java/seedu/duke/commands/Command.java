package seedu.duke.commands;

import seedu.duke.lists.ListManager;
import seedu.duke.ui.TextUi;

public abstract class Command {

    public static final String TAG_BOOK = "-b";
    public static final String TAG_QUOTE = "-q";
    public static final String TAG_RATING = "-r";
    public static final String TAG_CATEGORY = "-c";

    public static final String ERROR_INVALID_QUOTE_NUM = "Invalid quote number specified!";

    public static final String ERROR_NO_BOOK_FOUND = "There is no such book!";
    public static final String ERROR_RATING_EXIST = "This book has already been rated";
    public static final String ERROR_BOOK_TO_RATE_NOT_FOUND = "I can't find this book to rate!";

    public abstract void execute(TextUi ui, ListManager listManager);

    public abstract boolean isExit();
}
