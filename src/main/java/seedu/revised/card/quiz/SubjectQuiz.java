package seedu.revised.card.quiz;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.exception.card.NoFlashCardException;
import seedu.revised.exception.card.NoTopicException;
import seedu.revised.ui.Ui;

import java.time.Instant;
import java.util.ArrayList;

public class SubjectQuiz extends Quiz {

    private Subject subject;

    public SubjectQuiz(Subject subject) {
        this.subject = subject;
        this.flashcards = new ArrayList<>();


    }

    /**
     * Transfers the flashcards from the all the topics in a subject to the SubjectQuiz class and
     * sets the maximum score of the quiz.
     *
     * @throws NoFlashCardException If the topic has no flashcards
     * @throws NoTopicException     If the subject has no topic
     */
    public void setUpQuiz() throws NoFlashCardException, NoTopicException {
        if (subject.getTopics().getList().size() == 0) {
            throw new NoTopicException();
        }
        int maxScore = 0;
        for (Topic topic : subject.getTopics().getList()) {
            this.flashcards.addAll(topic.getFlashcards());
            maxScore += topic.getFlashcards().size();

        }
        this.result.setMaxScore(maxScore);

        if (this.flashcards.size() == 0) {
            throw new NoFlashCardException();
        }
    }

    /**
     * Begins the quiz for the user.
     *
     * @param results The results of the subject
     * @throws NoFlashCardException If the topic has no flashcards
     * @throws NoTopicException     If the subject has no topic
     */
    public void startQuiz(ResultList results) throws NoFlashCardException, NoTopicException {
        setUpQuiz();

        this.result.setScore(0);
        Ui.printStartSubjectQuiz(this.subject);

        Instant end = Instant.now().plusSeconds(120);
        String answer = null;


        for (Flashcard flashcard : this.flashcards) {
            if (Instant.now().isAfter(end)) {
                break;
            }
            Ui.printQuestion(flashcard.getQuestion());
            answer = Ui.readCommand().strip();
            if (answer.equals("stop")) {
                Ui.printStopQuiz();
                Ui.printScore(this.result);
                break;
            } else {
                checkAnswer(answer, flashcard);
            }
        }
        assert answer != null;
        if (!answer.equals("stop")) {
            Ui.printEndQuiz();
            Ui.printScore(this.result);
            if (this.result.getScore() < this.result.getMaxScore()) {
                Ui.printIncorrectAnswers(this.incorrectAnswers);
            }

        }
        this.subject.getResults().add(this.result);


    }


}
