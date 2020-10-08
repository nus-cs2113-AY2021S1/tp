package seedu.eduke8.question;

public interface QuestionListInterface {
    // ArrayList<QuestionInterface> getQuestions();

    void setQuizQuestions(int numberOfQuestionsForQuiz);

    int getNumberOfQuestionsInQuiz();

    int getNumberOfQuestionsInTopic();

    QuestionInterface getNextQuestion();

    int getCurrentQuestionNumber();

    boolean areAllQuestionsAnswered();
}
