package seedu.duke.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.ExitCommand;
import seedu.duke.logic.commands.SummaryCommand;
import seedu.duke.logic.commands.circuit.AddCircuitCommand;
import seedu.duke.logic.commands.circuit.CalculateCircuitCommand;
import seedu.duke.logic.commands.circuit.CircuitCommand;
import seedu.duke.logic.commands.circuit.SetCircuitCommand;
import seedu.duke.logic.commands.circuit.TemplateCircuitCommand;
import seedu.duke.logic.commands.circuit.TutorialCircuitCommand;
import seedu.duke.logic.parser.exceptions.InvalidArgumentException;
import seedu.duke.logic.parser.exceptions.InvalidCommandException;
import seedu.duke.logic.parser.exceptions.InvalidNumberException;
import seedu.duke.logic.parser.exceptions.NotEnoughArgumentsException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CircuitParserTest {

    @Test
    void parse_noLine_expectException() {
        Parser p = new Parser();
        String line = "";
        assertThrows(InvalidCommandException.class, () -> p.parse(line));
    }

    @Test
    void parseTutorial_tutorialCircuit_returnsTutorialCircuitCommand() throws DukeException {
        Parser p = new Parser();
        String line = "tutorial circuit";
        SummaryCommand c = (SummaryCommand) p.parse(line);
        assertTrue(c instanceof TutorialCircuitCommand); // change to HelpCommand once done
    }

    @Test
    void parseTemplate_Template_returnsTemplateCommand() throws DukeException {
        Parser p = new Parser();
        String line = "template ";
        String[] templates = {"r", "rl", "rc", "lc"};
        CircuitCommand c;
        for (String s : templates) {
            c = (CircuitCommand) p.parse(line + s);
            assertTrue(c instanceof TemplateCircuitCommand);
        }
    }

    @Test
    void parseTemplate_noTemplate_expectException() {
        Parser p = new Parser();
        String line = "template";
        assertThrows(NotEnoughArgumentsException.class, () -> p.parse(line));
    }

    @Test
    void parseTemplate_wrongArgument_expectException() {
        Parser p = new Parser();
        String line = "template haha";
        assertThrows(InvalidArgumentException.class, () -> p.parse(line));
    }

    @Test
    void parseSet_resistor_returnsSetCommand() throws DukeException {
        Parser p = new Parser();
        p.parse("template rl");
        String line = "set r 500";
        CircuitCommand c = (CircuitCommand) p.parse(line);
        assertTrue(c instanceof SetCircuitCommand);
    }

    @Test
    void parseSet_noResistance_expectException() throws DukeException {
        Parser p = new Parser();
        p.parse("template rl");
        String line = "set r ";
        assertThrows(NotEnoughArgumentsException.class, () -> p.parse(line));
    }

    @Test
    void parseSet_negativeResistance_expectException() throws DukeException {
        Parser p = new Parser();
        p.parse("template rl");
        String line = "set r -500";
        assertThrows(InvalidNumberException.class, () -> p.parse(line));
    }

    @Test
    void parseSet_invalidResistance_expectException() throws DukeException {
        Parser p = new Parser();
        p.parse("template rl");
        String line = "set r yolo";
        assertThrows(InvalidNumberException.class, () -> p.parse(line));
    }

    @Test
    void parseSet_invalidComponent_expectException() throws DukeException {
        Parser p = new Parser();
        p.parse("template rl");
        String line = "set c 500";
        CircuitCommand c = (CircuitCommand) p.parse(line);
        assertTrue(c instanceof SetCircuitCommand);
    }

    @Test
    void parseAdd_addResistor_returnsAddCommand() throws DukeException {
        Parser p = new Parser();
        p.parse("template rl");
        p.parse("set r 500");
        String line = "add series r 500";
        CircuitCommand c = (CircuitCommand) p.parse(line);
        assertTrue(c instanceof AddCircuitCommand);
    }

    @Test
    void parseAdd_wrongConfig_expectException() throws DukeException {
        Parser p = new Parser();
        p.parse("template rl");
        p.parse("set r 500");
        String line = "add diagonal r 500";
        assertThrows(DukeException.class, () -> p.parse(line));
    }

    @Test
    void parseAdd_wrongComponent_expectException() throws DukeException {
        Parser p = new Parser();
        p.parse("template rl");
        p.parse("set r 500");
        String line = "add series c 500";
        CircuitCommand c = (CircuitCommand) p.parse(line);
        assertTrue(c instanceof AddCircuitCommand);
    }

    @Test
    void parseCalc_valueEff_CalculateCommand() throws DukeException {
        Parser p = new Parser();
        p.parse("template rl");
        String line = "calc ";
        String[] effValues = {"reff", "leff", "current", "power"};
        CircuitCommand c;
        for (String s : effValues) {
            c = (CircuitCommand) p.parse(line + s);
            assertTrue(c instanceof CalculateCircuitCommand);
        }

        p.parse("template rc");
        String capEff = "ceff";
        c = (CircuitCommand) p.parse(line + capEff);
        assertTrue(c instanceof CalculateCircuitCommand);

    }

    @Test
    void parseExit_bye_ExitCommand() throws DukeException {
        Parser p = new Parser();
        String line = "bye";
        Command c = p.parse(line);
        assertTrue(c instanceof ExitCommand);
    }

    @Test
    void parse_invalidCommand_expectException() {
        Parser p = new Parser();
        String line = "invaliddd";
        assertThrows(InvalidCommandException.class, () -> p.parse(line));
    }
}
