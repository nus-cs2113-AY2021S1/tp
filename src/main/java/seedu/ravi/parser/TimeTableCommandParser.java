//@@author aseanseen

package seedu.ravi.parser;

import seedu.ravi.command.Command;
import seedu.ravi.command.IncorrectCommand;
import seedu.ravi.command.timetable.TimeTableAddCommand;
import seedu.ravi.command.timetable.TimeTableCommand;
import seedu.ravi.command.timetable.TimeTableDeleteCommand;
import seedu.ravi.command.timetable.TimeTableFilterCommand;
import seedu.ravi.command.timetable.TimeTableResetCommand;
import seedu.ravi.command.timetable.TimeTableViewCommand;
import seedu.ravi.data.Lesson;
import seedu.ravi.data.LessonFilter;
import seedu.ravi.exception.InvalidMatchException;
import seedu.ravi.exception.LessonInvalidTimeException;
import seedu.ravi.exception.ModuleNotFoundException;

import java.time.DayOfWeek;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.ravi.command.timetable.TimeTableCommand.TIMETABLE_LESSON_ADD_USER_FORMAT;
import static seedu.ravi.command.timetable.TimeTableCommand.TIMETABLE_LESSON_DELETE_USER_FORMAT;
import static seedu.ravi.command.timetable.TimeTableCommand.TIMETABLE_LESSON_FILTER_USER_FORMAT;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_ADD_LESSON_DATE_TIME_UNKNOWN;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_LESSON_INVALID_TIME;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.ravi.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.ravi.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

public class TimeTableCommandParser {
    public static final String REPEAT_GROUP = "repeat";
    public static final String TIMETABLE_GROUP = "timeTableParams";
    public static final String INDEX_GROUP = "index";
    public static final String MODULE_GROUP = "module";
    public static final String DAY_GROUP = "day";
    public static final String START_TIME_GROUP = "start";
    public static final String END_TIME_GROUP = "end";
    public static final String LESSON_TYPE_GROUP = "type";
    public static final String ADD_FORMAT = "-add";
    public static final String DELETE_FORMAT = "-del";
    public static final String FILTER_FORMAT = "-filter";
    public static final String VIEW_DAY_FORMAT = "-day";
    public static final String VIEW_WEEK_FORMAT = "-week";
    public static final String RESET_FORMAT = "-reset";
    private static final Pattern TIMETABLE_FORMAT = Pattern.compile("(?<commandFlag>-[a-zA-Z]+)"
            + "(?<timeTableParams>.*)");
    private static final Pattern TIMETABLE_LESSON_PARAMETER_FORMAT =
            Pattern.compile("(?<module>[a-zA-Z0-9]+\\s+)(?<day>[a-zA-Z]+\\s+)(?<start>[0-9]+\\s+)(?<end>[0-9]+\\s+)"
                    + "(?<type>[a-zA-Z]+\\s+)(?<repeat>[0-9]+\\s*)");
    private static final Pattern TIMETABLE_LESSON_FILTER_PARAMETER_FORMAT =
            Pattern.compile("(?<module>[-a-zA-Z0-9]+\\s+)(?<day>[-a-zA-Z]+\\s+)(?<start>[-0-9]+\\s+)(?<end>[-0-9]+\\s+)"
                    + "(?<type>[-a-zA-Z]+\\s*)");
    private static final Pattern TIMETABLE_DELETE_PARAMETER_FORMAT =
            Pattern.compile("(?<day>[a-zA-Z]+\\s+)(?<index>[0-9]+\\s*)");

    /**
     * Parses all timetable related commands into their respective parsers.
     * Able to Add, Delete, View.
     *
     * @param parameters User input after determining it is a timetable related command.
     * @return IncorrectCommand or any of the timetable commands.
     * @throws NumberFormatException When the timetable view command is not given DAY, WEEK or a number.
     * @throws InvalidMatchException When the command entered does not match the regex pattern.
     */
    public static Command parseTimeTableCommand(String parameters)
            throws NumberFormatException, InvalidMatchException {
        Command command;
        Matcher matcher = TIMETABLE_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher, parameters, TimeTableCommand.FORMAT, TimeTableCommand.PROMPT_HELP);

        try {
            String commandFlag = matcher.group(Parser.COMMAND_FLAG_GROUP).toLowerCase().trim();
            String timeTableParams = matcher.group(TIMETABLE_GROUP).toLowerCase().trim();
            switch (commandFlag) {
            case ADD_FORMAT:
                command = parseTimeTableAddCommand(timeTableParams);
                break;
            case DELETE_FORMAT:
                command = parseTimeTableDeleteCommand(timeTableParams);
                break;
            case FILTER_FORMAT:
                command = parseTimeTableFilterCommand(timeTableParams);
                break;
            case RESET_FORMAT:
                command = parseTimeTableResetCommand(timeTableParams);
                break;
            default: // Check if it is a view command
                command = parseTimeTableViewCommand(commandFlag, timeTableParams);
                break;
            }
        } catch (ModuleNotFoundException e) {
            return new IncorrectCommand(MESSAGE_MODULE_NOT_FOUND);
        } catch (LessonInvalidTimeException e) {
            return new IncorrectCommand(MESSAGE_LESSON_INVALID_TIME);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(MESSAGE_ADD_LESSON_DATE_TIME_UNKNOWN);
        }
        return command;
    }

    /**
     * Parses the timetable view command.
     * Accepted commands will be:
     * timetable -day
     * timetable -week
     *
     * @param commandFlag User input determining if it is day, week or custom view.
     * @param timeTableParams Remaining user input.
     * @return TimeTableViewCommand or IncorrectCommand.
     */
    private static Command parseTimeTableViewCommand(String commandFlag, String timeTableParams) {
        int daysToView;
        if (!timeTableParams.isEmpty()) {
            commandFlag = "";
        }
        switch (commandFlag) {
        case VIEW_DAY_FORMAT:
            daysToView = 1;
            break;
        case VIEW_WEEK_FORMAT:
            daysToView = 7;
            break;
        default:
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n", MESSAGE_INVALID_COMMAND_FORMAT, commandFlag,
                    MESSAGE_CHECK_COMMAND_FORMAT, TimeTableCommand.FORMAT));
        }
        return new TimeTableViewCommand(daysToView);
    }

    /**
     * Parses the timetable add command. For the adding of Lessons into the timetable.
     * Accepted commands will be in the format: timetable -add module day start end type repeat
     * e.g. timetable -add CS2101 FRIDAY 1400 1600 LECTURE 1
     *
     * @return TimeTableAddCommand or IncorrectCommand
     * @throws ModuleNotFoundException When the module is not found in the module list.
     * @throws LessonInvalidTimeException When the start is greater than or equal to end time of the Lesson.
     * @throws DateTimeParseException When the time of either the start or end is in the wrong format.
     * @throws InvalidMatchException When the lessonParams do not match the TimeTableAddCommand regex.
     */
    private static Command parseTimeTableAddCommand(String lessonParams)
            throws ModuleNotFoundException, LessonInvalidTimeException,
            DateTimeParseException, InvalidMatchException {
        Matcher lessonMatcher = TIMETABLE_LESSON_PARAMETER_FORMAT.matcher(lessonParams);

        Parser.matcherMatches(lessonMatcher, lessonParams, TIMETABLE_LESSON_ADD_USER_FORMAT,
                TimeTableCommand.PROMPT_HELP);

        Lesson newLesson = LessonParser.parseLesson(lessonMatcher);
        // Convert repeatString to int
        String repeatString = lessonMatcher.group(REPEAT_GROUP).toLowerCase().trim();
        int repeatFreq = Integer.parseInt(repeatString);
        return new TimeTableAddCommand(newLesson, repeatFreq);
    }

    /**
     * Parses the timetable delete command. For the deleting of Lessons from the timetable.
     * Accepted commands will be in the format: timetable -del day index
     * e.g. timetable -del MONDAY 2
     * index can be found through timetable -week
     *
     * @return TimeTableDeleteCommand or IncorrectCommand
     * @throws InvalidMatchException When the lessonParams do not match the TimeTableDeleteCommand regex.
     */
    private static Command parseTimeTableDeleteCommand(String deleteParams) throws InvalidMatchException {
        Matcher lessonMatcher = TIMETABLE_DELETE_PARAMETER_FORMAT.matcher(deleteParams);
        Parser.matcherMatches(lessonMatcher, deleteParams, TIMETABLE_LESSON_DELETE_USER_FORMAT,
                TimeTableCommand.PROMPT_HELP);
        String dayString = lessonMatcher.group(DAY_GROUP).toUpperCase().trim();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayString);
        // Must account for the user input vs the week of year number
        int indexToBeDeleted = Integer.parseInt(lessonMatcher.group(INDEX_GROUP)) - 1;
        return new TimeTableDeleteCommand(dayOfWeek, indexToBeDeleted);
    }

    public static Command parseTimeTableFilterCommand(String filterParams) throws
            InvalidMatchException, ModuleNotFoundException {
        Matcher filterMatcher = TIMETABLE_LESSON_FILTER_PARAMETER_FORMAT.matcher(filterParams);
        Parser.matcherMatches(filterMatcher, filterParams, TIMETABLE_LESSON_FILTER_USER_FORMAT,
                TimeTableCommand.PROMPT_HELP);
        ArrayList<LessonFilter> filterList = LessonParser.parseFilterLesson(filterMatcher);

        return new TimeTableFilterCommand(filterList);
    }

    /**
     * Parses the timetable reset command.
     * Accepted command will be:
     * timetable -reset
     *
     * @param resetParams Remaining user input.
     * @return TimeTableResetCommand or IncorrectCommand.
     */
    public static Command parseTimeTableResetCommand(String resetParams) {
        if (!resetParams.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n", MESSAGE_INVALID_COMMAND_FORMAT, resetParams,
                    MESSAGE_CHECK_COMMAND_FORMAT, TimeTableCommand.FORMAT));
        }
        return new TimeTableResetCommand();
    }
}
