package seedu.duke.model.gates;

import seedu.duke.DukeException;
import seedu.duke.model.exceptions.gatesexceptions.InputsNotSetException;

public class XnorGate extends XorGate {
    private static final String XNOR = "XNOR";

    /**
     * Performs bitwise XNOR (~^) operation on the input instance variables.
     *
     * @return int value of the gate output.
     * @throws InputsNotSetException If both inputs aren't set yet.
     */
    @Override
    public int getOutput() throws InputsNotSetException {
        return ~(super.getOutput());
    }

    /**
     * Returns String representation of the XNOR gate.
     *
     * @return String type depiction of XNOR gate.
     */
    @Override
    public String toString() {
        return XNOR;
    }
}
