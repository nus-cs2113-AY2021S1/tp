package seedu.revised.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.list.TopicList;
import seedu.revised.command.topic.AddTopicCommand;
import seedu.revised.command.topic.DeleteTopicCommand;
import seedu.revised.command.topic.FindTopicCommand;
import seedu.revised.command.topic.ListTopicCommand;
import seedu.revised.command.topic.AccessTopicCommand;
import seedu.revised.exception.topic.InvalidTopicException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.exception.topic.RepeatedTopicException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopicCommandTest {
    private Subject subject;
    private TopicList topics;
    private AddTopicCommand addCommand;
    private DeleteTopicCommand deleteCommand;
    private FindTopicCommand findCommand;
    private AccessTopicCommand returnCommand;
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
    public void addTopic() throws NoTopicException, RepeatedTopicException, InvalidTopicException {
        addCommand = new AddTopicCommand("add Geometry");
        addCommand.execute(subject);
        assertEquals("Geometry", topics.getList().get(3).getTitle());
    }

    @Test
    public void deleteTopic() {
        deleteCommand = new DeleteTopicCommand("delete 2");
        deleteCommand.execute(subject);
        assertEquals(Integer.valueOf("2"), subject.getTopics().getList().size());
    }

    @Test
    public void findTopic() throws InvalidTopicException {
        findCommand = new FindTopicCommand("find Speed");
        findCommand.execute(subject);
    }

    @Test
    public void listTopic() {
        listCommand = new ListTopicCommand();
        listCommand.execute(subject);
    }
}
