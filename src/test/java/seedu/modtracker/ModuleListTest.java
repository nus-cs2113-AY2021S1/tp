package seedu.modtracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModuleListTest {
    private static final String TEST_DATA_FILEPATH = "test/data/modlist.txt";
    Storage storage = new Storage(TEST_DATA_FILEPATH);

    @Test
    public void addExp_emptyModuleList_newModuleWithExpTimeAdded() {
        ArrayList<Module> testList = new ArrayList<>();
        Module testMod = new Module("CS3030", "4");
        testList.add(testMod);

        ModuleList modules = new ModuleList();
        modules.addExp("addExp CS3030 4", true, storage);
        assertArrayEquals(testList.toArray(), modules.modList.toArray());
    }

    @Test
    public void addModule_emptyModuleList_newModuleAdded() {

        ModuleList modules = new ModuleList();
        modules.addMod("addMod CS5000", true, storage);
        assertTrue(modules.checkIfModuleExist("CS5000"));
    }

    @Test
    public void addModule_invalidInput_noChange() {
        ArrayList<Module> testList = new ArrayList<>();
        Module testMod = new Module("CS1010");
        testList.add(testMod);

        ModuleList modules = new ModuleList();
        modules.addMod("addMod", true, storage);
        assertNotEquals(testList.toArray(), modules.modList.toArray());
    }


}