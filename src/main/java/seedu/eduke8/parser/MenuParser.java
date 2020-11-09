package seedu.eduke8.parser;

import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.command.AboutCommand;
import seedu.eduke8.command.BookmarkCommand;
import seedu.eduke8.command.Command;
import seedu.eduke8.command.ExitCommand;
import seedu.eduke8.command.HelpCommand;
import seedu.eduke8.command.IncorrectCommand;
import seedu.eduke8.command.NoteCommand;
import seedu.eduke8.command.QuizCommand;
import seedu.eduke8.command.StatsCommand;
import seedu.eduke8.command.TextbookCommand;
import seedu.eduke8.command.TopicsCommand;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.eduke8.exception.ExceptionMessages.ERROR_BOOKMARK_INCORRECT_COMMAND;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_QUIZ_WRONG_FORMAT;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_QUIZ_TIMER_NEGATIVE;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_BOOKMARK_DELETE_NFE;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_BOOKMARK_DELETE_IOB_ERROR;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_NOTE_WRONG_FORMAT;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_UNRECOGNIZED_COMMAND;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_QUIZ_TIMER_TOO_LONG;

/**
 * Parses user input from the main menu, in order to execute the correct option.
 */
public class MenuParser implements Parser {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String TOPIC_INDICATOR = "t/";
    private static final String TIMER_INDICATOR = "s/";
    private static final String NUMBER_OF_QUESTIONS_INDICATOR = "n/";
    private static final int LENGTH_OF_QUESTIONS_INDICATOR = 2;
    private static final int LENGTH_OF_TOPIC_INDICATOR = 2;
    private static final int LENGTH_OF_TIMER_INDICATOR = 2;
    private static final String BOOKMARK_LIST = "listing";
    private static final String COMMAND_ABOUT = "about";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_TOPICS = "topics";
    private static final String COMMAND_TEXTBOOK = "textbook";
    private static final String COMMAND_QUIZ = "quiz";
    private static final String COMMAND_NOTE = "note";
    private static final String COMMAND_BOOKMARK = "bookmark";
    private static final String COMMAND_BOOKMARK_DELETE = "delete";
    private static final String COMMAND_BOOKMARK_LIST = "list";
    private static final int BOOKMARK_DELETE_COMMAND_ARR_SIZE = 3;
    private static final int BOOKMARK_LIST_COMMAND_ARR_SIZE = 2;
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_STATS = "stats";
    private static final String COMMAND_NOTE_ADD = "add";
    private static final String COMMAND_NOTE_DELETE = "delete";
    private static final String COMMAND_NOTE_LIST = "list";
    private static final String SLASH = "/";
    private static final String SPACE = " ";


    private BookmarkList bookmarkList;

    /**
     * Parses user input while user is in the main menu.
     * @params bookmarkList a list of stored bookmarked questions as bookmarked by the user
     */
    public MenuParser(BookmarkList bookmarkList) {
        this.bookmarkList = bookmarkList;
    }

    /**
     * Parses the user input.
     *
     * @param topicList the topic list that contains the list of topics available in storage
     * @param userInput the string input typed by the user
     * @return a Command object which when executed will carry out the appropriate action
     */
    @Override
    public Command parseCommand(DisplayableList topicList, String userInput) {
        assert topicList != null;
        LOGGER.log(Level.INFO, "Begin parsing command.");
        String[] commandArr = userInput.trim().split(SPACE, 0);

        for (int i = 0; i < commandArr.length - 1; i++) {
            if (commandArr[i].equals("") || (i > 0 && (commandArr[i].equals(commandArr[i - 1])))) {
                int j = 1;
                while (commandArr[i + j].equals("") && ((i + j) < commandArr.length - 1)) {
                    j++;
                }
                commandArr[i] = commandArr[i + j];
                commandArr[i + j] = "";
            }
        }

        Ui ui = new Ui();
        switch (commandArr[0]) {
        case COMMAND_ABOUT:
            LOGGER.log(Level.INFO, "Parsing complete: about command chosen.");
            return new AboutCommand();
        case COMMAND_HELP:
            LOGGER.log(Level.INFO, "Parsing complete: help command chosen.");
            return new HelpCommand();
        case COMMAND_TOPICS:
            LOGGER.log(Level.INFO, "Parsing complete: topics command chosen.");
            return new TopicsCommand();
        case COMMAND_TEXTBOOK:
            LOGGER.log(Level.INFO, "Parsing complete: textbook command chosen.");
            return new TextbookCommand();
        case COMMAND_QUIZ:
            int numOfQuestions = 0;
            String topicName = "";
            int userTimer = 0;
            if ((commandArr.length > 4 && (!commandArr[3].contains(SLASH) || !commandArr[4].equals("")))
                    || commandArr.length < 4) {
                return new IncorrectCommand(ERROR_QUIZ_WRONG_FORMAT);
            }
            try {
                boolean isUserTimerSet = false;
                boolean isTopicNameSet = false;
                boolean isNumOfQuestionsSet = false;

                for (int i = 1; i < 4; i++) {
                    if (commandArr[i].contains(TIMER_INDICATOR) && !isUserTimerSet) {
                        userTimer = Integer.parseInt(commandArr[i].substring(
                                commandArr[i].indexOf(TIMER_INDICATOR) + LENGTH_OF_TIMER_INDICATOR));
                        isUserTimerSet = true;
                    } else if (commandArr[i].contains(TOPIC_INDICATOR) && !isTopicNameSet) {
                        topicName = commandArr[i].substring(
                                commandArr[i].indexOf(TOPIC_INDICATOR) + LENGTH_OF_TOPIC_INDICATOR);
                        isTopicNameSet = true;
                    } else if (commandArr[i].contains(NUMBER_OF_QUESTIONS_INDICATOR) && !isNumOfQuestionsSet) {
                        numOfQuestions = Integer.parseInt(commandArr[i].substring(
                                commandArr[i].indexOf(NUMBER_OF_QUESTIONS_INDICATOR) + LENGTH_OF_QUESTIONS_INDICATOR));
                        isNumOfQuestionsSet = true;
                    }
                }

                if (!(isUserTimerSet && isNumOfQuestionsSet && isTopicNameSet)) {
                    return new IncorrectCommand(ERROR_QUIZ_WRONG_FORMAT);
                }

                if (userTimer < 1) {
                    return new IncorrectCommand(ERROR_QUIZ_TIMER_NEGATIVE);
                } else if (userTimer > 1000) {
                    return new IncorrectCommand(ERROR_QUIZ_TIMER_TOO_LONG);
                }
                LOGGER.log(Level.INFO, "Parsing complete: quiz command chosen.");
                return new QuizCommand((TopicList) topicList, numOfQuestions, topicName, ui, bookmarkList, userTimer);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return new IncorrectCommand(ERROR_QUIZ_WRONG_FORMAT);
            }
        case COMMAND_BOOKMARK:
            LOGGER.log(Level.INFO, "Parsing complete: bookmark list command chosen.");
            if (commandArr.length >= BOOKMARK_DELETE_COMMAND_ARR_SIZE
                    && commandArr[1].trim().equalsIgnoreCase(COMMAND_BOOKMARK_DELETE)) {
                int deleteIndex;
                try {
                    deleteIndex = Integer.parseInt(commandArr[2]);
                    return new BookmarkCommand(deleteIndex, commandArr[1], bookmarkList);
                } catch (NumberFormatException nfe) {
                    return new IncorrectCommand(ERROR_BOOKMARK_DELETE_NFE);
                } catch (Eduke8Exception e) {
                    return new IncorrectCommand(e.getMessage());
                } catch (IndexOutOfBoundsException iobe) {
                    return new IncorrectCommand(ERROR_BOOKMARK_DELETE_IOB_ERROR);
                }
            } else if (commandArr.length >= BOOKMARK_LIST_COMMAND_ARR_SIZE
                    && commandArr[1].trim().equalsIgnoreCase(COMMAND_BOOKMARK_LIST)) {
                try {
                    return new BookmarkCommand(BOOKMARK_LIST, bookmarkList);
                } catch (Eduke8Exception e) {
                    return new IncorrectCommand(e.getMessage());
                }
            } else {
                return new IncorrectCommand(ERROR_BOOKMARK_INCORRECT_COMMAND);
            }
        case COMMAND_NOTE:
            try {
                if (commandArr[1].equalsIgnoreCase(COMMAND_NOTE_ADD) || commandArr[1]
                        .equalsIgnoreCase(COMMAND_NOTE_DELETE) || commandArr[1].equals(COMMAND_NOTE_LIST)) {
                    LOGGER.log(Level.INFO, "Parsing complete: note command chosen");
                    return new NoteCommand(commandArr[1], (TopicList) topicList);
                } else {
                    break;
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return new IncorrectCommand(ERROR_NOTE_WRONG_FORMAT);
            }
        case COMMAND_EXIT:
            LOGGER.log(Level.INFO, "Parsing complete: exit command chosen.");
            return new ExitCommand();
        case COMMAND_STATS:
            LOGGER.log(Level.INFO, "Parsing complete: stats command chosen.");
            return new StatsCommand((TopicList) topicList);
        default:
            break;
        }
        LOGGER.log(Level.WARNING, "Parsing Error: Unrecognised command was entered.");
        return new IncorrectCommand(ERROR_UNRECOGNIZED_COMMAND);
    }
}
