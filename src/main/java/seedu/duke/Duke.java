package seedu.duke;

import seedu.duke.backend.FileManager;
import seedu.duke.backend.Ui;

import java.io.IOException;

public class Duke {

    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) {
        String logo = " ___C____  ___C____  ___A____          "
                + "____ M _____   ___A____  ___N____   ____A___  ___G____  ___E___   ____R___ \n"
                + "|\\   ____\\|\\   ____\\|\\   __  \\        |\\  "
                + " _ \\  _   \\|\\   __  \\|\\  ___  \\|\\   __  \\|\\   ____\\|\\  ___ \\ |\\   __  \\ \n"
                + " \\ \\  \\___|\\ \\  \\___|\\ \\  \\|\\  \\       \\ \\  \\\\__\\ \\  \\ \\  \\|\\  \\ "
                + "\\  \\ \\  \\ \\  \\|\\  \\ \\  \\___|\\ \\   __/|\\ \\  \\|\\  \\ \n"
                + "  \\ \\  \\    \\ \\  \\    \\ \\   __  \\       \\ \\  \\|__| \\  \\ \\   __  \\ \\"
                + "  \\ \\  \\ \\   __  \\ \\  \\  __\\ \\  \\_|/_\\ \\   _  _\\ \n"
                + "   \\ \\  \\____\\ \\  \\____\\ \\  \\ \\  \\       \\ \\  \\    \\ \\  \\ \\  \\ \\ "
                + " \\ \\  \\ \\  \\ \\  \\ \\  \\ \\  \\|\\  \\ \\  \\_|\\ \\ \\  \\  \\| \n"
                + "    \\ \\_______\\ \\_______\\ \\__\\ \\__\\       \\ \\__\\    \\ \\__\\ \\__\\ \\__\\ "
                + "\\__\\ \\__\\ \\__\\ \\__\\ \\_______\\ \\_______\\ \\__\\ _\\ \n"
                + "     \\|_______|\\|_______|\\|__|\\|__|        \\|__|     \\|__|\\|__|\\|__|\\|__| \\|__|"
                + "\\|__|\\|__|\\|_______|\\|_______|\\|__|\\|__|";

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

