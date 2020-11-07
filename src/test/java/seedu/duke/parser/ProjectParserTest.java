package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ProjectParserTest {

    ProjectParser projectParser = new ProjectParser();
    ProjectManager projectManager = new ProjectManager();
    Hashtable<String, String> parameters = new Hashtable<>();

    @Test
    void parseMultipleCommandsExceptions_missingParameters_returnsDukeException() {
        String create = "create";
        assertThrows(DukeException.class, () ->
            projectParser.parseMultipleCommandsExceptions(parameters, create, projectManager));
    }

}