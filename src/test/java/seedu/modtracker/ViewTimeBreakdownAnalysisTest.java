package seedu.modtracker;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ViewTimeBreakdownAnalysisTest {
    private final ViewTimeBreakdownAnalysis view = new ViewTimeBreakdownAnalysis();

    @Test
    void printBreakdown_modulesWithNoValidInput_printNoValidInputsReturnFalse() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();

        ModuleList list = new ModuleList();
        list.getData().clear();
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
    void printBreakdown_modulesWithValidInputButNoTimeSpent_printTimeSpentEqualsZeroReturnFalse() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();

        ModuleList list = new ModuleList();
        list.getData().clear();
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
    void printBreakdown_modulesWithTimeEqualsZeroTimeEqualsFiveAndInvalidTime_ReturnTrue() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();

        ModuleList list = new ModuleList();
        list.getData().clear();
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
                + System.lineSeparator() + "No expected workload for CS2101"
                + System.lineSeparator() + System.lineSeparator();
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printAnalysis_modulesWithInvalidInput_noInput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();

        ModuleList list = new ModuleList();
        list.getData().clear();
        Module module1 = new Module("CS1231", "8");
        Module module2 = new Module("CS2113t", "10");
        list.getData().add(module1);
        list.getData().add(module2);

        view.printAnalysis(list, 2);

        String expected = ViewTimeBreakdownAnalysis.ANALYSIS + System.lineSeparator();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void printAnalysis_modulesWithTooLittleTimeSpent_tooLitteTimeSpent() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();

        ModuleList list = new ModuleList();
        list.getData().clear();

        Module module1 = new Module("CS1231", "8");
        module1.addActualTime("0", "2");
        list.getData().add(module1);

        Module module2 = new Module("CS2113t", "8");
        module2.addActualTime("5.55", "2");
        list.getData().add(module2);

        view.printAnalysis(list, 2);

        String expected = ViewTimeBreakdownAnalysis.ANALYSIS + System.lineSeparator()
                + "CS1231" + System.lineSeparator()
                + ViewTimeBreakdownAnalysis.TOO_LITTLE_TIME_SPENT + System.lineSeparator()
                + System.lineSeparator() + "CS2113t" + System.lineSeparator()
                + ViewTimeBreakdownAnalysis.TOO_LITTLE_TIME_SPENT + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void printAnalysis_modulesWithJustRightTimeSpent_justRightTimeSpent() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();

        ModuleList list = new ModuleList();
        list.getData().clear();

        Module module1 = new Module("CS1231", "8");
        module1.addActualTime("10.39", "2");
        list.getData().add(module1);

        Module module2 = new Module("CS2113t", "8");
        module2.addActualTime("5.6", "2");
        list.getData().add(module2);

        view.printAnalysis(list, 2);

        String expected = ViewTimeBreakdownAnalysis.ANALYSIS + System.lineSeparator()
                + "CS1231" + System.lineSeparator()
                + ViewTimeBreakdownAnalysis.JUST_RIGHT + System.lineSeparator()
                + System.lineSeparator() + "CS2113t" + System.lineSeparator()
                + ViewTimeBreakdownAnalysis.JUST_RIGHT + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void printAnalysis_modulesWithTooMuchTimeSpent_tooMuchTimeSpent() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();

        ModuleList list = new ModuleList();
        list.getData().clear();

        Module module1 = new Module("CS1231", "8");
        module1.addActualTime("10.4", "2");
        list.getData().add(module1);

        Module module2 = new Module("CS2113t", "8");
        module2.addActualTime("15", "2");
        list.getData().add(module2);

        view.printAnalysis(list, 2);

        String expected = ViewTimeBreakdownAnalysis.ANALYSIS + System.lineSeparator()
                + "CS1231" + System.lineSeparator()
                + ViewTimeBreakdownAnalysis.TOO_MUCH_TIME_SPENT + System.lineSeparator()
                + System.lineSeparator() + "CS2113t" + System.lineSeparator()
                + ViewTimeBreakdownAnalysis.TOO_MUCH_TIME_SPENT + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void validateInputs_weekNumberLessThanMinListNotEmpty_returnFalse() {
        ModuleList list = new ModuleList();
        list.getData().clear();

        Module module1 = new Module("CS1231", "8");
        list.getData().add(module1);

        boolean output1 = view.validateInputs(list, 0);
        boolean output2 = view.validateInputs(list, -1);

        assertFalse(output1);
        assertFalse(output2);
    }

    @Test
    void validateInputs_weekNumberMoreThanMaxListNotEmpty_returnFalse() {
        ModuleList list = new ModuleList();
        list.getData().clear();

        Module module1 = new Module("CS1231", "8");
        list.getData().add(module1);

        boolean output1 = view.validateInputs(list, 14);

        assertFalse(output1);
    }

    @Test
    void validateInputs_weekNumberValidListNotEmpty_returnTrue() {
        ModuleList list = new ModuleList();
        list.getData().clear();

        Module module1 = new Module("CS1231", "8");
        list.getData().add(module1);

        boolean output1 = view.validateInputs(list, 7);

        assertTrue(output1);
    }

    @Test
    void validateInputs_weekNumberValidListIsEmpty_returnFalse() {
        ModuleList list = new ModuleList();
        list.getData().clear();

        boolean output1 = view.validateInputs(list, 7);

        assertFalse(output1);
    }

    @Test
    void printTime_actualTimeExistsExpectedWorkloadExist_barGraphForActualTimeAndExpectedWorkload() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();

        ModuleList list = new ModuleList();
        list.getData().clear();

        Module module1 = new Module("CS1231", "5");
        module1.addActualTime("3.4", "2");
        list.getData().add(module1);

        view.printTime(list, 2);

        String expected = ViewTimeBreakdownAnalysis.MODULE_WEEK + "2" + System.lineSeparator()
                + System.lineSeparator() + "CS1231" + System.lineSeparator()
                + ViewTimeBreakdownAnalysis.ACTUAL + "███▌ 3.4"
                + System.lineSeparator()
                + ViewTimeBreakdownAnalysis.EXPECTED + "█████ 5.0" + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void printTime_actualTimeDoesNotExistExpectedWorkloadExist_noInputForActualTimeAndBarGraphForExpectedWorkload() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();

        ModuleList list = new ModuleList();
        list.getData().clear();

        Module module1 = new Module("CS1231", "5");
        list.getData().add(module1);

        view.printTime(list, 2);

        String expected = ViewTimeBreakdownAnalysis.MODULE_WEEK + "2" + System.lineSeparator()
                + System.lineSeparator() + "CS1231" + System.lineSeparator()
                + ViewTimeBreakdownAnalysis.ACTUAL + ViewTimeBreakdownAnalysis.NO_INPUT
                + System.lineSeparator()
                + ViewTimeBreakdownAnalysis.EXPECTED + "█████ 5.0" + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void printTime_actualTimeExistsNoExpectedWorkload_barGraphForActualTimeAndNoInputForExpectedWorkload() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();

        ModuleList list = new ModuleList();
        list.getData().clear();

        Module module1 = new Module("CS1231");
        module1.addActualTime("3.4", "2");
        list.getData().add(module1);

        view.printTime(list, 2);

        String expected = ViewTimeBreakdownAnalysis.MODULE_WEEK + "2" + System.lineSeparator()
                + System.lineSeparator() + "CS1231" + System.lineSeparator()
                + ViewTimeBreakdownAnalysis.ACTUAL + "███▌ 3.4"
                + System.lineSeparator()
                + ViewTimeBreakdownAnalysis.EXPECTED + ViewTimeBreakdownAnalysis.NO_INPUT + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expected, outContent.toString());
    }
}