package seedu.duke.logic.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteParserTest {

    @BeforeAll
    static void initLogger() {
        RouteParser.initLogger();
    }

    @Test
    void getLocations_noLocation_expectException() {
        String locations = "   ";
        RouteParser p = new RouteParser(locations);
        try {
            p.getLocations();
        } catch (CustomException error) {
            assertEquals("Oh dear! I don't have all locations.", error.toString());
        }
    }

    @Test
    void getLocations_noDelimiter_expectException() {
        String locations = "University Hall";
        RouteParser p = new RouteParser(locations);
        try {
            p.getLocations();
        } catch (CustomException error) {
            assertEquals("Oops! You are missing the delimiter /to.\n" +
                    "The format for this command is as follows:\n" +
                    "/route <starting loc> /to <destination>\n" +
                    "where starting location and destination must be the names of bus stops in NUS.", error.toString());
        }
    }

    @Test
    void getLocations_tooManyDelimiters_expectException() {
        String locations = "University Hall /to PGP /to PGPR";
        RouteParser p = new RouteParser(locations);
        try {
            p.getLocations();
        } catch (CustomException error) {
            assertEquals("Oops! You have too many delimiters!\n" +
                    "The format for this command is as follows:\n" +
                    "/route <starting loc> /to <destination>\n" +
                    "where starting location and destination must be the names of bus stops in NUS.", error.toString());
        }
    }

    @Test
    void getLocations_twoOneWordLocations_returnsStringArrayOf2() throws CustomException {
        String locations = "PGP /to pGPR";
        RouteParser p = new RouteParser(locations);
        assertEquals(2, p.getLocations().length);
        assertEquals("PGP ", p.getLocations()[0]);
        assertEquals(" pGPR", p.getLocations()[1]);
    }

    @Test
    void getLocations_twoMultiWordLocations_returnsStringArrayOf2() throws CustomException {
        String locations = "University Town /to university Health Centre";
        RouteParser p = new RouteParser(locations);
        assertEquals(2, p.getLocations().length);
        assertEquals("University Town ", p.getLocations()[0]);
        assertEquals(" university Health Centre", p.getLocations()[1]);
    }

}