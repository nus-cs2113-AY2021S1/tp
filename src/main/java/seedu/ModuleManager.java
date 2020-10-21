package seedu;

import seedu.exception.ModuleAlreadyExistsException;
import seedu.exception.ModuleNotExistsException;
import seedu.task.Deadline;
import seedu.task.Task;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to keep track of all the Module that the user is taking.
 * Whenever an event or task is added into the ScheduleManager, we will add that task or event
 * into the ModuleManager as well according to the Module Code.
 */
public class ModuleManager {
    ArrayList<Module> listOfModules;

    public ModuleManager() {
        this.listOfModules = new ArrayList<>();
    }

    public ModuleManager(ArrayList<Module> listOfModules) {
        this.listOfModules = listOfModules;
    }

    /**
     * Add new module to the app.
     * If the module with the same course code exists already, the message will be printed.
     * @param module new Module object to add
     */
    public void addModule(Module module) throws ModuleAlreadyExistsException{
        if (!this.checkIfModuleExist(module)) {
            this.listOfModules.add(module);
            Ui.print("added: "+ module.getModuleCode().toString());
        } else {
            throw new ModuleAlreadyExistsException();
        }
    }

    /**
     * Check if the module with the same module code already exists in the ModuleManager.
     * This is to check before adding in any modules.
     * @param module module to be check if it already exist.
     * @return ture if the module exist, false if it dosen't.
     */
    public boolean checkIfModuleExist(Module module) {
        return this.getListOfModuleCodes().contains(module.getModuleCode());
    }

    /**
     * Gets the module with specified module code from the list.
     * @param moduleCode module code in string
     * @return the Module object in list
     * @throws ModuleNotExistsException if nothing is found
     */
    public Module getModule(String moduleCode) throws ModuleNotExistsException{
        for (Module m: listOfModules) {
            if (m.getModuleCode() == moduleCode){
                return m;
            }
        }
        throw new ModuleNotExistsException();
    }

    /**
     * Gives the index of the module in the list.
     * @param moduleCode module code in string
     * @return the Module object in list
     * @throws ModuleNotExistsException if nothing is found
     */
    public int getModuleIndex(String moduleCode) throws ModuleNotExistsException{
        int indexCount = 0;

        for (Module m: listOfModules) {
            if (m.getModuleCode() == moduleCode){
                return indexCount;
            }
            else{
                indexCount++;
            }
        }
        throw new ModuleNotExistsException();
    }

    public int getTotalNumberOfModules() {
        return this.listOfModules.size();
    }

    /**
     *
     * @param moduleCode
     * @param task
     * @param date
     * @throws DueQuestException
     */

    /**
     * Display the tasks of a module.
     * @param moduleCode module code's string
     * @throws ModuleNotExistsException
     */
    public void display(String moduleCode) throws ModuleNotExistsException{
        for (Module m: listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {
                ArrayList<Task> tasks = m.getListOfTasks();
                System.out.println(m); // print the module's information
                Ui.print("The list of task in " + moduleCode + ":");
                Ui.printListGenericType(tasks);
                Ui.printSeparator();
                return;
            }
        }
        throw new ModuleNotExistsException();
    }
    // Display all the task in a module on a specific date
    public void display(String moduleCode, LocalDate date) throws ModuleNotExistsException{
        ArrayList<Task> filteredTasks = new ArrayList<>();
        ArrayList<Lesson> lessons = new ArrayList<>();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        for (Module m : listOfModules) {
            if (m.getModuleCode().equals(moduleCode)) {

                for (Task t : m.getListOfTasks()) {
                    if (t instanceof Deadline) {
                        if (((Deadline) t).getDate().isEqual(date)) {
                            filteredTasks.add(t);
                        }
                    } else if (t instanceof Lesson) {
                        if (((Lesson) t).getLessonDayInDayOfWeek() == dayOfWeek) {
                            lessons.add((Lesson)t);
                        }
                    }
                }
                Ui.print(moduleCode + " - " + Ui.convertDateToString(date));
                Ui.print("Events & Deadlines :");
                Ui.printListGenericType(filteredTasks);
                Ui.print("Lessons :");
                Ui.printListGenericType(lessons);
                Ui.printSeparator();
            }
        }
        throw new ModuleNotExistsException();
    }

    public ArrayList<Module> getListOfModules() {
        return listOfModules;
    }

    /**
     * Method to get a list of Module Codes in String form.
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
     * Method to add a task to the module inside the list of the module manager.
     * This is executed in the AddCommand method, when a task is added to both.
     * the module manager and schedule manager.
     * @param task task to be added into the module manager.
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

    /**
     * Finds the module contains the task with the specified description.
     * @param description the description in string
     * @return Module found
     */
    private Module findModuleContainsTask(String description) {
        for (Module m: this.listOfModules) {
            for (Task t: m.getListOfTasks()) {
                if (t.getDescription().equals(description)) {
                    return m;
                }
            }
        }
        return null;
    }

    private Module findModuleContainsTask(String description, LocalDate date) {
        for (Module m: this.listOfModules) {
            for (Task t: m.getListOfTasks()) {
                if (t.getDescription().equals(description) && date.isEqual(t.getDate())) {
                    return m;
                }
            }
        }
        return null;
    }

    public void deleteTask(String description) {
        Module module = findModuleContainsTask(description);
        if (module != null) {
            module.deleteTask(description);
        }
    }

    public void deleteTask(String description, LocalDate date) {
        Module module = findModuleContainsTask(description, date);
        if (module != null) {
            module.deleteTask(description, date);
        }
    }
}
