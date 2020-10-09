package seedu.eduke8.parser;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.option.Option;
import seedu.eduke8.question.Question;
import seedu.eduke8.quiz.SingleTopicQuiz;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import java.util.ArrayList;

public class MenuParser implements Parser {

    public MenuParser() {
    }

    public void parseCommand(TopicList topicList, String userInput) {
        int numOfQuestions = 0;
        String topicName = "";
        String[] commandArr = userInput.trim().split(" ", 0);
        if (commandArr.length == 3 && commandArr[0].equals("quiz")) {
            numOfQuestions = Integer.parseInt(commandArr[2].substring(2));
            topicName = commandArr[1].substring(2);
        }
        Ui ui = null;
        switch (commandArr[0]) {
        case "about":
            ui.printAbout();
            break;
        case "help":
            ui.printHelp();
            break;
        case "topics":
            break;
        case "textbook":
            ui.printTextbook();
            break;
        case "quiz":
            SingleTopicQuiz singleTopicQuiz = new SingleTopicQuiz(topicList.findTopic(topicName), numOfQuestions);
            singleTopicQuiz.startQuiz(ui);
            break;
        case "exit":
            System.exit(0);
            break;
        default:
            break;
        }
    }

    public void parseChosenOption(Ui ui, ArrayList<Displayable> options, Question question) {
        // Should probably use parser for this part to add hints also
        int chosenIndex = Integer.parseInt(ui.getInputFromUser());
        Option chosenOption = (Option) options.get(chosenIndex);
        if (chosenOption.isCorrectAnswer()) {
            ui.printAnswerIsCorrect();
            question.markAsAnsweredCorrectly();
        } else {
            ui.printAnswerIsWrong();
        }
    }

}
