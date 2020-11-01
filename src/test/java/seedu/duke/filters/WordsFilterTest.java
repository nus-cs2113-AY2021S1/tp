package seedu.duke.filters;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.FilterCommandException;
import seedu.duke.wordlist.WordList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordsFilterTest {

    public static void initializeTestDatabase() {
        WordList.addNoun("noun house d\\place to live");
        WordList.addVerb("verb eat d\\put sth into your mouth");
        WordList.addAdjective("adj beautiful d\\nice to look at");
        WordList.addNoun("noun grass d\\green plant");
        WordList.addNoun("noun computer d\\hitech stuff");
        WordList.addAdjective("adj nice d\\you're so nice");
        WordList.addAdjective("adj meaningful d\\something nice");
        WordList.addVerb("verb sleep d\\rest at night");
        WordList.addNoun("noun class d\\place to study");
        WordList.addVerb("verb love d\\fall for somebody");
    }

    @Test
    public void filterByType_filterNounAtTheEnd_getFourNouns() {
        WordList.getWordList().clear();
        initializeTestDatabase();
        assertEquals(10, WordList.getNumberOfWords());
        try {
            FilterExecutor.executeFilterCommand("filter words by\\type -noun -verb");
        } catch (FilterCommandException e) {
            e.printStackTrace();
        }
        assertEquals(7, WordsFilter.filteredWords.size());
        try {
            FilterExecutor.executeFilterCommand("filter words -continue by\\type -noun");
        } catch (FilterCommandException e) {
            e.printStackTrace();
        }
        assertEquals(4, WordsFilter.filteredWords.size());
    }

    @Test
    public void filterByStartingString_filterMultipleStartingStrings_getOneWord() {
        WordList.getWordList().clear();
        initializeTestDatabase();
        assertEquals(10, WordList.getNumberOfWords());
        try {
            FilterExecutor.executeFilterCommand("filter words by\\start -gr -co -s -ho");
        } catch (FilterCommandException e) {
            e.printStackTrace();
        }
        assertEquals(4, WordsFilter.filteredWords.size());
        try {
            FilterExecutor.executeFilterCommand("filter words -continue by\\start -g");
        } catch (FilterCommandException e) {
            e.printStackTrace();
        }
        assertEquals(1, WordsFilter.filteredWords.size());
    }

    @Test
    public void filterByIncludedString_filterMultipleIncludedStrings_getOneWord() {
        WordList.getWordList().clear();
        initializeTestDatabase();
        assertEquals(10, WordList.getNumberOfWords());
        try {
            FilterExecutor.executeFilterCommand("filter words by\\include -mp -pu -a -e");
        } catch (FilterCommandException e) {
            e.printStackTrace();
        }
        assertEquals(10, WordsFilter.filteredWords.size());
        try {
            FilterExecutor.executeFilterCommand("filter words -continue by\\include -e");
        } catch (FilterCommandException e) {
            e.printStackTrace();
        }
        assertEquals(8, WordsFilter.filteredWords.size());
        try {
            FilterExecutor.executeFilterCommand("filter words -continue by\\include -lo");
        } catch (FilterCommandException e) {
            e.printStackTrace();
        }
        assertEquals(1, WordsFilter.filteredWords.size());
    }
}