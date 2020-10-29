package seedu.eduke8.question;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.eduke8.exception.ExceptionMessages.ERROR_QUIZ_INSUFFICIENT_TOPIC_QUESTIONS;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_QUIZ_INVALID_QUESTION_NUMBER;

public class QuizQuestionsManager {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final Random RANDOM = new Random();

    private ArrayList<Question> quizQuestions;
    private int currentQuestionNumber;


    /**
     * Constructs a QuizQuestionsManager object with the user's desired number of questions in the quiz
     * and his / her choice of topic.
     * If the inputs are valid, the questions for the quiz will be selected at random from all of the questions
     * in the specified topic.
     *
     * @param numberOfQuestionsForQuiz User's desired number of questions for the quiz attempt.
     * @param questionsInTopic An ArrayList of all of the questions from the topic the user has chosen for the quiz
     * @throws Eduke8Exception If the number of questions specified for the quiz is more than the number of
     *     questions in the topic, or zero or less.
     */
    public QuizQuestionsManager(int numberOfQuestionsForQuiz,
                                ArrayList<Displayable> questionsInTopic) throws Eduke8Exception {
        currentQuestionNumber = 0;
        quizQuestions = new ArrayList<>();
        setQuizQuestions(numberOfQuestionsForQuiz, questionsInTopic);
    }

    private void setQuizQuestions(int numberOfQuestionsForQuiz,
                                 ArrayList<Displayable> questionsInTopic) throws Eduke8Exception {

        if (numberOfQuestionsForQuiz <= 0) {
            throw new Eduke8Exception(ERROR_QUIZ_INVALID_QUESTION_NUMBER);
        }

        if (numberOfQuestionsForQuiz > questionsInTopic.size()) {
            throw new Eduke8Exception(ERROR_QUIZ_INSUFFICIENT_TOPIC_QUESTIONS);
        }

        setRandomQuestions(numberOfQuestionsForQuiz, questionsInTopic);
    }


    /**
     * Returns the next question in the quiz and indicates that another question has been shown to the user by
     * incrementing the current question number of the quiz.
     *
     * @return Next question in the quiz.
     */
    public Question getNextQuestion() {
        // Automatically increases question count when a question is shown to the user
        return quizQuestions.get(currentQuestionNumber++);
    }

    /**
     * Returns the current question number as an indication of how many questions have been done by the user in
     * the current quiz instance.
     *
     * @return Current question number.
     */
    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    /**
     * Returns an indication of whether the user has attempted all the questions in the quiz.
     * The boolean variable returned is used to determine if the quiz should end.
     *
     * @return Boolean variable indicating if user has attempted all the questions in the quiz.
     */
    public boolean areAllQuestionsAnswered() {
        return currentQuestionNumber == quizQuestions.size();
    }

    public int getQuizQuestionsCount() {
        return quizQuestions.size();
    }

    private void setRandomQuestions(int numberOfQuestionsForQuiz, ArrayList<Displayable> questionsInTopic) {
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
}
