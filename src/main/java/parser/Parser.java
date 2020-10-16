package parser;


import command.*;

import event.Assignment;
import event.PersonalEvent;


import exception.EmptyEventIndexException;
import location.Location;
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
    public static final String ADD_ASSIGNMENT = "assignment";
    public static final String ADD_CLASS = "class";
    public static final String ADD_PERSONAL_EVENT = "personalEvent";
    public static final String Event_DELETE = "delete";
    public static final String Event_FIND = "find";
    public static final String EDIT = "edit";
    public static final String TIME_MARKER = "/t";
    public static final String SINGLE_SPACE = " ";
    public static final String LOCATION = "/l";
    private static final String Event_FIND_DATE = "date";
    public static final String EDIT_INSTRUCTION = "Enter new event:";

    /**
     * This function calls the correct command the user want to perform, by returning a <\code>Command<\code> object.
     *
     * @param fullCommand the full string of user input
     * @return the specific <\code>Command<\code> object to perform what the user want to do
     * //     * @throws NuScheduleException includes all exceptions may happen during parsing
     */
    public static Command parse(String fullCommand) throws NuScheduleException {
        // this block deals with exit and list command
        switch (fullCommand) {
            case EXIT:
                return new ExitCommand();
            break;
            case PRINT_Event_LIST:
                return new PrintFullListCommand();
            break;
            case PRINT_LOCATION_LIST:
                return new PrintLocationCommand();
            break;
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

        //this block deals with add command
        //we shall check that the user input is not meant for any other command beforehand
        //because the default block will throw an exception.
        // i.e. when this block is entered, the parser will not go to any other blocks
        int dividerPosition;
        int timeDivider;
        String dateTime;
        switch (words[0]) {
            case EDIT:
                UI ui = new UI();
                int index = -1;
                if (fullCommand.length() <= 4) {
                    throw new EmptyEventIndexException();
                }
                if (fullCommand.substring(5).isBlank()) {
                    throw new EmptyEventIndexException();
                }

                try {
                    index = Integer.parseInt(fullCommand.substring(5)) - 1;
                } catch (NumberFormatException e) {
                    throw new EmptyEventIndexException();
                }

                ui.print(EDIT_INSTRUCTION);
                String newCommand = ui.readCommand();
                int firstDivider;

                if (newCommand.startsWith(ADD_ASSIGNMENT)) {
                    firstDivider = 10;
                } else if (newCommand.startsWith(ADD_CLASS)) {
                    firstDivider = 5;
                } else if (newCommand.startsWith(ADD_PERSONAL_EVENT)) {
                    firstDivider = 13;
                } else {
                    firstDivider = 0;
                }

                if (firstDivider != 0) {
                    dividerPosition = newCommand.indexOf(TIME_MARKER);

                    if (newCommand.substring(firstDivider).isBlank()) {
                        throw new EmptyEventException();
                    }
                    if (dividerPosition == -1) {
                        throw new NoEventTimeMarkerException();
                    }
                    if (newCommand.substring(firstDivider, dividerPosition).isBlank()) {
                        throw new EmptyEventException();
                    }
                    try {
                        newCommand.substring(dividerPosition + 4);
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new NoEventTimeException();
                    }
                    try {
                        timeDivider = newCommand.substring(dividerPosition + 4).indexOf(SINGLE_SPACE);
                        dateTime = newCommand.substring(dividerPosition + 4, dividerPosition + 4 + timeDivider)
                                + "T"
                                + newCommand.substring(dividerPosition + 4 + timeDivider + 1);
                        return new EditCommand(new Assignment(newCommand.substring(firstDivider, dividerPosition)
                                , LocalDateTime.parse(dateTime), new Location()), index);
                    } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                        throw new TimeFormatException();
                    }
                }


            case ADD_ASSIGNMENT:
                dividerPosition = fullCommand.indexOf(TIME_MARKER);

                if (fullCommand.substring(10).isBlank()) {
                    throw new EmptyEventException();
                }
                if (dividerPosition == -1) {
                    throw new NoEventTimeMarkerException();
                }
                if (fullCommand.substring(10, dividerPosition).isBlank()) {
                    throw new EmptyEventException();
                }
                try {
                    fullCommand.substring(dividerPosition + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new NoEventTimeException();
                }
                try {
                    timeDivider = fullCommand.substring(dividerPosition + 4).indexOf(SINGLE_SPACE);
                    dateTime = fullCommand.substring(dividerPosition + 4, dividerPosition + 4 + timeDivider)
                            + "T"
                            + fullCommand.substring(dividerPosition + 4 + timeDivider + 1);
                    return new AddCommand(new Assignment(fullCommand.substring(10, dividerPosition)
                            , LocalDateTime.parse(dateTime)));
                } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                    throw new TimeFormatException();
                }


            case ADD_CLASS:
                dividerPosition = fullCommand.indexOf(TIME_MARKER);

                if (fullCommand.substring(5).isBlank()) {
                    throw new EmptyEventException();
                }
                if (dividerPosition == -1) {
                    throw new NoEventTimeMarkerException();
                }
                if (fullCommand.substring(5, dividerPosition).isBlank()) {
                    throw new EmptyEventException();
                }
                try {
                    fullCommand.substring(dividerPosition + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new NoEventTimeException();
                }
                try {
                    timeDivider = fullCommand.substring(dividerPosition + 4).indexOf(SINGLE_SPACE);
                    dateTime = fullCommand.substring(dividerPosition + 4, dividerPosition + 4 + timeDivider)
                            + "T"
                            + fullCommand.substring(dividerPosition + 4 + timeDivider + 1);

                    return new AddCommand(new Class(fullCommand.substring(5, dividerPosition)
                            , LocalDateTime.parse(dateTime)));
                } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                    throw new TimeFormatException();
                }

            case ADD_PERSONAL_EVENT:
                dividerPosition = fullCommand.indexOf(TIME_MARKER);
                if (fullCommand.substring(13).isBlank()) {
                    throw new EmptyEventException();
                }
                if (dividerPosition == -1) {
                    throw new NoEventTimeMarkerException();
                }
                if (fullCommand.substring(13, dividerPosition).isBlank()) {
                    throw new EmptyEventException();
                }
                try {
                    fullCommand.substring(dividerPosition + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new NoEventTimeException();
                }
                try {
                    timeDivider = fullCommand.substring(dividerPosition + 4).indexOf(SINGLE_SPACE);
                    dateTime = fullCommand.substring(dividerPosition + 4, dividerPosition + 4 + timeDivider)
                            + "T"
                            + fullCommand.substring(dividerPosition + 4 + timeDivider + 1);
                    return new AddCommand(new PersonalEvent(fullCommand.substring(13, dividerPosition)
                            , LocalDateTime.parse(dateTime)));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new EmptyEventException();
                }

            case LOCATE_EVENT:


            default:
                throw new WrongCommandException();


        }
        if (timeDividerPosition == -1 | locationDividerPosition == -1) {
            throw new NoEventTimeMarkerException();
        }
        if (fullCommand.substring(10, timeDividerPosition).isBlank()) {
            throw new EmptyEventException();
        }
        try {
            fullCommand.substring(timeDividerPosition + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoEventTimeException();
        }
        try {
            fullCommand.substring(locationDividerPosition + 3);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoEventLocationException();
        }
        try {
            timeDivider = fullCommand.substring(timeDividerPosition + 4).indexOf(SINGLE_SPACE);
            dateTime = fullCommand.substring(timeDividerPosition + 4, timeDividerPosition + 4 + timeDivider)
                    + "T"
                    + fullCommand.substring(timeDividerPosition + 4 + timeDivider + 1, locationDividerPosition - 1);

            location = parseLocation(fullCommand.substring(locationDividerPosition + 3));

            return new AddCommand(new Assignment(fullCommand.substring(10, timeDividerPosition)
                    , location, LocalDateTime.parse(dateTime)));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new TimeFormatException();
        }


        case ADD_CLASS:
        timeDividerPosition = fullCommand.indexOf(TIME_MARKER);
        locationDividerPosition = fullCommand.indexOf(LOCATION);

        if (fullCommand.substring(5).isBlank()) {
            throw new EmptyEventException();
        }
        if (timeDividerPosition == -1 | locationDividerPosition == -1) {
            throw new NoEventTimeMarkerException();
        }
        if (fullCommand.substring(5, timeDividerPosition).isBlank()) {
            throw new EmptyEventException();
        }
        try {
            fullCommand.substring(timeDividerPosition + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoEventTimeException();
        }
        try {
            fullCommand.substring(locationDividerPosition + 3);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoEventLocationException();
        }
        try {
            timeDivider = fullCommand.substring(timeDividerPosition + 4).indexOf(SINGLE_SPACE);
            dateTime = fullCommand.substring(timeDividerPosition + 4, timeDividerPosition + 4 + timeDivider)
                    + "T"
                    + fullCommand.substring(timeDividerPosition + 4 + timeDivider + 1, locationDividerPosition - 1);

            location = parseLocation(fullCommand.substring(locationDividerPosition + 3));

            if (location == null) {
                throw new NoEventLocationException();
            }

            return new AddCommand(new event.Class(fullCommand.substring(5, timeDividerPosition)
                    , location, LocalDateTime.parse(dateTime)));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new TimeFormatException();
        }

        case ADD_PERSONAL_EVENT:
        timeDividerPosition = fullCommand.indexOf(TIME_MARKER);
        locationDividerPosition = fullCommand.indexOf(LOCATION);

        if (fullCommand.substring(13).isBlank()) {
            throw new EmptyEventException();
        }
        if (timeDividerPosition == -1 | locationDividerPosition == -1) {
            throw new NoEventTimeMarkerException();
        }
        if (fullCommand.substring(13, timeDividerPosition).isBlank()) {
            throw new EmptyEventException();
        }
        try {
            fullCommand.substring(timeDividerPosition + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoEventTimeException();
        }
        try {
            fullCommand.substring(locationDividerPosition + 3);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoEventLocationException();
        }
        try {
            timeDivider = fullCommand.substring(timeDividerPosition + 4).indexOf(SINGLE_SPACE);
            dateTime = fullCommand.substring(timeDividerPosition + 4, timeDividerPosition + 4 + timeDivider)
                    + "T"
                    + fullCommand.substring(timeDividerPosition + 4 + timeDivider + 1, locationDividerPosition - 1);

            location = parseLocation(fullCommand.substring(locationDividerPosition + 3));

            return new AddCommand(new PersonalEvent(fullCommand.substring(13, timeDividerPosition)
                    , location, LocalDateTime.parse(dateTime)));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new TimeFormatException();
        }

        default:
        throw new WrongCommandException();

    }

}

    /**
     * This method parses the inputted location
     *
     * @param input the string inputted by the user
     * @return the parsed location
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
                    location = new OutOfNUS(info[1]);
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

