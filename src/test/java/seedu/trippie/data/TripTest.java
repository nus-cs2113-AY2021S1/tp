package seedu.trippie.data;

import org.junit.jupiter.api.Test;
import seedu.trippie.exception.TrippieException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TripTest {

    @Test
    void tripConstructor_validInputs_success() throws ParseException, TrippieException {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Trip trip = new Trip(1, "Trip name", df.parse("01-01-2020"));

        assertEquals(trip.getIndex(), 1);
        assertEquals(trip.getName(), "Trip name");
        assertEquals(df.format(trip.getStartDate()), "01-01-2020");
    }

    @Test
    void tripConstructor_invalidName_trippieExceptionThrown() {
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Trip trip = new Trip(-1, "Trip name", df.parse("01-01-2020"));

            assertEquals(trip.getIndex(), 1);
            assertEquals(trip.getName(), "Trip name");
            assertEquals(df.format(trip.getStartDate()), "01-01-2020");
        } catch (TrippieException e) {
            assertEquals(e.getMessage(), "Index out of bounds");
        } catch (ParseException p) {
            fail();
        }
    }

    @Test
    void tripConstructor_invalidIndex_trippieExceptionThrown() {
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Trip trip = new Trip(1, "", df.parse("01-01-2020"));

            assertEquals(trip.getIndex(), 1);
            assertEquals(trip.getName(), "Trip name");
            assertEquals(df.format(trip.getStartDate()), "01-01-2020");
        } catch (TrippieException e) {
            assertEquals(e.getMessage(), "Trip name should not be empty");
        } catch (ParseException p) {
            fail();
        }
    }

    @Test
    void getSetExpenseListObject_validInputs_success() {
        Trip trip = new Trip();
        ExpenseList expenseList = new ExpenseList();

        assertNotEquals(trip.getExpenseListObject(), expenseList);
        trip.setExpenseList(expenseList);
        assertEquals(trip.getExpenseListObject(), expenseList);
    }

    @Test
    void getSetPlaceListObject_validInputs_success() {
        Trip trip = new Trip();
        PlaceList placeList = new PlaceList();

        assertNotEquals(trip.getPlaceListObject(), placeList);
        trip.setPlaceList(placeList);
        assertEquals(trip.getPlaceListObject(), placeList);
    }

    @Test
    void getMaxDay_emptyPlaceAndExpenses_zero() throws TrippieException, ParseException {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Trip trip = new Trip(1, "Check", df.parse("01-01-2020"));

        assertEquals(trip.getMaxDay(), 0);
    }

    @Test
    void getMaxDay_withPlaceList_correct() throws TrippieException, ParseException {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        PlaceList placeList = new PlaceList();

        placeList.getPlaceList().add(new Place("A name", 9999, 200, 300));
        placeList.getPlaceList().add(new Place("A name1", 99999, 200, 300));
        placeList.getPlaceList().add(new Place("A name2", 999999, 200, 300));
        placeList.getPlaceList().add(new Place("A name3", 9999999, 200, 300));

        Trip trip = new Trip(1, "Check0", df.parse("01-01-2020"));
        trip.setPlaceList(placeList);
        trip.updateMaxDay();

        assertEquals(9999999, trip.getMaxDay());
    }

    @Test
    void getMaxDay_withExpenseListOnly_correct() throws TrippieException, ParseException {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        ExpenseList expenseList = new ExpenseList();

        expenseList.getExpenseList().add(new Expense(
                "Something",
                Float.valueOf("1.231"),
                9));
        expenseList.getExpenseList().add(new Expense(
                "Something2",
                Float.valueOf("1.231"),
                99));
        expenseList.getExpenseList().add(new Expense(
                "Something3",
                Float.valueOf("1.231"),
                999));
        expenseList.getExpenseList().add(new Expense(
                "Something4",
                Float.valueOf("1.231"),
                9999));

        Trip trip = new Trip(1, "Check1", df.parse("01-01-2020"));
        trip.setExpenseList(expenseList);
        trip.updateMaxDay();

        assertEquals(9999, trip.getMaxDay());
    }

    @Test
    void getMaxDay_withExpenseListAndPlaceList_correct() throws TrippieException, ParseException {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        PlaceList placeList = new PlaceList();

        placeList.getPlaceList().add(new Place("A name", 9999, 200, 300));
        placeList.getPlaceList().add(new Place("A name1", 99999, 200, 300));
        placeList.getPlaceList().add(new Place("A name2", 999999, 200, 300));
        placeList.getPlaceList().add(new Place("A name3", 9999999, 200, 300));

        ExpenseList expenseList = new ExpenseList();

        expenseList.getExpenseList().add(new Expense(
                "Something",
                Float.valueOf("1.231"),
                9));
        expenseList.getExpenseList().add(new Expense(
                "Something2",
                Float.valueOf("1.231"),
                99));
        expenseList.getExpenseList().add(new Expense(
                "Something3",
                Float.valueOf("1.231"),
                999));
        expenseList.getExpenseList().add(new Expense(
                "Something4",
                Float.valueOf("1.231"),
                9999));

        Trip trip = new Trip(1, "Check2", df.parse("01-01-2020"));
        trip.setPlaceList(placeList);
        trip.setExpenseList(expenseList);
        trip.updateMaxDay();

        assertEquals(9999999, trip.getMaxDay());
    }
}
