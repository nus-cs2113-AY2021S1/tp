package seedu.duke.filters;

import org.junit.jupiter.api.Test;
import seedu.duke.wordlist.WordList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordsFilterTest {

    private static void initializeTestDatabase() {
        WordList.addNoun("noun house d/place to live");
        WordList.addVerb("verb eat d/put sth into your mouth");
        WordList.addAdjective("adj beautiful d/nice to look at");
        WordList.addNoun("noun grass d/green plant");
        WordList.addNoun("noun computer d/hitech stuff");
        WordList.addAdjective("adj nice d/you're so nice");
        WordList.addAdjective("adj meaningful d/something nice");
        WordList.addVerb("verb sleep d/rest at night");
        WordList.addNoun("noun class d/place to study");
        WordList.addVerb("verb love d/fall for somebody");
    }

    @Test
    public void filterByType_filterNounAtTheEnd_getFourNouns() {
        WordList.wordList.clear();
        initializeTestDatabase();
        assertEquals(10, WordList.getNumberOfWords());
        FilterExecutor.executeFilterCommand("filter by\\type -noun -verb");
        assertEquals(7, WordsFilter.filteredWords.size());
        FilterExecutor.executeFilterCommand("filter -continue by\\type -noun");
        assertEquals(4, WordsFilter.filteredWords.size());
    }

    @Test
    public void filterByStartingString_filterMultipleStartingStrings_getOneWord() {
        WordList.wordList.clear();
        initializeTestDatabase();
        assertEquals(10, WordList.getNumberOfWords());
        FilterExecutor.executeFilterCommand("filter by\\start -gr -co -s -ho");
        assertEquals(4, WordsFilter.filteredWords.size());
        FilterExecutor.executeFilterCommand("filter -continue by\\start -g");
        assertEquals(1, WordsFilter.filteredWords.size());
    }

    @Test
    public void filterByIncludedString_filterMultipleIncludedStrings_getOneWord() {
        WordList.wordList.clear();
        initializeTestDatabase();
        assertEquals(10, WordList.getNumberOfWords());
        FilterExecutor.executeFilterCommand("filter by\\include -mp -pu -a -e");
        assertEquals(10, WordsFilter.filteredWords.size());
        FilterExecutor.executeFilterCommand("filter -continue by\\include -e");
        assertEquals(8, WordsFilter.filteredWords.size());
        FilterExecutor.executeFilterCommand("filter -continue by\\include -lo");
        assertEquals(1, WordsFilter.filteredWords.size());
    }
}