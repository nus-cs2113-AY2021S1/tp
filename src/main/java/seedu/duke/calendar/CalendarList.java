package seedu.duke.calendar;

import seedu.duke.DukeException;
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
    public static int convertTaskNumberToCalendarNumber(int taskNumber, CalendarList calendarList) {
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
    public static int convertEventNumberToCalendarNumber(int eventNumber, CalendarList calendarList) {
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
    public void markTaskAsDone(int calendarNumberCompleted) throws DukeException {

        if (calendarList.get(calendarNumberCompleted) instanceof Task) {
            ((Task) calendarList.get(calendarNumberCompleted)).markAsDone();
        } else {
            throw new DukeException("invalid done number");
        }

    }

    public void swapTasks(int i, int j) {
        Collections.swap(calendarList, i, j);
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public int getTotalEvents() {
        return totalEvents;
    }

    public ArrayList<CalendarItem> getCalendarList() {
        return calendarList;
    }

    public CalendarItem getItem(int i) {
        return calendarList.get(i);
    }
}
