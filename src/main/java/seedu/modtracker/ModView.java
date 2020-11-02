package seedu.modtracker;

import java.util.ArrayList;

public class ModView {

    private static final int LENGTH_OF_MODULE_CODE = 6;
    public static final String FIRST_PART_OF_BORDER = "+------+-";
    public static final String SECOND_PART_OF_BORDER = "-------+----------+----------+\n";
    public static final String FIRST_PART_OF_HEADER = "| Week | Module ";
    public static final String SECOND_PART_OF_HEADER = "| Expected |  Actual  |\n";
    public static final String NO_INPUT = " No Input ";
    private static final int DECIMAL_PLACES = 1;
    private static final int MIN_WEEK_VALUE = 1;
    private static final int MAX_WEEK_VALUE = 13;
    public static final String WEEK_NUMBER_PADDING_CHAR = "0";
    private static final int FACTOR = 10;
    private static final int INDEX_OFFSET = 1;
    private static final String REPLACE_BY_WEEK_NUMBER = "WK";
    private static final String REPLACE_BY_MODULE_CODE = "XXXXXX";
    public static final String FIRST_PART_OF_CONTENT = "|  " + REPLACE_BY_WEEK_NUMBER
            + "  | " + REPLACE_BY_MODULE_CODE;
    public static final String PADDING_CHAR = " ";
    public static final String REPLACE_BY_EXPECTED_WORKLOAD = "YYYY";
    public static final String REPLACE_BY_NO_EXPECTED_WORKLOAD_FOUND = "   YYYY   ";
    public static final String REPLACE_BY_ACTUAL_WORKLOAD = "ZZZZ";
    public static final String REPLACE_BY_NO_ACTUAL_WORKLOAD_FOUND = "   ZZZZ   ";
    public static final String SECOND_PART_OF_CONTENT = " |" + REPLACE_BY_NO_EXPECTED_WORKLOAD_FOUND
            + "|" + REPLACE_BY_NO_ACTUAL_WORKLOAD_FOUND + "|" + "\n";
    public static final String INVALID_WEEK_NUMBER = "Please input a week number between 1 and 13 inclusive.";
    public static final String EMPTY_MODULE_LIST = "The module list is empty. Please input some modules to be tracked.";


    /**
     * Prints the week number, module code, expected workload and actual time spent
     * in the specified week for all the modules taken in a table format.
     *
     * @param list       list class containing all the modules taken.
     * @param weekNumber specified week number.
     */
    public void printAllModuleInformation(ModuleList list, int weekNumber) {
        ArrayList<Module> modList = list.getData();

        if (!validateInputs(weekNumber, modList)) {
            return;
        }

        assert weekNumber >= MIN_WEEK_VALUE : "Week number should be between 1 and 13 inclusive.";
        assert weekNumber <= MAX_WEEK_VALUE : "Week number should be between 1 and 13 inclusive.";
        assert !modList.isEmpty() : "modList should not be empty";

        ArrayList<String> moduleCodes = list.getModuleCodes();
        int maxLength = getMaxModuleLength(moduleCodes);

        int extraCharsToBeAdded = maxLength - LENGTH_OF_MODULE_CODE;

        String[] updatedTemplates = updateTemplates(extraCharsToBeAdded);
        String border = updatedTemplates[0];
        String header = updatedTemplates[1];
        String contents = updatedTemplates[2];
        String crossToBeAdded = updatedTemplates[3];

        System.out.print(border + header + border);

        for (Module m : modList) {
            String updatedContent = contents;
            updatedContent = updateContent(weekNumber, crossToBeAdded, m, updatedContent);
            System.out.print(updatedContent + border);
        }
        System.out.println();
    }

    private String updateContent(int weekNumber, String crossToBeAdded, Module m, String out) {
        String crosses = REPLACE_BY_MODULE_CODE + crossToBeAdded;
        String weekNum = (isTwoDigitNumber(weekNumber) ? "" : WEEK_NUMBER_PADDING_CHAR) + weekNumber;
        out = out.replace(REPLACE_BY_WEEK_NUMBER, weekNum);

        StringBuilder moduleCode = new StringBuilder(m.getModuleCode());
        while (moduleCode.length() < crosses.length()) {
            moduleCode.append(" ");
        }
        out = out.replace(crosses, moduleCode.toString());

        if (m.doesExpectedWorkLoadExist()) {
            String expectedWorkLoad = (isTwoDigitNumber(m.getExpectedWorkload()) ? "" : PADDING_CHAR)
                    + m.getExpectedWorkload();
            out = out.replace(REPLACE_BY_EXPECTED_WORKLOAD, expectedWorkLoad);
        } else {
            out = out.replace(REPLACE_BY_NO_EXPECTED_WORKLOAD_FOUND, NO_INPUT);
        }

        if (m.doesActualTimeExist(weekNumber)) {
            double actualTime = m.getActualTime()[weekNumber - INDEX_OFFSET];
            actualTime = round(actualTime, DECIMAL_PLACES);
            String actualWorkLoad = (isTwoDigitNumber((int) actualTime) ? "" : PADDING_CHAR)
                    + actualTime;
            out = out.replace(REPLACE_BY_ACTUAL_WORKLOAD, actualWorkLoad);
        } else {
            out = out.replace(REPLACE_BY_NO_ACTUAL_WORKLOAD_FOUND, NO_INPUT);
        }
        return out;
    }

    private int getMaxModuleLength(ArrayList<String> moduleCodes) {
        int maxLength = 0;
        for (String s : moduleCodes) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }
        return maxLength;
    }

    private boolean validateInputs(int weekNumber, ArrayList<Module> modList) {
        if (weekNumber < MIN_WEEK_VALUE || weekNumber > MAX_WEEK_VALUE) {
            System.out.println(INVALID_WEEK_NUMBER + System.lineSeparator());
            return false;
        }

        if (modList.isEmpty()) {
            System.out.println(EMPTY_MODULE_LIST + System.lineSeparator());
            return false;
        }
        return true;
    }

    private double round(double value, int decimalPlaces) {
        int scale = (int) Math.pow(FACTOR, decimalPlaces);
        return (double) Math.round(value * scale) / scale;
    }

    private boolean isTwoDigitNumber(double num) {
        int value = (int)num;
        return String.valueOf(value).length() == 2;
        //return String.valueOf(num).length() == 2;
    }

    private String[] updateTemplates(int extraCharsToBeAdded) {
        String[] output = new String[4];

        StringBuilder dashToBeAdded = new StringBuilder();
        StringBuilder spaceToBeAdded = new StringBuilder();
        StringBuilder crossToBeAdded = new StringBuilder();
        for (int i = 0; i < extraCharsToBeAdded; i++) {
            dashToBeAdded.append("-");
            spaceToBeAdded.append(" ");
            crossToBeAdded.append("X");
        }

        output[0] = FIRST_PART_OF_BORDER + dashToBeAdded + SECOND_PART_OF_BORDER;
        output[1] = FIRST_PART_OF_HEADER + spaceToBeAdded + SECOND_PART_OF_HEADER;
        output[2] = FIRST_PART_OF_CONTENT + crossToBeAdded + SECOND_PART_OF_CONTENT;
        output[3] = crossToBeAdded.toString();
        return output;
    }
}
