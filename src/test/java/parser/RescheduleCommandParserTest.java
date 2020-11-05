package parser;

import commands.EditCardCommand;
import commands.EditChapterCommand;
import commands.EditModuleCommand;
import commands.RescheduleCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static common.Messages.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RescheduleCommandParserTest {
    private RescheduleCommandParser parser = new RescheduleCommandParser();
    private String date = LocalDate.now().plusDays(1).toString();
    private String dateBeforeToday = LocalDate.now().minusDays(1).toString();

    @Test
    public void parse_missingParts_throwsInvalidInputException() {
        // no index and no field specified
        assertThrows(InvalidInputException.class, () -> parser.parse("", MODULE));

        // no index specified
        assertThrows(InvalidInputException.class, () -> parser.parse(date, MODULE));

        // no field specified
        assertThrows(InvalidInputException.class, () -> parser.parse("1", MODULE));
    }

    @Test
    public void parse_incorrectLevel_throwsIncorrectAccessLevelException() {
        assertThrows(IncorrectAccessLevelException.class, () -> parser.parse("1 " + date, ""));
        assertThrows(IncorrectAccessLevelException.class, () -> parser.parse("1 " + date, ADMIN));
        assertThrows(IncorrectAccessLevelException.class, () -> parser.parse("1 " + date, CHAPTER));
    }

    @Test
    public void parse_invalidDateFormat_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> parser.parse("1 10-12-2020", MODULE));
    }

    @Test
    public void parse_invalidDateBeforeToday_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> parser.parse("1 " + dateBeforeToday, MODULE));
    }

    @Test
    public void parse_validInput_returnsRescheduleCommand() throws Exception {
        assertTrue(parser.parse("1 " + date, MODULE) instanceof RescheduleCommand);
    }

}
