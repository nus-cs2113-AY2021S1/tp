package seedu.trippie.command;

import org.junit.jupiter.api.Test;
import seedu.trippie.Storage;
import seedu.trippie.Ui;
import seedu.trippie.data.Place;
import seedu.trippie.data.Trip;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieException;
import seedu.trippie.exception.TrippieInvalidArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeletePlaceCommandTest {
    private final String[] validAddUserInputs = {
        "add /n Marina Bay Sands /d 2 /t 1000 to 1200",
        "add /n Changi Airport /d 1 /t 1900 to 2000",
        "add /n Bugis Junction /d 3 /t 1100 to 1400"
    };
    private final String[] validDeleteUserInputs = {"delete /p 3", "delete /p 2", "delete /p 1"};
    private final String[] badUserInputs = {"delete /p", "delete /p one", "delete "};
    private final int[] expectedSize = {2, 1, 0};

    @Test
    void deletePlaceCommand_invalidUserInput_throwsTrippieInvalidArgumentException() {
        for (String badUserInput : badUserInputs) {
            assertThrows(TrippieInvalidArgumentException.class, () -> new DeletePlaceCommand(badUserInput));
        }
    }

    @Test
    void deletePlaceCommand_validUserInput_parsedCorrectly() throws TrippieInvalidArgumentException, ParseException,
            TrippieException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        for (String validAddUserInput : validAddUserInputs) {
            AddPlaceCommand c = new AddPlaceCommand(validAddUserInput);
            c.execute(ui, trippieData);
        }

        for (int i = 0; i < validDeleteUserInputs.length; i++) {
            DeletePlaceCommand c = new DeletePlaceCommand(validDeleteUserInputs[i]);
            c.execute(ui, trippieData);
            List<Place> places = trippieData.getCurrentTrip().getPlaceListObject().getPlaceList();
            assertEquals(expectedSize[i], places.size());
        }

    }

    private void fileSetup(Storage storage, TrippieData trippieData) throws ParseException, TrippieException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Trip newTrip = new Trip(trippieData.getTripList().size(), "Singapore", df.parse("11-11-2020"));
        newTrip.getExpenseListObject().setForExValue(Float.parseFloat("100"));
        newTrip.getExpenseListObject().setCurrencyAbbreviation("SGD");
        newTrip.getExpenseListObject().setBudgetValue(Float.parseFloat("1000"));
        trippieData.getTripList().add(newTrip);
    }

}
