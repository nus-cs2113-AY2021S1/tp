package seedu.duke.logic.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DescFavParserTest {

    @BeforeAll
    static void initLogger() {
        DescFavParser.initLogger();
    }

    @Test
    void parseInput_onlySpacesString_expectException() {
        String message = "   ";
        DescFavParser p = new DescFavParser(message);
        try {
            p.parseInput();
        } catch (CustomException error) {
            assertEquals("Oh dear! You haven't typed in the index or changed description.", error.toString());
        }
    }

    @Test
    void parseInput_noDelimiter_expectException() {
        String message = "2 I like the tea there.";
        DescFavParser p = new DescFavParser(message);
        try {
            p.parseInput();
        } catch (CustomException error) {
            assertEquals("Oops! You are missing the delimiter /to.\nThe format for this command is as follows:"
                    + "\n/descfav <index> /to <description>\nwhere index is a number between 1 and the maximum items in"
                    + " the list\nand description is the new description you want for your favourite command.",
                    error.toString());
        }
    }

    @Test
    void parseInput_tooManyDelimiters_expectException() {
        String message = "2 /to I like the tea there /to Go every Sunday.";
        DescFavParser p = new DescFavParser(message);
        try {
            p.parseInput();
        } catch (CustomException error) {
            assertEquals("Oops! You have too many delimiters!\nThe format for this command is as follows:"
                            + "\n/descfav <index> /to <description>\nwhere index is a number between 1 and the maximum items in"
                            + " the list\nand description is the new description you want for your favourite command.",
                    error.toString());
        }
    }

    @Test
    void parseInput_delimiterWithSpacesOnly_expectException() {
        String message = "   /to   ";
        DescFavParser p = new DescFavParser(message);
        try {
            p.parseInput();
        } catch (CustomException error) {
            assertEquals("Oh dear! You haven't typed in the index or changed description.", error.toString());
        }
    }

    @Test
    void parseInput_indexMissing_expectException() {
        String message = "  /to frequent visits.";
        DescFavParser p = new DescFavParser(message);
        try {
            p.parseInput();
        } catch (CustomException error) {
            assertEquals("Yikes! That is not even a number.", error.toString());
        }
    }

    @Test
    void parseInput_descriptionMissing_expectException() {
        String message = " 2 /to  ";
        DescFavParser p = new DescFavParser(message);
        try {
            p.parseInput();
        } catch (CustomException error) {
            assertEquals("Hmmm, I don't think you would want an empty description.", error.toString());
        }
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