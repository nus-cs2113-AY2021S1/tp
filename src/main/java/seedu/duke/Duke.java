package seedu.duke;

import seedu.duke.book.BookList;
import seedu.duke.commands.Command;
import seedu.duke.lists.ListManager;
import seedu.duke.lists.QuotesifyList;
import seedu.duke.parser.Parser;
import seedu.duke.ui.TextUi;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private TextUi ui;
    private BookList books;
    private final ListManager listManager = new ListManager();


    public Duke() {
        ui = new TextUi();
        books = new BookList();
        listManager.addToList(ListManager.BOOK_LIST, books);
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