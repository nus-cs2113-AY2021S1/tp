package seedu.duke.command;

import seedu.duke.DateTimeParser;
import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.event.Activity;
import seedu.duke.calendar.task.Deadline;
import seedu.duke.calendar.task.Exam;
import seedu.duke.calendar.task.Lab;
import seedu.duke.calendar.task.Lecture;
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
    public static final String EVENT = "event";
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
        LocalDate taskDate;
        LocalTime taskTime;
        String venue;

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
                taskDate = DateTimeParser.inputDateProcessor(command[1].trim());

                if (taskDescription.isEmpty()) {
                    throw new DukeException("deadline");
                } else {
                    calendarList.addTask(new Deadline(taskDescription, taskDate));
                }
            } catch (Exception e) {
                throw new DukeException("deadline");
            }
            break;
        case EVENT:
            try {
                command = command[1].split("/at");
                taskDescription = command[0].trim();
                taskDate = DateTimeParser.inputDateProcessor(command[1].trim());

                if (taskDescription.isEmpty()) {
                    throw new DukeException("event");
                } else {
                    calendarList.addTask(new Activity(taskDescription, taskDate));
                }
            } catch (Exception e) {
                throw new DukeException("event");
            }
            break;
        case EXAM:
            String moduleCode;
            String examDetails;
            /**
             * User input for Exam task example: exam CS2113 open book /at 020202 1200
             */
            try {
                command = command[1].trim().split(" ", 2); // splits to CS2113 and open book...
                moduleCode = command[0];
                command = command[1].split("/at");
                examDetails = command[0].trim();
                dateTime = command[1].trim().split(" ", 2);
                taskDate = DateTimeParser.inputDateProcessor(dateTime[0].trim());
                taskTime = DateTimeParser.inputTimeProcessor(dateTime[1].trim());


                if (moduleCode.isEmpty()) {
                    throw new DukeException("exam");
                } else {
                    calendarList.addTask(new Exam(moduleCode, examDetails, taskDate, taskTime));
                }
            } catch (Exception e) {
                throw new DukeException("exam");
            }
            break;
        case LECTURE:
            /**
             * User input for Exam task example: exam CS2113 open book /at 020202 1200
             */
            try {
                command = command[1].trim().split(" ", 2); // splits to CS2113 and open book...
                moduleCode = command[0];
                command = command[1].split("/at");
                venue = command[0].trim();
                dateTime = command[1].trim().split(" ", 2);
                taskDate = DateTimeParser.inputDateProcessor(dateTime[0].trim());
                taskTime = DateTimeParser.inputTimeProcessor(dateTime[1].trim());


                if (moduleCode.isEmpty()) {
                    throw new DukeException("exam");
                } else {
                    calendarList.addEvent(new Lecture(moduleCode, taskDate, taskTime, venue));
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
