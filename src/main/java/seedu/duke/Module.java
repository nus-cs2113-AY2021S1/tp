package seedu.duke;

public class Module {
    protected String modulecode;
    protected int expected = -1;
    //create an array here for actual workload

    public Module(String mod) {
        this.modulecode = mod;
    }

    public Module(String mod, String expected) {
        this.modulecode = mod;
        this.expected = Integer.parseInt(expected);
    }

    @Override
    public String toString() {
        if (this.expected == -1) {
            return this.modulecode;
        } else {
            return this.modulecode + ", Expected Workload: " + this.expected + "h";
        }
    }
}
