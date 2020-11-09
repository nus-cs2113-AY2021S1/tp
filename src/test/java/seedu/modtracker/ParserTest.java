package seedu.modtracker;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    ModuleList modList = new ModuleList();
    TaskList taskList = new TaskList();
    Parser parser = new Parser();


    @Test
    public void parse_invalidInput_expectPrintInvalid() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input1 = "exit addmod ";
        parser.parse(input1, modList, null, null, true, null);
        String expected = Ui.INVALID_COMMAND + System.lineSeparator() + Ui.ENTER_HELP + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());

        outContent.reset();
        String input2 = "addexps";
        parser.parse(input2, modList, null, null, true, null);
        assertEquals(expected + System.lineSeparator(), outContent.toString());

        outContent.reset();
        String input3 = "help 1";
        parser.parse(input3, modList, null, null, true, null);
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_blankInput_expectPrintInvalid() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "";
        parser.parse(input, modList, null, null, true, null);
        String expected = Ui.INVALID_COMMAND + System.lineSeparator() + Ui.ENTER_HELP + System.lineSeparator();

        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_exitInput_expectPrintExit() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "exit";
        String name = "Lee";
        parser.parse(input, modList, name, null, true, null);
        String expected = "All changes saved." + System.lineSeparator() + Ui.BYE_LOGO + System.lineSeparator()
                + "Bye " + name + ". Hope to see you again soon!" + System.lineSeparator();

        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_exitInput_expectExitStatusTrue() {
        String input = "exit";
        parser.parse(input, modList, null, null, true, null);
        assertTrue(parser.isExit());
    }

    @Test
    public void parse_anyInput_expectExitStatusFalse() {
        String input = "addexp ";
        parser.parse(input, modList, null, null, true, null);
        assertFalse(parser.isExit());
    }

    @Test
    public void parse_anyInput_expectRestartStatusFalse() {
        String input = "addmod ";
        parser.parse(input, modList, null, null, true, null);
        assertFalse(parser.isRestart());
    }

    @Test
    public void parse_inputHelp_expectPrintHelpList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "Help";
        parser.parse(input, modList, null, null, true, null);
        String expected = Ui.HELP_LIST;
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_invalidEditTimeInput_expectPrintWrongFormat() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "edittime hello";
        parser.parse(input, modList, null, null, true, null);
        String expected = Ui.WRONG_FORMAT + System.lineSeparator()
                + "Format: edittime <module code> <actual time spent> <week number>"
                + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_invalidOpenInput_expectPrintWrongFormat() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "open 1";
        parser.parse(input, modList, null, null, true, null);
        String expected = Ui.INVALID_COMMAND + System.lineSeparator() + Ui.ENTER_HELP
                + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_invalidAddTimeInput_expectPrintWrongFormat() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "addtime  ";
        parser.parse(input, modList, null, null, true, null);
        String expected = Ui.WRONG_FORMAT + System.lineSeparator()
                + "Format: addtime <module code> <actual time spent> <week number>"
                + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_invalidDeleteTimeInput_expectPrintWrongFormat() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "deletetime";
        parser.parse(input, modList, null, null, true, null);
        String expected = "Please type deletetime <module code> <week number> "
                + "with week number as a whole number between 1 and 13."
                + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_invalidListInput_expectPrintWrongFormat() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "list";
        parser.parse(input, modList, null, null, true, null);
        String expected = Ui.WRONG_FORMAT + System.lineSeparator()
                + "Format: list <week number>"
                + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_listInput_expectEmptyList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "list 1";
        modList.clear();
        parser.parse(input, modList, null, null, true, null);
        String expected = ModView.EMPTY_MODULE_LIST + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_invalidMinusTimeInput_expectPrintWrongFormat() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "minustime  ";
        parser.parse(input, modList, null, null, true, null);
        String expected = Ui.WRONG_FORMAT + System.lineSeparator()
                + "Format: minustime <module code> <time> <week number>"
                + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_invalidAnalyseInput_expectPrintWrongFormat() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "Analyse ";
        parser.parse(input, modList, null, null, true, null);
        String expected = Ui.WRONG_FORMAT + System.lineSeparator()
                + "Format: analyse <week number>"
                + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_analyseInput_expectEmptyList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modList.clear();
        String input = "analyse 2";
        parser.parse(input, modList, null, null, true, null);
        String expected = ModView.EMPTY_MODULE_LIST + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_listTaskInput_expectEmptyTaskList() {
        TaskList.tasks.clear();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "listtask ";
        parser.parse(input, modList, null, null, true, taskList);
        String expected = "The current task list is empty." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }
    
    @Test
    public void parse_invalidDoneInput_expectPrintWrongFormat() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "done";
        parser.parse(input, modList, null, null, true, taskList);
        String expected = Ui.WRONG_FORMAT + System.lineSeparator()
                + "Format: done <task number>"
                + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_doneInput_expectEmptyTaskList() {
        TaskList.tasks.clear();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "done 1";
        parser.parse(input, modList, null, null, true, taskList);
        String expected = "The current task list is empty." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_invalidDeleteTaskInput_expectPrintWrongFormat() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "deletetask";
        parser.parse(input, modList, null, null, true, taskList);
        String expected = Ui.WRONG_FORMAT + System.lineSeparator()
                + "Format: deletetask <task number>"
                + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_deleteTaskInput_expectEmptyTaskList() {
        TaskList.tasks.clear();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "deletetask 1";
        parser.parse(input, modList, null, null, true, taskList);
        String expected = "The current task list is empty." + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void parse_invalidAddTaskInput_expectPrintWrongFormat() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "addtask";
        parser.parse(input, modList, null, null, true, taskList);
        String expected = Ui.WRONG_FORMAT + System.lineSeparator()
                + "Format: addtask <module code> <task description>"
                + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
    }
}
