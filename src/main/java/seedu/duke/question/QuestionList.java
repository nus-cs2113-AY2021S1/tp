package seedu.duke.question;

import java.util.ArrayList;
import java.util.Random;

public class QuestionList implements QuestionListInterface {
    private ArrayList<Question> questions;
    private ArrayList<Question> questionsForQuiz;
    private int questionsAnswered;

    private static final Random RANDOM = new Random();

    public QuestionList(ArrayList<Question> loadedQuestions) {
        questions = loadedQuestions;
        questionsForQuiz = null;
        questionsAnswered = 0;
    }


    @Override
    public ArrayList<Question> getChosenNumberOfRandomQuestion(int numberOfQuestionsForQuiz) {
        int numberOfQuestionsSelected = 0;
        ArrayList<Integer> integersChosen = new ArrayList<>();

        while (numberOfQuestionsSelected < numberOfQuestionsForQuiz) {
            // get a random question that is within the bounds of the size of the available question list
            int randomQuestionIndex = RANDOM.nextInt(questions.size() - 1);

            // if the number is already selected - the question is already selected, we re run the loop
            // to select another random number
            if (integersChosen.contains(randomQuestionIndex)) {
                continue;
            }

            // choose a random question from the question list and add it to the questions for quiz
            questionsForQuiz.add(questions.get(randomQuestionIndex));
            numberOfQuestionsSelected++;
            integersChosen.add(randomQuestionIndex);
        }

        return questionsForQuiz;
    }


    @Override
    public int getQuestionCount() {
        return questionsForQuiz.size();
    }

    // should throw indexOutOfBounds exception as even though we might
    // do a check in the program that calls this method, we still need the exception
    // in case anything happens
    @Override
    public void markQuestionAsAttempted() {
        // mark the current question as attempted if method is called in the Quiz class
        questionsForQuiz.get(questionsAnswered).setAsAttempted();
        // the last increment will make it be the same as the number of questions there are
        // for instance, if there are 5 questions, the last call of this method will set the
        // question at index 4 to be attempted, and will increment the variable questionsAnswered
        // to 5, while not running again, since no more questions left.
        questionsAnswered++;
    }

    @Override
    public QuestionInterface getNextQuestion() {
        // will be called after markQuestionAsAttempted(), so naturally the
        // questionsAnswered would have incremented
        return questionsForQuiz.get(questionsAnswered);
    }

    @Override
    public Boolean allQuestionsAnswered() {
        return questionsAnswered == questionsForQuiz.size();
    }
}
