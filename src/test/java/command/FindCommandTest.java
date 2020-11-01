package command;

import exception.DateFormatException;
import exception.EmptyFindDateException;
import exception.EmptyFindException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.Parser;

public class FindCommandTest {

    @Test
    void execute_NoDescriptionGiven_EmptyEventException() {
        Assertions.assertThrows(EmptyFindException.class, () -> {
            Command c = Parser.parse("find",  null,0);
        });
    }

    @Test
    void execute_NoDateGiven_EmptyFindDateException() {
        Assertions.assertThrows(EmptyFindDateException.class, () -> {
            Command c = Parser.parse("date",  null,0);
        });
    }

    @Test
    void execute_InvalidTimeDate_DateFormatException() {
        Assertions.assertThrows(DateFormatException.class, () -> {
            Command c = Parser.parse("date 2100",  null,0);
        });
    }
}
