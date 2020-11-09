package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.resources.ModuleChecker;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleChecherTest {

    @Test
    void moduleChekcer_invalidModuleCodeExamle_exceptionThrown() {
        ModuleChecker moduleChecker = new ModuleChecker();
        String modeleCode = "CS11111";
        try {
            moduleChecker.isModuleValid(modeleCode);
        } catch (Exception e) {
            assertEquals("invalid module code", e.getMessage());
        }
    }

    @Test
    void moduleChekcer_noModuleCode_exceptionThrown() {
        ModuleChecker moduleChecker = new ModuleChecker();
        String modeleCode = "";
        try {
            moduleChecker.isModuleValid(modeleCode);
        } catch (Exception e) {
            assertEquals("invalid module code", e.getMessage());
        }
    }

    @Test
    void moduleChekcer_emptyModuleCode_exceptionThrown() {
        ModuleChecker moduleChecker = new ModuleChecker();
        String modeleCode = " ";
        try {
            moduleChecker.isModuleValid(modeleCode);
        } catch (Exception e) {
            assertEquals("invalid module code", e.getMessage());
        }
    }
}
