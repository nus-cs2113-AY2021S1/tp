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
import java.time.format.DateTimeFormatter;
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
     * @param commandWords from user input
     */
    public AddCommand(String[] commandWords) {
        this.isExit = false;
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
        String[] argumentWords = Arrays.copyOfRange(commandWords, 1, commandWords.length);
        argument = String.join(" ", argumentWords);

    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) {
        String[] argumentWords = argument.split(";");
        Boolean successfulAdd = false;

        if (!isInvalidEventType) {
            switch (eventType) {
            case "Personal":
                if (argumentWords.length >= 1 && argumentWords.length <= 3) {
                    // 3 cases: only description, description and date, description and date and time
                    if (argumentWords.length == 1) {
                        data.getEventList("Personal").add(new Personal(argumentWords[0]));
                        successfulAdd = true;
                    } else if (argumentWords.length == 2) {
                        try {
                            LocalDate localDate = DateTimeParser.dateParser(argumentWords[1].trim());
                            data.getEventList("Personal").add(new Personal(argumentWords[0], localDate));
                            successfulAdd = true;
                        } catch (Exception e) {
                            ui.printExceptionMessage(e.toString());
                        }
                    } else {
                        try {
                            LocalDate localDate = DateTimeParser.dateParser(argumentWords[1].trim());
                            LocalTime localTime = DateTimeParser.timeParser(argumentWords[2].trim());
                            data.getEventList("Personal").add(new Personal(argumentWords[0], localDate, localTime));
                            successfulAdd = true;
                        } catch (Exception e) {
                            ui.printExceptionMessage(e.toString());
                        }
                    }
                } else {
                    System.out.println("Personal event creating error");
                }
                break;
            case "Zoom":
                if (argumentWords.length == 2 || argumentWords.length == 4) {
                    // 2 cases: only have description & zoomlink , have description,zoomlink, date,time
                    if (argumentWords.length == 2) {
                        data.getEventList("Zoom").add(new Zoom(argumentWords[0].trim(), argumentWords[1].trim()));
                        successfulAdd = true;
                    } else {
                        try {
                            LocalDate localDate = DateTimeParser.dateParser(argumentWords[2].trim());
                            LocalTime localTime = DateTimeParser.timeParser(argumentWords[3].trim());
                            data.getEventList("Zoom").add(new Zoom(argumentWords[0], argumentWords[1].trim(),
                                    localDate, localTime));
                            successfulAdd = true;
                        } catch (Exception e) {
                            ui.printExceptionMessage(e.toString());
                        }
                    }
                } else {
                    System.out.println("Zoom event creating error");
                }
                break;
            case "Timetable":
                if (argumentWords.length == 3 || argumentWords.length == 4) {
                    // 2 cases: description & date & time , description & location & date & time
                    if (argumentWords.length == 3) {
                        try {
                            LocalDate localDate = DateTimeParser.dateParser(argumentWords[1].trim());
                            LocalTime localTime = DateTimeParser.timeParser(argumentWords[2].trim());
                            data.getEventList("Timetable").add(new Timetable(argumentWords[0].trim(),
                                    localDate, localTime));
                            successfulAdd = true;
                        } catch (Exception e) {
                            ui.printExceptionMessage(e.toString());
                        }
                    } else {
                        try {
                            LocalDate localDate = DateTimeParser.dateParser(argumentWords[2].trim());
                            LocalTime localTime = DateTimeParser.timeParser(argumentWords[3].trim());
                            data.getEventList("Timetable").add(new Timetable(argumentWords[0].trim(),
                                    argumentWords[1].trim(), localDate, localTime));
                            successfulAdd = true;
                        } catch (Exception e) {
                            ui.printExceptionMessage(e.toString());
                        }
                    }
                } else {
                    System.out.println("Timetable event creating error");
                }
                break;
            default:
                System.out.println("Invalid event type to be added");
                break;
            }
            if (successfulAdd) {
                ui.printEventAddedMessage(data.getEventList(eventType).getNewestEvent());
            } else {
                System.out.println("Error adding " + eventType + " event, please try again");
            }
        } else {
            System.out.println("Invalid add command!");
        }
    }
}
