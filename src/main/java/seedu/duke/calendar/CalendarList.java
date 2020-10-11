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
     * Deletes the task from the task list.
     *
     * @param taskNumber of the task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        calendarList.remove(taskNumber);
        totalTasks--;
        totalItems--;
    }

    /**
     * Sets the task as done.
     *
     * @param taskNumber of the task that is completed.
     */
    public void markTaskAsDone(int taskNumber) throws DukeException {
        if (calendarList.get(taskNumber - 1) instanceof Task) {
            ((Task) calendarList.get(taskNumber - 1)).markAsDone();
        } else {
            throw new DukeException("invalid done number");
        }
    }

    public void swapTasks(int i, int j) {
        if (calendarList.get(i)!=null && calendarList.get(j)!=null) {
            Collections.swap(calendarList, i, j);
        }
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
