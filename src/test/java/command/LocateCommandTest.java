package command;

import exception.EmptyLocationException;
import exception.InvalidEventIndexException;
import exception.InvalidLocationException;
import locationlist.LocationList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.Parser;

public class LocateCommandTest {
    private LocationList locations = new LocationList();

    @Test
    void execute_EmptyLocation_EmptyLocationException() {
        Assertions.assertThrows(EmptyLocationException.class, () -> {
            Command d = Parser.parse("locate", null, 0);
        });
    }

    @Test
    void execute_InvalidEventIndex_InvalidEventIndexException() {
        Assertions.assertThrows(InvalidEventIndexException.class, () -> {
            Command d = Parser.parse("locate -1", locations, 0);
        });
    }

    @Test
    void execute_LocationNotFound_InvalidLocationException() {
        Assertions.assertThrows(InvalidLocationException.class, () -> {
            Command d = Parser.parse("locate home", locations, 0);
        });
    }
}
