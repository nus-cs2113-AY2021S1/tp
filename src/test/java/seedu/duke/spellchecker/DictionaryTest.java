package seedu.duke.spellchecker;

import org.junit.jupiter.api.Test;

class DictionaryTest {

    @Test
    void checkDictionaryLoaded() {
        Dictionary dict = Dictionary.getInstance();
        assert dict.getWordList().size() > 0;
    }

}
