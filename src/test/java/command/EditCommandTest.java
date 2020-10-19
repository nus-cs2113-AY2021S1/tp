package command;

import exception.EditIndexOutOfBoundsException;
import exception.EmptyEventIndexException;
import exception.WrongEditFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import exception.NuScheduleException;
import parser.Parser;


class EditCommandTest {

    @Test
    void execute_noIndexSpecified_emptyEventIndexException() throws NuScheduleException {

        Assertions.assertThrows(EmptyEventIndexException.class, () -> {
            Command d = Parser.parse("edit", 0);
        });

    }

    @Test
    void execute_inputIsNotInteger_WrongEditFormatException() {
        Assertions.assertThrows(WrongEditFormatException.class, () -> {
            Command c = Parser.parse("edit c", 0);
        });
    }

    @Test
    void execute_indexOutOfBounds_editIndexOutOfBoundsException() {
        Assertions.assertThrows(EditIndexOutOfBoundsException.class, () -> {
            Command c = Parser.parse("edit 1", 0);
        });
    }



}