package logic.parser;

import exceptions.SchwarzeneggerException;
import exceptions.profile.InvalidCommandFormatException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static logic.parser.ProfileParser.extractCommandTagAndInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author tienkhoa16
class ProfileParserTest {

    @Test
    void testExtractCommandTagAndInfo_missingSlashInput_throwInvalidCommandFormatException() {
        assertThrows(InvalidCommandFormatException.class, () ->
                extractCommandTagAndInfo("add", "add n Schwarzenegger"));
    }

    @Test
    void testExtractCommandTagAndInfo_excessiveSlashInput_throwInvalidCommandFormatException() {
        assertThrows(InvalidCommandFormatException.class, () ->
                extractCommandTagAndInfo("add", "add //n Schwarzenegger"));
    }

    @Test
    void testExtractCommandTagAndInfo_validInput_returnValidParsedParams() throws SchwarzeneggerException {
        HashMap<String, String> parsedParams = new HashMap<>();
        parsedParams.put("/n", "Schwarzenegger");
        parsedParams.put("/c", "2500");
        parsedParams.put("/h", "188");
        parsedParams.put("/w", "113");
        parsedParams.put("/e", "100");

        String command = "add";
        String commandArgs = "/n Schwarzenegger /c 2500 /h 188 /w 113 /e 100";

        assertEquals(parsedParams.toString(), extractCommandTagAndInfo(command, commandArgs).toString());
    }
}
