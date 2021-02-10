package seedu.trippie.command;

import org.junit.jupiter.api.Test;
import seedu.trippie.exception.TrippieInvalidArgumentException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddPlaceCommandTest {

    private final String[] goodUserInputs = {
        "add /n Marina Bay Sands /d 2 /t 1000 to 1200",
        "add /n Changi Airport /d 1 /t 1900 to 2000",
        "add /n Bugis Junction /d 3 /t 1100 to 1400"
    };

    private final String[] badUserInputs = {
        "add /n Marina Bay Sands /d 2 /t 1300 to 1200",
        "add /n Changi Airport /d one /t 1900 to 2000",
        "add /n Bugis /d 1 /t 2100 to 2000",
        "add /n Clementi /d 4 /t 9999 to 0100",
        "addx /n Haw Par Villa /d 3 /t 0100 to 0500",
        "add /n Apple Park /d 3 /t 1300 1200",
        "add /n"
    };

    @Test
    void addPlaceCommand_validUserInput_setPlaceParameters() throws TrippieInvalidArgumentException {
        String[] placeNames = {"Marina Bay Sands", "Changi Airport", "Bugis Junction"};
        int[] placeStartTimes = {1000, 1900, 1100};
        int[] placeEndTimes = {1200, 2000, 1400};
        int[] placeDays = {2, 1, 3};
        for (int i = 0; i < goodUserInputs.length; i++) {
            AddPlaceCommand c = new AddPlaceCommand(goodUserInputs[i]);
            assertEquals(placeNames[i], c.extractName(goodUserInputs[i]));
            assertEquals(placeStartTimes[i], c.extractStartTime(goodUserInputs[i]));
            assertEquals(placeEndTimes[i], c.extractEndTime(goodUserInputs[i]));
            assertEquals(placeDays[i], c.extractDay(goodUserInputs[i]));
        }
    }

    @Test
    void addPlaceCommand_invalidUserInput_throwTrippieInvalidArgumentException() {
        for (String badUserInput : badUserInputs) {
            assertThrows(TrippieInvalidArgumentException.class, () -> new AddPlaceCommand(badUserInput));
        }
    }

    @Test
    void isExit_addPlaceCommandParsed_returnsFalse() throws TrippieInvalidArgumentException {
        String userInput = "add /n Marina Bay Sands /d 2 /t 1000 to 1200";
        AddPlaceCommand c = new AddPlaceCommand(userInput);
        assertFalse(c.isExit());
    }
}
