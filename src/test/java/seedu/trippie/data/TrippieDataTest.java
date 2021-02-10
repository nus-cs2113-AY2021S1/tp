package seedu.trippie.data;

import org.junit.jupiter.api.Test;
import seedu.trippie.Storage;
import seedu.trippie.Trippie;
import seedu.trippie.exception.TrippieException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrippieDataTest {

    @Test
    void list_singleTrip_success() throws TrippieException, ParseException {
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Trip trip = new Trip(0, "TripName", df.parse("01-01-2020"));

        trippieData.getTripList().add(trip);

        assertEquals("1. TripName [No places or expenses added yet]\n", trippieData.list());
    }

    @Test
    void list_noTrip_success() throws TrippieException, ParseException {
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Trip trip = new Trip(0, "TripName", df.parse("01-01-2020"));

        trippieData.addTrip(trip);

        assertEquals("1. TripName [No places or expenses added yet]\n", trippieData.list());
    }

    @Test
    void addTrip_singleTrip_largerTripList() throws ParseException, TrippieException {
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");


        Trip[] trips = {
            new Trip(0, "TripName0", df.parse("01-01-2020")),
            new Trip(0, "TripName1", df.parse("01-01-2020")),
            new Trip(0, "TripName2", df.parse("01-01-2020")),
            new Trip(0, "TripName3", df.parse("01-01-2020")),
        };

        for (Trip t: trips) {
            trippieData.addTrip(t);
        }

        assertEquals(
                "1. TripName0 [No places or expenses added yet]\n"
                    + "2. TripName1 [No places or expenses added yet]\n"
                    + "3. TripName2 [No places or expenses added yet]\n"
                    + "4. TripName3 [No places or expenses added yet]\n",
                    trippieData.list()
        );
    }

    @Test
    void removeTripFromIndex_singleTrip_reducedTripList() throws ParseException, TrippieException {
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");


        Trip[] trips = {
            new Trip(0, "TripName0", df.parse("01-01-2020")),
            new Trip(1, "TripName1", df.parse("01-01-2020")),
            new Trip(2, "TripName2", df.parse("01-01-2020")),
            new Trip(3, "TripName3", df.parse("01-01-2020")),
        };

        for (Trip t: trips) {
            trippieData.addTrip(t);
        }

        Trip x = trippieData.removeTripFromIndex(3);

        assertEquals("4 TripName3 01-01-2020", x.toString());
    }

    @Test
    void getTripFromIndex_singleTrip_correctIndex() throws ParseException, TrippieException {
        Storage storage = new Storage();
        TrippieData trippieData = new TrippieData(storage);

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");


        Trip[] trips = {
            new Trip(0, "TripName0", df.parse("01-01-2020")),
            new Trip(1, "TripName1", df.parse("01-01-2020")),
            new Trip(2, "TripName2", df.parse("01-01-2020")),
            new Trip(3, "TripName3", df.parse("01-01-2020")),
        };

        for (Trip t: trips) {
            trippieData.addTrip(t);
        }

        assertEquals("4 TripName3 01-01-2020", trippieData.getTripFromIndex(3).toString());
    }

}