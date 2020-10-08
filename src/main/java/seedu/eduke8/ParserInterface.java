package seedu.eduke8;

import seedu.eduke8.topic.TopicListInterface;

public interface ParserInterface {
    void parseCommand(TopicListInterface topicList, String userInput);
}
