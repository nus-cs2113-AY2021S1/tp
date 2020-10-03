package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.tasks.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    public static String FILENAME = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;

    /**
     * Initialises Duke
     * @param FileName of the <code>File</code> that stores the text data of the to-do list
     */
    public Duke(String FileName) {
        storage = new Storage(FileName);
        tasks = new TaskList(new ArrayList<>());
        storage.load(tasks);
    }

    /**
     * Runs the Duke program until the user exits the program
     */
    public void run() {
        Ui.printStart();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
                tasks.saveTask(storage.getFileName());
            } catch (IOException e) {
                Ui.printWritingError();
            }catch (NumberFormatException e){
                Ui.printIndexError();
            }
        }
        Ui.printBye();
    }

    public static void main(String[] args) {
        new Duke(FILENAME).run();
    }
}
