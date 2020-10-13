package seedu.duke.card.quiz;

import seedu.duke.card.Flashcard;
import seedu.duke.card.Subject;
import seedu.duke.card.Topic;
import seedu.duke.exception.NoFlashCardException;
import seedu.duke.exception.NoTopicException;
import seedu.duke.ui.Ui;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SubjectQuiz extends Quiz {

    private Subject subject;

    public SubjectQuiz(Subject subject) {
        this.subject = subject;
        this.flashcards = new ArrayList<>();


    }

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
        assert answer!=null;
        if (!answer.equals("stop")) {
            Ui.printEndQuiz();
            Ui.printScore(this.result);
            if(this.result.getScore() < this.result.getMaxScore()) {
                Ui.printIncorrectAnswers(this.incorrectAnswers);
            }

        }
        this.subject.getResults().add(this.result);




    }


}
