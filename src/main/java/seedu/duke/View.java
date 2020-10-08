package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;

public class View {

    private static final int LENGTH_OF_MODULE_CODE = 6;
    public static final String FIRST_PART_OF_BORDER = "+------+-";
    public static final String SECOND_PART_OF_BORDER = "-------+----------+----------+\n";
    public static final String FIRST_PART_OF_HEADER = "| Week | Module ";
    public static final String SECOND_PART_OF_HEADER = "| Expected |  Actual  |\n";
    public static final String NO_INPUT = " No Input ";
    public static final int DECIMAL_PLACES = 1;
    public static final int MIN_WEEK_VALUE = 1;
    public static final int MAX_WEEK_VALUE = 13;
    public static final String WEEK_NUMBER_PADING_CHAR = "0";
    public static final int FACTOR = 10;
    public static final int INDEX_OFFSET = 1;
    public static final String REPLACE_BY_WEEK_NUMBER = "WK";
    public static final String REPLACE_BY_MODULE_CODE = "XXXXXX";
    public static final String FIRST_PART_OF_CONTENT = "|  " + REPLACE_BY_WEEK_NUMBER
            + "  | " + REPLACE_BY_MODULE_CODE;
    public static final String PADING_CHAR = " ";
    public static final String REPLACE_BY_EXPECTEDWORKLOAD = "YY";
    public static final String REPLACE_BY_NO_EXPECTED_WORKLOAD_FOUND = "    YY    ";
    public static final String REPLACE_BY_ACTUAL_WORKLOAD = "ZZZZ";
    public static final String REPLACE_BY_NO_ACTUAL_WORKLOAD_FOUND = "   ZZZZ   ";
    public static final String SECOND_PART_OF_CONTENT = REPLACE_BY_NO_EXPECTED_WORKLOAD_FOUND
            + "|" + REPLACE_BY_NO_ACTUAL_WORKLOAD_FOUND + "|" + "\n";

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
     * @param modList    list containing all the modules taken.
     * @param weekNumber specified week number.
     */
    public void printAllModuleInformation(ArrayList<Module> modList, int weekNumber) {
        ArrayList<String> moduleCodes = getModuleCode(modList);

        if (weekNumber < MIN_WEEK_VALUE || weekNumber > MAX_WEEK_VALUE) {
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

        String border = FIRST_PART_OF_BORDER + dashToBeAdded + SECOND_PART_OF_BORDER;
        String header = FIRST_PART_OF_HEADER + spaceToBeAdded + SECOND_PART_OF_HEADER;
        String contents = FIRST_PART_OF_CONTENT + crossToBeAdded + " |" + SECOND_PART_OF_CONTENT;

        System.out.print(border + header + border);

        for (Module m : modList) {
            String out = contents;
            String crosses = REPLACE_BY_MODULE_CODE + crossToBeAdded;
            String weekNum = (isTwoDigitNumber(weekNumber) ? "" : WEEK_NUMBER_PADING_CHAR) + weekNumber;
            out = out.replace(REPLACE_BY_WEEK_NUMBER, weekNum);
            StringBuilder moduleCode = new StringBuilder(m.getModuleCode());
            while (moduleCode.length() < crosses.length()) {
                moduleCode.append(" ");
            }
            out = out.replace(crosses, moduleCode.toString());
            if (m.doesExpectedWorkLoadExist()) {
                String expectedWorkLoad = (isTwoDigitNumber(m.getExpectedWorkload()) ? "" : PADING_CHAR)
                        + m.getExpectedWorkload();
                out = out.replace(REPLACE_BY_EXPECTEDWORKLOAD, expectedWorkLoad);
            } else {
                out = out.replace(REPLACE_BY_NO_EXPECTED_WORKLOAD_FOUND, NO_INPUT);
            }

            if (m.doesActualTimeExist(weekNumber)) {
                double actualTime = m.getActualTime()[weekNumber - INDEX_OFFSET];
                actualTime = round(actualTime, DECIMAL_PLACES);
                String actualWorkLoad = (isTwoDigitNumber((int) actualTime) ? "" : PADING_CHAR)
                        + actualTime;
                out = out.replace(REPLACE_BY_ACTUAL_WORKLOAD, actualWorkLoad);
            } else {
                out = out.replace(REPLACE_BY_NO_ACTUAL_WORKLOAD_FOUND, NO_INPUT);
            }
            System.out.print(out + border);
        }
    }

    private double round(double value, int decimalPlaces) {
        int scale = (int) Math.pow(FACTOR, decimalPlaces);
        return (double) Math.round(value * scale) / scale;
    }

    private boolean isTwoDigitNumber (int num) {
        return String.valueOf(num).length() == 2;
    }
}
