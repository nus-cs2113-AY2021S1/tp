package seedu.duke;

import java.util.Arrays;

public class Module {

    protected String moduleCode;
    protected int expected = -1;
    protected double[] actualTime = new double[13];

    public Module(String mod) {
        this.moduleCode = mod;
        Arrays.fill(actualTime, -1);
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

    public boolean doesExpectedWorkLoadExist() {
        return (expected != -1);
    }

    public boolean doesActualTimeExist(int weekNumber) {
        return (actualTime[weekNumber] != -1);
    }
}
