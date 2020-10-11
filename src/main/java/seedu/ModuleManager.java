package seedu;

import seedu.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to keep track of all the Module that the user is taking
 * Whenever an event or task is added into the ScheduleManager, we will add that task or event
 * into the ModuleManager as well according to the Module Code
 */
public class ModuleManager {
    List<Module> listOfModules;

    public ModuleManager() {
        this.listOfModules = new ArrayList<>();
    }

    public ModuleManager(List<Module> listOfModules) {
        this.listOfModules = listOfModules;
    }

    public List<Module> getListOfModules() {
        return listOfModules;
    }

    /**
     * Method to get a list of Module Codes in String form
     * @return the list of module codes.
     */
    public List<String> getListOfModuleCodes() {
        List<String> listOfModuleCodes = new ArrayList<>();
        for (Module module: listOfModules) {
            listOfModuleCodes.add(module.getModuleCode());
        }
        return listOfModuleCodes;
    }

    /**
     * Method to check if a module exist in the list of modules based on a given module code
     * @param moduleCode
     * @return true if module exist and false if it does not.
     */
    public boolean checkIfModuleExist(String moduleCode) {
        List<String> listOfModuleCodes = this.getListOfModuleCodes();
        for (String code: listOfModuleCodes) {
            if (moduleCode.equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to add a task to the module inside the list of the module manager
     * This is executed in the AddCommand method, when a task is added to both
     * the module manager and schedule manager
     * @param task task to be added into the module manager
     * @param moduleCode this is the modulecode of the task. Remember, moduleCode is an attribute of task.
     */
    public void addTaskToModule(Task task, String moduleCode) {
        for (int i = 0; i < this.listOfModules.size(); i++) {
            if (this.listOfModules.get(i).getModuleCode().equals(moduleCode)) {
                this.listOfModules.get(i).addTask(task);
                return;
            }
        }
        // if we reach the end of the for loop, it means that the moduleCode does not exist
        // hence, we create this module first, add the task to it and
        // then add it to the module manager
        Module module = new Module(moduleCode);
        module.addTask(task);
        this.listOfModules.add(module);
    }
}
