package seedu.eduke8.topic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.explanation.Explanation;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TopicListTest {

    private static final String TOPIC_ONE_DESCRIPTION = "First Topic";
    private static final String TOPIC_TWO_DESCRIPTION = "Second Topic";
    private static final String TOPIC_THREE_DESCRIPTION = "Third Topic";
    private static final String QUESTION_ONE_DESCRIPTION = "First Question";
    private static final String QUESTION_TWO_DESCRIPTION = "Second Question";
    private static final String QUESTION_THREE_DESCRIPTION = "Third Question";
    private static final String HINT = "Hint";
    private static final String EXPLANATION = "Explanation";
    private static final int DEFAULT_TOPIC_COUNT = 2;

    @Test
    void getCount_TwoTopics_returnsCountOfTwo() {
        TopicList topicList = createTestTopicList();

        assertEquals(DEFAULT_TOPIC_COUNT, topicList.getCount());
    }


    @Test
    void find_topicListWithTwoTopics_returnsFirstTopic() throws Eduke8Exception {
        TopicList topicList = createTestTopicList();
        Displayable topic1 = topicList.find(TOPIC_ONE_DESCRIPTION);

        assertEquals(TOPIC_ONE_DESCRIPTION, topic1.getDescription());
    }


    private TopicList createTestTopicList() {
        ArrayList<Displayable> topics = new ArrayList<>();
        Topic topic1 = createTestTopic(TOPIC_ONE_DESCRIPTION);
        Topic topic2 = createTestTopic(TOPIC_TWO_DESCRIPTION);

        topics.add(topic1);
        topics.add(topic2);

        TopicList topicList = new TopicList(topics);
        return topicList;
    }

    private Topic createTestTopic(String description) {
        QuestionList questionList = createQuestionList();
        Topic topic = new Topic(description, questionList);

        return topic;
    }

    private QuestionList createQuestionList() {
        ArrayList<Displayable> questions = new ArrayList<>();
        Question question1 = createTestQuestion(QUESTION_ONE_DESCRIPTION);
        Question question2 = createTestQuestion(QUESTION_TWO_DESCRIPTION);
        Question question3 = createTestQuestion(QUESTION_THREE_DESCRIPTION);

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        QuestionList questionList = new QuestionList(questions);
        return questionList;
    }

    private Question createTestQuestion(String description) {
        Option option1 = new Option("test1");
        Option option2 = new Option("test2");
        ArrayList<Displayable> optionsArrayList = new ArrayList();
        optionsArrayList.add(option1);
        optionsArrayList.add(option2);
        OptionList optionList = new OptionList(optionsArrayList);

        Hint hint = new Hint(HINT);
        Explanation explanation = new Explanation(EXPLANATION);

        Question question = new Question(description, optionList, hint, explanation);

        return question;
    }
}
