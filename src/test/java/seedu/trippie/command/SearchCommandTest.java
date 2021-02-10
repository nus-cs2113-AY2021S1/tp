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

class SearchCommandTest {

    private static final String LIST_IS_EMPTY = "There is currently nothing in your place list. Why not add one?";


    @Test
    void searchCommand_validUserInput_emptyList() throws TrippieException, ParseException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        SearchCommand c = new SearchCommand("search find");
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
    void searchCommand_validUserInput_noPlaceFound() throws ParseException, TrippieException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        AddPlaceCommand c = new AddPlaceCommand("add /n Marina Bay Sands /d 2 /t 1000 to 1200");
        c.execute(ui, trippieData);
        SearchCommand l = new SearchCommand("search handsome");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
        PrintStream old = System.out;
        l.execute(ui, trippieData);
        System.out.flush();
        System.setOut(old);
        assertTrue(outputStream.toString().contains("Sorry I can't find any place with the keyword \"handsome\""));
    }

    @Test
    void searchCommand_validUserInput_placeFound() throws TrippieException, ParseException {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);
        fileSetup(storage, trippieData);

        AddPlaceCommand c = new AddPlaceCommand("add /n Marina Bay Sands /d 2 /t 1000 to 1200");
        c.execute(ui, trippieData);
        SearchCommand l = new SearchCommand("search Marina");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
        PrintStream old = System.out;
        l.execute(ui, trippieData);
        System.out.flush();
        System.setOut(old);
        assertTrue(outputStream.toString().contains("1.1000 - 1200 Marina Bay Sands on DAY 2"));
    }



    @Test
    void searchCommand_invalidUserInput_throwsTrippieInvalidArgumentException() {
        assertThrows(TrippieInvalidArgumentException.class, () -> new AddExpenseCommand("search"));
    }

    @Test
    void isExit_searchCommandParsed_returnsFalse() throws TrippieInvalidArgumentException {
        String userInput = "search happy";
        SearchCommand c = new SearchCommand(userInput);
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
