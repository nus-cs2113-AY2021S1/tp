package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Personal;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Command to add events.
 */
public class AddCommand extends Command {
    private String eventType;
    private String argument;
    /**
     * Constructor for adding events seedu.duke
     *
     * @param commandWords from user input
     */
    public AddCommand(String[] commandWords) {
        this.isExit = false;
        String tempEventType = commandWords[0].toLowerCase();
        switch(tempEventType) {
        case "zoom":
            eventType = "Zoom";
            break;
        case "personal":
            eventType = "Personal";
            break;
        case "timetable":
            eventType = "Timetable";
            break;
        default:
            break;
        }
        argument = String.join(" ", commandWords);

    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) {
        String[] argumentWords = argument.split(";");
        //
        // example input : add Zoom math; 27/9/2020; 1300
        // example argumentWords[] : math | 27/9/2020 | 1300

        switch(eventType) {
        case "Personal":
            if (argumentWords.length >= 1 && argumentWords.length <= 3) {
                // 3 cases: only description, description and date, description, date, time
                if (argumentWords.length == 1) {
                    userData.getEventList("Personal").add(new Personal(argumentWords[0]));
                } else if (argumentWords.length == 2) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(argumentWords[1], formatter);
                    userData.getEventList("Personal").add(new Personal(argumentWords[0], localDate));
                } else if (argumentWords.length == 3) {

                }

            } else {
                System.out.println("Personal event creating error");
            }
            break;
        case "Zoom":
            if (argumentWords.length == 3) {
                // contains description,date and time

            } else {
                System.out.println("Zoom event creating error");
            }
            break;
        case "Timetable":
            if (argumentWords.length == 3) {
                // contains description,date and time

            } else {
                System.out.println("Timetable event creating error");
            }
            break;
        default:
            break;
        }
    }
}
