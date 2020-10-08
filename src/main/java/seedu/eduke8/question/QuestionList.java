package seedu.eduke8.question;

import java.util.ArrayList;
import java.util.Random;

public class QuestionList implements QuestionListInterface {
    private ArrayList<QuestionInterface> allQuestions;  // list of questions for the particular topic
    private ArrayList<QuestionInterface> quizQuestions;
    private int currentQuestionNumber;

    private static final Random RANDOM = new Random();

    public QuestionList(ArrayList<QuestionInterface> loadedQuestions) {
        allQuestions = loadedQuestions;
        quizQuestions = null;
        currentQuestionNumber = 0;
    }


    @Override
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
            quizQuestions.add(allQuestions.get(randomQuestionIndex));
            numberOfQuestionsSelected++;
            integersChosen.add(randomQuestionIndex);
        }
    }


    @Override
    public int getNumberOfQuestionsInQuiz() {
        return quizQuestions.size();
    }

    @Override
    public int getNumberOfQuestionsInTopic() {
        return allQuestions.size();
    }

    @Override
    public QuestionInterface getNextQuestion() {
        // Gets current question and increment count for number of questions shown to user
        return quizQuestions.get(currentQuestionNumber++);
    }

    @Override
    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    @Override
    public boolean areAllQuestionsAnswered() {
        return currentQuestionNumber == quizQuestions.size();
    }
}
