package seedu.duke;

import seedu.duke.book.BookList;
import seedu.duke.category.CategoryList;
import seedu.duke.commands.Command;
import seedu.duke.lists.ListManager;
import seedu.duke.lists.QuotesifyList;
import seedu.duke.parser.Parser;
import seedu.duke.quote.QuoteList;
import seedu.duke.ui.TextUi;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private TextUi ui;
    private BookList books;
    private QuoteList quotes;
    private CategoryList categories;
    private final ListManager listManager = new ListManager();


    public Duke() {
        ui = new TextUi();
        books = new BookList();
        categories = new CategoryList();
        quotes = new QuoteList();

        listManager.addToList(ListManager.BOOK_LIST, books);
        listManager.addToList(ListManager.QUOTE_LIST, quotes);
        listManager.addToList(ListManager.CATEGORY_LIST, categories);
    }

    public void start() {
        ui.showWelcomeMessage();
    }

    public void exit() {
        ui.showGoodbyeMessage();
    }

    public void runLoopUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            String userCommandText = ui.getUserCommand();
            Command command = new Parser().parseUserCommand(userCommandText);
            if (command == null) {
                System.out.println("Invalid command, bye!");
                break;
            }
            command.execute(ui, listManager);
            isExit = command.isExit();
        }
    }

    public void run() {
        start();
        runLoopUntilExitCommand();
        exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}