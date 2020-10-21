package seedu.eduke8.parser;

import org.junit.jupiter.api.Test;
import seedu.eduke8.command.Command;
import seedu.eduke8.command.IncorrectCommand;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuParserTest {
    @Test
    public void menuParser_wrongStringInput_expectIncorrectCommand() {
        Ui ui = new Ui();
        Hint hint = new Hint("description");
        OptionList optionList = new OptionList();
        ArrayList<Displayable> questions = new ArrayList<>();
        Question question = new Question("description", optionList, hint);
        questions.add(question);
        QuestionList questionList = new QuestionList(questions);
        Topic topic = new Topic("description", questionList);
        ArrayList<Displayable> topics = new ArrayList<>();
        topics.add(topic);
        TopicList topicList = new TopicList(topics);
        MenuParser menuParser = new MenuParser();

        try {
            Command badCommand = menuParser.parseCommand(topicList, "quiz /twrongtopic /nbadnnum");
            assertTrue(badCommand instanceof IncorrectCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
