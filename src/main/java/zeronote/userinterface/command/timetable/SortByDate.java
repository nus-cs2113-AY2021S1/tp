package zeronote.userinterface.command.timetable;

import java.util.Comparator;

import zeronote.tasks.Task;

//@@author longngng

/**
 * Represents the comparator to sort the Task object based on the deadline.
 */
public class SortByDate implements Comparator<Task> {

    /**
     * Returns which task has earlier deadline.
     *
     * @param o1 the first task.
     * @param o2 the second task.
     * @return 0 if two task have the same deadline, 1 if deadline of o1 is before deadline of o2, -1 if deadline of o2
     *      is before deadline of o1.
     */
    @Override
    public int compare(Task o1, Task o2) {
        if (o1.getDueDateTime() == null || o2.getDueDateTime() == null) {
            return 0;
        }
        return o1.getDueDateTime().compareTo(o2.getDueDateTime());
    }
}
