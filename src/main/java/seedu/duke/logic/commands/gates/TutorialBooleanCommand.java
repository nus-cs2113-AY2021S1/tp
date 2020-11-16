package seedu.duke.logic.commands.gates;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.SummaryCommand;
import seedu.duke.logic.commands.TutorialCommand;
import seedu.duke.logic.commands.exceptions.IncorrectCommandException;
import seedu.duke.logic.parser.Parser;
import seedu.duke.ui.Ui;

import java.util.logging.Level;

/**
 * The TutorialCircuitCommand class inherits from the SummaryCommand and implements the TutorialCommand interface
 * It is used to go through the full interactive tutorial on boolean logic gates.
 */
public class TutorialBooleanCommand extends SummaryCommand implements TutorialCommand {
    private static final Parser PARSER = new Parser();
    private int numberOfCommandsDone = 0;
    private static final String[] orderOfInstructions = {"template and", "set b", "set c", "calc",
        "add c", "set f", "set g", "calc"};
    private final Ui ui;

    public TutorialBooleanCommand() {
        super();
        ui = new Ui();
    }

    @Override
    public void execute() {
        ui.printWelcomeTutorial();
        String command;
        String readCommand;
        boolean isNotDone = true;

        while (isNotDone) {
            assert numberOfCommandsDone < Ui.BOOLEAN_INSTRUCTIONS.length;
            ui.printBooleanInstruction(numberOfCommandsDone);
            readCommand = ui.readLine();
            command = readCommand.toLowerCase();
            try {
                isNotDone = continueTutorial(command, ui);
            } catch (DukeException e) {
                LOGGER.log(Level.FINE, "Bad Command DukeException thrown");
                ui.showError(e.getMessage());
            }
        }
        LOGGER.info("Exiting help mode");
    }

    @Override
    public boolean continueTutorial(String command, Ui ui) throws DukeException {
        if (command.equals("exit")) {
            return false;
        }
        if (numberOfCommandsDone >= 8 || !command.matches(orderOfInstructions[numberOfCommandsDone] + "(.*)")) {
            throw new IncorrectCommandException();
        }
        BooleanCommand b = (BooleanCommand) PARSER.parse(command);
        b.execute();
        ui.printMessage(b.toString());
        numberOfCommandsDone++;
        return true;
    }

    @Override
    public String toString() {
        return "You have exited Tutorial Mode!\n" + super.toString() + "\t:) Have fun using CLIrcuit Assistant!";
    }
}
