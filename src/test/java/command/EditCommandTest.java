package command;

import exception.EmptyEventIndexException;
import exception.WrongEditFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import command.Command;
import eventlist.EventList;
import exception.CreatingFileException;
import exception.NuScheduleException;
import locationlist.BusStopList;
import locationlist.LocationList;
import parser.Parser;
import storage.Storage;
import ui.UI;



class EditCommandTest {


    @Test
    void execute_noIndexSpecified_emptyEventIndexException() {
        Assertions.assertThrows(EmptyEventIndexException.class, () ->
        {
            Command c = Parser.parse("edit");
        });

    }

    @Test
    void execute_inputIsNotInteger_WrongEditFormatException() {
        Assertions.assertThrows(WrongEditFormatException.class, () ->
        {
            Command c = Parser.parse("edit c");
        });
    }
}