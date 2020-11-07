package seedu.duke.calendar;

import seedu.duke.CommandException;
import seedu.duke.calendar.event.Event;
import seedu.duke.calendar.task.Task;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a calendar list.
 */
public class CalendarList {

    private ArrayList<CalendarItem> calendarList; // contains all events and tasks
    private int totalItems = 0;
    private int totalTasks = 0;
    private int totalEvents = 0;


    public CalendarList() {
        calendarList = new ArrayList<>();
    }

    /**
     * Adds a task to the calendar list.
     *
     * @param task to be added.
     */
    public void addTask(Task task) {
        calendarList.add(task);
        totalTasks++;
        totalItems++;
    }

    /**
     * Adds an event to the calendar list.
     *
     * @param event to be added.
     */
    public void addEvent(Event event) {
        calendarList.add(event);
        totalEvents++;
        totalItems++;
    }

    public void addItem(CalendarItem item) {
        calendarList.add(item);
        totalItems++;
    }

    /**
     * Deletes the task from the calendar list.
     *
     * @param calendarNumber of the task to be deleted.
     */
    public void deleteTask(int calendarNumber) {

        calendarList.remove(calendarNumber);
        totalTasks--;
        totalItems--;
    }

    /**
     * Deletes the event from the event list.
     *
     * @param calendarNumber of the event to be deleted.
     */
    public void deleteEvent(int calendarNumber) {

        calendarList.remove(calendarNumber);
        totalEvents--;
        totalItems--;
    }

    /**
     * Converts the task index to the calendar index.
     *
     * @param taskNumber   task index
     * @param calendarList calendar list of the task.
     * @return calendar index of the task
     */
    public static int convertTaskNumberToCalendarNumber(int taskNumber, CalendarList calendarList)
            throws CommandException {

        if (taskNumber > calendarList.getTotalTasks() || taskNumber <= 0) {
            throw new CommandException("invalid task action");
        }

        int taskCount = 0;
        int itemCount = 0;
        for (CalendarItem s : calendarList.getCalendarList()) {

            if (s instanceof Task) {
                taskCount++;
            }

            if (taskNumber == taskCount) {
                break;
            }
            itemCount++;
        }
        return itemCount;
    }

    /**
     * Converts the event index to the calendar index.
     *
     * @param eventNumber  event index
     * @param calendarList calendar list of the task.
     * @return calendar index of the event
     */
    public static int convertEventNumberToCalendarNumber(int eventNumber, CalendarList calendarList)
            throws CommandException {

        if (eventNumber > calendarList.getTotalEvents() || eventNumber <= 0) {
            throw new CommandException("invalid event action");
        }

        int eventCount = 0;
        int itemCount = 0;
        for (CalendarItem s : calendarList.getCalendarList()) {

            if (s instanceof Event) {
                eventCount++;
            }

            if (eventNumber == eventCount) {
                break;
            }
            itemCount++;
        }
        return itemCount;
    }

    /**
     * Sets the task as done.
     *
     * @param calendarNumberCompleted of the task that is completed.
     */
    public void markTaskAsDone(int calendarNumberCompleted) throws CommandException {

        if (calendarList.get(calendarNumberCompleted) instanceof Task) {
            ((Task) calendarList.get(calendarNumberCompleted)).markAsDone();
        } else {
            throw new CommandException("invalid done number");
        }

    }

    /**
     * Mark the task as important.
     *
     * @param calendarNumber the index of the task in the calendar list.
     */
    public void markTaskAsImportant(int calendarNumber) throws CommandException {
        if (calendarList.get(calendarNumber) instanceof Task) {
            ((Task) calendarList.get(calendarNumber)).markAsImportant();
        } else {
            throw new CommandException("prioritize");
        }
    }

    /**
     * Swaps two tasks with index i and index j.
     *
     * @param i the index of the first task to be swapped.
     * @param j the index of the second task to be swapped.
     */
    public void swapTasks(int i, int j) {
        if (calendarList.get(i) != null && calendarList.get(j) != null) {
            Collections.swap(calendarList, i, j);
        }
    }

    /**
     * Returns the total number of items in calendarList.
     */
    public int getTotalItems() {
        return totalItems;
    }

    /**
     * Returns the total number of tasks in calendarList.
     */
    public int getTotalTasks() {
        return totalTasks;
    }

    /**
     * Returns the total number of events in the calendarList.
     */
    public int getTotalEvents() {
        return totalEvents;
    }

    /**
     * Returns the whole calendarList.
     */
    public ArrayList<CalendarItem> getCalendarList() {
        return calendarList;
    }

    /**
     * Returns the specific item with index i.
     *
     * @param i the index of the item wanted.
     * @return the item with index i.
     */
    public CalendarItem getItem(int i) {
        return calendarList.get(i);
    }
}
