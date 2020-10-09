package seedu.eduke8.parser;

import seedu.eduke8.topic.TopicList;

public interface Parser {
    void parseCommand(TopicList topicList, String userInput);
}
