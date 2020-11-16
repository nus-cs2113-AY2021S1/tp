package seedu.duke.logic.commands.gates;

import seedu.duke.model.template.BooleanTemplate;

/**
 * The BooleanCircuitCommand class inherits from the BooleanCommand class
 *  and is used to instantiate a boolean template.
 */
public class TemplateBooleanCommand extends BooleanCommand {
    public static final String COMMAND_WORD = "template";

    public TemplateBooleanCommand(BooleanTemplate template) {
        super(template);
    }

    @Override
    public void execute() {

    }

    @Override
    public String toString() {
        return "New Boolean template chosen with " + template.getCircuit().getRoot() + " gate at the root."
                + System.lineSeparator() + '\t' + super.toString();
    }
}
