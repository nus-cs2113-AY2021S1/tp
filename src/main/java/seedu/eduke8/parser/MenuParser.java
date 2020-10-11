package seedu.eduke8.parser;

import seedu.eduke8.Command;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.quiz.SingleTopicQuiz;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

public class MenuParser implements Parser {

    public MenuParser() {
    }

    @Override
    public Command parseCommand(DisplayableList topicList, String userInput) {
        int numOfQuestions = 0;
        String topicName = "";
        String[] commandArr = userInput.trim().split(" ", 0);
        if (commandArr.length == 3 && commandArr[0].equals("quiz")) {
            numOfQuestions = Integer.parseInt(commandArr[2].substring(2));
            topicName = commandArr[1].substring(2);
        }

        // Move all these executions with Ui to Command classes and pass Ui to the execute method
        Ui ui = new Ui();
        switch (commandArr[0]) {
        case "about":
            ui.printAbout();
            break;
        case "help":
            ui.printHelp();
            break;
        case "topics":
            ((TopicList) topicList).showTopics();
            break;
        case "textbook":
            ui.printTextbook();
            break;
        case "quiz":
            parseQuizCommand((TopicList) topicList, numOfQuestions, topicName, ui);
            break;
        case "exit":
            System.exit(0);
            break;
        default:
            break;
        }

        return null; // Return command object instead
    }

    // Should move this to its own Command class
    private void parseQuizCommand(TopicList topicList, int numOfQuestions, String topicName, Ui ui) {
        Topic topic = (Topic) topicList.find(topicName);
        SingleTopicQuiz singleTopicQuiz = new SingleTopicQuiz(topic, numOfQuestions);
        singleTopicQuiz.startQuiz(ui);
    }
}
