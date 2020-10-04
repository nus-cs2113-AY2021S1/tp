package seedu.duke;

public class Module {
    protected String moduleCode;
    protected int exp = -1;
    //create an array here for actual workload
    protected double[] actualTime = new double[13];

    public Module(String mod) {
        this.moduleCode = mod;
    }

    public Module(String mod, String exp) {
        this.moduleCode = mod;
        this.exp = Integer.parseInt(exp);
    }

    public String toString() {
        if (this.exp == -1) {
            return this.moduleCode;
        } else {
            return this.moduleCode + " " + this.exp;
        }
    }


    public String getModuleCode() {
        return moduleCode;
    }

    public int getExpectedWorkload() {
        return exp;
    }

    public double[] getActualTime() {
        return actualTime;
    }
}
