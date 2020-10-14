package seedu.eduke8.parser;

import org.junit.jupiter.api.Test;
import seedu.eduke8.command.Command;
import seedu.eduke8.command.IncorrectCommand;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuParserTest {
    @Test
    public void menuParser_wrongStringInput_expectIncorrectCommand() {
        Ui ui = new Ui();
        TopicList topicList = new TopicList(null);
        MenuParser menuParser = new MenuParser();

        try {
            Command badCommand = menuParser.parseCommand(topicList, "quiz /twrongtopic /nbadnnum");
            assertTrue(badCommand instanceof IncorrectCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
