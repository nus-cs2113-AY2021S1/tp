package logic.parser;

import exceptions.InvalidDateFormatException;
import exceptions.profile.InvalidCommandFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;

import static logic.parser.ProfileParser.extractCommandTagAndInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author CFZeon
public class DietManagerParserTest {

    @Test
    void parseString_validInput_success() {
        String input = "first second";
        DietManagerParser parser = new DietManagerParser();
        String[] expected = {"first", "second"};
        String[] result = parser.parseCommand(input);
        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    void parseString_invalidInput_failure() {
        String input = "FirstSecond";
        DietManagerParser parser = new DietManagerParser();
        String[] expected = {"First", "Second"};
        String[] result = parser.parseCommand(input);
        Assertions.assertFalse(Arrays.equals(expected, result));
    }

    @Test
    void extractTagAndInfo_missingSlashInput_success() {
        assertThrows(InvalidCommandFormatException.class, () ->
                extractCommandTagAndInfo("search", "t breakfast"));
    }

    @Test
    void extractTagAndInfo_multipleSlashInput_throwInvalidCommandFormatException() {
        assertThrows(InvalidCommandFormatException.class, () ->
                extractCommandTagAndInfo("search", "//t breakfast"));
    }

    @Test
    void extractTagAndInfo_wrongCommandFormatInput_throwInvalidCommandFormatException() {
        assertThrows(InvalidCommandFormatException.class, () ->
                extractCommandTagAndInfo("search", "j"));
    }

    @Test
    void extractNewDate_validDateInput_success() throws InvalidCommandFormatException, InvalidDateFormatException {
        String input = "/d 2020-05-04";
        String expected = "04-05-2020";
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("new", input);
        String extractedDate = parser.extractNewDate(parsedParams, message);
        assertEquals(expected, extractedDate);
    }

    @Test
    void extractNewDate_invalidDateInput_throwInvalidDateFormatException() throws InvalidCommandFormatException {
        String input = "/d 2131-14-51";
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("new", input);
        assertThrows(InvalidDateFormatException.class, () ->
                parser.extractNewDate(parsedParams, message));
    }

    @Test
    void extractNewDate_emptyDateInput_returnCurrentDate() throws InvalidCommandFormatException,
            InvalidDateFormatException {
        String input = "/d ";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String expected = dtf.format(now);
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("new", input);
        String extractedDate = parser.extractNewDate(parsedParams, message);
        assertEquals(expected, extractedDate);
    }

    @Test
    void extractNewDate_noDateInputDetected_returnCurrentDate() throws InvalidCommandFormatException,
            InvalidDateFormatException {
        String input = "";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String expected = dtf.format(now);
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("new", input);
        String extractedDate = parser.extractNewDate(parsedParams, message);
        assertEquals(expected, extractedDate);
    }

    @Test
    void extractSearchTag_validTagInput_success() throws InvalidCommandFormatException {
        String input = "/t lunch";
        String expected = "lunch";
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("search", input);
        String extractedTag = parser.extractSearchTag(parsedParams, message);
        assertEquals(expected, extractedTag);
    }

    @Test
    void extractSearchTag_noTagInputDetected_returnDefaultTag() throws InvalidCommandFormatException {
        String input = "/s 2020-11-11 /e 2020-11-23";
        String expected = "";
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("search", input);
        String extractedTag = parser.extractSearchTag(parsedParams, message);
        assertEquals(expected, extractedTag);
    }

    @Test
    void extractNewTag_validTagInput_success() throws InvalidCommandFormatException {
        String input = "/t lunch";
        String expected = "lunch";
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("new", input);
        String extractedTag = parser.extractNewTag(parsedParams, message);
        assertEquals(expected, extractedTag);
    }

    @Test
    void extractNewTag_emptyTagInput_returnDefaultTag() throws InvalidCommandFormatException {
        String input = "/t ";
        String expected = "unspecified";
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("new", input);
        String extractedTag = parser.extractNewTag(parsedParams, message);
        assertEquals(expected, extractedTag);
    }

    @Test
    void extractNewTag_noTagInputDetected_returnDefaultTag() throws InvalidCommandFormatException {
        String input = "";
        String expected = "unspecified";
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("new", input);
        String extractedTag = parser.extractNewTag(parsedParams, message);
        assertEquals(expected, extractedTag);
    }

    @Test
    void extractStartDates_correctDateInput_success()
            throws InvalidDateFormatException, InvalidCommandFormatException {
        String input = "/s 2020-11-02";
        LocalDateTime expected = DateParser.parseDate("2020-11-02");
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("search", input);
        LocalDateTime extractedDate = parser.extractStartDates(parsedParams, message);
        assertEquals(expected, extractedDate);
    }

    @Test
    void extractStartDates_noDateInputDetected_returnDefaultDate()
            throws InvalidDateFormatException, InvalidCommandFormatException {
        String input = "/e 2020-11-11 /t lunch";
        LocalDateTime expected = DateParser.parseDate("0001-01-01");
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("search", input);
        LocalDateTime extractedDate = parser.extractStartDates(parsedParams, message);
        assertEquals(expected, extractedDate);
    }

    @Test
    void extractStartDates_invalidDateInput_throwInvalidDateFormatException() throws InvalidCommandFormatException {
        String input = "/s 2131-14-51";
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("search", input);
        assertThrows(InvalidDateFormatException.class, () ->
                parser.extractStartDates(parsedParams, message));
    }

    @Test
    void extractEndDates_correctDateInput_success()
            throws InvalidDateFormatException, InvalidCommandFormatException {
        String input = "/e 2020-11-02";
        LocalDateTime expected = DateParser.parseDate("2020-11-02");
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("search", input);
        LocalDateTime extractedDate = parser.extractEndDates(parsedParams, message);
        assertEquals(expected, extractedDate);
    }

    @Test
    void extractEndDates_noDateInputDetected_returnDefaultDate()
            throws InvalidDateFormatException, InvalidCommandFormatException {
        String input = "/s 2020-11-11 /t lunch";
        LocalDateTime expected = DateParser.parseDate("9999-12-12");
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("search", input);
        LocalDateTime extractedDate = parser.extractEndDates(parsedParams, message);
        assertEquals(expected, extractedDate);
    }

    @Test
    void extractEndDates_invalidDateInput_throwInvalidDateFormatException() throws InvalidCommandFormatException {
        String input = "/e 2131-14-51";
        StringBuilder message = new StringBuilder();
        DietManagerParser parser = new DietManagerParser();
        HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("search", input);
        assertThrows(InvalidDateFormatException.class, () ->
                parser.extractEndDates(parsedParams, message));
    }
}
