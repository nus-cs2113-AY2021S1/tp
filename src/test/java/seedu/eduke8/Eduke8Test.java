package seedu.eduke8;

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

    protected TopicList createTestTopicList() {
        ArrayList<Displayable> topics = new ArrayList<>();
        Topic topic1 = createTestTopic(PLACEHOLDER_TOPIC_ONE_DESCRIPTION);
        Topic topic2 = createTestTopic(PLACEHOLDER_TOPIC_TWO_DESCRIPTION);

        topics.add(topic1);
        topics.add(topic2);

        return new TopicList(topics);
    }

    protected Topic createTestTopic(String description) {
        QuestionList questionList = createQuestionList();

        return new Topic(description, questionList);
    }

    protected QuestionList createQuestionList() {
        Question question1 = createTestQuestion(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        Question question2 = createTestQuestion(PLACEHOLDER_QUESTION_TWO_DESCRIPTION);
        Question question3 = createTestQuestion(PLACEHOLDER_QUESTION_THREE_DESCRIPTION);

        ArrayList<Displayable> questions = new ArrayList<>(
                Arrays.asList(question1, question2, question3)
        );

        return new QuestionList(questions);
    }

    protected Question createTestQuestion(String description) {
        OptionList optionList = createOptionList();

        Hint hint = new Hint(PLACEHOLDER_HINT_DESCRIPTION);
        Explanation explanation = new Explanation(PLACEHOLDER_EXPLANATION_DESCRIPTION);

        return new Question(description, optionList, hint, explanation);
    }

    protected OptionList createOptionList() {
        Option option1 = new Option(PLACEHOLDER_OPTION_ONE_DESCRIPTION);
        Option option2 = new Option(PLACEHOLDER_OPTION_TWO_DESCRIPTION);
        ArrayList<Displayable> options = new ArrayList<>(
                Arrays.asList(option1, option2)
        );

        return new OptionList(options);
    }

    // Creates a question list that holds two questions, each with a unique description
    protected QuestionList createQuestionListWithTwoUniqueQuestions() {
        Question question1 = createTestQuestionWithCustomDescription(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        Question question2 = createTestQuestionWithCustomDescription(PLACEHOLDER_QUESTION_TWO_DESCRIPTION);

        ArrayList<Displayable> questions = new ArrayList<>(
                Arrays.asList(question1, question2)
        );

        return new QuestionList(questions);
    }

    // Creates a question with a specific description, 2 standard options, 1 standard explanation and a standard hint
    protected Question createTestQuestionWithCustomDescription(String description) {
        OptionList optionList = createOptionList();

        Hint hint = new Hint(PLACEHOLDER_HINT_DESCRIPTION);

        Explanation explanation = new Explanation(PLACEHOLDER_EXPLANATION_DESCRIPTION);

        return new Question(description, optionList, hint, explanation);
    }

    // Creates a question list that holds two questions, each with a unique description
    protected QuestionList createQuestionListWithThreeUniqueQuestions() {
        Question question1 = createTestQuestionWithCustomDescription(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        Question question2 = createTestQuestionWithCustomDescription(PLACEHOLDER_QUESTION_TWO_DESCRIPTION);
        Question question3 = createTestQuestionWithCustomDescription(PLACEHOLDER_QUESTION_THREE_DESCRIPTION);

        ArrayList<Displayable> questions = new ArrayList<>(
                Arrays.asList(question1, question2, question3)
        );

        return new QuestionList(questions);
    }
}
