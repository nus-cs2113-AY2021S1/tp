package seedu.duke.wordlist;

import seedu.duke.constants.FluffleMessages;
import seedu.duke.constants.Tags;
import seedu.duke.ui.UI;
import seedu.duke.words.Adjective;
import seedu.duke.words.Noun;
import seedu.duke.words.Verb;
import seedu.duke.words.Words;

import java.util.ArrayList;
import java.util.Collections;

public class WordList {
    public static ArrayList<Words> wordList = new ArrayList<>();

    public static int getNumberOfWords() {
        return wordList.size();
    }

    /**
     * Function to add noun to word list.
     * @param input a string which contains
     *              the word and its definition
     */
    public static void addNoun(String input) {
        if(input.equals("noun")) {
            System.out.println(FluffleMessages.EMPTY_INPUT_MSG);
        } else {
            // Remove the command word
            String[] word = input.split(" ", 2);
            if (word[1].equals("") || word[1].equals("d\\") || !word[1].contains(Tags.DESCRIPTION_TAG)) {
                System.out.println(FluffleMessages.INCORRECT_NOUN_MSG);
            } else {
                String[] splitInput = word[1].trim().split(Tags.DESCRIPTION_TAG);
                Words toAdd = new Noun(splitInput[0], splitInput[1]);
                wordList.add(toAdd);
                UI.addNounMessage(toAdd.getDescription());
            }
        }
    }

    /**
     * Function to add verb to word list.
     * @param input a string which contains
     *              the word and its definition
     */
    public static void addVerb(String input) {
        if(input.equals("verb")) {
            System.out.println(FluffleMessages.EMPTY_INPUT_MSG);
        } else {
            // Remove the command word
            String[] word = input.split(" ", 2);
            if (word[1].equals("") || word[1].equals("d\\") || !word[1].contains(Tags.DESCRIPTION_TAG)) {
                System.out.println(FluffleMessages.INCORRECT_VERB_MSG);
            } else {
                String[] splitInput = word[1].split(Tags.DESCRIPTION_TAG);
                Words toAdd = new Verb(splitInput[0], splitInput[1]);
                wordList.add(toAdd);
                UI.addVerbMessage(toAdd.getDescription());
            }
        }
    }

    /**
     * Function to add adjective to word list.
     * @param input a string which contains
     *              the word and its definition
     */
    public static void addAdjective(String input) {
        if(input.equals("adj")) {
            System.out.println(FluffleMessages.EMPTY_INPUT_MSG);
        } else {
            // Remove the command word
            String[] word = input.split(" ", 2);
            if (word[1].equals("") || word[1].equals("d\\") || !word[1].contains(Tags.DESCRIPTION_TAG)) {
                System.out.println(FluffleMessages.INCORRECT_ADJ_MSG);
            } else {
                String[] splitInput = word[1].split(Tags.DESCRIPTION_TAG);
                Words toAdd = new Adjective(splitInput[0], splitInput[1]);
                wordList.add(toAdd);
                UI.addAdjectiveMessage(toAdd.getDescription());
            }
        }
    }

    public static void listWords() {
        UI.listWordsMessage();
        for (int i = 0; i < wordList.size(); i++) {
            System.out.println((i + 1) + "." + wordList.get(i).getDescription());
        }
    }

    public static ArrayList<Words> generateThreeWords() {
        ArrayList<Words> shuffledWords = new ArrayList<Words>(wordList);
        ArrayList<Words> threeWords = new ArrayList<Words>();
        Collections.shuffle(shuffledWords);
        int count = 0;
        while (count < 3) {
            threeWords.add(shuffledWords.get(count));
            count++;
        }
        return threeWords;
    }

    public static void listThreeWords() {
        if (wordList.size() < 3) {
            System.out.println(FluffleMessages.THREE_WORDS_ERROR_MSG);
        } else {
            ArrayList<Words> threeWords = generateThreeWords();
            System.out.println(FluffleMessages.THREE_WORDS_MSG);
            for (int i = 0; i < 3; i++) {
                System.out.println("   " + (i + 1) + ". " + threeWords.get(i).getDescription());
            }
        }
    }
}
