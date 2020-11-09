package seedu.duke.logic.commands.buscommand;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteCommandTest {

    @Test
    void executeCommand_oneLocationWithDelimiter_expectException() {
        String locations = "PGP /to   ";
        RouteCommand com = new RouteCommand(locations);
        try {
            com.executeCommand();
        } catch (CustomException error) {
            assertEquals("Oh dear! I don't have all locations.", error.toString());
        }
    }

    @Test
    void executeCommand_sameLocations_expectException() {
        String locations = " University Town /to univerSity TowN  ";
        RouteCommand com = new RouteCommand(locations);
        try {
            com.executeCommand();
        } catch (CustomException error) {
            assertEquals("You don't need a bus to get there...You are right there!", error.toString());
        }
    }

    @Test
    void executeCommand_spellingError_expectException() {
        String locations = " Univarsity Town /to PGP  ";
        RouteCommand com = new RouteCommand(locations);
        try {
            com.executeCommand();
        } catch (CustomException error) {
            assertEquals("Possible Locs shown", error.toString());
        }
    }

    @Test
    void executeCommand_nonExistentLocations_expectException() {
        String locationsInvalidSource = " Orchard /to PGP  ";
        RouteCommand com = new RouteCommand(locationsInvalidSource);
        try {
            com.executeCommand();
        } catch (CustomException error) {
            assertEquals("The starting location you have provided is not the name of any bus stop in our\n"
                    + "database :(\nIf you are typing a short form of the name, please type the full bus stop name and"
                    + "\ntry again.", error.toString());
        }

        String locationsInvalidDest = " University Health Centre /to Vivocity ";
        com = new RouteCommand(locationsInvalidDest);
        try {
            com.executeCommand();
        } catch (CustomException error) {
            assertEquals("The destination you have provided is not the name of any bus stop in our database :("
                            + "\nIf you are typing a short form of the name, please type the full bus stop name and\n"
                            + "try again.", error.toString());
        }

        String locationsInvalidAll = " Santosa /to West coast park";
        com = new RouteCommand(locationsInvalidAll);
        try {
            com.executeCommand();
        } catch (CustomException error) {
            assertEquals("The starting location and destination you have provided are not the names of any "
                    + "bus\nstops in our database :(\nIf you are typing a short form of the name, please type the full "
                    + "bus stop name and\ntry again.", error.toString());
        }

    }

}