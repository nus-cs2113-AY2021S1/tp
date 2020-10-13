package seedu.duke.command;

import seedu.duke.DateTimeParser;
import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarItem;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.task.Task;
import seedu.duke.calendar.task.Todo;

import java.time.LocalTime;


public class PrintTimelineCommand extends Command {
    public PrintTimelineCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(CalendarList calendarList, Storage storage) throws DukeException {
        CalendarList timelineList = new CalendarList();
        CalendarList todoList = new CalendarList();

        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem temp = calendarList.getItem(i);
            if (temp instanceof Todo) {
                todoList.addItem(temp);
            } else {
                timelineList.addItem(temp);
            }
        }

        CalendarList sortedList = sortByDateTime(timelineList);
        int numberOfItems = sortedList.getTotalItems();
        int inde = 1;
        for (int i=0; i<numberOfItems; i++) {
            System.out.println(inde + ". "+ sortedList.getItem(i).toString());
            inde ++;
        }

        System.out.println("Here is your timeline:");
        System.out.println("Timeline\n|");
        System.out.println("|__ " + sortedList.getItem(0).getDate() + " " + sortedList.getItem(0).getDate().getDayOfWeek());
        if (sortedList.getItem(0).getTime() == null) {
            System.out.println("|            |_____ " + "23:59");
        } else {
            System.out.println("|            |_____ " + sortedList.getItem(0).getTime());
        }
        System.out.println("|                     |_____ " + sortedList.getItem(0).getDescription());

        for (int i = 1; i < numberOfItems; i++) {
            LocalTime thisTime = (sortedList.getItem(i).getTime() == null? LocalTime.of(23, 59) : sortedList.getItem(i).getTime());
            LocalTime prevTime = (sortedList.getItem(i-1).getTime() == null? LocalTime.of(23, 59) : sortedList.getItem(i-1).getTime());

            if (!(sortedList.getItem(i - 1).getDate().isEqual(sortedList.getItem(i).getDate()))) {
                System.out.println("|__ " + sortedList.getItem(i).getDate() + " " + sortedList.getItem(i).getDate().getDayOfWeek());
            }

            if (!thisTime.equals(prevTime)) {
                System.out.println("|            |_____ " + thisTime);
            }
            System.out.println("|                     |_____ " + sortedList.getItem(i).getDescription());
        }

        System.out.println("|__________________ Todo items");
        int index = 1;
        for (int i=0; i<todoList.getTotalItems(); i++) {
            System.out.println("|                     |_____ " + index + ". "+ todoList.getItem(i).toString());
            index ++;
        }
    }

    /**
     * Sort the items in a CalendarList in time sequence.
     * @param calendarList list to be sorted.
     * @return sorted CalendarList.
     */
    public CalendarList sortByDateTime(CalendarList calendarList) {
        CalendarList sortingList = calendarList;
        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            for (int j = i + 1; j < calendarList.getTotalItems(); j++) {
                if (calendarList.getItem(i).getDate() != null && calendarList.getItem(j).getDate() != null) {
                    if (calendarList.getItem(j).getDate().isBefore(calendarList.getItem(i).getDate())) {
                        sortingList.swapTasks(i, j);
                    } else if (calendarList.getItem(j).getDate().isEqual(calendarList.getItem(i).getDate())) {
                        LocalTime iTime = (calendarList.getItem(i).getTime() == null? LocalTime.of(23, 59) : calendarList.getItem(i).getTime());
                        LocalTime jTime = (calendarList.getItem(j).getTime() == null? LocalTime.of(23, 59) : calendarList.getItem(j).getTime());
                        if (jTime.isBefore(iTime)) {
                            sortingList.swapTasks(i, j);
                        }
                    }
                }
            }
        }
        return sortingList;
    }
}
