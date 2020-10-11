package seedu.eduke8.parser;

import seedu.eduke8.command.*;
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
            return new AboutCommand();
        case "help":
            return new HelpCommand();
        case "topics":
            return new TopicsCommand();
        case "textbook":
            return new TextbookCommand();
        case "quiz":
            return new QuizCommand((TopicList) topicList, numOfQuestions, topicName, ui);
        case "exit":
            return new ExitCommand();
        default:
            break;
        }

        return null; // Return command object instead
    }
}
