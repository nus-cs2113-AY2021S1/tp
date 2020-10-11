package seedu.duke;

import seedu.duke.book.BookList;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.category.CategoryList;
import seedu.duke.commands.Command;
import seedu.duke.lists.ListManager;
import seedu.duke.parser.Parser;
import seedu.duke.quote.QuoteList;
import seedu.duke.rating.RatingList;
import seedu.duke.todo.ToDoList;
import seedu.duke.ui.TextUi;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private TextUi ui;
    private BookList books;
    private QuoteList quotes;
    private CategoryList categories;
    private RatingList ratings;
    private ToDoList toDos;
    private BookmarkList bookmarks;

    public Duke() {
        ui = new TextUi();
        books = new BookList();
        categories = new CategoryList();
        quotes = new QuoteList();
        ratings = new RatingList();
        toDos = new ToDoList();
        bookmarks = new BookmarkList();

        ListManager.addToList(ListManager.BOOK_LIST, books);
        ListManager.addToList(ListManager.QUOTE_LIST, quotes);
        ListManager.addToList(ListManager.CATEGORY_LIST, categories);
        ListManager.addToList(ListManager.RATING_LIST, ratings);
        ListManager.addToList(ListManager.TODO_LIST, toDos);
        ListManager.addToList(ListManager.BOOKMARK_LIST, bookmarks);
    }

    public void start() {
        ui.showWelcomeMessage();
        //Print random quote - After storage implementation
    }

    public void exit() {
        ui.printRandomQuote();
        ui.showGoodbyeMessage();
    }

    public void runLoopUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            String userCommandText = ui.getUserCommand();
            Command command = new Parser().parseUserCommand(userCommandText);
            if (command == null) {
                System.out.println("Invalid command!");
                continue;
            }
            command.execute(ui);
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