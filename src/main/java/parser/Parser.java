package parser;


import command.AddCommand;
import command.Command;
import command.ExitCommand;
import command.PrintFullListCommand;

import event.Assignment;
import event.PersonalEvent;

import exception.*;
import location.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * This class contains one function -- parse, to call the respective command function according to the user input.
 */
public abstract class Parser {
    public static final String EXIT = "bye";
    public static final String PRINT_Event_LIST = "list";
    public static final String Event_DONE = "done";
    public static final String ADD_ASSIGNMENT = "assignment";
    public static final String ADD_CLASS = "class";
    public static final String ADD_PERSONAL_EVENT = "personalevent";
    public static final String Event_DELETE = "delete";
    public static final String Event_FIND = "find";
    public static final String BY = "/by";
    public static final String SINGLE_SPACE = " ";
    public static final String AT = "/at";
    public static final String LOCATION = "/l";
    private static final String Event_FIND_DATE = "date";

    /**
     * This function calls the correct command the user want to perform, by returning a <\code>Command<\code> object.
     *
     * @param fullCommand the full string of user input
     * @return the specific <\code>Command<\code> object to perform what the user want to do
     * //     * @throws NuScheduleException includes all exceptions may happen during parsing
     */
    public static Command parse(String fullCommand) throws NuScheduleException {
        // this block deals with exit and list command
        if (fullCommand.equals(EXIT)) {
            return new ExitCommand();
        } else if (fullCommand.equals(PRINT_Event_LIST)) {
            return new PrintFullListCommand();
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
        int timeDividerPosition;
        int timeDivider;
        int locationDividerPosition;
        String dateTime;
        Location location;
        switch (words[0]) {
            case ADD_ASSIGNMENT:
                timeDividerPosition = fullCommand.indexOf(BY);
                locationDividerPosition = fullCommand.indexOf(LOCATION);

                if (fullCommand.substring(10).isBlank()) {
                    throw new EmptyEventException();
                }
                if (timeDividerPosition == -1 | locationDividerPosition == -1) {
                    throw new NoEventMarkerException();
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
                timeDividerPosition = fullCommand.indexOf(AT);
                locationDividerPosition = fullCommand.indexOf(LOCATION);

                if (fullCommand.substring(5).isBlank()) {
                    throw new EmptyEventException();
                }
                if (timeDividerPosition == -1 | locationDividerPosition == -1) {
                    throw new NoEventMarkerException();
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

                    if (location == null){
                        throw new NoEventLocationException();
                    }

                    return new AddCommand(new event.Class(fullCommand.substring(5, timeDividerPosition)
                            , location, LocalDateTime.parse(dateTime)));
                } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                    throw new TimeFormatException();
                }

            case ADD_PERSONAL_EVENT:
                timeDividerPosition = fullCommand.indexOf(AT);
                locationDividerPosition = fullCommand.indexOf(LOCATION);

                if (fullCommand.substring(13).isBlank()) {
                    throw new EmptyEventException();
                }
                if (timeDividerPosition == -1 | locationDividerPosition == -1) {
                    throw new NoEventMarkerException();
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
//                case "OUT":
//                    location = new OutOfNUS(info[1]);
//                    break;
                default:
                    location = null;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            location = null;
        }
        return location;
    }
}

