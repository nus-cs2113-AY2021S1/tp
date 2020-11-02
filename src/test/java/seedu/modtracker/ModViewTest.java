package seedu.modtracker;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModViewTest {
    private final ModView view = new ModView();

    @Test
    void printAllModuleInformation_emptyList_emptyModuleList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        outContent.reset();
        ModuleList emptyList = new ModuleList();
        emptyList.getData().clear();
        view.printAllModuleInformation(emptyList, 1);
        assertEquals(ModView.EMPTY_MODULE_LIST + System.lineSeparator()
                + System.lineSeparator(), outContent.toString());
    }

    @Test
    void printAllModuleInformation_invalidWeekNumber_invalidWeekNumber() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        outContent.reset();
        ModuleList emptyList = new ModuleList();
        emptyList.getData().clear();

        view.printAllModuleInformation(emptyList, 0);
        assertEquals(ModView.INVALID_WEEK_NUMBER + System.lineSeparator()
                + System.lineSeparator(), outContent.toString());

        outContent.reset();
        view.printAllModuleInformation(emptyList, -1);
        assertEquals(ModView.INVALID_WEEK_NUMBER + System.lineSeparator()
                + System.lineSeparator(), outContent.toString());

        outContent.reset();
        view.printAllModuleInformation(emptyList, 14);
        assertEquals(ModView.INVALID_WEEK_NUMBER + System.lineSeparator()
                + System.lineSeparator(), outContent.toString());

    }

    @Test
    void printAllModuleInformation_6CharLongModuleCode_tableAdjustedTo6Char() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        outContent.reset();
        ModuleList filledList = new ModuleList();
        ArrayList<Module> modList = filledList.getData();
        modList.clear();
        modList.add(new Module("CS1231"));

        String expected = ModView.FIRST_PART_OF_BORDER + ModView.SECOND_PART_OF_BORDER
                + ModView.FIRST_PART_OF_HEADER + ModView.SECOND_PART_OF_HEADER
                + ModView.FIRST_PART_OF_BORDER + ModView.SECOND_PART_OF_BORDER
                + "|  01  | CS1231 | No Input | No Input |\n"
                + ModView.FIRST_PART_OF_BORDER + ModView.SECOND_PART_OF_BORDER;
        view.printAllModuleInformation(filledList, 1);
        assertEquals(expected + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    void printAllModuleInformation_7CharLongModuleCode_tableAdjustedTo7Char() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        outContent.reset();
        ModuleList filledList = new ModuleList();
        ArrayList<Module> modList = filledList.getData();
        modList.clear();
        modList.add(new Module("GER1000"));
        modList.add(new Module("CS1231"));

        String expected = ModView.FIRST_PART_OF_BORDER + "-" + ModView.SECOND_PART_OF_BORDER
                + ModView.FIRST_PART_OF_HEADER + " " + ModView.SECOND_PART_OF_HEADER
                + ModView.FIRST_PART_OF_BORDER + "-" + ModView.SECOND_PART_OF_BORDER
                + "|  01  | GER1000 | No Input | No Input |\n"
                + ModView.FIRST_PART_OF_BORDER + "-" + ModView.SECOND_PART_OF_BORDER
                + "|  01  | CS1231  | No Input | No Input |\n"
                + ModView.FIRST_PART_OF_BORDER + "-" + ModView.SECOND_PART_OF_BORDER;
        view.printAllModuleInformation(filledList, 1);
        assertEquals(expected + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    void printAllModuleInformation_8CharLongModuleCode_tableAdjustedTo8Char() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        outContent.reset();
        ModuleList filledList = new ModuleList();
        ArrayList<Module> modList = filledList.getData();
        modList.clear();
        modList.add(new Module("GER1000H"));
        modList.add(new Module("CS1231"));

        String expected = ModView.FIRST_PART_OF_BORDER + "--" + ModView.SECOND_PART_OF_BORDER
                + ModView.FIRST_PART_OF_HEADER + "  " + ModView.SECOND_PART_OF_HEADER
                + ModView.FIRST_PART_OF_BORDER + "--" + ModView.SECOND_PART_OF_BORDER
                + "|  01  | GER1000H | No Input | No Input |\n"
                + ModView.FIRST_PART_OF_BORDER + "--" + ModView.SECOND_PART_OF_BORDER
                + "|  01  | CS1231   | No Input | No Input |\n"
                + ModView.FIRST_PART_OF_BORDER + "--" + ModView.SECOND_PART_OF_BORDER;
        view.printAllModuleInformation(filledList, 1);
        assertEquals(expected + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    void printAllModuleInformation_hasExpectedWorkload_displaysExpectedWorkLoad() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        outContent.reset();
        ModuleList filledList = new ModuleList();
        ArrayList<Module> modList = filledList.getData();
        modList.clear();
        modList.add(new Module("GER1000H", "8"));

        String expected = ModView.FIRST_PART_OF_BORDER + "--" + ModView.SECOND_PART_OF_BORDER
                + ModView.FIRST_PART_OF_HEADER + "  " + ModView.SECOND_PART_OF_HEADER
                + ModView.FIRST_PART_OF_BORDER + "--" + ModView.SECOND_PART_OF_BORDER
                + "|  01  | GER1000H |    8.0   | No Input |\n"
                + ModView.FIRST_PART_OF_BORDER + "--" + ModView.SECOND_PART_OF_BORDER;
        view.printAllModuleInformation(filledList, 1);
        assertEquals(expected + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    void printAllModuleInformation_hasActualWorkload_displaysActualWorkLoad() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        outContent.reset();
        ModuleList filledList = new ModuleList();
        ArrayList<Module> modList = filledList.getData();
        modList.clear();
        Module mod = new Module("GER1000H");
        mod.addActualTime("8", "1");
        modList.add(mod);


        String expected = ModView.FIRST_PART_OF_BORDER + "--" + ModView.SECOND_PART_OF_BORDER
                + ModView.FIRST_PART_OF_HEADER + "  " + ModView.SECOND_PART_OF_HEADER
                + ModView.FIRST_PART_OF_BORDER + "--" + ModView.SECOND_PART_OF_BORDER
                + "|  01  | GER1000H | No Input |    8.0   |\n"
                + ModView.FIRST_PART_OF_BORDER + "--" + ModView.SECOND_PART_OF_BORDER;
        view.printAllModuleInformation(filledList, 1);
        assertEquals(expected + System.lineSeparator(),
                outContent.toString());
    }
}
