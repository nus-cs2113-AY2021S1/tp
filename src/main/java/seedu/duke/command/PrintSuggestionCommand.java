package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarItem;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.task.Deadline;
import seedu.duke.calendar.task.Task;
import seedu.duke.calendar.task.Todo;


public class PrintSuggestionCommand extends Command {
    public PrintSuggestionCommand(String userInput) {
        super(userInput);
    }

    /**
     * Give user suggestions about preparing which tasks.
     *
     * @param calendarList the calendar list of all tasks and events
     * @param storage      not required
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) {
        Task earliestDeadline = (Deadline) getEarliestDeadline(calendarList);
        Task earImportantDeadline = (Deadline) getEarImportantDeadline(calendarList);
        Task firstTodo = (Todo) getFirstTodo(calendarList);
        Task firImportantTodo = (Todo) getFirImportantTodo(calendarList);
        Ui.printSuggestion(earliestDeadline, earImportantDeadline, firstTodo, firImportantTodo);
    }

    /**
     * Get the ordinary deadline task with earliest due date.
     *
     * @param calendarList the list of all tasks and events
     * @return ordinary deadline task with earliest due date, null if no ordinary deadline task.
     */
    public Task getEarliestDeadline(CalendarList calendarList) {
        int deadlineCount = 0;
        Task earliestDeadline = new Deadline(null, null);

        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem item = calendarList.getCalendarList().get(i);
            if (!(item instanceof Task)) {
                continue;
            }
            if (item instanceof Deadline && !((Task) item).getIsImportant() && !((Deadline) item).getIsDone()) {
                deadlineCount++;
                if (deadlineCount == 1) {
                    earliestDeadline = (Deadline) item;
                } else if (item.getDate().isBefore(earliestDeadline.getDate())) {
                    earliestDeadline = (Deadline) item;
                }
            }
        }
        if (deadlineCount == 0) {
            return null;
        }
        return earliestDeadline;
    }

    /**
     * Get the important deadline task with earliest due date.
     *
     * @param calendarList the list of all tasks and events
     * @return important deadline task with earliest due date, null if no important deadline task.
     */
    public Task getEarImportantDeadline(CalendarList calendarList) {
        int count = 0;
        Task earImportantDeadline = new Deadline(null, null);

        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem item = calendarList.getCalendarList().get(i);
            if (!(item instanceof Task)) {
                continue;
            }

            if (item instanceof Deadline && ((Deadline) item).getIsImportant() && !((Deadline) item).getIsDone()) {
                count++;
                if (count == 1) {
                    earImportantDeadline = (Deadline) item;
                } else if (item.getDate().isBefore(earImportantDeadline.getDate())) {
                    earImportantDeadline = (Deadline) item;
                }
            }
        }
        if (count == 0) {
            return null;
        }
        return earImportantDeadline;
    }

    /**
     * Get the first ordinary todo task in the list.
     *
     * @param calendarList the list of all tasks and events
     * @return first ordinary todo task, null if no ordinary task.
     */
    public Task getFirstTodo(CalendarList calendarList) {
        int count = 0;
        Task firstTodo = new Todo(null);

        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem item = calendarList.getCalendarList().get(i);
            if (!(item instanceof Task)) {
                continue;
            }

            if (item instanceof Todo && !((Todo) item).getIsImportant() && !((Todo) item).getIsDone()) {
                count++;
                firstTodo = (Todo) item;
                break;
            }
        }
        if (count == 0) {
            return null;
        }
        return firstTodo;
    }

    /**
     * Get the first important todo task in the list.
     *
     * @param calendarList the list of all tasks and events
     * @return first important todo task, null if no important task.
     */
    public Task getFirImportantTodo(CalendarList calendarList) {
        int count = 0;
        Task firstTodo = new Todo(null);

        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem item = calendarList.getCalendarList().get(i);
            if (!(item instanceof Task)) {
                continue;
            }

            if (item instanceof Todo && ((Todo) item).getIsImportant() && !((Todo) item).getIsDone()) {
                count++;
                firstTodo = (Todo) item;
                break;
            }
        }

        if (count == 0) {
            return null;
        }
        return firstTodo;
    }
}
