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

    public Module getModule(int moduleIndex) {
        return modules.get(moduleIndex);
    }

    public boolean checkModuleExistence(Module newModule) {
        String newModuleName = newModule.getModuleName().toLowerCase();
        for (Module module : modules) {
            String moduleName = module.getModuleName().toLowerCase();
            if (moduleName.equals(newModuleName)) {
                return true;
            }
        }
        return false;
    }
}
