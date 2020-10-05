package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.parser.Parser;
import seedu.duke.ui.TextUi;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private TextUi ui;

    public Duke() {
        ui = new TextUi();
    }

    public void start() {
        ui.showWelcomeMessage();
    }

    public void exit() {
        ui.showGoodbyeMessage();
    }

    public void runLoopUntilExitCommand() {
        boolean isExit = false;
        while(!isExit){
            String userCommandText = ui.getUserCommand();
            Command command = new Parser().parseUserCommand(userCommandText);
            if (command == null) {
                System.out.println("Invalid command, bye!");
                break;
            }
            command.execute();
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