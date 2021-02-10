package seedu.trippie.data;

import seedu.trippie.Trippie;
import seedu.trippie.exception.TrippieException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip {
    private int index;
    private String name;
    private Date startDate;
    private ExpenseList expenseList;
    private PlaceList placeList;
    private int maxDay;

    protected void setIndex(int index) {
        this.index = index;
    }

    /**
     * A trip constructor that initializes both an empty expense list and an empty place list.
     */
    public Trip() {
        this.expenseList = new ExpenseList();
        this.placeList = new PlaceList();
    }

    /**
     * A trip constructor based on given parameters.
     * @param index Given trip index
     * @param name Given trip name
     * @param startDate Given trip startDate
     * @throws TrippieException A common Trippie exception
     */
    public Trip(int index, String name, Date startDate) throws TrippieException {

        if (index < 0) {
            throw new TrippieException("Index out of bounds");
        }

        if (name.isEmpty()) {
            throw new TrippieException("Trip name should not be empty");
        }


        this.index = index;
        this.name = name;
        this.startDate = startDate;
        this.expenseList = new ExpenseList();
        this.placeList = new PlaceList();
    }

    /**
     * Creates a trip.
     * @param index trip index
     * @param name trip name
     * @param startDate start date in a Date object
     * @param maxDay max day of the trip
     * @throws TrippieException Fails to parse the inputs or index is out of bounds
     */
    public Trip(int index, String name, Date startDate, int maxDay) throws TrippieException {
        this(index, name, startDate);
        this.maxDay = maxDay;
    }

    /**
     * Retrieves the expenseList object.
     *
     * @return ExpenseList object of this trip
     */
    public ExpenseList getExpenseListObject() {
        return this.expenseList;
    }

    /**
     * Retrieves the placeList object.
     *
     * @return PlaceList object of the trip
     */
    public PlaceList getPlaceListObject() {
        return this.placeList;
    }

    /**
     * Retrieves the index of the trip.
     *
     * @return index of the trip
     */
    public int getIndex() {
        return index;
    }

    /**
     * Retrieves the name of the trip.
     *
     * @return name of the trip
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the start date of the trip.
     *
     * @return start date of the trip as a Date object
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Retrieves the max day of the trip.
     *
     * @return the max day of the trip
     */
    public int getMaxDay() {
        return maxDay;
    }

    /**
     * Changes the name of the trip.
     *
     * @param name new name of the trip
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the start date of the trip.
     *
     * @param startDate new start date of the trip
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Changes the expenseList object of the trip.
     *
     * @param expenseList new expenseList object of the trip
     */
    public void setExpenseList(ExpenseList expenseList) {
        this.expenseList = expenseList;
    }

    /**
     * Changes the placeList object of the trip.
     *
     * @param placeList new placeList object of the trip
     */
    public void setPlaceList(PlaceList placeList) {
        this.placeList = placeList;
    }

    /**
     * Updates maxDay of the trip based on the current placeList and expenseList.
     */
    public void updateMaxDay() {
        maxDay = Integer.max(placeList.getMaxDay(), expenseList.getMaxDay());
    }

    /**
     * Summarizes the trip into a string, with index, name, and startDate.
     *
     * @return a single line String
     */
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return index + 1 + " " + name + " " + df.format(startDate);
    }

}
