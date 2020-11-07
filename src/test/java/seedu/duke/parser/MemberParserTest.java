package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberParserTest {

    MemberParser memberParser = new MemberParser();
    ProjectManager projectManager = new ProjectManager();
    Hashtable<String, String> parameters = new Hashtable<>();

    @Test
    void parseMultipleCommandsExceptions_missingName_returnsDukeException() {
        String add = "add";
        assertThrows(DukeException.class, () ->
            memberParser.parseMultipleCommandsExceptions(parameters, add, projectManager));
    }
}