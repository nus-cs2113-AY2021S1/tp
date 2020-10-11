package manager.admin;

import manager.module.Module;

import java.util.ArrayList;

public class ModuleList {
    private final ArrayList<Module> modules;

    public ModuleList() {
        modules = new ArrayList<>();
    }

    public ModuleList(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public ArrayList<Module> getAllModules() {
        return modules;
    }

    public int getModuleCount() {
        return modules.size();
    }
}
