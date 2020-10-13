package seedu.duke.command;

import seedu.duke.DateTimeParser;
import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.event.Activity;
import seedu.duke.calendar.event.Lab;
import seedu.duke.calendar.event.Lecture;
import seedu.duke.calendar.event.Tutorial;
import seedu.duke.calendar.task.Deadline;
import seedu.duke.calendar.event.Exam;
import seedu.duke.calendar.task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Adds a task to the task list, depending on what kind of task (event, deadline, todo) it is.
 */
public class AddCommand extends Command {

    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String ACTIVITY = "act";
    public static final String LECTURE = "lect";
    public static final String TUTORIAL = "tut";
    public static final String LAB = "lab";
    public static final String EXAM = "exam";
    public static final int COMMAND_TYPE = 0;
    public static final int DESCRIPTION = 1;

    private String venue;
    private String moduleCode;
    private LocalTime time;
    private String[] dateTime;
    private LocalDate date;

    public AddCommand(String userInput) {
        super(userInput);
    }

    /**
     * Creates a task in the task list after determining what type of task (event, deadline, todo).
     * Saves the updated calendar list in the storage after the new task is added.
     *
     * @param calendarList the calendar list to add the new task to.
     * @param storage      the storage to be saved to.
     * @throws DukeException if the add command input is invalid.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws DukeException {
        String[] command;
        boolean isTask = false;

        command = userInput.split(" ", 2);
        String commandType = command[COMMAND_TYPE];

        switch (commandType) {
        case TODO:
            try {
                isTask = addTodoTask(calendarList, command[DESCRIPTION]);
            } catch (Exception e) {
                throw new DukeException("todo");
            }
            break;
        case DEADLINE:
            try {
                isTask = addDeadlineTask(calendarList, command);
            } catch (Exception e) {
                throw new DukeException("deadline");
            }
            break;
        case ACTIVITY:
            try {
                addActivityEvent(calendarList, command);
            } catch (Exception e) {
                throw new DukeException("activity");
            }
            break;
        case EXAM:
            try {
                addExamEvent(calendarList, command);
            } catch (Exception e) {
                throw new DukeException("exam");
            }
            break;
        case LECTURE:
            try {
                addLectureEvent(calendarList, command);
            } catch (Exception e) {
                throw new DukeException("lecture");
            }
            break;
        case TUTORIAL:
            try {
                addTutorialEvent(calendarList, command);
            } catch (Exception e) {
                throw new DukeException("tutorial");
            }
            break;
        case LAB:
            try {
                addLabEvent(calendarList, command);
            } catch (Exception e) {
                throw new DukeException("lab");
            }
            break;
        default:
            throw new DukeException("invalid command");
        }
        Ui.printAddMessage(calendarList);
        if (isTask) {
            Ui.printTotalTaskNumber(calendarList);
        }

        storage.writeToFile(calendarList);
    }

    /**
     * User input for Lab event example: lab CS1010 com1-b1-14 /at 100820 1400.
     */
    private void addLabEvent(CalendarList calendarList, String[] command) throws Exception {
        command = command[1].trim().split(" ", 2);
        moduleCode = command[0];
        command = command[1].split("/", 2);
        String[] temp = command[0].split("@");
        venue = temp[1].trim();
        dateTime = command[1].trim().split(" ", 2);
        date = DateTimeParser.inputDateProcessor(dateTime[0].trim());
        time = DateTimeParser.inputTimeProcessor(dateTime[1].trim());

        if (moduleCode.isEmpty()) {
            throw new DukeException("lab");
        } else {
            calendarList.addEvent(new Lab(moduleCode, date, time, venue));
        }
    }

    /**
     * User input for Tutorial event example: tutorial CS1010 lt12 /at 090820 1000.
     */
    private void addTutorialEvent(CalendarList calendarList, String[] command) throws Exception {
        command = command[1].trim().split(" ", 2);
        moduleCode = command[0];
        command = command[1].split("/", 2);
        String[] temp = command[0].split("@");
        venue = temp[1].trim();
        dateTime = command[1].trim().split(" ", 2);
        date = DateTimeParser.inputDateProcessor(dateTime[0].trim());
        time = DateTimeParser.inputTimeProcessor(dateTime[1].trim());


        if (moduleCode.isEmpty()) {
            throw new DukeException("tutorial");
        } else {
            calendarList.addEvent(new Tutorial(moduleCode, date, time, venue));
        }
    }

    /**
     * User input for Lecture task example: lecture CS2113 LT2 /at 020202 1200.
     */
    private void addLectureEvent(CalendarList calendarList, String[] command) throws Exception {
        command = command[1].trim().split(" ", 2);
        moduleCode = command[0];
        command = command[1].split("/", 2);
        String[] temp = command[0].split("@");
        venue = temp[1].trim();
        dateTime = command[1].trim().split(" ", 2);
        date = DateTimeParser.inputDateProcessor(dateTime[0].trim());
        time = DateTimeParser.inputTimeProcessor(dateTime[1].trim());

        if (moduleCode.isEmpty()) {
            throw new DukeException("lecture");
        } else {
            calendarList.addEvent(new Lecture(moduleCode, date, time, venue));
        }
    }

    /**
     * User input for Exam task example: exam CS2113 BLK:EA LT2 /at 020202 1200.
     */
    private void addExamEvent(CalendarList calendarList, String[] command) throws Exception {
        command = command[1].trim().split(" ", 2);
        moduleCode = command[0];
        command = command[1].split("/", 2);
        String[] temp = command[0].split("@");
        venue = temp[1].trim();
        dateTime = command[1].trim().split(" ", 2);
        date = DateTimeParser.inputDateProcessor(dateTime[0].trim());
        time = DateTimeParser.inputTimeProcessor(dateTime[1].trim());

        if (moduleCode.isEmpty()) {
            throw new DukeException("exam");
        } else {
            calendarList.addEvent(new Exam(moduleCode, date, time, venue));
        }
    }

    /**
     * User input for Activity event example: activity run training @sentosa / 020202 1200.
     */
    private void addActivityEvent(CalendarList calendarList, String[] command) throws Exception {
        String eventDescription;
        command = command[1].trim().split("@", 2);
        eventDescription = command[0].trim();
        command = command[1].trim().split("/", 2);
        venue = command[0].trim();
        dateTime = command[1].trim().split(" ", 2);
        date = DateTimeParser.inputDateProcessor(dateTime[0].trim());
        time = DateTimeParser.inputTimeProcessor(dateTime[1].trim());


        if (eventDescription.isEmpty()) {
            throw new DukeException("activity");
        } else {
            calendarList.addEvent(new Activity(eventDescription, date, time, venue));
        }
    }

    private boolean addDeadlineTask(CalendarList calendarList, String[] command) throws Exception {
        String taskDescription;
        boolean isTask;
        command = command[1].split("/");
        taskDescription = command[0].trim();
        date = DateTimeParser.inputDateProcessor(command[1].trim());

        if (taskDescription.isEmpty()) {
            throw new DukeException("deadline");
        } else {
            calendarList.addTask(new Deadline(taskDescription, date));
            isTask = true;
        }
        return isTask;
    }

    private boolean addTodoTask(CalendarList calendarList, String s) throws DukeException {
        String taskDescription;
        boolean isTask;
        taskDescription = s.trim();
        if (taskDescription.isEmpty()) {
            throw new DukeException("todo");
        } else {
            calendarList.addTask(new Todo(taskDescription));
            isTask = true;
        }
        return isTask;
    }
}
