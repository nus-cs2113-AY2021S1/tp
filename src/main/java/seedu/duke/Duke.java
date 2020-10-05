package seedu.duke;

import seedu.duke.book.BookList;
import seedu.duke.commands.Command;
import seedu.duke.parser.Parser;
import seedu.duke.ui.TextUi;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private TextUi ui;
    private BookList books;

    public Duke() {
        ui = new TextUi();
        books = new BookList();
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
            command.execute(ui, books);
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