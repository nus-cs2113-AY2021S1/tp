package seedu.revised.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.revised.card.taskcard.Deadline;
import seedu.revised.card.taskcard.Event;
import seedu.revised.card.taskcard.Todo;
import seedu.revised.command.taskcommand.AddDeadlineCommand;
import seedu.revised.command.taskcommand.AddEventCommand;
import seedu.revised.command.taskcommand.AddTodoCommand;
import seedu.revised.command.taskcommand.DeleteTaskCommand;
import seedu.revised.command.taskcommand.DoneTaskCommand;
import seedu.revised.command.taskcommand.FindTaskCommand;
import seedu.revised.exception.taskexception.TaskDeadlineException;
import seedu.revised.exception.taskexception.TaskEventException;
import seedu.revised.exception.taskexception.TaskTodoException;
import seedu.revised.list.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskCommandTest {
    private TaskList tasks;
    private AddTodoCommand addTodoCommand;
    private AddDeadlineCommand addDeadlineCommand;
    private AddEventCommand addEventCommand;
    private DeleteTaskCommand deleteCommand;
    private FindTaskCommand findCommand;
    private DoneTaskCommand doneCommand;

    @BeforeEach
    void setUp() {
        String by = "23:59 11-11-2020";
        String at = "12:00 13-11-2020";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm d-MM-yyyy");
        LocalDateTime dateTime = LocalDateTime.parse(by, format);
        LocalDateTime dateTime2 = LocalDateTime.parse(at, format);
        tasks = new TaskList(
                new ArrayList<>(List.of(
                        new Todo("Revise Speed",false),
                        new Deadline("Project",false,dateTime),
                        new Event("Exam",false,dateTime2)
                )));
    }

    @Test
    public void addDeadlineCommand_validCommand_returnsDeadlineString()
            throws TaskDeadlineException {
        addDeadlineCommand = new AddDeadlineCommand("deadline Report /by 23:59 20-10-2020");
        addDeadlineCommand.execute(tasks);
        assertEquals("[D][\u2718] Report (by: 11:59 PM 20 Oct 2020)", tasks.getList().get(0).toString());
    }

    @Test
    public void addDeadlineCommand_missingDescription_throwsException() {
        addDeadlineCommand = new AddDeadlineCommand("deadline Report /by");
        assertThrows(StringIndexOutOfBoundsException.class, () -> addDeadlineCommand.execute(tasks));
    }

    @Test
    public void addDeadlineCommand_noDescription_throwsException() {
        addDeadlineCommand = new AddDeadlineCommand("deadline");
        assertThrows(TaskDeadlineException.class, () -> addDeadlineCommand.execute(tasks));
    }

    @Test
    public void addEventCommand_validCommand_returnsEventString()
            throws TaskEventException {
        addEventCommand = new AddEventCommand("event Finals /at 14:00 21-11-2020");
        addEventCommand.execute(tasks);
        assertEquals("[E][\u2718] Finals (at: 2:00 PM 21 Nov 2020)", tasks.getList().get(2).toString());
    }

    @Test
    public void addEventCommand_missingDescription_throwsException() {
        addEventCommand = new AddEventCommand("event Report /at");
        assertThrows(StringIndexOutOfBoundsException.class, () -> addEventCommand.execute(tasks));
    }

    @Test
    public void addEventCommand_noDescription_throwsException() {
        addEventCommand = new AddEventCommand("event");
        assertThrows(TaskEventException.class, () -> addEventCommand.execute(tasks));
    }

    @Test
    public void addTodoCommand_validCommand_returnsTodoString()
            throws TaskTodoException {
        addTodoCommand = new AddTodoCommand("Todo Draw mindmap of speed topic");
        addTodoCommand.execute(tasks);
        assertEquals("[T][\u2718] Draw mindmap of speed topic", tasks.getList().get(3).toString());
    }

    @Test
    public void addTodoCommand_noDescription_throwsException() {
        addTodoCommand = new AddTodoCommand("todo");
        assertThrows(TaskTodoException.class, () -> addTodoCommand.execute(tasks));
    }

    @Test
    public void addTodoCommand_missingDescription_throwsException() {
        addTodoCommand = new AddTodoCommand("todo ");
        assertThrows(TaskTodoException.class, () -> addTodoCommand.execute(tasks));
    }

    @Test
    public void deleteTaskCommand_validCommand_returnsIndex() {
        deleteCommand = new DeleteTaskCommand("delete 2");
        deleteCommand.execute(tasks);
        assertEquals(Integer.valueOf("2"), tasks.getList().size());
    }

    @Test
    public void deleteTaskCommand_InputNonIntegerAsIndex_throwsException() {
        deleteCommand = new DeleteTaskCommand("delete Revise Speed");
        assertThrows(NumberFormatException.class, () -> deleteCommand.execute(tasks));
    }

    @Test
    public void doneTaskCommand_validCommand_returnsIndex() {
        doneCommand = new DoneTaskCommand("done 2");
        doneCommand.execute(tasks);
        assertEquals("[E][\u2718] Exam (at: 12:00 PM 13 Nov 2020)", tasks.getList().get(2).toString());
    }

    @Test
    public void doneTaskCommand_InputNonIntegerAsIndex_throwsException() {
        doneCommand = new DoneTaskCommand("done Revise Speed");
        assertThrows(NumberFormatException.class, () -> doneCommand.execute(tasks));
    }
}
