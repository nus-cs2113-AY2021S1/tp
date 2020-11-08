package seedu.quotesify.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class ToDoListTest {
    private ToDoList toDoList;
    private ToDo toDo1;
    private ToDo toDo2;
    private ToDo toDo3;

    private String task1Name = "Return Harry Potter";
    private String task1Deadline = "tmr 2pm";
    private String task2Name = "Review Fantastic Beasts";
    private String task2Deadline = "2020-11-09";
    private String task3Name = "Finish reading Chapter 3 of Harry Potter";
    private String task3Deadline = "2020-10-20";


    @BeforeEach
    void setToDo() {
        toDoList = new ToDoList();

        toDo1 = new ToDo(task1Name, task1Deadline);
        toDo2 = new ToDo(task2Name, task2Deadline);
        toDo3 = new ToDo(task3Name, task3Deadline);

        toDoList.add(toDo1);
        toDoList.add(toDo2);
        toDoList.add(toDo3);
    }

    @Test
    public void printQuoteListBeforeFormatting() {
        String param = "1. " + toDo1.toString() + System.lineSeparator()
                + "2. " + toDo2.toString() + System.lineSeparator()
                + "3. " + toDo3.toString() + System.lineSeparator();
        assertEquals(param, toDoList.toString());
    }

    @Test
    public void printQuoteListAfterFormatting() {
        toDo1.updateDateFormat();
        toDo2.updateDateFormat();
        toDo3.updateDateFormat();
        toDoList.sortByDate();

        String param = "1. " + toDo3.toString() + System.lineSeparator()
                + "2. " + toDo2.toString() + System.lineSeparator()
                + "3. " + toDo1.toString() + System.lineSeparator();
        assertEquals(param, toDoList.toString());
    }
}