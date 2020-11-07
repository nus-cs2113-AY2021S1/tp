package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class HelpParserTest {

    HelpParser helpParser = new HelpParser();
    ProjectManager projectManager = new ProjectManager();
    Hashtable<String, String> parameters = new Hashtable<>();

    @Test
    void parseMultipleCommandsExceptions_helpActionIsNotAnInteger_returnsDukeException() {
        String notNumber = "not number";
        assertThrows(DukeException.class, () ->
            helpParser.parseMultipleCommandsExceptions(parameters, notNumber, projectManager));
    }

    @Test
    void parseMultipleCommandsExceptions_helpNumberIsNotinList_returnsDukeException() {
        String number = "6";
        assertThrows(DukeException.class, () ->
            helpParser.parseMultipleCommandsExceptions(parameters, number, projectManager));
    }
}