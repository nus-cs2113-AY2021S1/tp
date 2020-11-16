package seedu.duke.model.template;

import seedu.duke.model.component.Capacitor;
import seedu.duke.model.component.LoadComponent;
import seedu.duke.model.exceptions.componentexceptions.ZeroComponentException;
import seedu.duke.model.exceptions.templateexceptions.InvalidComponentException;
import seedu.duke.model.exceptions.templateexceptions.TemplateComponentsNotSetException;

/**
 * The RcTemplate class inherits from the RTemplate class
 * and is used to create RcTemplate objects.
 */
public class RcTemplate extends RTemplate {

    private static final String RC_TEMPLATE = "+---R-----C---+\n"
                                            + "\t|             |\n"
                                            + "\t|             |\n"
                                            + "\t+----+V_ac+---+\n";

    private final Capacitor capacitor;

    public RcTemplate(double resistance, double capacitance, double powerSupply) {

        super(resistance, powerSupply);
        capacitor = new Capacitor(capacitance);
    }

    public RcTemplate() {
        this(0,0,0);
    }

    /**
     * Returns impedance value of circuit.
     *
     * @return impedance, a double representation of the impedance value of circuit.
     * @throws TemplateComponentsNotSetException If component values are not yet set.
     */
    @Override
    public double calcImpedance() throws TemplateComponentsNotSetException {
        double resistance = super.calcImpedance();
        double capacitance = capacitor.getValue() * Math.pow(10, -6);
        if (capacitance == 0) {
            throw new TemplateComponentsNotSetException();
        }
        return Math.sqrt(Math.pow(resistance, 2)
                + (1 / Math.pow((ANGULAR_FREQUENCY * capacitance), 2)));
    }

    /**
     * Returns capacitor object, an attribute of the instance of RcTemplate.
     *
     * @return capacitor, an instance of the Capacitor class.
     */
    public Capacitor getCapacitor() {
        return capacitor;
    }

    /**
     * Sets the value of the capacitor in the Rc Template circuit to the value specified.
     *
     * @param value double type value to be set to the capacitor in the circuit.
     */
    protected void setCapacitor(double value) throws ZeroComponentException {
        capacitor.setValue(value);
    }

    /**
     * Sets the value of the component specified to the value specified in the Rc Template.
     *
     * @param s String corresponding to component type.
     * @param value double type value to be set to the component in the circuit.
     */
    @Override
    public void setComponent(String s, double value) throws ZeroComponentException {
        assert s.equals("c") || s.equals("r");
        if (s.equals("c")) {
            setCapacitor(value);
        } else {
            super.setComponent(s, value);
        }
    }

    protected boolean isSetCapacitor() {
        return capacitor.getValue() != 0;
    }

    protected String capacitorToString() {
        return "\tTotal Capacitance: " + (isSetCapacitor() ? capacitor : NOT_SET) + System.lineSeparator();
    }

    /**
     * Returns String consisting of total capacitance and resistance values of the RcTemplate object.
     *
     * @return String representation of RcTemplate object.
     */
    @Override
    public String toString() {
        return RC_TEMPLATE + voltageToString() + resistorToString() + capacitorToString();
    }

    /**
     * Returns LoadComponent object depending on input String.
     *
     * @param component String representing the component.
     * @return LoadComponent object.
     * @throws InvalidComponentException If input String does not match a component.
     */
    @Override
    public LoadComponent getComponent(String component) throws InvalidComponentException {
        if (component.equals("c")) {
            return getCapacitor();
        }
        return super.getComponent(component);
    }
}
