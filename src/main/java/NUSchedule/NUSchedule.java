package NUSchedule;

import NUSchedule.command.Command;
import NUSchedule.eventList.EventList;
import NUSchedule.exception.CreatingFileException;
import NUSchedule.exception.NUScheduleException;
import NUSchedule.parser.Parser;
import NUSchedule.storage.Storage;
import NUSchedule.ui.UI;

public class NUSchedule {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private Storage storage;
    private EventList Events;
    private UI ui;

    public NUSchedule(String filePath) {
        ui = new UI();
        try {
            storage = new Storage(filePath);
        } catch (CreatingFileException e) {
            ui.showError(e.getMessage());
        }
//        try {todo after implement load, uncomment it
//            Events = new EventList(storage.load());
//        } catch (NUScheduleException e) {
//            ui.showLoadingError();
//            Events = new EventList();
//        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.printGreetingMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(Events, ui, storage);
                isExit = c.isExit();
            } catch (NUScheduleException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new NUSchedule("data/events.txt").run();
    }
}
