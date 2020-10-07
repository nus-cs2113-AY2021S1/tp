package seedu.eduke8.question;

public interface QuestionListInterface {
    // ArrayList<QuestionInterface> getQuestions();

    void setQuizQuestions(int numberOfQuestionsForQuiz);

    // String addQuestion(QuestionInterface question);

    // void deleteQuestion(QuestionInterface question);

    int getQuestionCount();

    void markQuestionAsAttempted();

    QuestionInterface getNextQuestion();

    boolean areAllQuestionsAnswered();

}
