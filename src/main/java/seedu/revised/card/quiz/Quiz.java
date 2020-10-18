package seedu.revised.card.quiz;

import seedu.revised.card.Flashcard;
import seedu.revised.exception.card.NoFlashCardException;
import seedu.revised.exception.card.NoTopicException;

import java.util.ArrayList;
import java.util.List;

public abstract class Quiz {
    protected Result result = new Result(0, 0);
    protected List<Flashcard> flashcards;
    protected List<String> incorrectAnswers = new ArrayList<>();

    public abstract void startQuiz(ResultList resultList) throws NoFlashCardException, NoTopicException;

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

        if (flashcard.getAnswer().equalsIgnoreCase(answer)) {
            double updatedResult = this.result.getScore() + 1.0;
            this.result.updateResult(updatedResult);

        } else {
            this.incorrectAnswers.add(flashcard.getQuestion());
            this.incorrectAnswers.add(flashcard.getAnswer());
            this.incorrectAnswers.add(answer);
        }
    }

}
