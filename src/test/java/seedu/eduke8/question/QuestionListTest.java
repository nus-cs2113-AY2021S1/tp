package seedu.eduke8.question;

import org.junit.jupiter.api.Test;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.explanation.Explanation;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionListTest {

    private static final String PLACEHOLDER_QUESTION_ONE_DESCRIPTION = "First question description.";
    private static final String PLACEHOLDER_QUESTION_TWO_DESCRIPTION = "Second question description.";
    private static final String PLACEHOLDER_QUESTION_THREE_DESCRIPTION = "Second question description.";
    private static final String PLACEHOLDER_HINT_DESCRIPTION = "Please check the textbook page 88";
    private static final String PLACEHOLDER_EXPLANATION_DESCRIPTION = "Option A gives the best explanation out of all.";
    private static final int DEFAULT_QUESTION_COUNT = 2;


    @Test
    void getCount_twoQuestions_returnsCountOfTwo() {
        QuestionList questionList = createQuestionListWithTwoUniqueQuestions();

        assertEquals(DEFAULT_QUESTION_COUNT, questionList.getCount());
    }

    @Test
    void find_questionListWithTwoQuestions_returnsFirstQuestion() throws Eduke8Exception {
        QuestionList questionList = createQuestionListWithTwoUniqueQuestions();

        Displayable firstQuestionOfQuestionList = questionList.find(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);

        assertEquals(PLACEHOLDER_QUESTION_ONE_DESCRIPTION, firstQuestionOfQuestionList.getDescription());
    }


    // Creates a question list that holds two questions, each with a unique description
    private QuestionList createQuestionListWithTwoUniqueQuestions() {
        ArrayList<Displayable> questions = new ArrayList<>();
        Question question1 = createTestQuestionWithCustomDescription(PLACEHOLDER_QUESTION_ONE_DESCRIPTION);
        Question question2 = createTestQuestionWithCustomDescription(PLACEHOLDER_QUESTION_TWO_DESCRIPTION);

        questions.add(question1);
        questions.add(question2);

        QuestionList questionList = new QuestionList(questions);
        return questionList;
    }

    // Creates a question with a specific description, 2 standard options, 1 standard explanation and a standard hint
    private Question createTestQuestionWithCustomDescription(String description) {
        Option option1 = new Option("test1");
        Option option2 = new Option("test2");
        ArrayList<Displayable> optionsArrayList = new ArrayList<>();
        optionsArrayList.add(option1);
        optionsArrayList.add(option2);
        OptionList optionList = new OptionList(optionsArrayList);

        String inputHintDescription = PLACEHOLDER_HINT_DESCRIPTION;
        Hint hint = new Hint(inputHintDescription);

        String inputExplanationDescription = PLACEHOLDER_EXPLANATION_DESCRIPTION;
        Explanation explanation = new Explanation(inputExplanationDescription);

        Question question = new Question(description, optionList, hint, explanation);

        return question;
    }
}