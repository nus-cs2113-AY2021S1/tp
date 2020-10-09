package seedu.eduke8.question;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;

import java.util.ArrayList;
import java.util.Random;

public class QuestionList implements DisplayableList {
    private ArrayList<Displayable> allQuestions;  // list of questions for the particular topic
    private ArrayList<Question> quizQuestions;
    private int currentQuestionNumber;

    private static final Random RANDOM = new Random();

    public QuestionList(ArrayList<Displayable> questions) {
        allQuestions = questions;
        quizQuestions = null;
        currentQuestionNumber = 0;
    }

    public void setQuizQuestions(int numberOfQuestionsForQuiz) {
        int numberOfQuestionsSelected = 0;
        // prevent repeated questions from being selected again
        ArrayList<Integer> integersChosen = new ArrayList<>();

        while (numberOfQuestionsSelected < numberOfQuestionsForQuiz) {
            // get a random question that is within the bounds of the size of the available question list
            int randomQuestionIndex = RANDOM.nextInt(allQuestions.size() - 1);

            // if the number is already selected - the question is already selected, we re run the loop
            // to select another random number
            if (integersChosen.contains(randomQuestionIndex)) {
                continue;
            }

            // choose a random question from the question list and add it to the questions for quiz
            quizQuestions.add((Question) allQuestions.get(randomQuestionIndex));
            numberOfQuestionsSelected++;
            integersChosen.add(randomQuestionIndex);
        }
    }

    public int getNumberOfQuestionsInQuiz() {
        return quizQuestions.size();
    }

    public int getNumberOfQuestionsInTopic() {
        return allQuestions.size();
    }

    public Question getNextQuestion() {
        // Gets current question and increment count for number of questions shown to user
        return quizQuestions.get(currentQuestionNumber++);
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public boolean areAllQuestionsAnswered() {
        return currentQuestionNumber == quizQuestions.size();
    }

    @Override
    public ArrayList<Displayable> getInnerList() {
        return allQuestions;
    }

    @Override
    public void add(Displayable question) {
        allQuestions.add(question);
    }

    @Override
    public void delete(int index) {
        allQuestions.remove(index);
    }
}
