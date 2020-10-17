package parser;


import command.AddCommand;
import command.ClearCommand;
import command.EditCommand;
import command.ExitCommand;
import command.HelpCommand;
import command.PrintFullListCommand;
import command.PrintLocationCommand;
import command.Command;

import event.Assignment;
import event.PersonalEvent;


import exception.EmptyEventIndexException;
import exception.NoEventLocationException;
import exception.UnknownErrorException;
import exception.WrongEditFormatException;
import location.Building;
import location.Hostel;
import location.LectureTheatre;
import location.Location;
import location.OutOfNuS;
import ui.UI;
import event.Class;
import exception.NoEventTimeMarkerException;
import exception.TimeFormatException;
import exception.EmptyEventException;
import exception.NuScheduleException;
import exception.NoEventTimeException;
import exception.WrongCommandException;


import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


/**
 * This class contains one function -- parse, to call the respective command function according to the user input.
 */
public abstract class Parser {
    public static final String EXIT = "bye";
    public static final String PRINT_Event_LIST = "list";
    public static final String PRINT_LOCATION_LIST = "locations";
    public static final String LOCATE_EVENT = "locate";
    public static final String Event_DONE = "done";
    public static final String ASSIGNMENT = "assignment";
    public static final String CLASS = "class";
    public static final String PERSONAL_EVENT = "personalEvent";
    public static final String Event_DELETE = "delete";
    public static final String Event_FIND = "find";
    public static final String EDIT = "edit";
    public static final String TIME_MARKER = "/t";
    public static final String SINGLE_SPACE = " ";
    public static final String LOCATION_MARKER = "/l";
    private static final String Event_FIND_DATE = "date";
    public static final String EDIT_INSTRUCTION = "Enter new event:";
    public static final String HELP = "help";
    public static final String CLEAR = "clear";

    /**
     * This function calls the correct command the user want to perform, by returning a Command object.
     *
     * @param fullCommand the full string of user input
     * @return the specific Command object to perform what the user want to do
     * @throws NuScheduleException includes all exceptions may happen during parsing
     */
    public static Command parse(String fullCommand) throws NuScheduleException {
        // this block deals with exit and list command
        switch (fullCommand.trim()) {
        case EXIT:
            return new ExitCommand();
        case PRINT_Event_LIST:
            return new PrintFullListCommand();
        case PRINT_LOCATION_LIST:
            return new PrintLocationCommand();
        case HELP:
            return new HelpCommand();
        case CLEAR:
            return new ClearCommand();
        default:
            break;
        }

        String[] words = fullCommand.split(SINGLE_SPACE);

        //        //this block deals with find command
        //        if (words[0].equals(Event_FIND)) {
        //            if (fullCommand.substring(4).isBlank()) {
        //                throw new EmptyFindException();
        //            }
        //            return new FindCommand(fullCommand.substring(5));
        //        }
        //
        //        //this block deals with find date command
        //        if (words[0].equals(Event_FIND_DATE)) {
        //            if (fullCommand.substring(4).isBlank()) {
        //                throw new EmptyFindDateException();
        //            }
        //            try {
        //                return new FindDateCommand(LocalDate.parse(fullCommand.substring(5)));
        //            } catch (DateTimeParseException e) {
        //                throw new DateFormatException();
        //            }
        //        }
        //
        //        int EventIndex;//to indicate what is the Event we are dealing with. may not be used.
        //
        //        //this block deals with done command
        //        if (words[0].equals(Event_DONE)) {
        //            if (fullCommand.substring(4).isBlank()) {
        //                throw new EmptyDoneException();
        //            }
        //            try {
        //                EventIndex = Integer.parseInt(fullCommand.substring(5)) - 1;
        //            } catch (NumberFormatException e) {
        //                throw new DoneNumberFormatException();
        //            }
        //            return new DoneCommand(EventIndex);
        //        }
        //
        //        //this block deals with delete command
        //        if (words[0].equals(Event_DELETE)) {
        //            if (fullCommand.substring(6).isBlank()) {
        //                throw new EmptyDeleteException();
        //            }
        //            try {
        //                EventIndex = Integer.parseInt(fullCommand.substring(7)) - 1;
        //            } catch (NumberFormatException e) {
        //                throw new DeleteNumberFormatException();
        //            }
        //            return new DeleteCommand(EventIndex);
        //        }
        //

        //these variables are used by either Edit or Add
        //the position of /t
        int timeDividerPosition;
        //the position of the space when the user enters a date time in the format yyyy-mm-dd HH:mm
        int timeDivider;
        //the position of /l
        int locationDividerPosition;
        String dateTime;
        Location location;

        //this block deals with edit command
        //this block will change fullCommand, but this does not affect the later block since
        //it either return an EditCommand, or throw an exception
        if (words[0].equals(EDIT)) {
            UI ui = new UI();
            int eventIndex = -1;
            if (fullCommand.substring(5).isBlank()) {
                throw new EmptyEventIndexException();
            }

            try {
                eventIndex = Integer.parseInt(fullCommand.substring(5)) - 1;
            } catch (NumberFormatException e) {
                throw new WrongEditFormatException();
            }

            ui.print(EDIT_INSTRUCTION);
            fullCommand = ui.readCommand();
            words = fullCommand.split(SINGLE_SPACE);

            //the following part is almost the same as AddCommand, but returns EditCommand
            timeDividerPosition = fullCommand.indexOf(TIME_MARKER);
            locationDividerPosition = fullCommand.indexOf(LOCATION_MARKER);

            switch (words[0]) {
            case ASSIGNMENT:
            case CLASS:
            case PERSONAL_EVENT:
                if (timeDividerPosition == -1) {
                    throw new NoEventTimeMarkerException();
                }

                if (locationDividerPosition == -1) {
                    throw new NoEventLocationException();
                }

                if (fullCommand.substring(words[0].length(), timeDividerPosition).isBlank()) {
                    throw new EmptyEventException();
                }

                if (fullCommand.substring(timeDividerPosition + 3, locationDividerPosition - 1).isBlank()) {
                    throw new NoEventTimeException();
                }

                if (fullCommand.substring(locationDividerPosition + 3).isBlank()) {
                    throw new NoEventLocationException();
                }

                try {
                    timeDivider = fullCommand.substring(timeDividerPosition + 3).indexOf(SINGLE_SPACE);
                    dateTime = fullCommand.substring(timeDividerPosition + 3, timeDividerPosition + 4 + timeDivider)
                            + "T"
                            + fullCommand.substring(timeDividerPosition + 3 + timeDivider + 1,
                            locationDividerPosition - 1);

                    location = parseLocation(fullCommand.substring(locationDividerPosition + 3));
                    switch (words[0]) {
                    case ASSIGNMENT:
                        return new EditCommand(new Assignment(fullCommand.substring(words[0].length() + 1,
                                timeDividerPosition - 1), location, LocalDateTime.parse(dateTime)), eventIndex);
                    case CLASS:
                        return new EditCommand(new Class(fullCommand.substring(words[0].length() + 1,
                                timeDividerPosition - 1), location, LocalDateTime.parse(dateTime)), eventIndex);
                    case PERSONAL_EVENT:
                        return new EditCommand(new PersonalEvent(fullCommand.substring(words[0].length() + 1,
                                timeDividerPosition - 1), location, LocalDateTime.parse(dateTime)), eventIndex);
                    default:
                        break;
                    }
                } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                    throw new TimeFormatException();
                }
                break;
            default:
                throw new WrongCommandException();
            }
        }

        //this block deals with add command
        //we shall check that the user input is not meant for any other command beforehand
        //because the default block will throw an exception.
        // i.e. when this block is entered, the parser will not go to any other blocks
        switch (words[0]) {
        case ASSIGNMENT:
        case CLASS:
        case PERSONAL_EVENT:
            timeDividerPosition = fullCommand.indexOf(TIME_MARKER);
            locationDividerPosition = fullCommand.indexOf(LOCATION_MARKER);
            if (timeDividerPosition == -1) {
                throw new NoEventTimeMarkerException();
            }

            if (locationDividerPosition == -1) {
                throw new NoEventLocationException();
            }

            if (fullCommand.substring(words[0].length(), timeDividerPosition).isBlank()) {
                throw new EmptyEventException();
            }

            if (fullCommand.substring(timeDividerPosition + 3, locationDividerPosition - 1).isBlank()) {
                throw new NoEventTimeException();
            }

            if (fullCommand.substring(locationDividerPosition + 3).isBlank()) {
                throw new NoEventLocationException();
            }

            try {
                timeDivider = fullCommand.substring(timeDividerPosition + 3).indexOf(SINGLE_SPACE);
                dateTime = fullCommand.substring(timeDividerPosition + 3, timeDividerPosition + 3 + timeDivider)
                        + "T"
                        + fullCommand.substring(timeDividerPosition + 3 + timeDivider + 1,
                        locationDividerPosition - 1);
                System.out.println(dateTime);
                location = parseLocation(fullCommand.substring(locationDividerPosition + 3));
                switch (words[0]) {
                case ASSIGNMENT:
                    return new AddCommand(new Assignment(fullCommand.substring(words[0].length() + 1,
                            timeDividerPosition - 1), location, LocalDateTime.parse(dateTime)));
                case CLASS:
                    return new AddCommand(new Class(fullCommand.substring(words[0].length() + 1,
                            timeDividerPosition - 1), location, LocalDateTime.parse(dateTime)));
                case PERSONAL_EVENT:
                    return new AddCommand(new PersonalEvent(fullCommand.substring(words[0].length() + 1,
                            timeDividerPosition - 1), location, LocalDateTime.parse(dateTime)));
                default:
                    break;
                }
            } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                throw new TimeFormatException();
            }
            break;
        default:
            throw new WrongCommandException();
        }

        assert false;//nothing should reach here
        throw new UnknownErrorException();
    }

    /**
     * This method parses the inputted location.
     *
     * @param input the string inputted by the user.
     * @return the parsed location.
     */
    public static Location parseLocation(String input) {
        Location location = null;
        try {
            String[] info = input.split("/");
            String[] additionalInfo = info[2].split(",");


            switch (info[0]) {
            case "BLK":
                location = new Building(info[1], additionalInfo);
                break;
            case "H":
                location = new Hostel(info[1], additionalInfo);
                break;
            case "L":
                location = new LectureTheatre(info[1], info[2]);
                break;
            case "OUT":
                location = new OutOfNuS(info[1]);
                break;
            default:
                location = null;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            location = null;
        }
        return location;
    }
}

