package seedu.duke.calendar.event;

import seedu.duke.calendar.CalendarItem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents an event in the event list.
 */
public abstract class Event extends CalendarItem {
    protected LocalDate date;
    protected LocalTime time;
    protected String venue;
    protected boolean isOver;
    protected ArrayList<String> additionalInformation;
    protected int additionalInformationCount = 0;

    /**
     * A constructor for events.
     *
     * @param date  date of the event
     * @param time  time of the event
     * @param venue venue of the event
     */
    public Event(LocalDate date, LocalTime time, String venue) {
        this.date = date;
        this.time = time;
        this.venue = venue;
        additionalInformation = new ArrayList<>();
    }


    /**
     * Describe the event.
     *
     * @return a string containing the information about the event
     */
    @Override
    public String toString() {
        String additionalInformationIndicator = "";
        if (additionalInformationCount != 0) {
            additionalInformationIndicator = " #info";
        }
        return date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy E")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mma"))
                + " (" + venue + ")" + additionalInformationIndicator;
    }

    /**
     * Description for adding an recurring event.
     *
     * @return a string containing the information about the recurring event.
     */
    @Override
    public String getRecurringDescription() {
        return "every " + date.getDayOfWeek() + " " + time.format(DateTimeFormatter.ofPattern("h:mma"))
                + " (" + venue + ")";
    }


    @Override
    public String getDescription() {
        return " (" + venue + ")";
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation.add(additionalInformation);
        additionalInformationCount++;
    }

    public ArrayList<String> getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * Returns the additional information of at particular index.
     *
     * @param informationNumber index of the additional information.
     */
    public String getAdditionalInformationElement(int informationNumber) {
        return additionalInformation.get(informationNumber);
    }

    public int getAdditionalInformationCount() {
        return additionalInformationCount;
    }

    public abstract String getType();

    public LocalDate getDate() {
        return date;
    }

    public void markAsOver() {
        isOver = true;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public boolean isOver() {
        return isOver;
    }

    /**
     * Removes the additional information from the array list based on the index.
     *
     * @param indexInfo the additional information's index number.
     */
    public void deleteAdditionalInformation(int indexInfo) {
        additionalInformation.remove(indexInfo);
        additionalInformationCount--;
    }
}
