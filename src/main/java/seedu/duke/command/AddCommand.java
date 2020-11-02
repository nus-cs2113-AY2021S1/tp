package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Personal;
import seedu.duke.event.Timetable;
import seedu.duke.event.Zoom;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DateErrorException;
import seedu.duke.exception.TimeErrorException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.exception.EventAddErrorException;
import seedu.duke.exception.MissingDescriptionException;
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
        if (command == null || command.equals("") || command.equals(" ")) {
            isInvalidEventType = true;
        } else {
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
            String[] argumentWords = Arrays.copyOfRange(commandWords, 1, commandWords.length);
            argument = String.join(" ", argumentWords);
        }
    }

    /**
     * Adds an event to individual eventLists.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      containing the responses to print.
     * @param storage with the save file path to write to.
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        if (argument == null) {
            throw new EventAddErrorException("Wrong format for the add command!");
        }
        if (isInvalidEventType) {
            throw new EventAddErrorException("Invalid event type to be added!");
        }

        try {
            String[] argumentWords = argument.split(";");
            if (argumentWords[0].trim().equals("")) {
                throw new MissingDescriptionException("This event has an empty description!");
            }
            switch (eventType) {
            case "Personal":
                addPersonal(data, ui, argumentWords);
                ui.printEventAddedMessage(data.getEventList(eventType).getNewestEvent());
                storage.saveFile(storage.getFileLocation(eventType), data, eventType);
                break;
            case "Zoom":
                addZoom(data, ui, argumentWords);
                ui.printEventAddedMessage(data.getEventList(eventType).getNewestEvent());
                storage.saveFile(storage.getFileLocation(eventType), data, eventType);
                break;
            case "Timetable":
                addTimetable(data, ui, argumentWords);
                ui.printEventAddedMessage(data.getEventList(eventType).getNewestEvent());
                storage.saveFile(storage.getFileLocation(eventType), data, eventType);
                break;
            default:
                throw new EventAddErrorException("Invalid event type to be added!");
            }
        } catch (DukeException e) {
            e.printErrorMessage();
        }
    }

    /**
     * Adds a Timetable event.
     *
     * @param data          object of UserData class containing user's data.
     * @param ui            containing the responses to print.
     * @param argumentWords String array containing user input arguments
     * @return Boolean that confirms if the event was added
     */
    private void addTimetable(UserData data, Ui ui, String[] argumentWords) throws DukeException {
        if (argumentWords.length == 3 || argumentWords.length == 4) {
            // 2 cases: description & date & time , description & location & date & time
            if (argumentWords.length == 3) {
                try {
                    LocalDate localDate = DateTimeParser.dateParser(argumentWords[1].trim());
                    assert localDate != null : "date is not detected after parsing";
                    LocalTime localTime = DateTimeParser.timeParser(argumentWords[2].trim());
                    assert localTime != null : "time is not detected after parsing";
                    data.addToEventList("Timetable", new Timetable(argumentWords[0].trim(), localDate, localTime));
                } catch (DateErrorException e) {
                    throw new DateErrorException("Something is wrong with the date!");
                } catch (TimeErrorException e) {
                    throw new TimeErrorException("Something is wrong with the time!");
                }
            } else {
                try {
                    LocalDate localDate = DateTimeParser.dateParser(argumentWords[2].trim());
                    assert localDate != null : "date is not detected after parsing";
                    LocalTime localTime = DateTimeParser.timeParser(argumentWords[3].trim());
                    assert localTime != null : "time is not detected after parsing";
                    data.addToEventList("Timetable", new Timetable(argumentWords[0].trim(),
                            argumentWords[1].trim(), localDate, localTime));
                } catch (DateErrorException e) {
                    throw new DateErrorException("Something is wrong with the date!");
                } catch (TimeErrorException e) {
                    throw new TimeErrorException("Something is wrong with the time!");
                }
            }
        } else {
            throw new WrongNumberOfArgumentsException("Incorrect number of parameters for Timetable event!");
        }
    }

    /**
     * Adds a Personal event.
     *
     * @param data          object of UserData class containing user's data.
     * @param ui            containing the responses to print.
     * @param argumentWords String array containing user input arguments
     * @return Boolean that confirms if the event was added
     */
    private void addPersonal(UserData data, Ui ui, String[] argumentWords) throws DukeException {
        if (argumentWords.length >= 1 && argumentWords.length <= 3) {
            // 3 cases: only description, description and date, description and date and time
            if (argumentWords.length == 1) {
                data.addToEventList("Personal", new Personal(argumentWords[0].trim()));
            } else if (argumentWords.length == 2) {
                try {
                    LocalDate localDate = DateTimeParser.dateParser(argumentWords[1].trim());
                    assert localDate != null : "date is not detected after parsing";
                    data.addToEventList("Personal", new Personal(argumentWords[0].trim(), localDate));
                } catch (DateErrorException e) {
                    throw new DateErrorException("Something is wrong with the date!");
                }
            } else {
                try {
                    LocalDate localDate = DateTimeParser.dateParser(argumentWords[1].trim());
                    assert localDate != null : "date is not detected after parsing";
                    LocalTime localTime = DateTimeParser.timeParser(argumentWords[2].trim());
                    assert localTime != null : "time is not detected after parsing";
                    data.addToEventList("Personal", new Personal(argumentWords[0].trim(), localDate, localTime));
                } catch (DateErrorException e) {
                    throw new DateErrorException("Something is wrong with the date!");
                } catch (TimeErrorException e) {
                    throw new TimeErrorException("Something is wrong with the time!");
                }
            }
        } else {
            throw new WrongNumberOfArgumentsException("Incorrect number of parameters for Personal event!");
        }
    }

    /**
     * Adds a Zoom event.
     *
     * @param data          object of UserData class containing user's data.
     * @param ui            containing the responses to print.
     * @param argumentWords String array containing user input arguments
     * @return Boolean that confirms if the event was added
     */
    private void addZoom(UserData data, Ui ui, String[] argumentWords) throws DukeException {
        if (argumentWords.length == 2 || argumentWords.length == 4) {
            // 2 cases: only have description & zoomlink , have description,zoomlink, date,time
            if (argumentWords.length == 2) {
                data.addToEventList("Zoom", new Zoom(argumentWords[0].trim(), argumentWords[1].trim()));
            } else {
                try {
                    LocalDate localDate = DateTimeParser.dateParser(argumentWords[2].trim());
                    assert localDate != null : "date is not detected after parsing";
                    LocalTime localTime = DateTimeParser.timeParser(argumentWords[3].trim());
                    assert localTime != null : "time is not detected after parsing";
                    data.addToEventList("Zoom", new Zoom(argumentWords[0].trim(),
                            argumentWords[1].trim(), localDate, localTime));
                } catch (DateErrorException e) {
                    throw new DateErrorException("Something is wrong with the date!");
                } catch (TimeErrorException e) {
                    throw new TimeErrorException("Something is wrong with the time!");
                }
            }
        } else {
            throw new WrongNumberOfArgumentsException("Incorrect number of parameters for Zoom event!");
        }
    }
}
