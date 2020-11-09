package athena.timetable;

import athena.TaskList;
import athena.task.Task;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a day in the timetable.
 * Contains the date and the list of tasks, which is a subset of all the tasks
 * that the user registered into the program.
 */
public class TimetableDay {
    private final LocalDate date;
    private final TaskList taskList;

    /**
     * Initializes the object with the date and an empty task list.
     *
     * @param date Date represented by this object.
     */
    public TimetableDay(LocalDate date) {
        assert date != null;
        this.date = date;
        this.taskList = new TaskList();
    }

    /**
     * Getter for date.
     *
     * @return date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Getter for taskList.
     *
     * @return taskList.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.addTask(task);
    }

    /**
     * Generates a string containing the date and list of tasks that is to
     * be printed to the user.
     *
     * @return A string containing the date and list of tasks.
     */
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder(date.toString() + ":\n");
        if (taskList.getTasks().isEmpty()) {
            message.append("Got no tasks for this day\n");
        } else {
            for (Task task : taskList.getTasks()) {
                message.append(task).append("\n");
            }
        }
        return message.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimetableDay that = (TimetableDay) o;
        return Objects.equals(date, that.date)
                && Objects.equals(taskList, that.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, taskList);
    }
}
