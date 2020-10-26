package seedu.revised.card.quiz;

import seedu.revised.card.Flashcard;
import seedu.revised.exception.flashcard.NoFlashcardException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.list.ResultList;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class Quiz {
    private static final Logger logger = Logger.getLogger(Quiz.class.getName());
    protected Result result = new Result(0, 0);
    protected List<Flashcard> flashcards;
    protected List<String> incorrectAnswers = new ArrayList<>();

    public abstract void startQuiz() throws NoFlashcardException, NoTopicException;

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    /**
     * Checks the given answer for its correctness. If the answer is wrong, the question, the correct answer
     * and the wrong answer given by the user is added to the incorrectAnswers List.
     *
     * @param answer    The answer given by the user
     * @param flashcard The flashcard for which the user is currently answering the questions
     */
    public void checkAnswer(String answer, Flashcard flashcard) {

        logger.info("Checks if the answer that the user gave was correct.");
        if (flashcard.getAnswer().equalsIgnoreCase(answer)) {
            logger.info("The current score of the user is increased by one.");
            double updatedResult = this.result.getScore() + 1.0;
            this.result.updateResult(updatedResult);

        } else {
            logger.info("The current score remains the same while the contents of the flashcard and the user's "
                    + "answer are added to the incorrectAnswers list");
            this.incorrectAnswers.add(flashcard.getQuestion());
            this.incorrectAnswers.add(flashcard.getAnswer());
            this.incorrectAnswers.add(answer);
        }
        logger.info("Finish checking the accuracy of the answer.");
    }

}
