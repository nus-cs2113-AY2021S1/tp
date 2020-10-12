package seedu.modtracker;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ModViewTest {
    //naming : methodUnderTest_inputGiven_expectedOutput

    @Test
    void printAllModuleInformation_emptyList_emptyModuleList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ModView view = new ModView();
        ModuleList emptyList = new ModuleList();
        view.printAllModuleInformation(emptyList, 1);
        assertEquals(ModView.EMPTY_MODULE_LIST + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    void printAllModuleInformation_invalidWeekNumber_invalidWeekNumber() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ModView view = new ModView();
        ModuleList emptyList = new ModuleList();

        view.printAllModuleInformation(emptyList, 0);
        assertEquals(ModView.INVALID_WEEK_NUMBER + System.lineSeparator(),
                outContent.toString());

        outContent.reset();
        view.printAllModuleInformation(emptyList, -1);
        assertEquals(ModView.INVALID_WEEK_NUMBER + System.lineSeparator(),
                outContent.toString());

        outContent.reset();
        view.printAllModuleInformation(emptyList, 14);
        assertEquals(ModView.INVALID_WEEK_NUMBER + System.lineSeparator(),
                outContent.toString());

    }
    
}