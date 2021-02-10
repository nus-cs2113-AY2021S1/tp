package seedu.trippie;

import org.junit.jupiter.api.Test;
import seedu.trippie.data.Place;
import seedu.trippie.data.Trip;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class StorageTest {

    private Scanner createSampleMasterFile() {

        String sampleMasterFileText = "DEFAULT 6\n"
               + "0,Chigago run,01-01-2021,1\n"
               + "1,Jakarta Fest,01-02-2021,2\n"
               + "2,Malaysia,20-08-2021,3\n"
               + "3,Malaysia,20-02-2020,4\n"
               + "4,Australia,20-12-2020,5\n"
               + "5,America,20-11-2020,6\n"
               + "6,Africa,04-03-2021,7\n";

        return new Scanner(sampleMasterFileText);
    }

    private Scanner createSampleTripFile() {

        String sampleTripFileText = "This file shows your saved trips under Trippie!\n"
                + "\n"
                + "Here is your itinerary! Enjoy your trip :)\n"
                + "Day | Start Time | End Time | Place\n"
                + "2 | 0900 | 1000 | Penang\n"
                + "\n"
                + "There is currently nothing in your Expense list.\n"
                + "Total budget: $8000.00\n"
                + "\n"
                + "Forex Abbreviation: MYR\n"
                + "\n"
                + "Forex Rate: 0.33";

        return new Scanner(sampleTripFileText);
    }

    @Test
    public void loadMasterFile_sampleMasterFile_success() throws TrippieException {
        Scanner sc = createSampleMasterFile();
        Storage storage = new Storage();
        TrippieData data = new TrippieData(storage);
        storage.loadMasterFile(sc, data);

        String expectedTripListString = "1. Chigago run [1 Day]\n"
                + "2. Jakarta Fest [2 Days]\n"
                + "3. Malaysia [3 Days]\n"
                + "4. Malaysia [4 Days]\n"
                + "5. Australia [5 Days]\n"
                + "6. America [6 Days]\n"
                + "7. Africa [7 Days]\n";

        assertEquals(expectedTripListString, data.list());
        assertEquals("7 Africa 04-03-2021", data.getCurrentTrip().toString());
        assertEquals("Malaysia", data.getTripList().get(3).getName());
    }

    @Test
    public void loadTripFile_sampleTripFile_success() throws ParseException, TrippieException {
        Scanner sc = createSampleTripFile();
        Storage storage = new Storage();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        Date date = df.parse("02-03-2030");
        Trip trip = storage.loadTrip(createSampleTripFile(), new Trip(1, "Malaysia Trip", date));


        assertEquals(trip.getName(), "Malaysia Trip");
        assertEquals(trip.getIndex(), 1);
        assertEquals(trip.getStartDate(), df.parse("02-03-2030"));

        assertEquals(trip.getPlaceListObject().getPlaceList().size(), 1);
        assertEquals(trip.getPlaceListObject().getPlaceList().get(0).getPlaceName(), "Penang");
        assertEquals(trip.getExpenseListObject().getBudgetValue(), 8000.00, 0.002);
        assertEquals(trip.getExpenseListObject().getCurrencyAbbreviation(), "MYR");
        assertEquals(trip.getExpenseListObject().getForExValue(), 0.33, 0.002);
    }
}
