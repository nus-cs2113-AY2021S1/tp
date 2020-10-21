package seedu.eduke8.parser;

import seedu.eduke8.command.Command;
import seedu.eduke8.command.AboutCommand;
import seedu.eduke8.command.ExitCommand;
import seedu.eduke8.command.HelpCommand;
import seedu.eduke8.command.IncorrectCommand;
import seedu.eduke8.command.TextbookCommand;
import seedu.eduke8.command.TopicsCommand;
import seedu.eduke8.command.QuizCommand;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;


import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.eduke8.exception.ExceptionMessages.ERROR_QUIZ_WRONG_FORMAT;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_UNRECOGNIZED_COMMAND;


public class MenuParser implements Parser {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public MenuParser() {
    }

    @Override
    public Command parseCommand(DisplayableList topicList, String userInput) {
        assert topicList != null;

        LOGGER.log(Level.INFO, "Begin parsing command.");
        String[] commandArr = userInput.trim().split(" ", 0);

        Ui ui = new Ui();
        switch (commandArr[0]) {
        case "about":
            LOGGER.log(Level.INFO, "Parsing complete: about command chosen.");
            return new AboutCommand();
        case "help":
            LOGGER.log(Level.INFO, "Parsing complete: help command chosen.");
            return new HelpCommand();
        case "topics":
            LOGGER.log(Level.INFO, "Parsing complete: topics command chosen.");
            return new TopicsCommand();
        case "textbook":
            LOGGER.log(Level.INFO, "Parsing complete: textbook command chosen.");
            return new TextbookCommand();
        case "quiz":
            int numOfQuestions = 0;
            String topicName = "";
            try {
                numOfQuestions = Integer.parseInt(commandArr[2].substring(2));
                topicName = commandArr[1].substring(2);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return new IncorrectCommand(ERROR_QUIZ_WRONG_FORMAT);
            }
            LOGGER.log(Level.INFO, "Parsing complete: quiz command chosen.");
            return new QuizCommand((TopicList) topicList, numOfQuestions, topicName, ui);
        case "exit":
            LOGGER.log(Level.INFO, "Parsing complete: exit command chosen.");
            return new ExitCommand();
        default:
            break;
        }
        LOGGER.log(Level.WARNING, "Parsing Error: Unrecognised command was entered.");
        return new IncorrectCommand(ERROR_UNRECOGNIZED_COMMAND);
    }

}
