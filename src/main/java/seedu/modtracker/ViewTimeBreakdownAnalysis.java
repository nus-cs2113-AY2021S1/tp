package seedu.modtracker;

import java.util.ArrayList;

import static seedu.modtracker.Analysis.justRight;
import static seedu.modtracker.Analysis.noInput;
import static seedu.modtracker.Analysis.tooLittleTimeSpent;
import static seedu.modtracker.Analysis.tooMuchTimeSpent;

public class ViewTimeBreakdownAnalysis {
    private static final int LOWER_BOUND_OF_JUST_NICE = -30;
    private static final String FULL_BAR = "█";
    private static final String HALF_BAR = "▌";
    public static final String NO_INPUT = "NO INPUT";
    public static final String ACTUAL = "Actual   | ";
    public static final String EXPECTED = "Expected | ";
    public static final String ANALYSIS = "Analysis:";
    private static final int UPPER_BOUND_OF_JUST_NICE = 30;
    public static final String TOO_LITTLE_TIME_SPENT = "You seem to be spending too little time on the module.\n"
            + "Perhaps you should spend more time on this module.";
    public static final String JUST_RIGHT = "Good Job! Great time management!";
    public static final String TOO_MUCH_TIME_SPENT = "Seems like you are spending too much time on this module.\n"
            + "Do you perhaps need help for this module?";
    public static final String MODULE_WEEK = "Module    Week ";
    private static final int NONE = 0;
    public static final String NO_VALID_INPUTS = "None of the modules has any valid inputs.";
    public static final int INDEX_OFFSET = 1;
    private static final int MIN_WEEK_VALUE = 1;
    private static final int MAX_WEEK_VALUE = 13;
    public static final String INVALID_WEEK_NUMBER = "Please input a week number between 1 and 13 inclusive.";
    public static final String EMPTY_MODULE_LIST = "The module list is empty. Please input some modules to be tracked.";
    public static final String NO_TIME_SPENT = "Seems like you didn't spend any time on your modules this week.";

    /**
     * Prints the breakdown and analysis of the
     * time spent in the specified week.
     *
     * @param list       list class containing all the modules taken.
     * @param weekNumber specified week number.
     */
    public void printTimeBreakDownAndAnalysis(ModuleList list, int weekNumber) {

        if (!validateInputs(list, weekNumber)) {
            return;
        }

        assert weekNumber >= MIN_WEEK_VALUE : "Week number should be between 1 and 13 inclusive.";
        assert weekNumber <= MAX_WEEK_VALUE : "Week number should be between 1 and 13 inclusive.";
        assert !list.getData().isEmpty() : "modList should not be empty";

        printTime(list, weekNumber);
        boolean toPrintAnalysis = printBreakdown(list, weekNumber);
        if (toPrintAnalysis) {
            printAnalysis(list, weekNumber);
        }
    }

    public boolean validateInputs(ModuleList list, int weekNumber) {
        if (weekNumber < MIN_WEEK_VALUE || weekNumber > MAX_WEEK_VALUE) {
            System.out.println(INVALID_WEEK_NUMBER + System.lineSeparator());
            return false;
        }

        if (list.getData().isEmpty()) {
            System.out.println(EMPTY_MODULE_LIST + System.lineSeparator());
            return false;
        }
        return true;
    }

    /**
     * Prints the breakdown of the time spent in the specified week.
     *
     * @param list       class containing all the modules taken.
     * @param weekNumber specified week number.
     * @return true if total time spent on the modules is greater than zero, otherwise false.
     */
    public boolean printBreakdown(ModuleList list, int weekNumber) {
        double totalTimeSpent = 0;
        ArrayList<Module> modList = list.getData();
        boolean modsWithValidInputExist = false;

        for (Module m : modList) {
            if (m.doesActualTimeExist(weekNumber) && m.doesExpectedWorkLoadExist()) {
                totalTimeSpent += m.getActualTime()[weekNumber - INDEX_OFFSET];
                modsWithValidInputExist = true;
            }
        }

        if (!modsWithValidInputExist) {
            System.out.println(NO_VALID_INPUTS + System.lineSeparator());
            return false;
        }

        System.out.println("Total time spent: " + totalTimeSpent + " H");
        if (totalTimeSpent == NONE) {
            System.out.println(NO_TIME_SPENT + System.lineSeparator());
            return false;
        }

        for (Module m : modList) {
            if (m.doesActualTimeExist(weekNumber) && m.doesExpectedWorkLoadExist()) {
                double actualTime = m.getActualTime()[weekNumber - INDEX_OFFSET];
                if (actualTime > NONE) {
                    int percentage;
                    percentage = (int) (actualTime * 100 / totalTimeSpent);
                    System.out.println(percentage + "% of time is spent on " + m.getModuleCode());
                } else {
                    System.out.println("Seems like you didn't spend any time on " + m.getModuleCode() + " this week.");
                }
            } else if (m.doesActualTimeExist(weekNumber)) {
                System.out.println("No actual workload for " + m.getModuleCode());
            } else {
                System.out.println("No expected workload for " + m.getModuleCode());
            }
        }
        System.out.println();
        return true;
    }

    /**
     * Prints the time spent in the specified week
     * in form of a horizontal bar graph.
     *
     * @param list       list class containing all the modules taken.
     * @param weekNumber specified week number.
     */
    public void printTime(ModuleList list, int weekNumber) {
        System.out.println(MODULE_WEEK + weekNumber + System.lineSeparator());
        ArrayList<Module> modList = list.getData();

        for (Module m : modList) {
            System.out.println(m.getModuleCode());
            System.out.print(ACTUAL);
            if (m.doesActualTimeExist(weekNumber)) {
                printBarGraph(m.getActualTime()[weekNumber - INDEX_OFFSET]);
            } else {
                System.out.println(NO_INPUT);
            }
            System.out.print(EXPECTED);
            if (m.doesExpectedWorkLoadExist()) {
                printBarGraph(m.getExpectedWorkload());
            } else {
                System.out.println(NO_INPUT);
            }
            System.out.println();
        }
    }

    /**
     * Prints the analysis of the time spent in the specified week.
     *
     * @param list       list class containing all the modules taken.
     * @param weekNumber specified week number.
     */
    public void printAnalysis(ModuleList list, int weekNumber) {
        ArrayList<Module> modList = list.getData();
        System.out.println(ANALYSIS);

        for (Module m : modList) {
            Analysis analysis = computeAnalysisOfTimeSpent(m, weekNumber);

            switch (analysis) {
            case tooMuchTimeSpent:
                System.out.println(m.getModuleCode());
                System.out.println(TOO_MUCH_TIME_SPENT);
                System.out.println();
                break;
            case justRight:
                System.out.println(m.getModuleCode());
                System.out.println(JUST_RIGHT);
                System.out.println();
                break;
            case tooLittleTimeSpent:
                System.out.println(m.getModuleCode());
                System.out.println(TOO_LITTLE_TIME_SPENT);
                System.out.println();
                break;
            case noInput:
                break;
            default:
                return;
            }
        }
    }

    /**
     * Computes the analysis of actual time spent on the module for the week.
     *
     * @param m          module taken.
     * @param weekNumber specified week number.
     * @return analysis of actual time spent compare to the expected workload.
     */
    public Analysis computeAnalysisOfTimeSpent(Module m, int weekNumber) {
        double actualTime = m.getActualTime()[weekNumber - INDEX_OFFSET];
        double expectedTime = m.getExpectedWorkload();

        double percentageDifference = Math.round((actualTime - (double) expectedTime) * 1000.0)
                / (expectedTime * 10.0);
        if (!m.doesActualTimeExist(weekNumber)) {
            return noInput;
        } else if (percentageDifference < LOWER_BOUND_OF_JUST_NICE) {
            return tooLittleTimeSpent;
        } else if (percentageDifference < UPPER_BOUND_OF_JUST_NICE) {
            return justRight;
        } else {
            return tooMuchTimeSpent;
        }
    }

    private void printBarGraph(double time) {
        StringBuilder output = new StringBuilder();
        boolean endsWithHalfBlock = !hasDecimalPlaces(time);

        for (int i = 0; i < (int) time; i++) {
            output.append(FULL_BAR);
        }

        if (endsWithHalfBlock) {
            output.append(HALF_BAR);
        }
        output.append(" ").append(time);
        System.out.println(output);
    }

    private boolean hasDecimalPlaces(double input) {
        return ((input * 10) % 10 == 0);
    }
}
