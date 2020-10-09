package seedu.duke.parser;

import seedu.duke.command.topiccommand.*;

/**
 * Parses the commands on the topic level
 */
public class TopicParser {

    /**
     * Parses the inputs provided by the user.
     *
     * @param fullCommand input by the user
     * @return returns a command instance to execute a command
     */
    public static TopicCommand parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new ExitTopicCommand();
        } else if (fullCommand.equals("list")) {
            return new ListTopicCommand();
        } else if (fullCommand.startsWith("add")) {
            return new AddTopicCommand(fullCommand);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteTopicCommand(fullCommand);
        } else if (fullCommand.startsWith("find")) {
            return new FindTopicCommand(fullCommand);
        } else if (fullCommand.startsWith("topic")) {
            return new ReturnTopicCommand(fullCommand);
        } else {
            return new SorryTopicCommand();
        }
    }
}