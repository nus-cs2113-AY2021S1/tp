package seedu.duke.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DescFavParserTest {

    @Test
    void parseInput_emptyString_expectException() {
        String message = "";
        DescFavParser p = new DescFavParser(message);
        assertThrows(CustomException.class, p::parseInput);
    }

    @Test
    void parseInput_onlySpacesString_expectException() {
        String message = "   ";
        DescFavParser p = new DescFavParser(message);
        assertThrows(CustomException.class, p::parseInput);
    }

    @Test
    void parseInput_noDelimiter_expectException() {
        String message = "2 I like the tea there.";
        DescFavParser p = new DescFavParser(message);
        assertThrows(CustomException.class, p::parseInput);
    }

    @Test
    void parseInput_delimiterWithSpacesOnly_expectException() {
        String message = "   /to   ";
        DescFavParser p = new DescFavParser(message);
        assertThrows(CustomException.class, p::parseInput);
    }

    @Test
    void parseInput_indexMissing_expectException() {
        String message = "  /to frequent visits.";
        DescFavParser p = new DescFavParser(message);
        assertThrows(CustomException.class, p::parseInput);
    }

    @Test
    void parseInput_descriptionMissing_expectException() {
        String message = " 2 /to  ";
        DescFavParser p = new DescFavParser(message);
        assertThrows(CustomException.class, p::parseInput);
    }

    @Test
    void parseInput_bothParametersArePresent_success() throws CustomException {
        String message = "3 /to free stuff here.";
        DescFavParser p = new DescFavParser(message);
        p.parseInput();
        assertEquals(3,p.getIndex());
        assertEquals("free stuff here.", p.getDescription());
    }

}