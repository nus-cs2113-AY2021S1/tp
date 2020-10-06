package seedu.duke.question;

import java.util.ArrayList;

public interface QuestionListInterface {
    // ArrayList<QuestionInterface> getQuestions();

    ArrayList<Question> getChosenNumberOfRandomQuestion(int numberOfQuestionsForQuiz);

    // String addQuestion(QuestionInterface question);

    // void deleteQuestion(QuestionInterface question);

    int getQuestionCount();

    void markQuestionAsAttempted();

    QuestionInterface getNextQuestion();

    Boolean allQuestionsAnswered();

}
