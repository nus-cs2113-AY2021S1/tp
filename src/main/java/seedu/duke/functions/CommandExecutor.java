package seedu.duke.functions;

import seedu.duke.parsers.CommandChecker;

public class CommandExecutor {
    public static void executeCommand(CommandChecker commandChecker) {
        switch (commandChecker) {
        case HELP:
            //print help function
            break;
        case SETTINGS:
            //modify settings
            break;
        case ADD:
            //add quiz question
            break;
        case LIST:
            // list quiz questions
            break;
        case HISTORY:
            //print user history
            break;
        case STATS:
            //print user stats
            break;
        case REVIEW:
            //review questions under certain topics
            break;
        case QUIZ:
            //take a quiz
            break;
        case CLEAR:
            //clear all quizzes
            break;
        case EXIT:
            //close the program
            break;
        default:
            //print confused message
        }
    }
}
