package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarList;


public class PrintTimelineCommand extends Command {
    public PrintTimelineCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(CalendarList calendarList, Storage storage) throws DukeException {
        CalendarList timelineList = new CalendarList();
        CalendarList todoList = new CalendarList();
        /*
        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem temp = calendarList.getItem(i);

            switch (calendarList.getItem(i).getItemType()) {
            case "E":
            case "D":
                timelineList.addTask(temp);
                break;
            case "T":
                todoList.addTask(temp);
                break;
            default:
                break;
            }
        }

        CalendarList sortedList = sortByDate(timelineList);
        System.out.println("Here is your timeline:");
        int numberOfItems = sortedList.getTotalItems();
        System.out.println("Timeline \n|");
        for (int i = 0; i < numberOfItems; i++) {
            if (i == 0 || (sortedList.getItem(i - 1).getTime() != sortedList.getItem(i).getTime())) {
                System.out.println("|__ " + sortedList.getItem(i).getTime());
            }
            System.out.println("|        |__ " + sortedList.getItem(i).toString());
        }
        */
    }

    public CalendarList sortByDate(CalendarList calendarList) {
        CalendarList sortingList = calendarList;
        /*
        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            for (int j = i + 1; j < calendarList.getTotalItems(); j++) {
                if (calendarList.getItem(i).getTime() != null && calendarList.getItem(j).getTime() != null) {
                    if (calendarList.getItem(j).getTime().isBefore(calendarList.getItem(i).getTime())) {
                        sortingList.swapTasks(i, j);
                    }
                }
            }
        }
        */
        return sortingList;
    }
}
