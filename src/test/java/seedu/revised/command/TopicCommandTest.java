package seedu.revised.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.subject.*;
import seedu.revised.command.topic.*;
import seedu.revised.exception.subject.InvalidSubjectException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.exception.subject.RepeatedSubjectException;
import seedu.revised.list.TopicList;
import seedu.revised.exception.topic.InvalidTopicException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.exception.topic.RepeatedTopicException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TopicCommandTest {
    private Subject subject;
    private TopicList topics;
    private AddTopicCommand addCommand;
    private DeleteTopicCommand deleteCommand;
    private FindTopicCommand findCommand;
    private AccessTopicCommand accessCommand;
    private ListTopicCommand listCommand;

    @BeforeEach
    void setUp() {
        List topicList = new ArrayList<Topic>(List.of(
                new Topic("Distance"),
                new Topic("Speed"),
                new Topic("Time")
        ));
        topics = new TopicList(topicList);
        subject = new Subject("Maths", topicList);
    }

    @Test
    public void accessSubjectCommand_NoTopicInputWithSpace_throwsException() {
        accessCommand = new AccessTopicCommand("topic ");
        assertThrows(IndexOutOfBoundsException.class, () -> accessCommand.execute(subject));
    }

    @Test
    public void accessTopicCommand_NoTopicInputWithoutSpace_throwsException() {
        accessCommand = new AccessTopicCommand("topic");
        assertThrows(IndexOutOfBoundsException.class, () -> accessCommand.execute(subject));
    }

    @Test
    public void accessTopicCommand_TopicNotPresent_throwsException() {
        accessCommand = new AccessTopicCommand("topic Algebra");
        assertThrows(NoTopicException.class, () -> accessCommand.execute(subject));
    }

    @Test
    public void addTopicCommand_validCommand_returnsSubjectTitle() throws RepeatedTopicException, InvalidTopicException {
        addCommand = new AddTopicCommand("add Geometry");
        addCommand.execute(subject);
        assertEquals("Geometry", topics.getList().get(3).getTitle());
    }

    @Test
    public void addTopicCommand_SameTopic_throwsException() {
        addCommand = new AddTopicCommand("add Distance");
        assertThrows(RepeatedTopicException.class, () -> addCommand.execute(subject));
    }

    @Test
    public void addTopicCommand_NoTopicInputWithSpace_throwsException() {
        addCommand = new AddTopicCommand("add ");
        assertThrows(InvalidTopicException.class, () -> addCommand.execute(subject));
    }

    @Test
    public void addTopicCommand_NoTopicInputWithoutSpace_throwsException() {
        addCommand = new AddTopicCommand("add");
        assertThrows(InvalidTopicException.class, () -> addCommand.execute(subject));
    }

    @Test
    public void deleteTopicCommand_validCommand_returnsIndex() {
        deleteCommand = new DeleteTopicCommand("delete 2");
        deleteCommand.execute(subject);
        assertEquals(Integer.valueOf("2"), subject.getTopics().getList().size());
    }

    @Test
    public void deleteTopicCommand_InputNonIntegerAsIndex_throwsException() {
        deleteCommand = new DeleteTopicCommand("delete Distance");
        assertThrows(NumberFormatException.class, () -> deleteCommand.execute(subject));
    }

    @Test
    public void findTopicCommand_validCommand_executesMethod() throws InvalidTopicException {
        findCommand = new FindTopicCommand("find Speed");
        findCommand.execute(subject);
    }

    @Test
    public void findTopicCommand_NoTopicInputWithSpace_throwsException() {
        findCommand = new FindTopicCommand("find ");
        assertThrows(InvalidTopicException.class, () -> findCommand.execute(subject));
    }

    @Test
    public void findTopicCommand_NoTopicInputWithoutSpace_throwsException() {
        findCommand = new FindTopicCommand("find");
        assertThrows(InvalidTopicException.class, () -> findCommand.execute(subject));
    }

    @Test
    public void listTopicCommand_validCommand_executesMethod() {
        listCommand = new ListTopicCommand();
        listCommand.execute(subject);
    }

    @Test
    public void quizTopicCommand_TopicNotPresent_throwsException() {
        QuizTopicCommand quizNoTopic = new QuizTopicCommand("quiz Geometry");
        assertThrows(NoTopicException.class, () -> quizNoTopic.execute(subject));
    }

    @Test
    public void quizTopicCommand_invalidCommand_throwsException() {
        QuizTopicCommand quizInvalidCommand = new QuizTopicCommand("quiz");
        assertThrows(InvalidTopicException.class, () -> quizInvalidCommand.execute(subject));

    }

    @Test
    public void resultTopicCommand_TopicNotPresent_throwsException() {
        ResultTopicCommand resultNoTopic = new ResultTopicCommand("quiz Geometry");
        assertThrows(NoTopicException.class, () -> resultNoTopic.execute(subject));

    }

    @Test
    public void resultTopicCommand_invalidCommand_throwsException() {
        ResultTopicCommand resultInvalidCommand = new ResultTopicCommand("quiz");
        assertThrows(InvalidTopicException.class, () -> resultInvalidCommand.execute(subject));
    }
}
