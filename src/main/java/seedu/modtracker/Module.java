package seedu.modtracker;

import java.util.Arrays;

/**
 * Represents a module. A <code>Module</code> object corresponds to
 * a module with its moduleCode, expected time and actual time.
 */
public class Module {

    protected String moduleCode;
    protected int expected = -1;
    protected double[] actualTime = new double[13];

    public Module(String mod) {
        this.moduleCode = mod;
        Arrays.fill(this.actualTime, -1);
    }

    public Module(String mod, String expected) {
        this.moduleCode = mod;
        this.expected = Integer.parseInt(expected);
        Arrays.fill(actualTime, -1);
    }

    @Override
    public String toString() {
        if (this.expected == -1) {
            return this.moduleCode;
        } else {
            return this.moduleCode + ", Expected Workload: " + this.expected + "h";
        }
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public int getExpectedWorkload() {
        return expected;
    }

    public double[] getActualTime() {
        return actualTime;
    }

    public void addActualTime(String time, String week) {
        double d = Double.parseDouble(time);
        int i = Integer.parseInt(week);
        if (this.actualTime[i] == -1) {
            this.actualTime[i] = d;
        } else {
            this.actualTime[i] += d;
        }
    }

    public void minusActualTime(String time, String week) {
        double d = Double.parseDouble(time);
        int i = Integer.parseInt(week);
        this.actualTime[i] -= d;
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

}
