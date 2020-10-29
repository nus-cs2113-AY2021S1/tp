package seedu.eduke8;

import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.explanation.Explanation;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Eduke8Test {
    protected static final String PLACEHOLDER_TOPIC_ONE_DESCRIPTION = "First Topic";
    protected static final String PLACEHOLDER_TOPIC_TWO_DESCRIPTION = "Second Topic";
    protected static final String PLACEHOLDER_QUESTION_ONE_DESCRIPTION = "First question description.";
    protected static final String PLACEHOLDER_QUESTION_TWO_DESCRIPTION = "Second question description.";
    protected static final String PLACEHOLDER_QUESTION_THREE_DESCRIPTION = "Second question description.";
    protected static final String PLACEHOLDER_OPTION_ONE_DESCRIPTION = "Option one";
    protected static final String PLACEHOLDER_OPTION_TWO_DESCRIPTION = "Option two";
    protected static final String PLACEHOLDER_HINT_DESCRIPTION = "Please check the textbook page 88";
    protected static final String PLACEHOLDER_EXPLANATION_DESCRIPTION =
            "Option A gives the best explanation out of all.";
    protected static final String NONSENSE_DESCRIPTION = "Nonsense";
    protected static final int TIMER = 5;

    protected static final String DATA_TEST_INVALID_PATH = "data/test/invalid.json";
    protected static final String DATA_TEST_EMPTY_JSON = "data/test/empty.json";
    protected static final String DATA_TEST_WRONG_FORMAT_JSON = "data/test/wrong_format.json";
    protected static final String DATA_TEST_MISSING_KEY_JSON = "data/test/missing_key.json";

    protected TopicList createTestTopicList() {
        Topic topic1 = createTestTopic(PLACEHOLDER_TOPIC_ONE_DESCRIPTION);
        Topic topic2 = createTestTopic(PLACEHOLDER_TOPIC_TWO_DESCRIPTION);

        ArrayList<Displayable> topics = new ArrayList<>(
                Arrays.asList(topic1, topic2)
        );

        return new TopicList(topics);
    }

    protected Topic createTestTopic(String description) {
        QuestionList questionList = createTestQuestionList();

        return new Topic(description, questionList);
    }

    protected QuestionList createTestQuestionList() {
        Question question1 = createTestQuestion(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        Question question2 = createTestQuestion(PLACEHOLDER_QUESTION_TWO_DESCRIPTION);
        Question question3 = createTestQuestion(PLACEHOLDER_QUESTION_THREE_DESCRIPTION);

        ArrayList<Displayable> questions = new ArrayList<>(
                Arrays.asList(question1, question2, question3)
        );

        return new QuestionList(questions);
    }

    protected Question createTestQuestion(String description) {
        OptionList optionList = createTestOptionList();

        Hint hint = new Hint(PLACEHOLDER_HINT_DESCRIPTION);
        Explanation explanation = new Explanation(PLACEHOLDER_EXPLANATION_DESCRIPTION);

        return new Question(description, optionList, hint, explanation);
    }

    protected OptionList createTestOptionList() {
        Option option1 = new Option(PLACEHOLDER_OPTION_ONE_DESCRIPTION);
        Option option2 = new Option(PLACEHOLDER_OPTION_TWO_DESCRIPTION);

        ArrayList<Displayable> options = new ArrayList<>(
                Arrays.asList(option1, option2)
        );

        return new OptionList(options);
    }

    protected BookmarkList createTestBookmarkList() {
        QuestionList questionList = createTestQuestionList();
        return new BookmarkList(questionList.getInnerList());
    }
}
