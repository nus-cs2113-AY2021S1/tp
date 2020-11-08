package manager.admin;

import manager.module.Module;

import java.util.ArrayList;

/**
 * A list of modules.
 */
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

    /**
     * Checks if the list contains an equivalent module that has the same {@code newModuleName}.
     *
     * @param newModuleName new name for a module
     * @return true if the list contains an equivalent module that has the same name as the given argument.
     */
    public boolean checkModuleExistence(String newModuleName) {
        for (Module module : modules) {
            String moduleName = module.getModuleName().toLowerCase();
            if (moduleName.equals(newModuleName)) {
                return true;
            }
        }
        return false;
    }
}
