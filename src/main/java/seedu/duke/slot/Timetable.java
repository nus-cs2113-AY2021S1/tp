package seedu.duke.slot;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    private List<Module> modules;

    public Timetable() {
        modules = new ArrayList<>();
    }

    public boolean moduleExists(String moduleCode) {
        boolean moduleExists = false;
        for (Module module : modules) {
            if (module.isModule(moduleCode)) {
                moduleExists = true;
            }
        }
        return moduleExists;
    }

    public Module addModule(String moduleCode) {
        Module module = new Module(moduleCode);
        modules.add(module);
        return module;
    }

    public List<Slot> getFullSlotList() {
        List<Slot> slotList = new ArrayList<>();
        for (Module module : modules) {
            slotList.addAll(module.getSlotList());
        }
        return slotList;
    }

    public List<Module> getFullModuleList() {
        return modules;
    }

    public Module getModule(String moduleCode) {
        Module module = null;
        for (Module mod : modules) {
            if (mod.isModule(moduleCode)) {
                module = mod;
            }
        }
        assert module != null : "module should not be null";
        return module;
    }

    public void deleteModule(Module module) {
        modules.remove(module);
    }

}
