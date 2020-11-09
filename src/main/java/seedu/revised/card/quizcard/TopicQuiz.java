package seedu.revised.card.quizcard;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.exception.flashcardexception.NoFlashcardException;
import seedu.revised.ui.Ui;

import java.time.Instant;
import java.util.logging.Logger;

public class TopicQuiz extends Quiz {
    private static final Logger logger = Logger.getLogger(TopicQuiz.class.getName());
    private Topic topic;

    public TopicQuiz(Topic topic) {
        this.topic = topic;
        this.flashcards = topic.getFlashcards();
        this.result.setMaxScore(this.flashcards.size());
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    /**
     * Checks if the topic has flashcards. Furthermore, this method sets the maximum score for the quiz.
     *
     * @throws NoFlashcardException If there are no flashcards in this topic
     */
    public void setUpQuiz() throws NoFlashcardException {
        logger.info("Start setting up the quiz for the topic");
        if (this.flashcards.size() == 0) {
            throw new NoFlashcardException(Ui.NO_FLASHCARD_EXCEPTION);
        }
        this.result.setMaxScore(this.flashcards.size());
        logger.info("Finished setting up the quiz");
        logger.fine(String.format("Max Score of quiz: %d", this.flashcards.size()));
    }

    /**
     * Begins the quiz for the user.
     *
     * @throws NoFlashcardException If the topic has no flashcards
     */
    public void startQuiz() throws NoFlashcardException {
        logger.info("Start of the quiz");
        logger.fine(String.format("The topic being tested is  %s", this.topic));
        setUpQuiz();
        this.result.setScore(0);

        Ui.printStartTopicQuiz(this.topic);


        String answer = null;
        logger.info("Start printing the questions");
        for (Flashcard flashcard : this.flashcards) {

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
        logger.fine(String.format("The last answer is : %s", answer));

        if (!answer.equals("stop")) {
            Ui.printEndQuiz();
            Ui.printScore(this.result);
            if (this.result.getScore() < this.result.getMaxScore()) {
                Ui.printIncorrectAnswers(this.incorrectAnswers);
            }

        }

        this.topic.getResults().add(this.result);
        logger.info("End of quiz for this topic");
        logger.fine(String.format("The result for the quiz is %f", this.result.getScore()));

    }


}
