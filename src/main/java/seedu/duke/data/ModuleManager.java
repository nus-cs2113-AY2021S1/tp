package seedu.duke.data;

import seedu.duke.directory.Module;
import seedu.duke.exception.DataNotFoundException;
import seedu.duke.exception.DuplicateDataException;
import seedu.duke.exception.ModuleNotProvidedException;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleManager {
    private static ArrayList<Module> moduleList;
    private static HashMap<String, String> modulesMap;

    /**
     * Initialises the ModuleManager class.
     *
     * @param modulesMap
     *  The hash map containing NUS provided modules
     */
    public static void initialise(HashMap<String, String> modulesMap) {
        if (modulesMap == null) {
            ModuleManager.modulesMap = new HashMap<>();
        } else {
            ModuleManager.modulesMap = modulesMap;
        }
        moduleList = new ArrayList<>();
    }

    /**
     *  Finds a module with the specified module code in the Module List.
     *
     * @param moduleCode
     *  The module code of the module to be found
     * @return
     *  The found module with the specified module code
     * @throws ModuleNotFoundException
     *  If the module is not found in the Module List
     */
    public static Module getModule(String moduleCode) throws ModuleNotFoundException {
        for (Module module : moduleList) {
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return module;
            }
        }
        throw new ModuleNotFoundException();
    }

    /**
     * Edits a module in the Module List.
     *
     * @param toEdit
     *  The module to be edited
     * @param newModuleCode
     *  The new module code of the module
     * @throws ModuleNotProvidedException
     *  If there is no module with the new module code offered by NUS
     * @throws DuplicateModuleException
     *  If there are duplicate modules with the same module code as the new module code in the Module List
     */
    public static void edit(Module toEdit, String newModuleCode)
            throws ModuleNotProvidedException, DuplicateModuleException {
        if (!modulesMap.containsKey(newModuleCode)) {
            throw new ModuleNotProvidedException();
        }
        if (!toEdit.isSameModule(newModuleCode) && contains(newModuleCode)) {
            throw new DuplicateModuleException();
        }
        String newTitle = modulesMap.get(newModuleCode);
        toEdit.setModuleCode(newModuleCode);
        toEdit.setTitle(newTitle);
    }

    /**
     * Checks for duplicates of the same module code in the Module List.
     * @param moduleCode
     *  The module code to check
     * @return
     *  <code>TRUE</code> if there exists a duplicate, and <code>FALSE</code> otherwise
     */
    public static boolean contains(String moduleCode) {
        for (Module module : moduleList) {
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the modules map
     *
     * @return modules map
     */
    public static HashMap<String, String> getModulesMap() {
        return modulesMap;
    }

    /**
     * Add a module to the Module List.
     *
     * @param toAdd
     *  The module to be added
     */
    public static void add(Module toAdd) throws DuplicateModuleException, ModuleNotProvidedException {
        //check duplicate
        if (contains(toAdd.getModuleCode())) {
            throw new DuplicateModuleException();
        } else if (modulesMap.size() > 0 && !modulesMap.containsKey(toAdd.getModuleCode())) {
            throw new ModuleNotProvidedException();
        } else {
            String moduleTitle = modulesMap.get(toAdd.getModuleCode());
            toAdd.setTitle(moduleTitle);
            moduleList.add(toAdd);
        }
    }

    public static class ModuleNotFoundException extends DataNotFoundException {
    }

    public static class DuplicateModuleException extends DuplicateDataException {
    }
}
