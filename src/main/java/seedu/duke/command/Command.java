package seedu.duke.command;

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class Command {


    protected Hashtable<String, String> parametersInHT = new Hashtable<>();
    protected ArrayList<String> parametersInAL = new ArrayList<>();

    /**
     * Creates a new abstract command.
     */
    public Command(Hashtable<String, String> parameters) {
        setParametersInHT(parameters);
    }

    public Command(ArrayList<String> parameters) {
        setParametersInAL(parameters);
    }

    /**
     * Abstract method that execute the command.
     */
    public abstract void execute();

    /**
     * Getters and Setters.
     */
    public Hashtable<String, String> getParametersInHT() {
        return parametersInHT;
    }

    public void setParametersInHT(Hashtable<String, String> parametersInHT) {
        this.parametersInHT = parametersInHT;
    }

    public ArrayList<String> getParametersInAL() {
        return parametersInAL;
    }

    public void setParametersInAL(ArrayList<String> parametersInAL) {
        this.parametersInAL = parametersInAL;
    }
}
