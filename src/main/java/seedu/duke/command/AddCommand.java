package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Personal;
import seedu.duke.event.Timetable;
import seedu.duke.event.Zoom;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;


/**
 * Command to add events.
 */
public class AddCommand extends Command {
    private String eventType;
    private String argument;
    private Boolean isInvalidEventType = false;

    /**
     * Constructor for adding events seedu.duke
     *
     * @param command from user input
     */
    public AddCommand(String command) {
        this.isExit = false;
        String[] commandWords = command.split(" ");
        String tempEventType = commandWords[0].toLowerCase();
        switch (tempEventType) {
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
            isInvalidEventType = true;
            break;
        }
        if (commandWords.length == 1) {
            isInvalidEventType = true;
        }
        String[] argumentWords = Arrays.copyOfRange(commandWords, 1, commandWords.length);
        argument = String.join(" ", argumentWords);

    }

    /**
     * Adds an event to individual eventLists.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      containing the responses to print.
     * @param storage with the save file path to write to.
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) {
        String[] argumentWords = argument.split(";");
        Boolean successfulAdd = false;

        if (!isInvalidEventType) {
            switch (eventType) {
            case "Personal":
                successfulAdd = addPersonal(data, ui, argumentWords);
                break;
            case "Zoom":
                successfulAdd = addZoom(data, ui, argumentWords);
                break;
            case "Timetable":
                successfulAdd = addTimetable(data, ui, argumentWords);
                break;
            default:
                System.out.println("Invalid event type to be added");
                break;
            }
            if (successfulAdd) {
                ui.printEventAddedMessage(data.getEventList(eventType).getNewestEvent());
            } else {
                System.out.println("Error adding " + eventType + " event, please try again!");
                ui.printDividerLine();
            }
        } else {
            System.out.println("Invalid add command!");
            ui.printDividerLine();
        }
    }

    /**
     * Adds a Timetable event.
     *
     * @param data object of UserData class containing user's data.
     * @param ui containing the responses to print.
     * @param argumentWords String array containing user input arguments
     * @return Boolean that confirms if the event was added
     */
    private Boolean addTimetable(UserData data, Ui ui, String[] argumentWords) {
        Boolean successfulAdd = false;
        if (argumentWords.length == 3 || argumentWords.length == 4) {
            // 2 cases: description & date & time , description & location & date & time
            if (argumentWords.length == 3) {
                try {
                    LocalDate localDate = DateTimeParser.dateParser(argumentWords[1].trim());
                    LocalTime localTime = DateTimeParser.timeParser(argumentWords[2].trim());
                    data.addToEventList("Timetable", new Timetable(argumentWords[0].trim(), localDate, localTime));
                    successfulAdd = true;
                } catch (Exception e) {
                    ui.printExceptionMessage(e.toString());
                }
            } else {
                try {
                    LocalDate localDate = DateTimeParser.dateParser(argumentWords[2].trim());
                    LocalTime localTime = DateTimeParser.timeParser(argumentWords[3].trim());
                    data.addToEventList("Timetable", new Timetable(argumentWords[0].trim(),
                            argumentWords[1].trim(), localDate, localTime));
                    successfulAdd = true;
                } catch (Exception e) {
                    ui.printExceptionMessage(e.toString());
                }
            }
        } else {
            System.out.println("Incorrect number of parameters for Timetable event!");
        }
        return successfulAdd;
    }

    /**
     * Adds a Personal event.
     *
     * @param data object of UserData class containing user's data.
     * @param ui containing the responses to print.
     * @param argumentWords String array containing user input arguments
     * @return Boolean that confirms if the event was added
     */
    private Boolean addPersonal(UserData data, Ui ui, String[] argumentWords) {
        Boolean successfulAdd = false;
        if (argumentWords.length >= 1 && argumentWords.length <= 3) {
            // 3 cases: only description, description and date, description and date and time
            if (argumentWords.length == 1) {
                data.addToEventList("Personal", new Personal(argumentWords[0].trim()));
                successfulAdd = true;
            } else if (argumentWords.length == 2) {
                try {
                    LocalDate localDate = DateTimeParser.dateParser(argumentWords[1].trim());
                    data.addToEventList("Personal", new Personal(argumentWords[0].trim(), localDate));
                    successfulAdd = true;
                } catch (Exception e) {
                    ui.printExceptionMessage(e.toString());
                }
            } else {
                try {
                    LocalDate localDate = DateTimeParser.dateParser(argumentWords[1].trim());
                    LocalTime localTime = DateTimeParser.timeParser(argumentWords[2].trim());
                    data.addToEventList("Personal", new Personal(argumentWords[0].trim(), localDate, localTime));
                    successfulAdd = true;
                } catch (Exception e) {
                    ui.printExceptionMessage(e.toString());
                }
            }
        } else {
            System.out.println("Incorrect number of parameters for Personal event!");
        }
        return successfulAdd;
    }

    /**
     * Adds a Zoom event.
     *
     * @param data object of UserData class containing user's data.
     * @param ui containing the responses to print.
     * @param argumentWords String array containing user input arguments
     * @return Boolean that confirms if the event was added
     */
    private Boolean addZoom(UserData data, Ui ui, String[] argumentWords) {
        Boolean successfulAdd = false;
        if (argumentWords.length == 2 || argumentWords.length == 4) {
            // 2 cases: only have description & zoomlink , have description,zoomlink, date,time
            if (argumentWords.length == 2) {
                data.addToEventList("Zoom", new Zoom(argumentWords[0].trim(), argumentWords[1].trim()));
                successfulAdd = true;
            } else {
                try {
                    LocalDate localDate = DateTimeParser.dateParser(argumentWords[2].trim());
                    LocalTime localTime = DateTimeParser.timeParser(argumentWords[3].trim());
                    data.addToEventList("Zoom", new Zoom(argumentWords[0].trim(),
                            argumentWords[1].trim(), localDate, localTime));
                    successfulAdd = true;
                } catch (Exception e) {
                    ui.printExceptionMessage(e.toString());
                }
            }
        } else {
            System.out.println("Incorrect number of parameters for Zoom event!");
        }
        return successfulAdd;
    }
}
