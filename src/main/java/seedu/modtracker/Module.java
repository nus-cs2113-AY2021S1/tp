package seedu.modtracker;

import java.util.Arrays;

/**
 * Represents a module. A <code>Module</code> object corresponds to
 * a module with its moduleCode, expected time and actual time.
 */
public class Module {

    public static final int NO_INPUT = -1;
    private final String moduleCode;
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
        } else {
            return moduleCode + ", Expected Workload: " + expected + "h";
        }
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

    public void addActualTime(String time, String week) {
        double d = Double.parseDouble(time);
        int i = Integer.parseInt(week);
        if (this.actualTime[i] == NO_INPUT) {
            this.actualTime[i] = d;
        } else {
            this.actualTime[i] += d;
        }
    }

    public void minusActualTime(String time, String week) {
        double d = Double.parseDouble(time);
        int i = Integer.parseInt(week);
        if (this.actualTime[i] != NO_INPUT) {
            this.actualTime[i] -= d;
        }
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

    public boolean doesExpectedWorkLoadExist() {
        return (expected != NO_INPUT);
    }

    public boolean doesActualTimeExist(int weekNumber) {
        return (actualTime[weekNumber] != NO_INPUT);
    }
}
