package command;

import exception.EmptyEventException;
import exception.EmptyEventIndexException;
import exception.NoEventLocationException;
import exception.NoEventLocationMarkerException;
import exception.NoEventTimeMarkerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.Parser;


class AddCommandTest {

    @Test
    void execute_NoDescriptionGiven_EmptyEventException() {
        Assertions.assertThrows(EmptyEventException.class, () -> {
            Command d = Parser.parse("class",  null, 0);
        });
    }

    @Test
    void execute_NoTimeMarkerGiven_NoEventTimeMarkerException() {
        Assertions.assertThrows(NoEventTimeMarkerException.class, () -> {
            Command d = Parser.parse("class something",  null,0);
        });
    }

    @Test
    void execute_NoLocationMarkerGiven_NoEventLocationException() {
        Assertions.assertThrows(NoEventLocationException.class, () -> {
            Command d = Parser.parse("class something /t 2020-02-02 20:00",  null,0);
        });
    }
}