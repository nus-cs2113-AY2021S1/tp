package seedu.duke;

import java.util.ArrayList;

public interface QuestionListInterface {
    public ArrayList<QuestionInterface> getQuestions();
    public ArrayList<QuestionInterface> getNumberOfRandomQuestion(int NumberOfQuestions);
    public String addQuestion(QuestionInterface question);
    public void deleteQuestion(QuestionInterface question);
    public int getQuestionCount();
    public void markQuestionAsAttempted();
    public QuestionInterface getNextQuestion();
    public Boolean allQuestionsAnswered();
}
