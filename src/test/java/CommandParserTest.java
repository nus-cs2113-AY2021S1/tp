import exceptions.InvalidModeException;
import org.junit.jupiter.api.Test;
import studyit.CommandParser;
import studyit.CommandType;
import studyit.Mode;
import studyit.StudyIt;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandParserTest {

    @Test
    void testStandardizeCommand() {
        CommandParser commandParser = new CommandParser();
        assertEquals("this is a test", commandParser.standardizeCommand(" ThIs is A teST  "));
    }

    @Test
    void getCommandType_correctCommandInputs_success() {
        CommandParser commandParser = new CommandParser();

        // Checks if exit detects correctly when it is in main menu
        assertEquals(CommandType.EXIT_PROGRAM, commandParser.getCommandType("exit"));
        assertEquals(CommandType.LOCATION, commandParser.getCommandType("location"));
        assertEquals(CommandType.CHANGE_MODE, commandParser.getCommandType("cd 3"));
        assertEquals(CommandType.HELP, commandParser.getCommandType("help"));

        // Checks if exit detects exit mode when inside one of the modes
        StudyIt studyIt = new StudyIt();
        studyIt.changeMode(Mode.ACADEMIC);
        assertEquals(CommandType.EXIT_MODE, commandParser.getCommandType("exit"));
    }

    @Test
    void getCommandType_incorrectCommandInput_unidentifiableCommandType() {
        CommandParser commandParser = new CommandParser();

        assertEquals(CommandType.UNIDENTIFIABLE, commandParser.getCommandType("asdhajskd"));
    }

    @Test
    void getDestinationMode_correctModeInputs_success() throws Exception {
        CommandParser commandParser = new CommandParser();

        // Test for mode index input
        assertEquals(Mode.MENU, commandParser.getDestinationMode("cd 1"));
        assertEquals(Mode.BOOKMARK, commandParser.getDestinationMode("cd 2"));
        assertEquals(Mode.TIMETABLE, commandParser.getDestinationMode("cd 3"));
        assertEquals(Mode.ACADEMIC, commandParser.getDestinationMode("cd 4"));
        assertEquals(Mode.FLASHCARD, commandParser.getDestinationMode("cd 5"));

        // Test for mode name input
        assertEquals(Mode.MENU, commandParser.getDestinationMode("cd menu"));
        assertEquals(Mode.BOOKMARK, commandParser.getDestinationMode("cd bookmark"));
        assertEquals(Mode.TIMETABLE, commandParser.getDestinationMode("cd timetable"));
        assertEquals(Mode.ACADEMIC, commandParser.getDestinationMode("cd academic"));
        assertEquals(Mode.FLASHCARD, commandParser.getDestinationMode("cd flashcard"));
    }

    @Test
    void getDestinationMode_incorrectModeNumber_exceptionThrown() {
        CommandParser commandParser = new CommandParser();

        assertThrows(InvalidModeException.class, () -> {
            commandParser.getDestinationMode("cd 10");
        });
    }

    @Test
    void getDestinationMode_incorrectModeName_exceptionThrown() {
        CommandParser commandParser = new CommandParser();

        assertThrows(InvalidModeException.class, () -> {
            commandParser.getDestinationMode("cd easyA");
        });
    }
}