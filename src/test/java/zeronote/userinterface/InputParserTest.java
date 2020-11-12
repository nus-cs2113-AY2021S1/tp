package zeronote.userinterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import zeronote.exceptions.IncorrectDeadlineFormatException;
import zeronote.exceptions.IndexIncorrectFormatException;
import zeronote.exceptions.InvalidCommandException;
import zeronote.exceptions.ZeroNoteException;
import zeronote.userinterface.command.CliCommand;

class InputParserTest {
    @Test
    void parseTaskIndex_validNumber_success() throws IndexIncorrectFormatException {
        InputParser parser = new InputParser();
        String num = "2";
        assertEquals(1, parser.parseTaskIndex(num));
    }

    @Test
    void parseTaskIndex_InvalidString_numberFormatExceptionThrown() {
        InputParser parser = new InputParser();
        String num = "num";
        Assertions.assertThrows(IndexIncorrectFormatException.class, () -> {
            parser.parseTaskIndex(num);
        });
    }

    @Test
    void parseTaskTitle_correctFormat() {
        InputParser parser = new InputParser();
        String input = "/tBlowing Bubbles /by2020-10-10";
        try {
            assertEquals("Blowing Bubbles", parser.parseTaskTitle(input));
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void parseTaskTitle_withSpaces() {
        InputParser parser = new InputParser();
        String input = "/t Blowing Bubbles /by 2020-10-10";
        try {
            assertEquals("Blowing Bubbles", parser.parseTaskTitle(input));
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void parseTaskTitle_noDate() {
        InputParser parser = new InputParser();
        String input = "/t Blowing Bubbles";
        assertThrows(ZeroNoteException.class, () -> {
            String output = parser.parseTaskTitle(input);
        });
    }

    @Test
    void parseDeadline_validFormat() {
        InputParser parser = new InputParser();
        String input = "/tBlowing Bubbles /by10-10-2020 1000";
        try {
            assertEquals("10-10-2020 1000", parser.parseDeadline(input));
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void parseDeadline_validFormatWithSpaces() {
        InputParser parser = new InputParser();
        String input = "/t Blowing Bubbles /by 10-10-2020 1000";
        try {
            assertEquals("10-10-2020 1000", parser.parseDeadline(input));
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void parseDeadline_invalidFormat() {
        InputParser parser = new InputParser();
        String input = "/tBlowing Bubbles /by 2020-10-10 12";
        assertThrows(IncorrectDeadlineFormatException.class, () -> parser.parseDeadline(input));
    }

    @Test
    void parseDeadline_noDate() {
        InputParser parser = new InputParser();
        String input = "/tBlowing Bubbles";
        assertThrows(IncorrectDeadlineFormatException.class, () -> parser.parseDeadline(input));
    }

    @Test
    void parseDeadline_blankDate() {
        InputParser parser = new InputParser();
        String input = "/tBlowing Bubbles /by";
        assertThrows(IncorrectDeadlineFormatException.class, () -> parser.parseDeadline(input));
    }

    @Test
    void parseCommand_validInput_Sucess() {
        AppState appState = new AppState();
        InputParser parser = new InputParser();
        String input = "list /d";
        CliCommand command;
        try {
            command = parser.getCommandFromInput(input, appState);
            assertEquals("/d", command.getCommandParams());
        } catch (ZeroNoteException e) {
            System.out.println("ZeroNoteException Catched");
        }
    }

    @Test
    void parseCommand_inputWithSpaces_Sucess() throws ZeroNoteException {
        AppState appState = new AppState();
        InputParser parser = new InputParser();
        String input = "list    /d  ";
        CliCommand command;
        command = parser.getCommandFromInput(input, appState);
        assertEquals("/d", command.getCommandParams());
    }

    @Test
    void parseCommand_inputWithDuplications_Sucess() throws ZeroNoteException {
        AppState appState = new AppState();
        InputParser parser = new InputParser();
        String input = "list /d /d    ";
        CliCommand command;
        command = parser.getCommandFromInput(input, appState);
        assertEquals("/d /d", command.getCommandParams());
    }

    @Test
    void parseCommand_emptyInput_exceptionThrown() throws ZeroNoteException {
        AppState appState = new AppState();
        InputParser parser = new InputParser();
        String input = "";
        assertThrows(InvalidCommandException.class, () -> parser.getCommandFromInput(input, appState));
    }
}