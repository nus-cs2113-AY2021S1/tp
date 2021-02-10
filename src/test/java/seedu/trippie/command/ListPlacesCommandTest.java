package seedu.trippie.command;

import org.junit.jupiter.api.Test;
import seedu.trippie.Storage;
import seedu.trippie.Ui;
import seedu.trippie.data.Trip;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieException;
import seedu.trippie.exception.TrippieInvalidArgumentException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListPlacesCommandTest {

    private static final String LIST_IS_EMPTY = "There is currently nothing in your place list. Why not add one?";
    private final String[] validAddUserInputs = {
        "add /n Marina Bay Sands /d 2 /t 1000 to 1200",
        "add /n Changi Airport /d 1 /t 1900 to 2000",
        "add /n Bugis Junction /d 3 /t 1100 to 1400"
    };

    @Test
    void listPlacesCommand_emptyList_printListIsEmpty() throws ParseException, TrippieException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        ListPlacesCommand c = new ListPlacesCommand("list /p");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
        PrintStream old = System.out;
        c.execute(ui, trippieData);
        System.out.flush();
        System.setOut(old);
        assertTrue(outputStream.toString().contains(LIST_IS_EMPTY));
    }

    @Test
    void listPlacesCommand_validUserInput_printListEveryday() throws ParseException, TrippieException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        for (int i = 0; i < validAddUserInputs.length; i++) {
            AddPlaceCommand c = new AddPlaceCommand(validAddUserInputs[i]);
            c.execute(ui, trippieData);
        }

        ListPlacesCommand l = new ListPlacesCommand("list /p");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
        PrintStream old = System.out;
        l.execute(ui, trippieData);
        System.out.flush();
        System.setOut(old);
        assertTrue(outputStream.toString().contains("[3] 1100 - 1400 Bugis Junction"));
    }

    @Test
    void listPlacesCommand_validUserInput_printListOneDay() throws ParseException, TrippieException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        for (int i = 0; i < validAddUserInputs.length; i++) {
            AddPlaceCommand c = new AddPlaceCommand(validAddUserInputs[i]);
            c.execute(ui, trippieData);
        }

        ListPlacesCommand l = new ListPlacesCommand("list /p /d 1");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
        PrintStream old = System.out;
        l.execute(ui, trippieData);
        System.out.flush();
        System.setOut(old);
        assertTrue(outputStream.toString().contains("1900 - 2000 Changi Airport"));
    }

    @Test
    void listPlacesCommand_invalidUserInput_throwTrippieInvalidArgumentExceptionArray() {
        assertThrows(TrippieInvalidArgumentException.class, () -> new ListPlacesCommand("list /p /d"));
    }

    @Test
    void listPlacesCommand_invalidUserInput_throwTrippieInvalidArgumentExceptionNumberFormat() {
        assertThrows(TrippieInvalidArgumentException.class, () -> new ListPlacesCommand("list /p /d hello"));
    }

    @Test
    void isExit_listPlacesCommandParsed_returnsFalse() throws TrippieInvalidArgumentException {
        String userInput = "list /p";
        ListPlacesCommand c = new ListPlacesCommand(userInput);
        assertFalse(c.isExit());
    }

    private void fileSetup(Storage storage, TrippieData trippieData) throws ParseException, TrippieException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Trip newTrip = new Trip(trippieData.getTripList().size(), "Singapore", df.parse("11-11-2020"));
        newTrip.getExpenseListObject().setForExValue(Float.parseFloat("100"));
        newTrip.getExpenseListObject().setCurrencyAbbreviation("SGD");
        newTrip.getExpenseListObject().setBudgetValue(Float.parseFloat("1000"));
        int index = trippieData.getTripList().size();
        trippieData.getTripList().add(newTrip);
        trippieData.setCurrentTripFromIndex(index);
    }

}
