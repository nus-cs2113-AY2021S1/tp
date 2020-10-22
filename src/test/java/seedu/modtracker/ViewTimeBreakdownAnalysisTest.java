package seedu.modtracker;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ViewTimeBreakdownAnalysisTest {
    private final ViewTimeBreakdownAnalysis view = new ViewTimeBreakdownAnalysis();

    @Test
    void printBreakdown_listOfModulesWithNoValidInput_printNoValidInputsReturnFalse() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();

        ModuleList list = new ModuleList();
        Module module1 = new Module("CS1231", "8");
        Module module2 = new Module("CS2113t", "10");
        list.getData().add(module1);
        list.getData().add(module2);

        boolean output = view.printBreakdown(list, 2);
        assertFalse(output);

        String expected = ViewTimeBreakdownAnalysis.NO_VALID_INPUTS + System.lineSeparator() + System.lineSeparator();
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printBreakdown_listOfModulesWithValidInputButNoTimeSpent_printTimeSpentEqualsZeroReturnFalse() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();

        ModuleList list = new ModuleList();
        Module module = new Module("CS1231", "8");
        module.addActualTime("0", "2");
        list.getData().add(module);

        boolean output = view.printBreakdown(list, 2);
        assertFalse(output);

        String expected = "Total time spent: 0.0 H" + System.lineSeparator()
                + ViewTimeBreakdownAnalysis.NO_TIME_SPENT + System.lineSeparator()
                + System.lineSeparator();
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printBreakdown_listOfModulesWithAllPossibleinputs_ReturnTrue() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();

        ModuleList list = new ModuleList();
        Module module1 = new Module("CS1231", "8");
        module1.addActualTime("5", "2");
        list.getData().add(module1);

        Module module2 = new Module("CS2113t", "8");
        module2.addActualTime("0", "2");
        list.getData().add(module2);

        Module module3 = new Module("CS2101", "8");
        list.getData().add(module3);

        boolean output = view.printBreakdown(list, 2);
        assertTrue(output);

        String expected = "Total time spent: 5.0 H"
                + System.lineSeparator() + "100% of time is spent on CS1231"
                + System.lineSeparator() + "Seems like you didn't spend any time on CS2113t this week."
                + System.lineSeparator() + "No input for CS2101"
                + System.lineSeparator() + System.lineSeparator();
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printTime() {
    }

    @Test
    void printAnalysis() {
    }
}