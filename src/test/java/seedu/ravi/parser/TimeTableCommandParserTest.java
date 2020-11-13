//@@author aseanseen

package seedu.ravi.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ravi.command.Command;
import seedu.ravi.command.CommandResult;
import seedu.ravi.command.IncorrectCommand;
import seedu.ravi.data.ModuleManager;
import seedu.ravi.data.TimeTableManager;
import seedu.ravi.data.storage.InputOutputManager;
import seedu.ravi.exception.DuplicateModuleException;
import seedu.ravi.exception.InvalidMatchException;
import seedu.ravi.exception.ModuleNotProvidedException;
import seedu.ravi.exception.NusModsNotLoadedException;
import seedu.ravi.exception.TimeTableInitialiseException;
import seedu.ravi.util.ExceptionMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ravi.parser.TimeTableCommandParser.ADD_FORMAT;
import static seedu.ravi.parser.TimeTableCommandParser.DELETE_FORMAT;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_LESSON_NOT_FOUND;

public class TimeTableCommandParserTest {
    static final String REPEAT_FREQ = "1";
    static final String DELETE_INDEX_1 = "1";
    static final String DELETE_INDEX_2 = "5";
    static final String WRONG_TIMETABLE_COMMAND_FORMAT = "-no";
    static final String MOD_CODE_1 = "CS2113T";
    static final String MOD_CODE_2 = "CS111111";
    static final String LESSON_START_TIME_1 = "2pm";
    static final String LESSON_START_TIME_2 = "10";
    static final String LESSON_START_TIME_3 = "1400";
    static final String LESSON_END_TIME_1 = "1500";
    static final String DAY_OF_WEEK_1 = "mon";
    static final String DAY_OF_WEEK_2 = "MONDAY";
    static final String LESSON_TYPE = "LECTURE";

    static final String WRONG_MOD_TIMETABLE_ADD_COMMAND_FORMAT = ADD_FORMAT + " " + MOD_CODE_2 + " " + DAY_OF_WEEK_1
            + " " + LESSON_START_TIME_3 + " " + LESSON_END_TIME_1 + " " + LESSON_TYPE + " " + REPEAT_FREQ;
    static final String WRONG_LESSON_TIME_1_TIMETABLE_ADD_COMMAND_FORMAT = ADD_FORMAT + " " + MOD_CODE_1 + " "
            + DAY_OF_WEEK_1 + " " + LESSON_START_TIME_1 + " " + LESSON_END_TIME_1 + " " + LESSON_TYPE + " "
            + REPEAT_FREQ;
    static final String WRONG_LESSON_TIME_2_TIMETABLE_ADD_COMMAND_FORMAT = ADD_FORMAT + " " + MOD_CODE_1 + " "
            + DAY_OF_WEEK_2 + " " + LESSON_START_TIME_2 + " " + LESSON_END_TIME_1 + " " + LESSON_TYPE + " "
            + REPEAT_FREQ;
    static final String WRONG_DAY_TIMETABLE_ADD_COMMAND_FORMAT = ADD_FORMAT + " " + MOD_CODE_1 + " " + DAY_OF_WEEK_1
            + " " + LESSON_START_TIME_3 + " " + LESSON_END_TIME_1 + " " + LESSON_TYPE + " " + REPEAT_FREQ;
    static final String WRONG_DAY_TIMETABLE_DELETE_COMMAND_FORMAT = DELETE_FORMAT + " " + DAY_OF_WEEK_1 + " "
            + DELETE_INDEX_1;
    static final String WRONG_INDEX_TIMETABLE_DELETE_COMMAND_FORMAT = DELETE_FORMAT + " " + DAY_OF_WEEK_2 + " "
            + DELETE_INDEX_2;

    @BeforeAll
    static void setupUserMods() throws DuplicateModuleException, ModuleNotProvidedException, NusModsNotLoadedException {
        InputOutputManager.tryLoadNusMods();
        ModuleManager.clearModules();
        ModuleManager.add(MOD_CODE_1);
    }

    @BeforeEach
    void setupTimeTable() throws TimeTableInitialiseException {
        TimeTableManager.clearTimeTable();
        TimeTableManager.initialise(1);
    }

    @Test
    void badTimeTableCommandFlag_IncorrectCommand_isReturned() throws InvalidMatchException {
        Command command = TimeTableCommandParser.parseTimeTableCommand(WRONG_TIMETABLE_COMMAND_FORMAT);
        assertTrue(command instanceof IncorrectCommand);
    }

    @Test
    void bad_Module_TimeTableAddCommand_Message_Module_Not_Found_isShown() throws InvalidMatchException {
        Command command = TimeTableCommandParser.parseTimeTableCommand(WRONG_MOD_TIMETABLE_ADD_COMMAND_FORMAT);
        CommandResult expectedCommandResult = new IncorrectCommand(ExceptionMessage.MESSAGE_MODULE_NOT_FOUND).execute();
        CommandResult actualCommandResult = command.execute();
        assertEquals(expectedCommandResult.feedbackToUser, actualCommandResult.feedbackToUser);
    }

    @Test
    void bad_Lesson_Time_1_TimeTableAddCommand_InvalidMatchException_isThrown() {
        assertThrows(InvalidMatchException.class,
                () -> TimeTableCommandParser.parseTimeTableCommand(WRONG_LESSON_TIME_1_TIMETABLE_ADD_COMMAND_FORMAT));
    }

    @Test
    void bad_Lesson_Time_2_TimeTableAddCommand_Message_Date_Time_Unknown_isShown() throws InvalidMatchException {
        Command command =
                TimeTableCommandParser.parseTimeTableCommand(WRONG_LESSON_TIME_2_TIMETABLE_ADD_COMMAND_FORMAT);
        CommandResult expectedCommandResult =
                new IncorrectCommand(ExceptionMessage.MESSAGE_ADD_LESSON_DATE_TIME_UNKNOWN).execute();
        CommandResult actualCommandResult = command.execute();
        assertEquals(expectedCommandResult.feedbackToUser, actualCommandResult.feedbackToUser);
    }

    @Test
    void bad_DateTime_TimeTableAddCommand_IllegalArgumentException_isThrown() {
        assertThrows(IllegalArgumentException.class,
                () -> TimeTableCommandParser.parseTimeTableCommand(WRONG_DAY_TIMETABLE_ADD_COMMAND_FORMAT));
    }

    @Test
    void bad_DateTime_TimeTableDeleteCommand_IllegalArgumentException_isThrown() {
        assertThrows(IllegalArgumentException.class,
                () -> TimeTableCommandParser.parseTimeTableCommand(WRONG_DAY_TIMETABLE_DELETE_COMMAND_FORMAT));
    }

    @Test
    void bad_Index_TimeTableDeleteCommand_Message_Lesson_Not_Found_isShown() throws InvalidMatchException {
        Command command = TimeTableCommandParser.parseTimeTableCommand(WRONG_INDEX_TIMETABLE_DELETE_COMMAND_FORMAT);
        CommandResult commandResult = command.execute();
        assertEquals(MESSAGE_LESSON_NOT_FOUND, commandResult.feedbackToUser);
    }
}
