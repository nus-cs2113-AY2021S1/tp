package seedu.duke.functions;

import seedu.duke.commands.CommandChecker;
import seedu.duke.wordlist.WordList;

import static seedu.duke.ui.UI.printHelpMessage;

public class CommandExecutor {
    public static void executeCommand(CommandChecker commandChecker, String userInput) {
        switch (commandChecker) {
        case HELP:
            printHelpMessage(userInput);
            break;
        case USERNAME:
            // change username
            break;
        case DIVIDER:
            // choose divider type
            break;
        case NOUN:
            WordList.addNoun(userInput);
            break;
        case VERB:
            WordList.addVerb(userInput);
            break;
        case ADJ:
            WordList.addAdjective(userInput);
            break;
        case LIST_WORDS:
            WordList.listWords();
            break;
        case BUNNY:
            //
            break;
        case LIST_BUNNY:
            //
            break;
        case STATS:
            //print user stats
            break;
        case DELETE:
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
