package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

//@@author wgzesg
/**
 * A class that represents past workout session records.
 */
public class PastWorkoutSessionRecord implements Comparable<PastWorkoutSessionRecord> {

    private final String filePath;
    private final LocalDateTime creationTime;
    private final LocalDateTime lastEditTime;
    private final ArrayList<String> tags;

    /**
     * Constructor of a new PastWorkoutSessionRecord.
     *
     * @param filePath File path of where the records of the session are stored.
     * @param tags Tags attached to this session.
     */
    public PastWorkoutSessionRecord(String filePath, ArrayList<String> tags) {
        this.filePath = filePath;
        creationTime = LocalDateTime.now();
        lastEditTime = LocalDateTime.now();
        this.tags = tags;
    }

    /**
     * Constructor of a new PastWorkoutSessionRecord.
     *
     * @param filePath File path of where the records of the session are stored.
     * @param creationTime Creation time.
     * @param lastEditTime Last edited time.
     * @param tags Tags attached to this session.
     */
    public PastWorkoutSessionRecord(String filePath, LocalDateTime creationTime,
                                    LocalDateTime lastEditTime, ArrayList<String> tags) {
        this.filePath = filePath;
        this.creationTime = creationTime;
        this.lastEditTime = lastEditTime;
        this.tags = tags;
    }

    /**
     * Checks if this record contains all the given tag.
     *
     * @param tgs list of tags specified.
     * @return True if all contained; otherwise false
     */
    public boolean containsAll(ArrayList<String> tgs) {
        for (String s : tgs) {
            if (!isContained(s)) {
                return false;
            }
        }
        return true;
    }

    private boolean isContained(String s) {
        for (String t : tags) {
            if (t.contains(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this record is created on a given date.
     *
     * @param date the given date
     * @return True if it is created on that day; otherwise false
     */
    public boolean isCreatedOn(LocalDate date) {
        return date.equals(creationTime.toLocalDate());
    }

    /**
     * Check if this record is created after a given date(inclusive).
     *
     * @param date the given date.
     * @return True if it is created after or on that day; otherwise false.
     */
    public boolean isCreatedAfter(LocalDate date) {
        return date.equals(creationTime.toLocalDate()) || date.isBefore(creationTime.toLocalDate());
    }

    /**
     * Check if this record is created before a given date(inclusive).
     *
     * @param date the given date.
     * @return True if it is created before or on that day; otherwise false.
     */
    public boolean isCreatedBefore(LocalDate date) {
        return date.equals(creationTime.toLocalDate()) || date.isAfter(creationTime.toLocalDate());
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return String.format("%-15s %s", creationTime.toLocalDate(), tags.toString());
    }

    @Override
    public int compareTo(PastWorkoutSessionRecord o) {
        if (creationTime.isBefore(o.creationTime)) {
            return -1;
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PastWorkoutSessionRecord)) {
            return false;
        }
        return creationTime.equals(((PastWorkoutSessionRecord) o).creationTime)
                && lastEditTime.equals(((PastWorkoutSessionRecord) o).lastEditTime)
                && tags.equals(((PastWorkoutSessionRecord) o).tags);
    }

    /**
     * Updates last edited time when edited.
     *
     * @return A new PastWorkoutSessionRecord with updated lastEdit time.
     */
    public PastWorkoutSessionRecord edit() {
        return new PastWorkoutSessionRecord(filePath, creationTime, LocalDateTime.now(), this.tags);
    }
}
