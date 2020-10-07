package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.level.Admin;
import seedu.duke.tool.Access;
import seedu.duke.tool.Parser;
import seedu.duke.tool.Storage;
import seedu.duke.tool.Ui;

import java.io.FileNotFoundException;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private Ui ui;
    private Access access;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            access = new Access(new Admin(storage.loadModule()));
            ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse("list");
            c.execute(access, ui, storage);
            ui.showLine();
        } catch (FileNotFoundException e) {
            storage.createAdmin();
            access = new Access();
        }
    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse(fullCommand);
            c.execute(access, ui, storage);
            isExit = c.isExit();
            ui.showLine();
            System.out.println(access.getLevel());
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        new Duke("data/admin").run();
    }
}
