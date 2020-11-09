package seedu.revised.card.quizcard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.revised.card.Flashcard;
import seedu.revised.card.Subject;
import seedu.revised.list.ResultList;
import seedu.revised.list.SubjectList;
import seedu.revised.card.Topic;
import seedu.revised.exception.flashcardexception.NoFlashcardException;
import seedu.revised.exception.topicexception.NoTopicException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class SubjectQuizTest {
    private SubjectList subjects;
    private Result result = new Result(0, 0);
    private List<Flashcard> flashcards = new ArrayList<>();
    private List<String> incorrectAnswers = new ArrayList<>();
    private ResultList results = new ResultList(new ArrayList<>());

    @BeforeEach
    void setup() {
        subjects = new SubjectList(
                new ArrayList<>(List.of(
                        new Subject("Maths"),
                        new Subject("History"),
                        new Subject("Science")
                )));
        subjects.getList().get(1).getTopics().add(new Topic("First War"));
        subjects.getList().get(2).getTopics().add(new Topic("Evolution"));

        Flashcard first = new Flashcard("middle of solar system", "Sun");
        Flashcard second = new Flashcard("lightspeed", "speed of light");
        subjects.getList().get(2).getTopics().get(0).addFlashcard(first);
        subjects.getList().get(2).getTopics().get(0).addFlashcard(second);

    }

    @Test
    void setUpQuiz_subjectWithoutTopic_throwsNoTopicException() {
        SubjectQuiz subjectQuiz = new SubjectQuiz(subjects.getList().get(0));
        assertThrows(NoTopicException.class, subjectQuiz::setUpQuiz);

    }

    @Test
    void setUpQuiz_subjectWithTopicWithoutFlashcard_throwsNoFlashcardException() {
        SubjectQuiz subjectQuiz = new SubjectQuiz(subjects.getList().get(1));
        assertThrows(NoFlashcardException.class, subjectQuiz::setUpQuiz);

    }

    @Test
    void setUpQuiz_subjectWithTopicsAndFlashcards_checkMaxScore()
            throws NoFlashcardException, NoTopicException {
        SubjectQuiz subjectQuiz = new SubjectQuiz(subjects.getList().get(2));
        subjectQuiz.setUpQuiz();
        assertEquals(subjectQuiz.result.getMaxScore(), 2.0);

    }


}