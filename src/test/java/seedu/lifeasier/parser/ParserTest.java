package seedu.lifeasier.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.lifeasier.commands.AddDeadlineCommand;
import seedu.lifeasier.commands.AddEventCommand;
import seedu.lifeasier.commands.AddLessonCommand;
import seedu.lifeasier.commands.Command;
import seedu.lifeasier.commands.ExitCommand;
import seedu.lifeasier.commands.FreeTimeCommand;
import seedu.lifeasier.commands.HelpCommand;
import seedu.lifeasier.commands.InvalidCommand;
import seedu.lifeasier.commands.SleepTimeCommand;
import seedu.lifeasier.ui.Ui;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    @Test
    void parseCommand_inputHelp_HelpCommand() throws ParserException {
        Parser parser = new Parser();
        Ui ui = new Ui();
        Command command = parser.parseCommand("help", ui);
        assertTrue(command instanceof HelpCommand);
    }

    @Test
    void parseCommand_inputAddLesson_AddLessonCommand() throws ParserException {
        Parser parser = new Parser();
        Ui ui = new Ui();
        Command command = parser.parseCommand(
                "addLesson /code cg1111 /date 10-10-20 /from 10:00 /to 20:00 /repeats 0", ui);
        assertTrue(command instanceof AddLessonCommand);
    }

    @Test
    void parseCommand_inputFreeTime_FreeTimeCommand() throws ParserException {
        Parser parser = new Parser();
        Ui ui = new Ui();
        Command command = parser.parseCommand(
                "freeTime", ui);
        assertTrue(command instanceof FreeTimeCommand);
    }

    @Test
    void parseCommand_inputSleepTime_SleepTimeCommand() throws ParserException {
        Parser parser = new Parser();
        Ui ui = new Ui();
        Command command = parser.parseCommand(
                "sleepTime", ui);
        assertTrue(command instanceof SleepTimeCommand);
    }

    @Test
    void parseCommand_inputExit_ExitCommand() throws ParserException {
        Parser parser = new Parser();
        Ui ui = new Ui();
        Command command = parser.parseCommand(
                "exit", ui);
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    void parseCommand_inputInvalidAddLesson_InvalidCommand() throws ParserException {
        Parser parser = new Parser();
        Ui ui = new Ui();
        Command command = parser.parseCommand(
                "addLesson /code cg1111 /date 10-13-20 /from 24:60 /to 26:76 /repeats 0", ui);
        assertTrue(command instanceof InvalidCommand);
    }

    @Test
    void parseCommand_inputInvalidCommand_ParserException() {
        Parser parser = new Parser();
        Ui ui = new Ui();
        assertThrows(ParserException.class, () -> {
            parser.parseCommand("I want to add a lesson", ui);
        });
    }

    @Test
    void parseAddDeadlineCommand_inputInvalidDateTime_ParserException() {
        Parser parser = new Parser();
        Ui ui = new Ui();
        assertThrows(DateTimeParseException.class, () -> {
            parser.parseAddDeadlineCommand(ui, "addDeadline do homework /by 10-30-40 24:67 /repeats 4");
        });
    }

    @Test
    void parseCommand_inputAddEvent_AddEventCommand() throws ParserException {
        Parser parser = new Parser();
        Ui ui = new Ui();
        Command command = parser.parseCommand(
                "addEvent my event /date 10-10-20 /from 10:00 /to 20:00 /repeats 1", ui);
        assertTrue(command instanceof AddEventCommand);
    }

    @Test
    void parseCommand_inputAddDeadline_AddDeadlineCommand() throws ParserException {
        Parser parser = new Parser();
        Ui ui = new Ui();
        Command command = parser.parseCommand(
                "addDeadline my deadline /by 10-10-20 10:00 /repeats 0", ui);
        assertTrue(command instanceof AddDeadlineCommand);
    }

    @Test
    void checkIfValidModuleCode_validModuleCodes_true() {
        Parser parser = new Parser();
        Assertions.assertAll(
            () -> assertTrue(parser.checkIfValidModuleCode("CS1010")),
            () -> assertTrue(parser.checkIfValidModuleCode("CS2113T")),
            () -> assertTrue(parser.checkIfValidModuleCode("GET1023")),
            () -> assertTrue(parser.checkIfValidModuleCode("ACC1000X"))
        );
    }

    @Test
    void checkIfValidModuleCode_invalidModuleCodes_false() {
        Parser parser = new Parser();
        Assertions.assertAll(
            () -> assertFalse(parser.checkIfValidModuleCode("")),
            () -> assertFalse(parser.checkIfValidModuleCode("addLesson /code CS1231")),
            () -> assertFalse(parser.checkIfValidModuleCode("         ")),
            () -> assertFalse(parser.checkIfValidModuleCode("G1000")),
            () -> assertFalse(parser.checkIfValidModuleCode("G1000S")),
            () -> assertFalse(parser.checkIfValidModuleCode("GERT1000")),
            () -> assertFalse(parser.checkIfValidModuleCode("#GE1000")),
            () -> assertFalse(parser.checkIfValidModuleCode("GES00000")),
            () -> assertFalse(parser.checkIfValidModuleCode("GERT1000X")),
            () -> assertFalse(parser.checkIfValidModuleCode("C9S1000X")),
            () -> assertFalse(parser.checkIfValidModuleCode("RE10TX")),
            () -> assertFalse(parser.checkIfValidModuleCode("CS9S000E")),
            () -> assertFalse(parser.checkIfValidModuleCode("CS2101XD")),
            () -> assertFalse(parser.checkIfValidModuleCode("CS2101#"))
        );
    }

    @Test
    void checkForSingleWordInput_invalidInput_InvalidCommandException() {
        Parser parser = new Parser();

        Assertions.assertAll(
            () -> assertThrows(ParserException.class, () -> {
                parser.checkForSingleWordInput("help me");
            })
        );
    }
}
