import command.Command;
import eventList.EventList;
import exception.CreatingFileException;
import exception.NUScheduleException;
import parser.Parser;
import storage.Storage;
import ui.UI;

public class NUSchedule {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private Storage storage;
    private EventList events;
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
                c.execute(events, ui, storage);
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
