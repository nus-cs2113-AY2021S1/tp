package zeronote.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import zeronote.exceptions.InvalidTagException;

/**
 * Represents a task.
 */
public class Task {
    private final String title;
    private final String by;
    protected DateFormat dateTime = new SimpleDateFormat("dd-MM-yyyy HHmm"); // in 24h format
    protected DateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy hh.mm aa"); // in 12h format
    protected boolean isDone;
    private Date dueDateTime;
    private String tag = "";

    public Task(String title, String by) {
        this.title = title;
        this.by = by;
        this.isDone = false;
    }

    //@@author longngng
    public Date getDueDateTime() {
        return dueDateTime;
    }

    //@@author chuckiex3

    /**
     * Returns the date specified in the deadline.
     *
     * @return date given by user.
     */
    public LocalDate getDueDate() {
        String[] dayTime = by.split(" ", 2);
        return LocalDate.parse(dayTime[0].trim());
    }

    /**
     * Reformats time, from 24h to 12h format.
     *
     * @return time in 12h format, hh MM AM/PM.
     */
    public LocalTime getTime() {
        String[] dayTime = by.split(" ", 2);
        return LocalTime.parse(dayTime[1].trim());
    }

    // @@author yAOwzers
    public void markAsDone() {
        this.isDone = true;
    }

    public String getTitle() {
        return this.title;
    }

    //@@author chuckiex3

    /**
     * Reformats the date from dd/MM/yyyy to MMM dd yyyy.
     *
     * @return date in MMM dd yyyy.
     * @throws ParseException when the date input by the user does not follow the expected format.
     */
    public String reformatDate() throws ParseException {
        Date date = dateTime.parse(by);
        this.dueDateTime = date;
        return outputFormat.format(date);
    }

    public String getStatusIcon() {
        return (isDone ? "o" : "x");
    }

    //@@author Lusi711

    /**
     * Sets the tag for this task.
     *
     * @param tag the tag of the task.
     * @throws InvalidTagException when the user inputs an empty tag.
     */
    public void setTag(String tag) throws InvalidTagException {
        if (!tag.equals("")) {
            this.tag = tag;
        } else {
            throw new InvalidTagException(tag);
        }
    }

    /**
     * Gets the tag of the task.
     *
     * @return the tag of the task.
     */
    public String getTag() {
        return tag;
    }

    //@@author longngng
    public boolean isDone() {
        return isDone;
    }

    //@@author chuckiex3
    @Override
    public String toString() {
        try {
            return ("[" + getStatusIcon() + "] " + getTitle() + " (by: " + reformatDate() + ")"
                    + (tag.equals("") ? "" : "(tag: " + getTag() + ")"));
        } catch (ParseException e) {
            System.out.println("\tAn error occurred while reading the given deadline.");
        }
        return null;
    }

    // @@author neilbaner
    public String serialize() {
        StringBuilder serialized = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        serialized.append(title);
        serialized.append(lineSeparator);
        serialized.append(by);
        serialized.append(lineSeparator);
        serialized.append(isDone);
        serialized.append(lineSeparator);
        return serialized.toString();
    }
}
