package seedu.eduke8.question;

import seedu.eduke8.common.Displayable;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

public class QuizQuestionList {
    private ArrayList<Question> quizQuestions;
    private int currentQuestionNumber;

    private static final Random RANDOM = new Random();

    public QuizQuestionList() {
        quizQuestions = null;
        currentQuestionNumber = 0;
    }

    public void setQuizQuestions(int numberOfQuestionsForQuiz, ArrayList<Displayable> questionsInTopic) {
        // Logger logger = Logger.getLogger("main");

        int numberOfQuestionsSelected = 0;

        // prevent repeated questionsInTopic from being selected again
        ArrayList<Integer> integersChosen = new ArrayList<>();

        while (numberOfQuestionsSelected < numberOfQuestionsForQuiz) {
            // get a random question that is within the bounds of the size of the available question list
            int randomQuestionIndex = RANDOM.nextInt(questionsInTopic.size() - 1);

            // if the number is already selected - the question is already selected, we re run the loop
            // to select another random number
            if (integersChosen.contains(randomQuestionIndex)) {
                // logger.info("chosen a repeated question");
                continue;
            }

            // choose a random question from the question list and add it to the questionsInTopic for quiz
            quizQuestions.add((Question) questionsInTopic.get(randomQuestionIndex));
            numberOfQuestionsSelected++;
            integersChosen.add(randomQuestionIndex);
        }
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
}
