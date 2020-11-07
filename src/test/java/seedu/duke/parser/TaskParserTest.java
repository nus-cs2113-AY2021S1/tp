package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskParserTest {

    TaskParser taskParser = new TaskParser();
    ProjectManager projectManager = new ProjectManager();
    Hashtable<String, String> parameters = new Hashtable<>();

    @Test
    void parseMultipleCommandsExceptions_missingParameters_returnsDukeException() {
        String add = "add";
        assertThrows(DukeException.class, () ->
            taskParser.parseMultipleCommandsExceptions(parameters, add, projectManager));
    }

}