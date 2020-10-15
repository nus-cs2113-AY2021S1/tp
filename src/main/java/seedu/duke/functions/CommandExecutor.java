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


import java.io.IOException;
import java.util.Scanner;

import static seedu.duke.Duke.user;
import static seedu.duke.Duke.writings;
import static seedu.duke.bunnylist.BunnyList.bunniesList;
import static seedu.duke.commands.CommandChecker.*;
import static seedu.duke.commands.CommandChecker.extractCommandType;
import static seedu.duke.database.BunnySaver.saveAllBunny;
import static seedu.duke.filters.BunnyFilter.filterBunny;
import static seedu.duke.parsers.Parsers.getUserInput;
import static seedu.duke.ui.UI.printHelpMessage;

public class CommandExecutor {
    private static final Scanner SCANNER = new Scanner(System.in);
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
            System.out.println(WritingList.getWritingSize());
            break;
        case START:
            try {
                CommandChecker commandStartChecker = UNRECOGNISED;
                commandStartChecker = extractCommandType(userInput);

                while (commandStartChecker != TYPE) {
                    System.out.println("Please indicate your type by typing in \"type\" command");
                    userInput = getUserInput(SCANNER);
                    commandStartChecker = extractCommandType(userInput);
                }
                executeCommand(commandStartChecker, userInput);
            } catch (Exception e) {
                System.out.println(e);
            }
            break;
        case TYPE:
            try {
                String type = "";
                while (! (userInput.equals("poem") || userInput.equals("essay"))) {
                    WritingList.printAskForType();
                    userInput = getUserInput(SCANNER);
                    type = userInput;
                }
                WritingList.printAskForTitle();
                userInput = getUserInput(SCANNER);
                String title = userInput;
                System.out.println("Now you can type your content, terminate by typing \"end\"");
                String content = "";
                while (!userInput.equals("end")) {
                    content = content.concat(userInput + "\n");
                    userInput = getUserInput(SCANNER);
                }
                if (type.equals("poem")) {
                    writings.add(new Poem(title, 0, "nothing", content, user.getName()));
                } else if (type.equals("essay")) {
                    writings.add(new Essay(title, 0, "nothing", content, user.getName()));
                }
                System.out.println("Done! We have added your writing to our storage! You can type \"stats\" "
                        + "for future reference!");
            } catch (Exception e) {
                System.out.println(e);
            }
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
