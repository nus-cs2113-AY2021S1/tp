package seedu.duke.logic.commands.buscommand;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RouteCommandTest {

    @Test
    void executeCommand_oneLocationWithDelimiter_expectException() {
        String locations = "PGP /to   ";
        RouteCommand com = new RouteCommand(locations);
        assertThrows(CustomException.class, com::executeCommand);
    }

    @Test
    void executeCommand_onlyDelimiter_expectException() {
        String locations = "    /to   ";
        RouteCommand com = new RouteCommand(locations);
        assertThrows(CustomException.class, com::executeCommand);
    }

    @Test
    void executeCommand_sameLocations_expectException() {
        String locations = " University Town /to univerSity TowN  ";
        RouteCommand com = new RouteCommand(locations);
        assertThrows(CustomException.class, com::executeCommand);
    }

}