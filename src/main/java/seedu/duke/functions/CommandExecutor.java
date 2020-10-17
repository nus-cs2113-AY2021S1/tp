package seedu.duke.functions;

import seedu.duke.bunnylist.BunnyList;
import seedu.duke.commands.CommandChecker;

import seedu.duke.exceptions.BunnyIdeaMissingException;
import seedu.duke.exceptions.CommandMissingArgumentsException;
import seedu.duke.exceptions.MissingFilterOptionsException;
import seedu.duke.exceptions.NoFilteredItemsException;
import seedu.duke.filters.FilterExecutor;
import seedu.duke.ui.UI;

import seedu.duke.wordlist.WordList;
import seedu.duke.filters.FilterExecutor;
import seedu.duke.names.Names;
import seedu.duke.writing.Essay;
import seedu.duke.writing.Poem;
import seedu.duke.writing.WritingList;
import seedu.duke.writing.Writings;


import java.io.IOException;
import java.util.Scanner;

import static seedu.duke.bunnylist.BunnyList.bunniesList;
import static seedu.duke.database.BunnySaver.saveAllBunny;
import static seedu.duke.filters.BunnyFilter.filterBunny;
import static seedu.duke.ui.UI.printHelpMessage;

public class CommandExecutor {
    public static void executeCommand(CommandChecker commandChecker, String userInput) {
        switch (commandChecker) {
        case HELP:
            printHelpMessage(userInput);
            break;
        //case USERNAME:
        //    // change username
        //    break;
        //case DIVIDER:
        //    // choose divider type
        //    break;
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
        case FILTER_WORDS:
            FilterExecutor.executeFilterCommand(userInput);
            break;
        case BUNNY:
            try {
                BunnyList.addBunny(userInput);
            } catch (CommandMissingArgumentsException e) {
                UI.bunnyWrongFormat();
            } catch (BunnyIdeaMissingException e) {
                UI.bunnyMissingIdea();
            }
            break;
        case LIST_BUNNY:
            BunnyList.listBunny();
            break;
        case FILTER_BUNNY:
            try {
                filterBunny(userInput, bunniesList);
            } catch (MissingFilterOptionsException e) {
                UI.bunnyMissingFilterOption();
            } catch (NoFilteredItemsException e) {
                UI.bunnyFilterNoneFound();
            }
            break;
        case SAVE_BUNNY:
            try {
                saveAllBunny(bunniesList);
                UI.bunnySaved();
            } catch (IOException e) {
                UI.failedToSaveBunny();
            }
            break;
        case GEN_NAME:
            try {
                Names.getName();
            } catch (Exception e) {
                System.out.println(e);
            }
            break;
        case FILTER_NAMES:
            Names.filterNames(userInput);
            break;
        case LIST_NAMES:
            Names.listNames();
            break;
        case STATS:
            WritingList.printWritings();
            break;
        case START:
            WritingList.checkStart();
            break;
        case TYPE:
            WritingList.checkType();
            break;
        case COUNT_WRITINGS:
            WritingList.getWritingSize();
            break;
        case DELETE:
            break;
        case EXIT:
            //closes the program
            break;
        default:
            UI.commandNotRecognisedMsg();
        }
    }
}
