package seedu.duke;

public class Module {
    protected String modulecode;
    protected int exp = -1;
    //create an array here for actual workload

    public Module(String mod) {
        this.modulecode = mod;
    }

    public Module(String mod, String exp) {
        this.modulecode = mod;
        this.exp = Integer.parseInt(exp);
    }

    public String toString() {
        if (this.exp == -1) {
            return this.modulecode;
        }
        else {
            return this.modulecode + " " + this.exp;
        }
    }
}
