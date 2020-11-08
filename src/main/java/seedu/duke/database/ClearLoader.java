package seedu.duke.database;

import seedu.duke.exceptions.FileEmptyException;
import seedu.duke.exceptions.WrongClearCommandFormat;
import seedu.duke.exceptions.ClearLoaderException;
import seedu.duke.exceptions.InvalidClearFormat;
import seedu.duke.exceptions.ItemNotFoundedException;

import seedu.duke.ui.UI;
import seedu.duke.wordlist.WordList;
import seedu.duke.words.Words;
import seedu.duke.writing.WritingList;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.parsers.Parsers.getUserInput;
import static seedu.duke.wordlist.WordList.listWords;

public class ClearLoader {
    private static Scanner scanner = new Scanner(System.in);

    public static void clearItems(String userInput, WritingList writings, ArrayList<Words> wordList)
            throws WrongClearCommandFormat {
        String type;
        String item;
        String[] words = userInput.split(" ");
        if (!(words.length == 3)) {
            throw new WrongClearCommandFormat();
        } else {
            type = getType(words[1]);
            item = getItem(words[2]);
            try {
                processing(type, item, writings, wordList);
            } catch (ClearLoaderException e) {
                System.out.println("The format is not correct!");
            }
        }
    }

    public static String getType(String rawType) {
        assert (rawType.contains("type\\")) : "Your format is incorrect";
        return rawType.substring("type\\".length()).trim();
    }

    public static String getItem(String rawItem) {
        assert (rawItem.contains("item\\")) : "Your format is incorrect";
        return rawItem.substring("item\\".length()).trim();
    }

    public static void processing(String type, String item, WritingList writings, ArrayList<Words> wordList)
            throws ClearLoaderException {
        try {
            if (type.equalsIgnoreCase("writing")) {
                clearingWriting(item, writings);
            } else if (type.equalsIgnoreCase("word")) {
                try {
                    clearingWord(item, wordList);
                } catch (InvalidClearFormat e) {
                    System.out.println("Please check your syntax again!");
                }
            } else {
                throw new ClearLoaderException();
            }
        } catch (NumberFormatException e) {
            System.out.println("The format of item number is inappropriate");
        }
    }

    /**
     * To process the removal of the item from writing list.
     *
     * @param item the item Identity to be removed
     * @param writings the writing list to be affected
     * @throws NumberFormatException indicate that the item is not an integer number
     */
    public static void clearingWriting(String item, WritingList writings) {
        if (item.contains("-id")) {
            String idNumber = item.substring("-id".length()).trim();
            try {
                processingWritingClearingID(idNumber, writings);
            } catch (NumberFormatException e) {
                System.out.println("Your item format is not an integer!");
            }
        } else {
            try {
                processingWritingClearing(item, writings);
            } catch (NumberFormatException e) {
                System.out.println("Your item format is not an integer!");
            }
        }
    }

    public static void processingWritingClearingID(String idNumber, WritingList writings) {
        try {
            clearWritingWithID(idNumber, writings);
        } catch (NullPointerException e) {
            System.out.println("Your current inventory for the writing is empty!");
        }
    }

    public static void clearWritingWithID(String idNumber, WritingList writings) throws NullPointerException {
        if (writings.getSize() == 0) {
            throw new NullPointerException();
        } else {
            int element = Integer.parseInt(idNumber);
            System.out.println("Do you want to delete this writing with ID: " + element + "Yes/No?");
            String userAnswer = getUserInput(scanner);
            while (!(userAnswer.equalsIgnoreCase("yes") || userAnswer.equalsIgnoreCase("no"))) {
                System.out.println("Do you want to delete this writing with ID: " + element + "Yes/No?");
                userAnswer = getUserInput(scanner);
            }
            if (userAnswer.equalsIgnoreCase("yes")) {
                System.out.println("We have removed this item");
                try {
                    writings.removeID(element);
                } catch (FileEmptyException e) {
                    System.out.println("Your current inventory for the writing is empty!");
                } catch (ItemNotFoundedException e) {
                    System.out.println("Your ID does not match with any writing is currently in our inventory.");
                }
            } else if (userAnswer.equalsIgnoreCase("no")) {
                return;
            }
        }
    }

    public static void processingWritingClearing(String item, WritingList writings) {
        try {
            clearWritingWithIndex(item, writings);
        } catch (NullPointerException e) {
            System.out.println("Your current inventory for writing is empty!");
        }
    }

    public static void clearWritingWithIndex(String item, WritingList writings) throws NullPointerException {
        if (writings.getSize() == 0) {
            throw new NullPointerException();
        } else {
            int element = Integer.parseInt(item);
            System.out.println("Do you want to delete this writing with ID: " + element + " Yes/No?");
            String userAnswer = getUserInput(scanner);
            while (!(userAnswer.equalsIgnoreCase("yes") || userAnswer.equalsIgnoreCase("no"))) {
                System.out.println("Do you want to delete this writing with ID: " + element + " Yes/No?");
                userAnswer = getUserInput(scanner);
            }
            if (userAnswer.equalsIgnoreCase("yes")) {
                System.out.println("We have removed this item");
                try {
                    writings.removeWriting(element);
                } catch (FileEmptyException e) {
                    System.out.println("Your current inventory for the writing is empty!");
                }
            } else if (userAnswer.equalsIgnoreCase("no")) {
                return;
            }
        }
    }

    public static void clearingWord(String item, ArrayList<Words> wordList) throws InvalidClearFormat {
        if (item.contains("-noun")) {
            String word = item.substring("-noun".length());
            try {
                try {
                    clearingWordNoun(word, wordList);
                    UI.printClearCommandSuccess("noun", word);
                } catch (NullPointerException e) {
                    System.out.println("There is nothing in your word list inventory");
                }
            } catch (InvalidClearFormat e) {
                System.out.println("This noun is not founded!");
            }
        } else if (item.contains("-adj")) {
            String word = item.substring("-adj".length());
            try {
                try {
                    clearingWordAdjective(word, wordList);
                    UI.printClearCommandSuccess("adjective", word);
                } catch (NullPointerException e) {
                    System.out.println("There is nothing in your word list inventory");
                }
            } catch (InvalidClearFormat e) {
                System.out.println("This adjective is not founded!");
            }
        } else if (item.contains("-verb")) {
            String word = item.substring("-verb".length());
            try {
                try {
                    clearingWordVerb(word, wordList);
                    UI.printClearCommandSuccess("verb", word);
                } catch (NullPointerException e) {
                    System.out.println("There is nothing in your word list inventory");
                }
            } catch (InvalidClearFormat e) {
                System.out.println("This verb is not founded!");
            }
        } else {
            throw new InvalidClearFormat();
        }
    }

    public static void clearingWordNoun(String word, ArrayList<Words> wordList) throws InvalidClearFormat,
            NullPointerException {
        if (wordList.size() == 0) {
            throw new NullPointerException();
        } else {
            int wordFounded = 0;
            int i = 0;
            while (i < wordList.size()) {
                if (wordList.get(i).getType().equals("noun")
                        && wordList.get(i).getDescription().trim().equalsIgnoreCase(word)) {
                    wordList.remove(i);
                    wordFounded = 1;
                } else {
                    i++;
                }
            }
            if (wordFounded == 1) {
                return;
            } else {
                throw new InvalidClearFormat();
            }
        }
    }

    public static void clearingWordAdjective(String word, ArrayList<Words> wordList) throws InvalidClearFormat,
            NullPointerException {
        if (wordList.size() == 0) {
            throw new NullPointerException();
        } else {
            int wordFounded = 0;
            int i = 0;
            while (i < wordList.size()) {
                if (wordList.get(i).getType().equals("adjective")
                        && wordList.get(i).getDescription().trim().equalsIgnoreCase(word)) {
                    wordList.remove(i);
                    wordFounded = 1;
                } else {
                    i++;
                }
            }
            if (wordFounded == 1) {
                return;
            } else {
                throw new InvalidClearFormat();
            }
        }
    }

    public static void clearingWordVerb(String word, ArrayList<Words> wordList) throws InvalidClearFormat,
            NullPointerException {
        if (wordList.size() == 0) {
            throw new NullPointerException();
        } else {
            int wordFounded = 0;
            int i = 0;
            while (i < wordList.size()) {
                if (wordList.get(i).getType().equals("verb")
                        && wordList.get(i).getDescription().trim().equalsIgnoreCase(word)) {
                    wordList.remove(i);
                    wordFounded = 1;
                } else {
                    i++;
                }
            }
            if (wordFounded == 1) {
                return;
            } else {
                throw new InvalidClearFormat();
            }
        }
    }
}
