package seedu.revised.command.taskcommand;

import seedu.revised.exception.taskexception.TaskEventException;
import seedu.revised.card.taskcard.Event;
import seedu.revised.card.taskcard.Task;
import seedu.revised.list.TaskList;
import seedu.revised.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class AddEventCommand extends TaskCommand {
    private String fullCommand;

    public AddEventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }


    /**
     * Adds an instance of an <code>Event</code> class into a <code>TaskList</code>.
     *
     * @param taskList An instance of the <code>TaskList</code> class for the user to append to
     * @throws TaskEventException If there are no parameters written to initialise the creation of a new Event class
     */
    public void execute(TaskList taskList) throws TaskEventException {
        int startOfMessage = 6;
        int endOfMessage = fullCommand.indexOf("/at") - 1;
        int startOfAt = fullCommand.indexOf("/at") + 4;
        int endOfAt = fullCommand.length();
        if (endOfMessage <= startOfMessage) {
            throw new TaskEventException(Ui.EVENT_EXCEPTION);
        }
        String message = fullCommand.substring(startOfMessage, endOfMessage).strip();
        String at = fullCommand.substring(startOfAt, endOfAt);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm d-MM-yyyy");
        LocalDateTime dateTime = LocalDateTime.parse(at, format);


        if (message.isEmpty() || at.isEmpty()) {
            throw new TaskEventException(Ui.EVENT_EXCEPTION);
        }
        Task temp = new Event(message, false, dateTime);
        taskList.getList().add(temp);
        taskList.getList().sort(Comparator.comparing(Task::getDateTime));
        Ui.printTask(temp, taskList);
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
