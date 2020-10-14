package seedu.duke.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RouteParserTest {

    @Test
    void getLocations_noLocation_expectException() {
        String locations = "   ";
        RouteParser p = new RouteParser(locations);
        assertThrows(CustomException.class, p::getLocations);
    }

    @Test
    void getLocations_oneLocation_expectException() {
        String locations = "PGP";
        RouteParser p = new RouteParser(locations);
        assertThrows(CustomException.class, p::getLocations);
    }

    @Test
    void getLocations_noDelimiter_expectException() {
        String locations = "PGP PGPR";
        RouteParser p = new RouteParser(locations);
        assertThrows(CustomException.class, p::getLocations);
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