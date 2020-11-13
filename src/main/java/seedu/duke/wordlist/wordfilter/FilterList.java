package seedu.duke.wordlist.wordfilter;

import seedu.duke.constants.FluffleMessages;
import seedu.duke.constants.Tags;
import seedu.duke.common.Parsers;
import seedu.duke.ui.UI;
import seedu.duke.words.Words;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains an array list of filter words and its print method.
 */
public class FilterList {

    public static Scanner SCANNER = new Scanner(System.in);
    public static ArrayList<Words> filterWords = new ArrayList<>();

    /**
     * Prints the list of filtered words in the command.
     * User can limit the number of the words printed.
     * If no print limit is provided, the method will print all words in list.
     *
     * @param printLimit An integer indicated the number of words that need printing.
     */
    public static void printFilterList(int printLimit) {
        if (printLimit < -1) {
            UI.printDivider();
            System.out.println(FluffleMessages.INVALID_LIMIT_LIST_FILTER_WORDS);
            UI.printDivider();
            return;
        }

        if (filterWords.size() == 0) {
            System.out.println(FluffleMessages.NO_FILTER_RESULT);
            return;
        }

        if (printLimit == -1) {
            //the user didn't specify the print limit so the program prints out all the words in the list
            System.out.println(FluffleMessages.PRINT_LIMIT_NOT_FOUND);
            System.out.printf(FluffleMessages.FILTER_MESSAGE, filterWords.size());
            int wordCount = 0;
            for (Words word : filterWords) {
                System.out.println("   " + (wordCount + 1) + ". " + word.getDescription()
                        + ": " + word.getDefinition());
                wordCount++;
            }
            return;
        }

        if (filterWords.size() <= printLimit) {
            UI.printDivider();
            System.out.printf(FluffleMessages.FILTER_MESSAGE, filterWords.size());
            int wordCount = 0;
            for (Words word : filterWords) {
                System.out.println("   " + (wordCount + 1) + ". " + word.getDescription()
                        + ": " + word.getDefinition());
                wordCount++;
            }
            UI.printDivider();
            return;
        }

        //if the size of the filterWords is greater than printLimit
        UI.printDivider();
        System.out.printf(FluffleMessages.LONG_FILTER_LIST_MESSAGE, printLimit);
        UI.printDivider();
        String userInput = Parsers.getUserInput(SCANNER);
        if (userInput.trim().equalsIgnoreCase(Tags.YES) || userInput.trim().equalsIgnoreCase(Tags.Y)) {
            UI.printDivider();
            System.out.printf(FluffleMessages.FILTER_MESSAGE, filterWords.size());
            int wordCount = 0;
            for (Words word : filterWords) {
                System.out.println("   " + (wordCount + 1) + ". " + word.getDescription()
                        + ": " + word.getDefinition());
                wordCount++;
            }
            UI.printDivider();
        } else if (userInput.trim().equalsIgnoreCase(Tags.N) || userInput.trim().equalsIgnoreCase(Tags.NO)) {
            UI.printDivider();
            System.out.printf(FluffleMessages.FILTER_MESSAGE_LIMIT, printLimit, filterWords.size());
            for (int i = 0; i < printLimit; i++) {
                Words word = filterWords.get(i);
                System.out.println("   " + (i + 1) + ". " + word.getDescription() + ": " + word.getDefinition());
            }
            UI.printDivider();
        } else {
            UI.printDivider();
            System.out.println(FluffleMessages.INVALID_LIST_FILTER_ANSWER);
            UI.printDivider();
        }
    }
}
