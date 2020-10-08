package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;

public class View {

    private static final int LENGTH_OF_MODULE_CODE = 6;

    /**
     * Displays all the modules taken by the user.
     *
     * @param modList list containing all the modules taken.
     * @return a list containing all the modules.
     */
    public ArrayList<String> getModuleCode(ArrayList<Module> modList) {
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
    public int getExpWorkLoad(ArrayList<Module> modList, String moduleCode) {
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
    public double getActualTime(ArrayList<Module> modList, String moduleCode, int week) {
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
    public double[] getActualTime(ArrayList<Module> modList, String moduleCode) {
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

    /**
     * Prints the week number, module code, expected workload and actual time spent
     * in the specified week for all the modules taken.
     *
     * @param modList list containing all the modules taken.
     * @param week    specified week number.
     */
    public void printAllModuleInformation(ArrayList<Module> modList, int week) {
        ArrayList<String> moduleCodes = getModuleCode(modList);

        if (week < 1 || week > 13) {
            //print error message
            return;
        }

        int maxLength = 0;

        for (String s : moduleCodes) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }

        int extraCharsToBeAdded = maxLength - LENGTH_OF_MODULE_CODE;
        StringBuilder dashToBeAdded = new StringBuilder();
        StringBuilder spaceToBeAdded = new StringBuilder();
        StringBuilder crossToBeAdded = new StringBuilder();
        for (int i = 0; i < extraCharsToBeAdded; i++) {
            dashToBeAdded.append("-");
            spaceToBeAdded.append(" ");
            crossToBeAdded.append("X");
        }

        String border = "+------+-" + dashToBeAdded + "-------+----------+----------+\n";
        String header = "| Week | Module " + spaceToBeAdded + "| Expected |  Actual  |\n";
        String contents = "|  WK  | XXXXXX" + crossToBeAdded + " |    YY    |   ZZZZ   |\n";

        System.out.print(border + header + border);

        for (Module m : modList) {
            String out = contents;
            String crosses = "XXXXXX" + crossToBeAdded;
            String weekNum = ((week > 10) ? "" : "0") + Integer.toString(week);
            out = out.replace("WK", weekNum);
            StringBuilder moduleCode = new StringBuilder(m.getModuleCode());
            while (moduleCode.length() < crosses.length()) {
                moduleCode.append(" ");
            }
            out = out.replace(crosses, moduleCode.toString());
            if (m.getExpectedWorkload() != -1) {
                String expectedWorkLoad = ((m.getExpectedWorkload() > 10) ? "" : " ")
                        + Integer.toString(m.getExpectedWorkload());
                out = out.replace("YY", expectedWorkLoad);
            } else {
                out = out.replace("    YY    ", " No Input ");
            }
            double actualTime = m.getActualTime()[week - 1];
            if (actualTime != -1) {
                actualTime = round(actualTime, 1);
                String actualWorkLoad = ((actualTime > 10) ? "" : " ") + Double.toString(actualTime);
                out = out.replace("ZZZZ", actualWorkLoad);
            } else {
                out = out.replace("   ZZZZ   ", " No Input ");
            }
            System.out.print(out + border);
        }
        System.out.println();
    }

    private double round(double value, int decimalPlaces) {
        int scale = (int) Math.pow(10, decimalPlaces);
        return (double) Math.round(value * scale) / scale;
    }
}
