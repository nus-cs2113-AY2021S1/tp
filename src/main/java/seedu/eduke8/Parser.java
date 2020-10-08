package seedu.eduke8;


import seedu.eduke8.option.OptionInterface;
import seedu.eduke8.question.QuestionInterface;
import seedu.eduke8.quiz.Quiz;
import seedu.eduke8.topic.TopicListInterface;
import seedu.eduke8.ui.UiInterface;

import java.util.ArrayList;

public class Parser implements ParserInterface {

    public Parser() {
    }

    public void parseCommand(TopicListInterface topicList, String userInput) {
        int numOfQuestions = 0;
        String topicName = "";
        String[] commandArr = userInput.trim().split(" ", 0);
        if (commandArr.length == 3 && commandArr[0].equals("quiz")) {
            numOfQuestions = Integer.parseInt(commandArr[2].substring(2));
            topicName = commandArr[1].substring(2);
        }
        UiInterface ui = null;
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
            Quiz quiz = new Quiz(topicList.findTopic(topicName), numOfQuestions);
            quiz.startQuiz(ui);
            break;
        case "exit":
            System.exit(0);
            break;
        default:
            break;
        }
    }

    public void parseChosenOption(UiInterface ui, ArrayList<OptionInterface> options, QuestionInterface question) {
        // Should probably use parser for this part to add hints also
        int chosenOption = Integer.parseInt(ui.getInputFromUser());
        if (options.get(chosenOption).isCorrectAnswer()) {
            ui.printAnswerIsCorrect();
            question.markAsAnsweredCorrectly();
        } else {
            ui.printAnswerIsWrong();
        }

        question.markAsAttempted();
    }

}
