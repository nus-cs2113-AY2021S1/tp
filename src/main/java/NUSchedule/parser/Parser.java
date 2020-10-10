package NUSchedule.Parser;


import NUSchedule.command.Command;
import NUSchedule.command.PrintFullListCommand;
import NUSchedule.Exception.DateFormatException;
import NUSchedule.Exception.DeleteNumberFormatException;
import NUSchedule.Exception.DoneNumberFormatException;
import NUSchedule.Exception.DukeException;
import NUSchedule.Exception.EmptyDeadlineException;
import NUSchedule.Exception.EmptyDeleteException;
import NUSchedule.Exception.EmptyDoneException;
import NUSchedule.Exception.EmptyEventException;
import NUSchedule.Exception.EmptyFindDateException;
import NUSchedule.Exception.EmptyFindException;
import NUSchedule.Exception.EmptyTodoException;
import NUSchedule.Exception.NoDeadlineTimeException;
import NUSchedule.Exception.NoDeadlineTimeMarkerException;
import NUSchedule.Exception.NoEventTimeException;
import NUSchedule.Exception.NoEventTimeMakerException;
import NUSchedule.Exception.TimeFormatException;
import NUSchedule.Exception.WrongCommandException;
import NUSchedule.Task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * This class contains one function -- parse, to call the respective command function according to the user input.
 */
public abstract class Parser {
    public static final String EXIT = "bye";
    public static final String PRINT_TASK_LIST = "list";
    public static final String TASK_DONE = "done";
    public static final String ADD_EVENT = "event";
    public static final String ADD_DEADLINE = "deadline";
    public static final String ADD_TODO = "todo";
    public static final String TASK_DELETE = "delete";
    public static final String TASK_FIND = "find";
    public static final String BY = "/by";
    public static final String SINGLE_SPACE = " ";
    public static final String AT = "/at";
    private static final String TASK_FIND_DATE = "date";

    /**
     * This function calls the correct command the user want to perform, by returning a <\code>Command</\code> object.
     *
     * @param fullCommand the full string of user input
     * @return the specific <\code>Command</\code> object to perform what the user want to do
     * @throws DukeException includes all exceptions may happen during parsing
     */
    public static Command parse(String fullCommand) throws DukeException {
        // this block deals with exit and list command
        if (fullCommand.equals(EXIT)) {
            return new ExitCommand();
        } else if (fullCommand.equals(PRINT_TASK_LIST)) {
            return new PrintFullListCommand();
        }

        String[] words = fullCommand.split(SINGLE_SPACE);

        //this block deals with find command
        if (words[0].equals(TASK_FIND)) {
            if (fullCommand.substring(4).isBlank()) {
                throw new EmptyFindException();
            }
            return new FindCommand(fullCommand.substring(5));
        }
        
        //this block deals with find date command
        if (words[0].equals(TASK_FIND_DATE)) {
            if (fullCommand.substring(4).isBlank()) {
                throw new EmptyFindDateException();
            }
            try {
                return new FindDateCommand(LocalDate.parse(fullCommand.substring(5)));
            } catch (DateTimeParseException e) {
                throw new DateFormatException();
            }
        }

        int taskIndex;//to indicate what is the task we are dealing with. may not be used.

        //this block deals with done command
        if (words[0].equals(TASK_DONE)) {
            if (fullCommand.substring(4).isBlank()) {
                throw new EmptyDoneException();
            }
            try {
                taskIndex = Integer.parseInt(fullCommand.substring(5)) - 1;
            } catch (NumberFormatException e) {
                throw new DoneNumberFormatException();
            }
            return new DoneCommand(taskIndex);
        }

        //this block deals with delete command
        if (words[0].equals(TASK_DELETE)) {
            if (fullCommand.substring(6).isBlank()) {
                throw new EmptyDeleteException();
            }
            try {
                taskIndex = Integer.parseInt(fullCommand.substring(7)) - 1;
            } catch (NumberFormatException e) {
                throw new DeleteNumberFormatException();
            }
            return new DeleteCommand(taskIndex);
        }

        //this block deals with add command
        //we shall check that the user input is not meant for any other command beforehand
        //because the default block will throw an exception.
        // i.e. when enter this block, the parser will not go to any other blocks
        int dividerPosition;
        int timeDivider;
        String dateTime;
        switch (words[0]) {
            case ADD_EVENT:
                dividerPosition = fullCommand.indexOf(AT);

                if (fullCommand.substring(5).isBlank()) {
                    throw new EmptyEventException();
                }
                if (dividerPosition == -1) {
                    throw new NoEventTimeMakerException();
                }
                if (fullCommand.substring(5, dividerPosition).isBlank()) {
                    throw new EmptyEventException();
                }
                try {
                    fullCommand.substring(dividerPosition + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new NoEventTimeException();
                }
                try {
                    timeDivider = fullCommand.substring(dividerPosition + 4).indexOf(SINGLE_SPACE);
                    dateTime = fullCommand.substring(dividerPosition + 4, dividerPosition + 4 + timeDivider)
                            + "T"
                            + fullCommand.substring(dividerPosition + 4 + timeDivider + 1);
                    return new AddCommand(new NUSchedule.Task.PersonalEvent(fullCommand.substring(6, dividerPosition), LocalDateTime.parse(dateTime)));
                } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                    throw new TimeFormatException();
                }


            case ADD_DEADLINE:
                dividerPosition = fullCommand.indexOf(BY);

                if (fullCommand.substring(8).isBlank()) {
                    throw new EmptyDeadlineException();
                }
                if (dividerPosition == -1) {
                    throw new NoDeadlineTimeMarkerException();
                }
                if (fullCommand.substring(8, dividerPosition).isBlank()) {
                    throw new EmptyDeadlineException();
                }
                try {
                    fullCommand.substring(dividerPosition + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new NoDeadlineTimeException();
                }
                try {
                    timeDivider = fullCommand.substring(dividerPosition + 4).indexOf(SINGLE_SPACE);
                    dateTime = fullCommand.substring(dividerPosition + 4, dividerPosition + 4 + timeDivider)
                            + "T"
                            + fullCommand.substring(dividerPosition + 4 + timeDivider + 1);
                    return new AddCommand(new NUSchedule.Task.Assignment(fullCommand.substring(9, dividerPosition), LocalDateTime.parse(dateTime)));
                } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                    throw new TimeFormatException();
                }

            case ADD_TODO:

                try {
                    if (fullCommand.substring(5).isBlank()) {
                        throw new EmptyTodoException();
                    }
                    return new AddCommand(new Todo(fullCommand.substring(5)));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new EmptyTodoException();
                }

            default:
                throw new WrongCommandException();

        }

    }
}
