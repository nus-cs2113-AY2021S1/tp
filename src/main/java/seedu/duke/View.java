package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;

public class View {
    /**
     * Displays all the modules taken by the user.
     *
     * @param modList list containing all the modules taken.
     * @return a list containing all the modules.
     */
    public ArrayList<String> viewMods(ArrayList<Module> modList) {
        ArrayList<String> output = new ArrayList<>();
        for (Module m : modList) {
            output.add(m.getModuleCode());
        }
        return output;
    }

    /**
     * Displays the expected workload for the specified module.
     *
     * @param modList    list containing all the modules taken.
     * @param moduleCode specified module code.
     * @return expected workload.
     */
    public int viewExpWorkLoad(ArrayList<Module> modList, String moduleCode) {
        int output;
        Module mod;

        if (doesModuleExist(modList, moduleCode)) {
            mod = getModule(modList, moduleCode);
            output = mod.getExpectedWorkload();
        } else {
            //print error msg of mod not found.
            output = -1;
        }
        return output;
    }

    /**
     * shows time spent for specified week.
     *
     * @param modList    list containing all the modules taken.
     * @param moduleCode specified module code.
     * @param week       the week that the user wishes to view actual amount of time spent.
     * @return amount of time spent on the specified module for the particular week.
     */
    public double viewActualTime(ArrayList<Module> modList, String moduleCode, int week) {
        double output;
        double[] actualTime;
        Module mod;

        if (doesModuleExist(modList, moduleCode)) {
            mod = getModule(modList, moduleCode);
            actualTime = mod.getActualTime();
            output = actualTime[week - 1];
        } else {
            //print error msg of mod not found.
            output = 0;
        }
        return output;
    }

    /**
     * shows time spent for week 1 up to present.
     * which week is not specified.
     *
     * @param modList    list containing all the modules taken.
     * @param moduleCode specified module code.
     * @return an array of amount of time spent on the specified module for the week 1 to week 13.
     */
    public double[] viewActualTime(ArrayList<Module> modList, String moduleCode) {
        Module mod;
        double[] actualTime = new double[13];

        if (doesModuleExist(modList, moduleCode)) {
            mod = getModule(modList, moduleCode);
            actualTime = mod.getActualTime();
        } else {
            //print error msg of mod not found.
            Arrays.fill(actualTime, -1);
        }
        return actualTime;
    }

    /**
     * Checks if the specified module exists in the module list.
     *
     * @param modList    list containing all the modules taken.
     * @param moduleCode specified module code.
     * @return whether the module exists in the list
     */
    protected boolean doesModuleExist(ArrayList<Module> modList, String moduleCode) {
        for (Module m : modList) {
            if (m.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Looks for the specified module in the given list.
     *
     * @param modList    list containing all the modules taken.
     * @param moduleCode specified module code.
     * @return the specified module from the given list.
     */
    protected Module getModule(ArrayList<Module> modList, String moduleCode) {
        for (Module m : modList) {
            if (m.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return m;
            }
        }
        return null;
    }

}
