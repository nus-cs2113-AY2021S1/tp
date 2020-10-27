package seedu.duke;

import seedu.duke.backend.FileManager;
import seedu.duke.backend.Ui;

import java.io.IOException;

public class Duke {

    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        Ui ui = new Ui();
        FileManager fm = new FileManager("data/");
        try {
            int rv = fm.readAll();
            if (rv != 0) {
                ui.printError("Oops I was unable to find your saved data!");
            } else {
                ui.printError("I've loaded your saved data successfully!");
            }
        } catch (DukeFileFormatException e) {
            ui.printError("Oops it appears your saved data was corrupted!");
            e.printStackTrace();
        } catch (DukeFileHeaderException e) {
            e.printStackTrace();
            ui.printError("Oops it appears your saved data was corrupted!");
        } catch (IOException e) {
            e.printStackTrace();
            ui.printError("Oops I was unable to find your saved data!");
        }
        ui.printError("Welcome to CCA Manager Integrated! Enter any command to begin!");
        while (!Ui.shouldShutdown()) {
            ui.run();
            try {
                fm.saveAll();
            } catch (IOException e) {
                ui.printError("Oops! Unable to save data!");
            }
        }
    }
}

