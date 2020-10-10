package seedu.duke.filters;

// input: an array list of words
/*
Type of filters:
 - filter nouns/verbs/adjectives or together
 - filter based on starting letter
 - print a number of random words needed
 */

/*
Filter command: filter -continue by\typeOfFilter(type, start, contain) limit\10 random\y
{[type1\noun] [type2\adj] [type3\verb]} (if filterType is type) or
{start\"string"} (if filterType is start) or
{contain\"string"} (if filterType is contain)

returns: a list of filter words
 */

import seedu.duke.database.WordsLoader;
import seedu.duke.words.Words;

import java.util.ArrayList;

public class WordsFilter {

    public static final ArrayList<Words> filteredWords = new ArrayList<>();

    public static void filterByType (String ... types) {
        int typesCount = types.length;
        ArrayList<Words> words = WordsLoader.getWordsList();

        for (int i = 0; i < typesCount; i++) {
            String type = types[i].toLowerCase();
            for (int j = 0; j < WordsLoader.getWordsListSize(); j++) {
                if (words.get(i).getType().equalsIgnoreCase(type)) {
                    filteredWords.add(words.get(i));
                }
            }
        }

        System.out.println("Words filtered by type are: ");
        for (Words word : filteredWords) {
            System.out.println(word.getDefinition());
        }
    }

    public static void filterByStartingString (String ... startingStrings) {
        int stringsCount = startingStrings.length;
        ArrayList<Words> words = WordsLoader.getWordsList();

        for (int i = 0; i < stringsCount; i++) {
            String string = startingStrings[i].toLowerCase();
            for (int j = 0; j < WordsLoader.getWordsListSize(); j++) {
                if (words.get(i).getDefinition().startsWith(string)) {
                    filteredWords.add(words.get(i));
                }
            }
        }

        System.out.println("Words filtered by starting strings are: ");
        for (Words word : filteredWords) {
            System.out.println(word.getDefinition());
        }
    }

    public static void filteredByContainingString (String ... containingStrings) {
        int stringsCount = containingStrings.length;
        ArrayList<Words> words = WordsLoader.getWordsList();

        for (int i = 0; i < stringsCount; i++) {
            String string = containingStrings[i].toLowerCase();
            for (int j = 0; j < WordsLoader.getWordsListSize(); j++) {
                if (words.get(i).getDefinition().contains(string)) {
                    filteredWords.add(words.get(i));
                }
            }
        }

        System.out.println("Words filtered by containing strings are: ");
        for (Words word : filteredWords) {
            System.out.println(word.getDefinition());
        }
    }

}
