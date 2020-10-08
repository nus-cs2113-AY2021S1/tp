package seedu.eduke8.question;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionTest {

    @Test
    void getQuestionDescription_questionDescription_returnsQuestionDescription() {
        String inputQuestionDescription = "This is a question description.";

        Question question = new Question(inputQuestionDescription, null, null);
        assertEquals(inputQuestionDescription, question.getQuestionDescription());
    }

    @Test
    void getOptions() {
    }

    @Test
    void getHint() {
    }

    @Test
    void hintWasShown() {
    }

    @Test
    void wasAnsweredCorrectly() {
    }

    @Test
    void wasAttempted() {
    }
}