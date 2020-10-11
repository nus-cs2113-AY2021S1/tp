package seedu.modtracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ModuleListTest {

    @Test
    public void addModule_emptyModuleList_newModuleAdded() {
        ArrayList<Module> testList = new ArrayList<>();
        Module testMod = new Module("CS1010");
        testList.add(testMod);

        ModuleList modules = new ModuleList();
        modules.addMod("addMod CS1010");
        assertArrayEquals(testList.toArray(), modules.modList.toArray());
    }

    @Test
    public void addModule_invalidInput_noChange() {
        ArrayList<Module> testList = new ArrayList<>();
        Module testMod = new Module("CS1010");
        testList.add(testMod);

        ModuleList modules = new ModuleList();
        modules.addMod("addMod");
        assertNotEquals(testList.toArray(), modules.modList.toArray());
    }

    @Test
    public void addExp_emptyModuleList_newModuleWithExpTimeAdded() {
        ArrayList<Module> testList = new ArrayList<>();
        Module testMod = new Module("CS3030", "4");
        testList.add(testMod);

        ModuleList modules = new ModuleList();
        modules.addExp("addExp CS3030 4");
        assertArrayEquals(testList.toArray(), modules.modList.toArray());
    }
}