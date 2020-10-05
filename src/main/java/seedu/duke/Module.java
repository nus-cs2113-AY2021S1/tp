package seedu.duke;

public class Module {
    protected String moduleCode;

    protected int expected = -1;
    //create an array here for actual workload
    protected double[] actualTime = new double[13];

    public Module(String mod) {
        this.moduleCode = mod;
    }


    public Module(String mod, String expected) {
        this.moduleCode = mod;
        this.expected = Integer.parseInt(expected);
    }

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
}
