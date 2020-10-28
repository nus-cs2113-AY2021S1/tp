package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarItem;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.event.Exam;
import seedu.duke.calendar.task.Deadline;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * A countdown for exams and deadlines.
 */
public class CountdownCommand extends Command {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public CountdownCommand(String userInput) {
        super(userInput);
    }

    /**
     * Calculates the countdown for every unfinished deadline tasks and future exams.
     *
     * @param calendarList the calendarList to calculate the countdown for.
     * @param storage      the storage to store data to.
     * @throws DukeException when there is an invalid command.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws DukeException {
        if (userInput.equals("countdown")) {
            countdownExamsDeadlines(calendarList);
        } else if (userInput.equals("countdown deadlines")) {
            countdownDeadlines(calendarList);
        } else if (userInput.equals("countdown exams")) {
            countdownExams(calendarList);
        } else {
            throw new DukeException("invalid countdown");
        }
    }

    /**
     * Calculates the countdown for both exams event and deadline tasks.
     *
     * @param calendarList the calendarList to calculate the countdown for.
     */
    public void countdownExamsDeadlines(CalendarList calendarList) {
        countdownDeadlines(calendarList);
        Ui.printDukeBorder(false);
        countdownExams(calendarList);
    }

    /**
     * Calculates the countdown for deadline tasks.
     *
     * @param calendarList the calendarList to calculate the countdown for.
     */
    public void countdownDeadlines(CalendarList calendarList) {
        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem item = calendarList.getCalendarList().get(i);
            if (item instanceof Deadline) {
                ((Deadline) item).setCountdown(countdown(item));
            }
        }
        sortDeadlinesAndPrintCountdown(calendarList);
    }

    /**
     * Calculates the countdown for exams events.
     *
     * @param calendarList the calendarList to calculate the countdown for.
     */
    public void countdownExams(CalendarList calendarList) {
        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem item = calendarList.getCalendarList().get(i);
            if (item instanceof Exam) {
                if (item.getTime().isBefore(LocalTime.now())) {
                    ((Exam) item).setCountdown(countdown(item) - 1);
                } else {
                    ((Exam) item).setCountdown(countdown(item));
                }
            }
        }
        sortExamsAndPrintCountdown(calendarList);
    }

    /**
     * Calculates the countdown days.
     *
     * @param item the calendar item we want to calculate the countdown.
     * @return the countdown.
     */
    public int countdown(CalendarItem item) {
        Date date = null;
        Date now = null;
        String itemDate;
        String nowDate;
        itemDate = item.getDate().toString();
        nowDate = LocalDate.now().toString();
        try {
            date = format.parse(itemDate);
            now = format.parse(nowDate);
        } catch (Exception e) {
            System.out.println("The input date is in the wrong format");
        }
        int days = (int) ((date.getTime() - now.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * Sort the exam events according to their countdowns in ascending manner.
     *
     * @param calendarList the calendarList to calculate the countdown for.
     */
    public void sortExamsAndPrintCountdown(CalendarList calendarList) {
        CalendarList examList = new CalendarList();
        CalendarList deadlineList = new CalendarList();

        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem temp = calendarList.getItem(i);
            if (temp instanceof Exam) {
                if (!((Exam) temp).getIsOver()) {
                    examList.addItem(temp);
                }
            }
        }

        for (int i = 0; i < examList.getTotalItems() - 1; i++) {
            for (int j = 0; j < examList.getTotalItems() - i - 1; j++) {
                CalendarItem item1 = examList.getCalendarList().get(j);
                CalendarItem item2 = examList.getCalendarList().get(j + 1);
                if (((Exam) item1).getCountdown() > ((Exam) item2).getCountdown()) {
                    examList.swapTasks(j, j + 1);
                }
            }
        }

        for (int i = 0; i < examList.getTotalItems(); i++) {
            CalendarItem temp = examList.getItem(i);
        }
        Ui.printCountDownMessage(examList, 0);
    }

    /**
     * Sort the deadline events according to their countdowns in ascending manner.
     *
     * @param calendarList the calendarList to calculate the countdown for.
     */
    public void sortDeadlinesAndPrintCountdown(CalendarList calendarList) {
        CalendarList deadlineList = new CalendarList();

        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem temp = calendarList.getItem(i);
            if (temp instanceof Deadline) {
                if (!((Deadline) temp).getIsDone()) {
                    deadlineList.addItem(temp);
                }
            }
        }

        for (int i = 0; i < deadlineList.getTotalItems() - 1; i++) {
            for (int j = 0; j < deadlineList.getTotalItems() - i - 1; j++) {
                CalendarItem item1 = deadlineList.getCalendarList().get(j);
                CalendarItem item2 = deadlineList.getCalendarList().get(j + 1);
                if (((Deadline) item1).getCountdown() > ((Deadline) item2).getCountdown()) {
                    deadlineList.swapTasks(j, j + 1);
                }
            }
        }

        for (int i = 0; i < deadlineList.getTotalItems(); i++) {
            CalendarItem temp = deadlineList.getItem(i);
        }
        Ui.printCountDownMessage(deadlineList, 1);
    }
}
