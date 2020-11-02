package seedu.duke.functions;

import seedu.duke.bunnylist.BunnyList;
import seedu.duke.bunnylist.DeleteBunny;
import seedu.duke.bunnylist.GenBunny;
import seedu.duke.commands.CommandChecker;
import seedu.duke.constants.FluffleMessages;
import seedu.duke.database.ClearLoader;
import seedu.duke.database.WordsSaver;
import seedu.duke.exceptions.BunnyIdeaMissingException;
import seedu.duke.exceptions.BunnyIndexOutOfBoundsException;
import seedu.duke.exceptions.BunnyListEmptyException;
import seedu.duke.exceptions.CommandInvalidException;
import seedu.duke.exceptions.CommandMissingArgumentsException;
import seedu.duke.exceptions.DividerCommandWrongFormatException;
import seedu.duke.exceptions.DividerIndexOutOfBoundsException;
import seedu.duke.exceptions.FilterCommandException;
import seedu.duke.exceptions.MissingFilterOptionsException;
import seedu.duke.exceptions.NameException;
import seedu.duke.exceptions.NoFilteredItemsException;
import seedu.duke.exceptions.WrongClearCommandFormat;
import seedu.duke.filters.FilterCommandSlicer;
import seedu.duke.filters.FilterExecutor;
import seedu.duke.filters.FilterList;
import seedu.duke.names.Names;
import seedu.duke.reminder.WritingReminder;
import seedu.duke.ui.UI;
import seedu.duke.wordlist.WordList;
import seedu.duke.writing.WritingList;

import java.io.IOException;

import static seedu.duke.bunnylist.BunnyList.bunniesList;
import static seedu.duke.wordlist.WordList.wordList;
import static seedu.duke.database.BunnySaver.saveAllBunny;
import static seedu.duke.database.WordsSaver.saveWordsToFile;
import static seedu.duke.filters.BunnyFilter.filterBunny;
import static seedu.duke.ui.UI.changeLineDivider;
import static seedu.duke.ui.UI.printHelpMessage;
//import static seedu.duke.database.ClearLoader.clearItems;


public class CommandExecutor {
    public static void executeCommand(CommandChecker commandChecker, String userInput, WritingList writings) {
        switch (commandChecker) {
        case HELP:
            String[] command = userInput.split(" ", 2);
            if (command.length > 1) {
                printHelpMessage(command[1]);
            } else {
                printHelpMessage(userInput);
            }
            break;
        case DIVIDER:
            try {
                changeLineDivider(userInput);
            } catch (DividerCommandWrongFormatException e) {
                UI.dividerCommandWrongFormat();
            } catch (DividerIndexOutOfBoundsException e) {
                UI.dividerIndexOutOfBounds();
            }
            break;
        case NOUN:
            WordList.addNoun(userInput);
            saveWordsToFile();
            break;
        case VERB:
            WordList.addVerb(userInput);
            saveWordsToFile();
            break;
        case ADJ:
            WordList.addAdjective(userInput);
            saveWordsToFile();
            break;
        case GEN_THREE_WORDS:
            WordList.listThreeWords();
            break;
        case LIST_WORDS:
            WordList.listWords();
            break;
        case FILTER_WORDS:
            try {
                FilterExecutor.executeFilterCommand(userInput);
            } catch (FilterCommandException e) {
                System.out.println(FluffleMessages.FILTER_UNKNOWN_TYPE);
            }
            break;
        case LIST_FILTER:
            int printLimit = FilterCommandSlicer.getWordPrintLimitFromListFilterCommand(userInput);
            FilterList.printFilterList(printLimit);
            break;
        case BUNNY:
            try {
                BunnyList.addBunny(userInput);
            } catch (CommandMissingArgumentsException e) {
                UI.bunnyWrongFormat();
            } catch (BunnyIdeaMissingException e) {
                UI.bunnyMissingIdea();
            } catch (CommandInvalidException e) {
                UI.commandNotRecognisedMsg();
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
            } catch (CommandInvalidException e) {
                UI.commandNotRecognisedMsg();
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
        case DELETE_BUNNY:
            try {
                DeleteBunny.deleteBunny(userInput, bunniesList);
            } catch (BunnyIndexOutOfBoundsException e) {
                UI.bunnyIndexOutOfBounds();
            }
            break;
        case RANDOM_BUNNY:
            try {
                GenBunny.pickRandomBunny(bunniesList);
            } catch (BunnyListEmptyException e) {
                UI.bunnyListEmpty();
            }
            break;
        case REMIND:
            WritingReminder.printReminderOnADay(userInput);
            break;
        case GEN_NAME:
            try {
                Names.getName();
            } catch (NameException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e);
            }
            break;
        case ADD_NAME:
            try {
                Names.addName(userInput);
            } catch (NameException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e);
            }
            break;
        case DELETE_NAME:
            try {
                Names.deleteName(userInput);
            } catch (NameException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e);
            }
            break;
        case FILTER_NAMES:
            try {
                Names.filterNames(userInput);
            } catch (NameException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e);
            }
            break;
        case LIST_NAMES:
            try {
                Names.listNames();
            } catch (NameException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e);
            }
            break;
        case STATS:
            WritingList.printWritings();
            break;
        case START:
            WritingList.checkStart(writings);
            break;
        case TYPE:
            WritingList.checkType(writings);
            break;
        case COUNT_WRITINGS:
            WritingList.printWritingSize();
            break;
        case RESET_WRITINGS:
            WritingList.clearAll(writings);
            break;
        case CLEAR:
            try {
                ClearLoader.clearItems(userInput, writings, wordList);
            } catch (WrongClearCommandFormat e) {
                System.out.println("The appropriate format is:\n"
                        + "clear type\\<TYPE_OF_ELEMENT> item\\<INDICATOR_OF_ELEMENT>");
            }
            break;
        case DELETE:
            break;
        case EXIT:
            WordsSaver.saveWordsToFile();
            //closes the program
            break;
        default:
            UI.commandNotRecognisedMsg();
        }
    }
}
