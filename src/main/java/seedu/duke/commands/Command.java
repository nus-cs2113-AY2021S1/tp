package seedu.duke.commands;

import seedu.duke.book.BookList;
import seedu.duke.ui.TextUi;

public abstract class Command {
    public abstract void execute(TextUi ui, BookList books);

    public abstract boolean isExit();
}
