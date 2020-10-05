package seedu.duke.commands;

import seedu.duke.book.BookList;
import seedu.duke.lists.QuotesifyList;
import seedu.duke.ui.TextUi;

public abstract class Command {
    public abstract void execute(TextUi ui, QuotesifyList list);

    public abstract boolean isExit();
}
