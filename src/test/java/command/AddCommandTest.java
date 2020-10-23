package command;

import exception.EmptyEventException;
import exception.EmptyEventIndexException;
import exception.NoEventLocationMarkerException;
import exception.NoEventTimeMarkerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.Parser;


class AddCommandTest {

    @Test
    void execute_NoDescriptionGiven_EmptyEventException() {
        Assertions.assertThrows(EmptyEventException.class, () -> {
            Command d = Parser.parse("class",  null);
        });
    }

    @Test
    void execute_NoTimeMarkerGiven_NoEventTimeMarkerException() {
        Assertions.assertThrows(NoEventTimeMarkerException.class, () -> {
            Command d = Parser.parse("class something",  null);
        });
    }

    @Test
    void execute_NoLocationMarkerGiven_NoEventLocationMarkerException() {
        Assertions.assertThrows(NoEventLocationMarkerException.class, () -> {
            Command d = Parser.parse("class something /t 2020-02-02 20:00",  null);
        });
    }
}