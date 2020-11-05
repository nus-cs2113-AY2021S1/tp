package parser;

import command.AddCommand;
import command.ClearCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.EditCommand;
import command.ExitCommand;
import command.FindCommand;
import command.FindDateCommand;
import command.HelpCommand;
import command.LocateCommand;
import command.PrintFullListCommand;
import command.PrintLocationCommand;
import command.ReminderCommand;
import command.SortCommand;

import command.StudyTimeCommand;
import command.UserInfoCommand;
import event.Assignment;
import event.PersonalEvent;
import event.Assignment;
import event.Class;
import event.PersonalEvent;
import event.SelfStudy;
import exception.DateFormatException;
import exception.DeleteNumberFormatException;
import exception.DoneNumberFormatException;
import exception.DoubleTimeAssignmentException;
import exception.EmptyDeleteException;
import exception.EmptyDoneException;
import exception.EmptyEventException;
import exception.EmptyEventIndexException;
import exception.EmptyFindDateException;
import exception.EmptyFindException;
import exception.EmptyStudyTimeDateException;
import exception.InvalidEditLocationException;
import exception.InvalidEditTypeException;
import exception.InvalidSortCriteriaException;
import exception.NoEndTimeClassException;
import exception.NoEditEventDescriptionException;

import exception.NoEventLocationException;
import exception.NoEventTimeException;
import exception.NoEventTimeMarkerException;
import exception.NoPasswordException;
import exception.NoSortCriteriaException;
import exception.NuScheduleException;
import exception.TimeFormatException;
import exception.UndefinedEventException;
import exception.UnknownErrorException;
import exception.WrongCommandException;
import exception.WrongEditFormatException;
import location.Building;
import location.Hostel;
import location.LectureTheatre;
import location.Location;
import location.OnlineLocation;
import location.OutOfNuS;
import locationlist.LocationList;
import usercommunication.UserInfo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


/**
 * This class contains one function -- parse, to call the respective command function according to the user input.
 */
public abstract class Parser {
    public static final String EXIT = "bye";
    public static final String PRINT_EVENT_LIST = "list";
    public static final String PRINT_LOCATION_LIST = "locations";
    public static final String LOCATE_EVENT = "locate";
    public static final String EVENT_DONE = "done";
    public static final String ASSIGNMENT = "assignment";
    public static final String CLASS = "class";
    public static final String PERSONAL_EVENT = "personalEvent";
    public static final String EVENT_DELETE = "delete";
    public static final String EVENT_FIND = "find";
    public static final String EDIT = "edit";
    public static final String TIME_MARKER = "/t";
    public static final String SINGLE_SPACE = " ";
    public static final String LOCATION_MARKER = "/l";
    public static final String EVENT_FIND_DATE = "date";
    public static final String HELP = "help";
    public static final String CLEAR = "clear";
    public static final String SORT = "sort";
    public static final String END_TIME_MARKER = "/e";
    public static final String ONLINE_LOCATION_MARKER = "/o";
    public static final String PASSWORD_MARKER = "/p";
    public static final String REMIND = "reminder";
    public static final String STUDY_TIME = "studyTime";
    public static final String SELF_STUDY = "selfStudy";

    /**
     * This function calls the correct command the user want to perform, by returning a Command object.
     *
     * @param fullCommand the full string of user input
     * @return the specific Command object to perform what the user want to do
     * @throws NuScheduleException includes all exceptions may happen during parsing
     */

    public static Command parse(String fullCommand, LocationList locations, int size) throws NuScheduleException {

        //deletes all the starting and ending spaces
        fullCommand = fullCommand.trim();
        // this block deals with single word command
        switch (fullCommand) {
        case EXIT:
            return new ExitCommand();
        case PRINT_EVENT_LIST:
            return new PrintFullListCommand();
        case PRINT_LOCATION_LIST:
            return new PrintLocationCommand();
        case HELP:
            return new HelpCommand();
        case CLEAR:
            return new ClearCommand();
        case REMIND:
            return new ReminderCommand();
        default:
            break;
        }

        String[] words = fullCommand.split(SINGLE_SPACE);

        //this block deals with user info
        switch (words[0]) {
        case "student":
        case "professor":
            return new UserInfoCommand(new UserInfo(fullCommand.substring(words[0].length() + 1), words[0]));
        default:
            break;
        }

        //this block deals with locate command
        if (words[0].equalsIgnoreCase(LOCATE_EVENT)) {
            return new LocateCommand(words[1]);
        }

        //this block deals with find command
        if (words[0].equalsIgnoreCase(EVENT_FIND)) {
            if (fullCommand.substring(4).isBlank()) {
                throw new EmptyFindException();
            }
            return new FindCommand(fullCommand.substring(5));
        }

        //this block deals with find date command
        if (words[0].equalsIgnoreCase(EVENT_FIND_DATE)) {
            if (fullCommand.substring(4).isBlank()) {
                throw new EmptyFindDateException();
            }
            try {
                return new FindDateCommand(LocalDate.parse(fullCommand.substring(5)));
            } catch (DateTimeParseException e) {
                throw new DateFormatException();
            }
        }

        //this block deals with study time command
        if (words[0].equalsIgnoreCase(STUDY_TIME)) {
            if (fullCommand.substring(9).isBlank()) {
                throw new EmptyStudyTimeDateException();
            }
            try {
                return new StudyTimeCommand(LocalDate.parse(fullCommand.substring(10)));
            } catch (DateTimeParseException e) {
                throw new DateFormatException();
            }
        }

        int eventIndex;

        //this block deals with done command
        if (words[0].equalsIgnoreCase(EVENT_DONE)) {
            if (fullCommand.substring(4).isBlank()) {
                throw new EmptyDoneException();
            }
            try {
                eventIndex = Integer.parseInt(fullCommand.substring(5).trim()) - 1;
            } catch (NumberFormatException e) {
                throw new DoneNumberFormatException();
            }
            return new DoneCommand(eventIndex);
        }

        //this block deals with delete command
        if (words[0].equalsIgnoreCase(EVENT_DELETE)) {
            if (fullCommand.substring(6).isBlank()) {
                throw new EmptyDeleteException();
            }
            try {
                eventIndex = Integer.parseInt(fullCommand.substring(7)) - 1;
            } catch (NumberFormatException e) {
                throw new DeleteNumberFormatException();
            }
            return new DeleteCommand(eventIndex);
        }

        //this block deals with sorting
        if (words[0].equalsIgnoreCase(SORT)) {
            if (fullCommand.length() == 4) {
                throw new NoSortCriteriaException();
            }
            String type = words[1].trim().toLowerCase();
            switch (type) {
            case "description":
            case "time":
            case "location":
                return new SortCommand(type);
            default:
                throw new InvalidSortCriteriaException();
            }
        }


        //these variables are used by either Edit or Add
        //the position of /t
        int startTimeDividerPosition = fullCommand.indexOf(TIME_MARKER);
        ;
        //the position of the space when the user enters a date time in the format yyyy-mm-dd HH:mm
        int timeDivider;
        //the position of /et
        int endTimeDividerPosition = fullCommand.indexOf(END_TIME_MARKER);
        //the position of /l
        int locationDividerPosition = fullCommand.indexOf(LOCATION_MARKER);
        //the position of /o
        int onlineLocationDividerPosition = fullCommand.indexOf(ONLINE_LOCATION_MARKER);
        //the position of /p
        int pwdDividerPosition = fullCommand.indexOf(PASSWORD_MARKER);
        String startDateTime;
        Location location = null;
        OnlineLocation onlineLocation = null;
        String endDateTime;
        //the position of the space when the user enters an ending date time in the format yyyy-mm-dd HH:mm
        int endingTimeDivider;


        //this block deals with edit command
        if (words[0].equalsIgnoreCase(EDIT)) {
            if (fullCommand.length() == 4) {
                throw new EmptyEventIndexException();
            }

            try {
                eventIndex = Integer.parseInt(fullCommand.substring(5).trim()) - 1;
            } catch (NumberFormatException e) {
                throw new WrongEditFormatException();
            }

            // Check if the index exists
            if (eventIndex + 1 > size || eventIndex == -1) {
                throw new UndefinedEventException(eventIndex + 1);
            }

            String[] editInformation = EditCommand.newEditInformation();
            LocalDateTime[] startEnd = new LocalDateTime[2];


            if (!editInformation[0].isBlank()) {
                if (!editInformation[0].equalsIgnoreCase(ASSIGNMENT) && !editInformation[0].equalsIgnoreCase(CLASS)
                        && !editInformation[0].equalsIgnoreCase(PERSONAL_EVENT)
                        && !editInformation[0].equalsIgnoreCase(SELF_STUDY)) {

                    throw new InvalidEditTypeException();
                }
            }


            // user input validation for location
            if (!editInformation[2].isBlank()) {
                editInformation[2] = editInformation[2].trim();
                if (editInformation[2].startsWith(LOCATION_MARKER)) {
                    location = parseLocation(editInformation[2].substring(3), locations);
                } else if (editInformation[2].startsWith(ONLINE_LOCATION_MARKER)) {
                    int pwdPos = editInformation[2].indexOf(PASSWORD_MARKER);
                    if (pwdPos == -1) {
                        onlineLocation =
                                new OnlineLocation(editInformation[2].substring(3).trim());
                    } else {
                        onlineLocation =
                                new OnlineLocation(editInformation[2].substring(3, pwdPos - 1).trim(),
                                        editInformation[2].substring(pwdPos + 3).trim());
                    }
                } else {
                    throw new InvalidEditLocationException();

                }
            }


            // user input validation for start and end time
            if (!editInformation[3].isBlank()) {
                if (editInformation[3].length() != 16) {
                    throw new TimeFormatException();
                }
                startDateTime = editInformation[3].substring(0, 10) + "T" + editInformation[3].substring(11);
                try {
                    startEnd[0] = LocalDateTime.parse(startDateTime);
                    //System.out.println(startEnd[0]);
                } catch (DateTimeException e) {
                    throw new TimeFormatException();
                }
            }

            if (!editInformation[4].isBlank()) {
                if (!editInformation[4].equals("nil")) {
                    if (editInformation[4].length() != 16) {
                        throw new TimeFormatException();
                    }
                    startDateTime = editInformation[4].substring(0, 10) + "T" + editInformation[4].substring(11);
                    try {
                        startEnd[1] = LocalDateTime.parse(startDateTime);
                        //System.out.println(startEnd[1]);
                    } catch (DateTimeException e) {
                        throw new TimeFormatException();
                    }
                }
            }

            return new EditCommand(eventIndex, editInformation, startEnd, location, onlineLocation);

        }

        //this block deals with add command
        //we shall check that the user input is not meant for any other command beforehand
        //because the default block will throw an exception.
        // i.e. when this block is entered, the parser will not go to any other blocks
        if (words[0].equalsIgnoreCase(ASSIGNMENT) || words[0].equalsIgnoreCase(CLASS)
                || words[0].equalsIgnoreCase(PERSONAL_EVENT) || words[0].equalsIgnoreCase(SELF_STUDY)) {
            if (fullCommand.substring(words[0].length()).isBlank()) {
                throw new EmptyEventException();
            }

            if (startTimeDividerPosition == -1) {
                throw new NoEventTimeMarkerException();
            }

            if (locationDividerPosition == -1 && onlineLocationDividerPosition == -1) {
                throw new NoEventLocationException();
            }

            if (fullCommand.substring(words[0].length(), startTimeDividerPosition).isBlank()) {
                throw new EmptyEventException();
            }

            if (locationDividerPosition != -1) {
                if (fullCommand.substring(startTimeDividerPosition + 2, locationDividerPosition - 1).isBlank()) {
                    throw new NoEventTimeException();
                }
                if (fullCommand.substring(locationDividerPosition + 2).isBlank()) {
                    throw new NoEventLocationException();
                }
            } else {
                if (fullCommand.substring(startTimeDividerPosition + 2, onlineLocationDividerPosition - 1)
                        .isBlank()) {
                    throw new NoEventTimeException();
                }
                if (fullCommand.substring(onlineLocationDividerPosition + 2).isBlank()) {
                    throw new NoEventLocationException();
                }

            }


            //this deals with the event holding offline
            if (locationDividerPosition != -1) {
                try {
                    timeDivider = fullCommand.substring(startTimeDividerPosition + 3).indexOf(SINGLE_SPACE);
                    location = parseLocation(fullCommand.substring(locationDividerPosition + 3), locations);

                    switch (words[0]) {
                    case ASSIGNMENT:
                        if (endTimeDividerPosition != -1) {
                            throw new DoubleTimeAssignmentException();
                        }
                        startDateTime = getStartDateTime(fullCommand, startTimeDividerPosition, timeDivider,
                                locationDividerPosition);

                        return new AddCommand(new Assignment(fullCommand.substring(words[0].length() + 1,
                                startTimeDividerPosition - 1), location, LocalDateTime.parse(startDateTime)));
                    case CLASS:
                        if (endTimeDividerPosition == -1) {
                            throw new NoEndTimeClassException();
                        }

                        startDateTime = getStartDateTime(fullCommand, startTimeDividerPosition, timeDivider,
                                endTimeDividerPosition);

                        endingTimeDivider = fullCommand.substring(endTimeDividerPosition + 3,
                                locationDividerPosition - 1).indexOf(SINGLE_SPACE);

                        //if the user does not input the date of the ending time, by default it ends at the same
                        // day as the starting time
                        endDateTime = getEndDateTime(fullCommand, startTimeDividerPosition, timeDivider,
                                endTimeDividerPosition, locationDividerPosition, endingTimeDivider);

                        return new AddCommand(new Class(fullCommand.substring(words[0].length() + 1,
                                startTimeDividerPosition - 1), location, LocalDateTime.parse(startDateTime),
                                LocalDateTime.parse(endDateTime)));
                    case PERSONAL_EVENT:
                    case SELF_STUDY:
                        if (endTimeDividerPosition == -1) {
                            startDateTime = getStartDateTime(fullCommand, startTimeDividerPosition, timeDivider,
                                    locationDividerPosition);
                            if (words[0].equalsIgnoreCase(PERSONAL_EVENT)) {
                                return new AddCommand(new PersonalEvent(fullCommand.substring(words[0].length() + 1,
                                        startTimeDividerPosition - 1), location, LocalDateTime.parse(startDateTime)));
                            } else {
                                return new AddCommand(new SelfStudy(fullCommand.substring(words[0].length() + 1,
                                        startTimeDividerPosition - 1), location, LocalDateTime.parse(startDateTime)));
                            }
                        } else {
                            startDateTime = getStartDateTime(fullCommand, startTimeDividerPosition, timeDivider,
                                    endTimeDividerPosition);

                            endingTimeDivider = fullCommand.substring(endTimeDividerPosition + 3,
                                    locationDividerPosition - 1).indexOf(SINGLE_SPACE);

                            //if the user does not input the date of the ending time, by default it ends at the same
                            // day as the starting time
                            endDateTime = getEndDateTime(fullCommand, startTimeDividerPosition, timeDivider,
                                    endTimeDividerPosition, locationDividerPosition, endingTimeDivider);
                            if (words[0].equalsIgnoreCase(PERSONAL_EVENT)) {
                                return new AddCommand(new PersonalEvent(fullCommand.substring(words[0].length() + 1,
                                        startTimeDividerPosition - 1), location, LocalDateTime.parse(startDateTime),
                                        LocalDateTime.parse(endDateTime)));
                            } else {
                                return new AddCommand(new SelfStudy(fullCommand.substring(words[0].length() + 1,
                                        startTimeDividerPosition - 1), location, LocalDateTime.parse(startDateTime),
                                        LocalDateTime.parse(endDateTime)));
                            }
                        }
                    default:
                        break;
                    }
                } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                    throw new TimeFormatException();

                }
            } else { //this deals with the event holding online
                try {
                    timeDivider = fullCommand.substring(startTimeDividerPosition + 3).indexOf(SINGLE_SPACE);
                    OnlineLocation virtualLocation;
                    if (pwdDividerPosition == -1) {
                        virtualLocation =
                                new OnlineLocation(fullCommand.substring(onlineLocationDividerPosition + 3));
                    } else {
                        try {
                            if (fullCommand.substring(onlineLocationDividerPosition + 2, pwdDividerPosition - 1)
                                    .isBlank()) {
                                throw new NoEventLocationException();
                            }
                        } catch (NullPointerException | StringIndexOutOfBoundsException e) {
                            throw new NoEventLocationException();
                        }
                        if (fullCommand.substring(pwdDividerPosition + 2).isBlank()) {
                            throw new NoPasswordException();
                        }
                        virtualLocation =
                                new OnlineLocation(fullCommand.substring(onlineLocationDividerPosition + 3,
                                        pwdDividerPosition - 1), fullCommand.substring(pwdDividerPosition + 3));
                    }

                    switch (words[0]) {
                    case ASSIGNMENT:
                        if (endTimeDividerPosition != -1) {
                            throw new DoubleTimeAssignmentException();
                        }
                        startDateTime = getStartDateTime(fullCommand, startTimeDividerPosition, timeDivider,
                                onlineLocationDividerPosition);

                        return new AddCommand(new Assignment(fullCommand.substring(words[0].length() + 1,
                                startTimeDividerPosition - 1), virtualLocation, LocalDateTime.parse(startDateTime)));
                    case CLASS:
                        if (endTimeDividerPosition == -1) {
                            throw new NoEndTimeClassException();
                        }
                        startDateTime = getStartDateTime(fullCommand, startTimeDividerPosition, timeDivider,
                                endTimeDividerPosition);

                        endingTimeDivider = fullCommand.substring(endTimeDividerPosition + 3,
                                onlineLocationDividerPosition - 1).indexOf(SINGLE_SPACE);

                        endDateTime = getEndDateTime(fullCommand, startTimeDividerPosition, timeDivider,
                                endTimeDividerPosition, onlineLocationDividerPosition, endingTimeDivider);

                        return new AddCommand(new Class(fullCommand.substring(words[0].length() + 1,
                                startTimeDividerPosition - 1), virtualLocation, LocalDateTime.parse(startDateTime),
                                LocalDateTime.parse(endDateTime)));
                    case PERSONAL_EVENT:
                    case SELF_STUDY:
                        if (endTimeDividerPosition == -1) {
                            startDateTime = getStartDateTime(fullCommand, startTimeDividerPosition, timeDivider,
                                    onlineLocationDividerPosition);
                            if (words[0].equalsIgnoreCase(PERSONAL_EVENT)) {
                                return new AddCommand(new PersonalEvent(fullCommand.substring(words[0].length() + 1,
                                        startTimeDividerPosition - 1), virtualLocation,
                                        LocalDateTime.parse(startDateTime)));
                            } else {
                                return new AddCommand(new SelfStudy(fullCommand.substring(words[0].length() + 1,
                                        startTimeDividerPosition - 1), virtualLocation,
                                        LocalDateTime.parse(startDateTime)));
                            }
                        } else {
                            startDateTime = getStartDateTime(fullCommand, startTimeDividerPosition, timeDivider,
                                    endTimeDividerPosition);

                            endingTimeDivider = fullCommand.substring(endTimeDividerPosition + 3,
                                    onlineLocationDividerPosition - 1).indexOf(SINGLE_SPACE);

                            endDateTime = getEndDateTime(fullCommand, startTimeDividerPosition, timeDivider,
                                    endTimeDividerPosition, onlineLocationDividerPosition, endingTimeDivider);
                            if (words[0].equalsIgnoreCase(PERSONAL_EVENT)) {
                                return new AddCommand(new PersonalEvent(fullCommand.substring(words[0].length() + 1,
                                        startTimeDividerPosition - 1), virtualLocation,
                                        LocalDateTime.parse(startDateTime),
                                        LocalDateTime.parse(endDateTime)));
                            } else {
                                return new AddCommand(new SelfStudy(fullCommand.substring(words[0].length() + 1,
                                        startTimeDividerPosition - 1), virtualLocation,
                                        LocalDateTime.parse(startDateTime),
                                        LocalDateTime.parse(endDateTime)));
                            }
                        }
                    default:
                        break;
                    }
                } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                    throw new TimeFormatException();
                }

            }
        } else {
            throw new WrongCommandException();
        }

        assert false;//nothing should reach here
        throw new UnknownErrorException();
    }

    /**
     * return the EndDateTime. if the date is not specified, by default, it ends at the same date as the starting date.
     *
     * @param fullCommand              the full command provided by user
     * @param startTimeDividerPosition index of "/t"
     * @param timeDivider              index of " " in the start time
     * @param endTimeDividerPosition   index of "/e"
     * @param locationDividerPosition  index of "/o" or "/l"
     * @param endingTimeDivider        index of " " in the end date time, may be -1
     * @return a string in the format "yyyy-MM-dd HH:mm" that can be parsed into a LocalDateTime object
     */
    private static String getEndDateTime(String fullCommand, int startTimeDividerPosition, int timeDivider,
                                         int endTimeDividerPosition, int locationDividerPosition,
                                         int endingTimeDivider) {
        return (endingTimeDivider != -1 ? fullCommand.substring(endTimeDividerPosition + 3,
                endTimeDividerPosition + 3 + endingTimeDivider) :
                fullCommand.substring(startTimeDividerPosition + 3,
                        startTimeDividerPosition + 3 + timeDivider))
                + "T"
                + (endingTimeDivider != -1 ? fullCommand.substring(endTimeDividerPosition + 3 + endingTimeDivider + 1,
                locationDividerPosition - 1) : fullCommand.substring(endTimeDividerPosition + 3,
                locationDividerPosition - 1));
    }

    /**
     * return the StartDateTime.
     *
     * @param fullCommand              the full command provided by user
     * @param startTimeDividerPosition index of "/t"
     * @param timeDivider              index of " " in the start time
     * @param endTimeDividerPosition   index of "/e"
     * @return a string in the format "yyyy-MM-dd HH:mm" that can be parsed into a LocalDateTime object
     */
    private static String getStartDateTime(String fullCommand, int startTimeDividerPosition, int timeDivider,
                                           int endTimeDividerPosition) {
        return fullCommand.substring(startTimeDividerPosition + 3,
                startTimeDividerPosition + 3 + timeDivider)
                + "T"
                + fullCommand.substring(startTimeDividerPosition + 3 + timeDivider + 1,
                endTimeDividerPosition - 1);
    }

    /**
     * This method parses the inputted location.
     *
     * @param input the string inputted by the user.
     * @return the parsed location.
     */
    public static Location parseLocation(String input, LocationList locations) {
        assert locations != null;
        Location location;
        String[] info = input.split("/");
        // parse location from event.txt file
        try {
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
                location = new OutOfNuS(info[0]);
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if (input.contains("/")) {
                location = new OutOfNuS(info[1]);
                locations.getLocationList().add(location);
            }
            // parse location from user input
            // System.out.print("Invalid Location Format.");
            location = locations.findLocation(input.trim());
            if (location == null) {
                location = new OutOfNuS(input.trim());
            }
        }
        return location;
    }
}

