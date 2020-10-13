package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.project.Project;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parser_invalidCommands_returnsInvalidMessage() {
        ArrayList<Project> projectList = new ArrayList<>(10);
        Parser p = new Parser();
        String userInput = "projekt /create -title Duke -desc lmao -end 11102020 -sd 7";
        String actualOutput = p.parser(userInput, projectList);
        String expectedOutput = "Invalid command!";
        assertEquals(actualOutput, expectedOutput);
    }
}