package seedu.duke.command;

import seedu.duke.DateTimeParser;
import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.event.Activity;
import seedu.duke.calendar.event.Lecture;
import seedu.duke.calendar.task.Deadline;
import seedu.duke.calendar.task.Exam;
import seedu.duke.calendar.task.Lab;
import seedu.duke.calendar.task.Todo;
import seedu.duke.calendar.task.Tutorial;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Adds a task to the task list, depending on what kind of task (event, deadline, todo) it is.
 */
public class AddCommand extends Command {

    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String ACTIVITY = "activity";
    public static final String LECTURE = "lecture";
    public static final String TUTORIAL = "tutorial";
    public static final String LAB = "lab";
    public static final String EXAM = "exam";

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
        String[] dateTime;
        String taskDescription;
        LocalDate date;
        LocalTime time;
        String venue;
        String moduleCode;

        command = userInput.split(" ", 2);
        String commandType = command[0];

        switch (commandType) {
        case TODO:
            try {
                taskDescription = command[1].trim();

                if (taskDescription.isEmpty()) {
                    throw new DukeException("todo");
                } else {
                    calendarList.addTask(new Todo(taskDescription));
                }
            } catch (Exception e) {
                throw new DukeException("todo");
            }
            break;
        case DEADLINE:
            try {
                command = command[1].split("/by");
                taskDescription = command[0].trim();
                date = DateTimeParser.inputDateProcessor(command[1].trim());

                if (taskDescription.isEmpty()) {
                    throw new DukeException("deadline");
                } else {
                    calendarList.addTask(new Deadline(taskDescription, date));
                }
            } catch (Exception e) {
                throw new DukeException("deadline");
            }
            break;
        case ACTIVITY:
            /**
             * User input for Activity event example: run training /at 020202 1200 sentosa
             */
            try {
                command = command[1].split("/at");
                taskDescription = command[0].trim();
                String[] dateTimeVenue;
                dateTimeVenue = command[1].trim().split(" ", 3);
                date = DateTimeParser.inputDateProcessor(dateTimeVenue[0].trim());
                time = DateTimeParser.inputTimeProcessor(dateTimeVenue[1].trim());
                venue = dateTimeVenue[2].trim();


                if (taskDescription.isEmpty()) {
                    throw new DukeException("activity");
                } else {
                    calendarList.addEvent(new Activity(taskDescription, date, time, venue));
                }
            } catch (Exception e) {
                throw new DukeException("activity");
            }
            break;
        case EXAM:

            /**
             * User input for Exam task example: exam CS2113 BLK:EA LT2 /at 020202 1200
             */
            try {
                command = command[1].trim().split(" ", 2); // splits to CS2113 and BLK:EA LT2...
                moduleCode = command[0];
                command = command[1].split("/at");
                venue = command[0].trim();
                dateTime = command[1].trim().split(" ", 2);
                date = DateTimeParser.inputDateProcessor(dateTime[0].trim());
                time = DateTimeParser.inputTimeProcessor(dateTime[1].trim());


                if (moduleCode.isEmpty()) {
                    throw new DukeException("exam");
                } else {
                    calendarList.addEvent(new Exam(moduleCode, date, time, venue));
                }
            } catch (Exception e) {
                throw new DukeException("exam");
            }
            break;
        case LECTURE:
            /**
             * User input for Lecture task example: lecture CS2113 LT2 /at 020202 1200
             */
            try {
                command = command[1].trim().split(" ", 2); // splits to CS2113 and open book...
                moduleCode = command[0];
                command = command[1].split("/at");
                venue = command[0].trim();
                dateTime = command[1].trim().split(" ", 2);
                date = DateTimeParser.inputDateProcessor(dateTime[0].trim());
                time = DateTimeParser.inputTimeProcessor(dateTime[1].trim());


                if (moduleCode.isEmpty()) {
                    throw new DukeException("exam");
                } else {
                    calendarList.addEvent(new Lecture(moduleCode, date, time, venue));
                }
            } catch (Exception e) {
                throw new DukeException("lecture");
            }
            break;
        case TUTORIAL:
            try {
                command = command[1].split("/");
                taskDescription = command[0].trim();

                command = command[1].trim().split(" ");

                if (taskDescription.isEmpty()) {
                    throw new DukeException("tutorial");
                } else {
                    calendarList.addTask(new Tutorial(taskDescription, command[0], command[1]));
                }
            } catch (Exception e) {
                throw new DukeException("tutorial");
            }
            break;
        case LAB:
            try {
                command = command[1].split("/");
                taskDescription = command[0].trim();

                command = command[1].trim().split(" ");

                if (taskDescription.isEmpty()) {
                    throw new DukeException("lab");
                } else {
                    calendarList.addTask(new Lab(taskDescription, command[0], command[1]));
                }
            } catch (Exception e) {
                throw new DukeException("lab");
            }
            break;
        default:
            throw new DukeException("invalid command");
        }

        Ui.printAddTaskMessage(calendarList);
        storage.writeToFile(calendarList);
    }
}
