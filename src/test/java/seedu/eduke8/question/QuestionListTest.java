package seedu.eduke8.question;

import org.junit.jupiter.api.Test;
import seedu.eduke8.common.Displayable;
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
    private static final int DEFAULT_QUESTION_COUNT = 2;


    @Test
    void getCount_twoQuestions_returnsCountOfTwo() {
        QuestionList questionList = createQuestionListWithTwoUniqueQuestions();

        assertEquals(DEFAULT_QUESTION_COUNT, questionList.getCount());
    }


    @Test
    void add_thirdQuestionToQuestionList_returnsCountOfThree() {
        QuestionList questionList = createQuestionListWithTwoUniqueQuestions();

        Question thirdQuestion = createTestQuestionWithCustomDescription(PLACEHOLDER_QUESTION_THREE_DESCRIPTION);
        questionList.add(thirdQuestion);

        assertEquals(DEFAULT_QUESTION_COUNT + 1, questionList.getCount());
    }

    @Test
    void delete_questionListWithTwoQuestions_returnsCountOfOne() {
        QuestionList questionList = createQuestionListWithTwoUniqueQuestions();

        questionList.delete(1);

        assertEquals(DEFAULT_QUESTION_COUNT - 1, questionList.getCount());
    }

    @Test
    void find_questionListWithTwoQuestions_returnsFirstQuestion() {
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

    // Creates a question with a specific description, 2 standard options and a standard hint
    private Question createTestQuestionWithCustomDescription(String description) {
        Option option1 = new Option("test1");
        Option option2 = new Option("test2");
        OptionList optionList = new OptionList();
        optionList.add(option1);
        optionList.add(option2);

        String inputHintDescription = PLACEHOLDER_HINT_DESCRIPTION;
        Hint hint = new Hint(inputHintDescription);

        Question question = new Question(description, optionList, hint);

        return question;
    }
}