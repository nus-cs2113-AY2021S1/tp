package seedu.duke.commands;

import seedu.duke.book.BookList;
import seedu.duke.ui.TextUi;

public class ExitCommand extends Command {
    @Override
    public void execute(TextUi ui, BookList books) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
