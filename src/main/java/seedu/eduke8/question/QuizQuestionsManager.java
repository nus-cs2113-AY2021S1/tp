package seedu.eduke8.question;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizQuestionsManager {
    public static final String INVALID_QUIZ_QUESTION_NUMBER = "Number of quiz questions must be more than 1";
    public static final String INSUFFICIENT_TOPIC_QUESTIONS_FOR_QUIZ =
            "There is not enough questions in the topic for the quiz!";

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    private ArrayList<Question> quizQuestions = new ArrayList<>();
    private int currentQuestionNumber;

    private static final Random RANDOM = new Random();

    public QuizQuestionsManager(int numberOfQuestionsForQuiz,
                                ArrayList<Displayable> questionsInTopic) throws Eduke8Exception {
        currentQuestionNumber = 0;
        setQuizQuestions(numberOfQuestionsForQuiz, questionsInTopic);
    }

    public void setQuizQuestions(int numberOfQuestionsForQuiz,
                                 ArrayList<Displayable> questionsInTopic) throws Eduke8Exception {

        if (numberOfQuestionsForQuiz <= 0) {
            throw new Eduke8Exception(INVALID_QUIZ_QUESTION_NUMBER);
        }

        if (numberOfQuestionsForQuiz > questionsInTopic.size()) {
            throw new Eduke8Exception(INSUFFICIENT_TOPIC_QUESTIONS_FOR_QUIZ);
        }

        // Stores the questions' indexes selected from the topic question list
        ArrayList<Integer> integersChosen = new ArrayList<>();

        while (quizQuestions.size() < numberOfQuestionsForQuiz) {
            // Gets a random question that is within the bounds of the size of the available question list
            int randomQuestionIndex = RANDOM.nextInt(questionsInTopic.size());

            // To ensure we do not pick the same question again
            if (integersChosen.contains(randomQuestionIndex)) {
                LOGGER.log(Level.INFO, "Chosen a repeated question");
                continue;
            }

            // choose a random question from the question list and add it to the questionsInTopic for quiz
            quizQuestions.add((Question) questionsInTopic.get(randomQuestionIndex));
            integersChosen.add(randomQuestionIndex);
        }
    }

    public Question getNextQuestion() {
        // Automatically increases question count when a question is shown to the user
        return quizQuestions.get(currentQuestionNumber++);
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public boolean areAllQuestionsAnswered() {
        return currentQuestionNumber == quizQuestions.size();
    }

    public int getQuizQuestionsCount() {
        return quizQuestions.size();
    }
}
