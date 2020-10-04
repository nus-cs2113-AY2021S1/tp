package seedu.duke;

import java.util.ArrayList;

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
        int output = -1;
        for (Module m : modList) {
            if (m.getModuleCode().equalsIgnoreCase(moduleCode)) {
                output = m.getExpectedWorkload();
            }
        }
        if (output == -1) {
            //print error msg of mod not found
        }
        return output;
    }

    /**
     * shows time spent for week 1 up to present.
     * which week is not specified.
     *
     * @param modList    list containing all the modules taken.
     * @param moduleCode specified module code.
     * @param week       the week that the user wishes to view actual amount of time spent.
     * @return amount of time spent on the specified module for the particular week.
     */
    public double viewActualTime(ArrayList<Module> modList, String moduleCode, int week) {
        double output = -1;
        double[] actualTime = new double[13];
        for (int i = 0; i < 13; i++) {
            actualTime[i] = -1;
        }
        for (Module m : modList) {
            if (m.getModuleCode().equalsIgnoreCase(moduleCode)) {
                actualTime = m.getActualTime();
            }
        }
        if (actualTime[0] == -1) {
            //print error msg of mod not found.
        } else {
            output = actualTime[week];
        }

        return output;
    }

    /**
     * shows time spent for week 1 up to present.
     * which week is not specified.
     *
     * @param modList    list containing all the modules taken.
     * @param moduleCode specified module code.
     * @return a list of amount of time spent on the specified module for the week 1 up to present.
     */
    public double[] viewActualTime(ArrayList<Module> modList, String moduleCode) {
        double[] actualTime = new double[13];
        for (int i = 0; i < 13; i++) {
            actualTime[i] = -1;
        }
        for (Module m : modList) {
            if (m.getModuleCode().equalsIgnoreCase(moduleCode)) {
                actualTime = m.getActualTime();
            }
        }
        if (actualTime[0] == -1) {
            //print error msg of mod not found.
        }

        return actualTime;
    }


}
