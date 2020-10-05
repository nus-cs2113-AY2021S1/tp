package seedu.duke.commands;

import seedu.duke.author.Author;
import seedu.duke.book.Book;
import seedu.duke.book.BookList;
import seedu.duke.lists.QuotesifyList;
import seedu.duke.ui.TextUi;

public class AddCommand extends Command {
    private static final String BOOK = "-b";

    private String type;
    private String information;

    public AddCommand(String arguments) {
        String[] details = arguments.split(" ", 2);
        type = details[0];
        information = details[1];
    }

    public void execute(TextUi ui, QuotesifyList list) {
        switch (type) {
        case BOOK:
            addBook((BookList)list);
            break;
        default:
        }

        ui.printSuccessfulAddCommand();
    }

    private void addBook(BookList books) {
        String[] titleAndAuthor = information.split("/by", 2);
        Author author = new Author(titleAndAuthor[1].trim());
        books.add(new Book(author, titleAndAuthor[0].trim()));
    }

    public boolean isExit() {
        return false;
    }
}
