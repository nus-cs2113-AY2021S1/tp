package seedu.eduke8.parser;

import org.junit.jupiter.api.Test;
import seedu.eduke8.command.Command;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MenuParserTest {
    @Test
    public void menuParser_wrongStringInput_expectEduk8Exception() {
        Ui ui = new Ui();
        TopicList topicList = new TopicList(null);
        MenuParser menuParser = new MenuParser();

        try {
            Command badCommand = menuParser.parseCommand(topicList, "quiz /twrongtopic /nbadnnum");
            assertThrows(Eduke8Exception.class, () -> {
                badCommand.execute(topicList, ui);
            });
        } catch (Eduke8Exception ee) {
            ee.printStackTrace();
        }
    }
}
