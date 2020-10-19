package seedu.modtracker;

import java.util.ArrayList;

import static seedu.modtracker.Analysis.justRight;
import static seedu.modtracker.Analysis.tooLittleTimeSpent;
import static seedu.modtracker.Analysis.tooMuchTimeSpent;

public class ViewTimeBreakDownAnalysis {
    private static final int LOWER_BOUND_OF_JUST_NICE = -30;
    private static final String FULL_BAR = "█";
    private static final String HALF_BAR = "▌";
    private static final String NO_INPUT = "| NO INPUT";
    private static final String ACTUAL = "Actual   ";
    private static final String EXPECTED = "Expected ";
    private static final String ANALYSIS = "Analysis: ";
    private static final int UPPER_BOUND_OF_JUST_NICE = 30;
    private static final String TOO_LITTLE_TIME_SPENT = "You seem to be spending too little time on the module.\n"
            + "Perhaps you should spend more time on this module.";
    private static final String JUST_RIGHT = "Good Job! Great time management!";
    private static final String TOO_MUCH_TIME_SPENT = "Seems like you are spending too much time one this module.\n"
            + "Do you perhaps need help for this module?";
    private static final String MODULE_WEEK = "Module   Week ";

    /**
     * Prints the breakdown and analysis of the
     * time spent in the specified week.
     *
     * @param list       list class containing all the modules taken.
     * @param weekNumber specified week number.
     */
    public void printTimeBreakDownAndAnalysis(ModuleList list, int weekNumber) {
        printTimeBreakDown(list, weekNumber);
        printAnalysis(list, weekNumber);
    }

    /**
     * Prints the breakdown of the time spent in the specified week
     * in form of a horizontal bar graph.
     *
     * @param list       list class containing all the modules taken.
     * @param weekNumber specified week number.
     */
    public void printTimeBreakDown(ModuleList list, int weekNumber) {
        System.out.println(MODULE_WEEK + weekNumber);
        ArrayList<Module> modList = list.getData();
        double totalTimeSpent = 0;

        for (Module m : modList) {
            System.out.println(m.getModuleCode());
            System.out.print(ACTUAL);
            if (m.doesActualTimeExist(weekNumber)) {
                printBarGraph(m.getActualTime()[weekNumber]);
            } else {
                System.out.println(NO_INPUT);
            }
            System.out.print(EXPECTED);
            if (m.doesExpectedWorkLoadExist()) {
                printBarGraph(m.getExpectedWorkload());
            }
            System.out.println();
            totalTimeSpent += m.getActualTime()[weekNumber];
        }

        System.out.println("Total time spent: " + totalTimeSpent + " H");

        for (Module m : modList) {
            int percentage;
            percentage = (int) (m.getActualTime()[weekNumber] * 100 / totalTimeSpent);
            System.out.println(percentage + "% of time is spent on " + m.getModuleCode());
        }
        System.out.println();
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
            System.out.println(m.getModuleCode());
            switch (analysis) {
            case tooMuchTimeSpent:
                System.out.println(TOO_MUCH_TIME_SPENT);
                break;
            case justRight:
                System.out.println(JUST_RIGHT);
                break;
            case tooLittleTimeSpent:
                System.out.println(TOO_LITTLE_TIME_SPENT);
                break;
            default:
                return;
            }
            System.out.println();
        }
    }

    private Analysis computeAnalysisOfTimeSpent(Module m, int weekNumber) {
        double actualTime = m.getActualTime()[weekNumber];
        int expectedTime = m.getExpectedWorkload();

        double percentageDifference = (actualTime - expectedTime) / expectedTime * 100.0;
        if (percentageDifference < LOWER_BOUND_OF_JUST_NICE) {
            return tooLittleTimeSpent;
        } else if (percentageDifference <= UPPER_BOUND_OF_JUST_NICE) {
            return justRight;
        } else {
            return tooMuchTimeSpent;
        }
    }

    private void printBarGraph(double time) {
        StringBuilder output = new StringBuilder("| ");
        boolean endsWithHalfBlock = !hasDecimalPlaces(time);

        for (int i = 0; i < time; i++) {
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
