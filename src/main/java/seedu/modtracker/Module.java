package seedu.modtracker;

import java.util.Arrays;

/**
 * Represents a module. A <code>Module</code> object corresponds to
 * a module with its moduleCode, expected time and actual time.
 */
public class Module {

    public static final double NO_INPUT = -1.0;
    public static final int INDEX_OFFSET = 1;
    private String moduleCode;
    private double expected = NO_INPUT;
    private final double[] actualTime = new double[13];

    public Module(String mod) {
        moduleCode = mod;
        Arrays.fill(actualTime, NO_INPUT);
    }

    public Module(String mod, String expected) {
        moduleCode = mod;
        double exp = Double.parseDouble(expected);
        this.expected = Math.round(exp * 10) / 10.0;

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

    public double getExpectedWorkload() {
        return expected;
    }

    public void setExpectedWorkload(double expected) {
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
        this.actualTime[week - INDEX_OFFSET] = NO_INPUT;
    }

    public void addActualTime(String time, String week) {
        double d = Double.parseDouble(time);
        double roundedTime = Math.round(d * 10.0) / 10.0;
        int i = Integer.parseInt(week);
        double timeDifference = 99 - this.actualTime[i - INDEX_OFFSET];
        if (timeDifference < roundedTime) {
            throw new IllegalArgumentException("Total workload cannot be more than 99 hours.");
        }
        if (this.actualTime[i - INDEX_OFFSET] == NO_INPUT) {
            this.actualTime[i - INDEX_OFFSET] = roundedTime;
        } else {
            this.actualTime[i - INDEX_OFFSET] += roundedTime;
        }

    }

    public void minusActualTime(String time, String week) {
        double d = Double.parseDouble(time);
        int i = Integer.parseInt(week);
        double roundedTime = Math.round(d * 10.0) / 10.0;
        //assert this.actualTime[i - INDEX_OFFSET] != NO_INPUT : "Cannot minus if actual time is not initialised";
        if (this.actualTime[i - INDEX_OFFSET] != NO_INPUT) {
            this.actualTime[i - INDEX_OFFSET] -= roundedTime;
        }
    }

    public void editsActualTime(String time, String week) {
        double d = Double.parseDouble(time);
        double roundedTime = Math.round(d * 10.0) / 10.0;
        int i = Integer.parseInt(week);
        this.actualTime[i - INDEX_OFFSET] = roundedTime;
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
