package seedu.duke.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ParserTest {

    @Test
    void extractType_unknownCommand_expectException() {
        String command = "Hello";
        Parser p = new Parser(command);
        assertThrows(CustomException.class, p::extractType);
    }

    @Test
    void extractType_exitCommand_returnsFalse() throws CustomException {
        String command = "/exit";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertFalse(isOngoing);
    }

    @Test
    void extractType_listStopsCommand_returnsTrue() throws CustomException {
        String command = "/liststops";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_helpCommand_returnsTrue() throws CustomException {
        String command = "/help";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_allBusCommand_returnsTrue() throws CustomException {
        String command = "/allbus";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_routeCommand_returnsTrue() throws CustomException {
        String command = "/route PGP /to PGPR";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_busCommand_returnsTrue() throws CustomException {
        String command = "/bus PGP";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_routeMapCommand_returnsTrue() throws CustomException {
        String command = "/routemap AA1";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

}
