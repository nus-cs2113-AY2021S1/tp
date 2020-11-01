package command;

import exception.DeleteException;
import exception.DeleteNumberFormatException;
import exception.UndefinedEventException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.Parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {

    @Test
    void execute_InvalidIndexGiven_DeleteNumberFormatException() {
        Assertions.assertThrows(DeleteNumberFormatException.class,() -> {
            Command c = Parser.parse("delete f", null,0);
        });
    }
}
