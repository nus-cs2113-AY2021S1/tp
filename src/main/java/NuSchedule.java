import command.Command;
import eventlist.EventList;
import exception.CreatingFileException;
import exception.NuScheduleException;
import parser.Parser;
import storage.Storage;
import ui.UI;

public class NuSchedule {
    /**
     * Main entry-point for the NUSchedule application.
     */
    private Storage storage;
    private EventList events;
    private UI ui;

    public NuSchedule(String filePath) {
        ui = new UI();
        try {
            storage = new Storage(filePath);
        } catch (CreatingFileException e) {
            ui.showError(e.getMessage());
        }
        try {
            events = new EventList(storage.load());
        } catch (NuScheduleException e) {
            ui.showLoadingError();
            events = new EventList();
        }
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
            } catch (NuScheduleException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new NuSchedule("data/events.txt").run();
    }
}
