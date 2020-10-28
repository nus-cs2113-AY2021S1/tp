package seedu.duke.command;

import seedu.duke.DateTimeParser;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarItem;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;


public class PrintTimelineCommand extends Command {
    public PrintTimelineCommand(String userInput) {
        super(userInput);
    }

    /**
     * Prints out timeline of all items in calendarList.
     *
     * @param calendarList the calendar list to print the timeline from.
     * @param storage      the storage to input the data from.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) {
        CalendarList timelineList = new CalendarList();
        CalendarList todoList = new CalendarList();
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = null;
        try {
            endDate = detectEndDate(userInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (calendarList.getTotalItems() == 0) {
            System.out.println("Your calendar list is empty!");
        } else {
            if (endDate != null) {
                endDate = startDate.plusDays(7);
                for (int i = 0; i < calendarList.getTotalItems(); i++) {
                    if ((calendarList.getItem(i).getDate() == null)
                            || ((calendarList.getItem(i).getDate().isAfter(startDate))
                            && (calendarList.getItem(i).getDate().isBefore(endDate)))) {
                        CalendarItem temp = calendarList.getItem(i);
                        if (temp instanceof Todo) {
                            todoList.addItem(temp);
                        } else {
                            timelineList.addItem(temp);
                        }
                    }
                }
            } else {
                for (int i = 0; i < calendarList.getTotalItems(); i++) {
                    CalendarItem temp = calendarList.getItem(i);
                    if (temp instanceof Todo) {
                        todoList.addItem(temp);
                    } else {
                        timelineList.addItem(temp);
                    }
                }
            }

            CalendarList sortedList = sortByDateTime(timelineList);

            System.out.println("Here is your timeline:");
            System.out.println("Timeline\n|");
            if (sortedList.getTotalItems() != 0) {
                System.out.println("|__ " + sortedList.getItem(0).getDate()
                        + " " + sortedList.getItem(0).getDate().getDayOfWeek());
                if (sortedList.getItem(0).getTime() == null) {
                    System.out.println("|            |_____ " + "23:59");
                } else {
                    System.out.println("|            |_____ " + sortedList.getItem(0).getTime());
                }
                System.out.println("|                     |_____ " + sortedList.getItem(0).getDescription());

                for (int i = 1; i < sortedList.getTotalItems(); i++) {
                    LocalTime thisTime = (sortedList.getItem(i).getTime() == null
                            ? LocalTime.of(23, 59) : sortedList.getItem(i).getTime());
                    LocalTime prevTime = (sortedList.getItem(i - 1).getTime() == null
                            ? LocalTime.of(23, 59) : sortedList.getItem(i - 1).getTime());
                    if (!(sortedList.getItem(i - 1).getDate().isEqual(sortedList.getItem(i).getDate()))) {
                        System.out.println("|__ " + sortedList.getItem(i).getDate()
                                + " " + sortedList.getItem(i).getDate().getDayOfWeek());
                    }
                    if (!thisTime.equals(prevTime)) {
                        System.out.println("|            |_____ " + thisTime);
                    }
                    System.out.println("|                     |_____ "
                            + sortedList.getItem(i).getDescription());
                }
            }

            System.out.println("|__ Todo items");
            int index = 1;
            if (todoList.getTotalItems() != 0) {
                for (int i = 0; i < todoList.getTotalItems(); i++) {
                    System.out.println(index + ". " + todoList.getItem(i).toString());
                    index++;
                }
                System.out.println("\n");
            }
        }
    }

    /**
     * Sort the items in a CalendarList in time sequence.
     *
     * @param calendarList list to be sorted.
     * @return sorted CalendarList.
     */
    public CalendarList sortByDateTime(CalendarList calendarList) {
        CalendarList sortingList = calendarList;
        if (calendarList.getTotalItems() == 0) {
            return calendarList;
        } else {
            for (int i = 0; i < calendarList.getTotalItems(); i++) {
                for (int j = i + 1; j < calendarList.getTotalItems(); j++) {
                    if (calendarList.getItem(i).getDate() != null && calendarList.getItem(j).getDate() != null) {
                        if (calendarList.getItem(j).getDate().isBefore(calendarList.getItem(i).getDate())) {
                            sortingList.swapTasks(i, j);
                        } else if (calendarList.getItem(j).getDate().isEqual(calendarList.getItem(i).getDate())) {
                            LocalTime timeOfi = (calendarList.getItem(i).getTime() == null
                                    ? LocalTime.of(23, 59) : calendarList.getItem(i).getTime());
                            LocalTime timeOfj = (calendarList.getItem(j).getTime() == null
                                    ? LocalTime.of(23, 59) : calendarList.getItem(j).getTime());
                            if (timeOfj.isBefore(timeOfi)) {
                                sortingList.swapTasks(i, j);
                            }
                        }
                    }
                }
            }
            return sortingList;
        }
    }

    public LocalDate detectEndDate(String userInput) throws Exception {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate;
        if (userInput.contains("week")) {
            endDate = startDate.plusDays(7);
        } else if (userInput.contains(("month"))) {
            endDate = startDate.plusDays(31);
        } else if (userInput.contains("date")) {
            String[] userInputSplit = userInput.split("date", 2);
            endDate = DateTimeParser.inputDateProcessor(userInputSplit[1].trim());
        } else {
            endDate = null;
        }
        return endDate;
    }
}
