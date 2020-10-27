package seedu.modtracker;

import java.util.Arrays;

/**
 * Represents a module. A <code>Module</code> object corresponds to
 * a module with its moduleCode, expected time and actual time.
 */
public class Module {

    public static final int NO_INPUT = -1;
    public static final int INDEX_OFFSET = 1;
    private String moduleCode;
    private int expected = NO_INPUT;
    private final double[] actualTime = new double[13];

    public Module(String mod) {
        moduleCode = mod;
        Arrays.fill(actualTime, NO_INPUT);
    }

    public Module(String mod, String expected) {
        moduleCode = mod;
        this.expected = Integer.parseInt(expected);
        Arrays.fill(actualTime, NO_INPUT);
    }

    @Override
    public String toString() {
        if (expected == NO_INPUT) {
            return moduleCode;
        }
        return moduleCode + ", Expected Workload: " + expected + "h";
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public int getExpectedWorkload() {
        return expected;
    }

    public void setExpectedWorkload(int expected) {
        this.expected = expected;
    }

    public double[] getActualTime() {
        return actualTime;
    }

    public double getActualTimeInSpecificWeek(String week) {
        int i = Integer.parseInt(week);
        return actualTime[i - INDEX_OFFSET];
    }

    public void deleteActualTime(int week) {
        if (this.actualTime[week - INDEX_OFFSET] != NO_INPUT) {
            this.actualTime[week - INDEX_OFFSET] = NO_INPUT;
        }

    }

    public void addActualTime(String time, String week) {
        double d = Double.parseDouble(time);
        int i = Integer.parseInt(week);
        if (this.actualTime[i - INDEX_OFFSET] == NO_INPUT) {
            this.actualTime[i - INDEX_OFFSET] = d;
        } else {
            this.actualTime[i - INDEX_OFFSET] += d;
        }
    }

    public void minusActualTime(String time, String week) {
        double d = Double.parseDouble(time);
        int i = Integer.parseInt(week);
        //assert this.actualTime[i - INDEX_OFFSET] != NO_INPUT : "Cannot minus if actual time is not initialised";
        if (this.actualTime[i - INDEX_OFFSET] != NO_INPUT) {
            this.actualTime[i - INDEX_OFFSET] -= d;
        }
    }

    public void editsActualTime(String time, String week) {
        double d = Double.parseDouble(time);
        int i = Integer.parseInt(week);
        this.actualTime[i - INDEX_OFFSET] = d;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Module)) {
            return false;
        }
        Module m = (Module) obj;
        return moduleCode.equals(m.moduleCode);
    }

    @Override
    public int hashCode() {
        return moduleCode.hashCode();
    }

    public boolean doesExpectedWorkLoadExist() {
        return (expected != NO_INPUT);
    }

    public boolean doesActualTimeExist(int weekNumber) {
        return (actualTime[weekNumber - INDEX_OFFSET] != NO_INPUT);
    }

    public boolean doesHoursExceedTotal(double time, int weekNumber) {
        return (actualTime[weekNumber - INDEX_OFFSET] < time);
    }
}
