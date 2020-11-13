package seedu.duke.spellchecker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class SpellCheckerTest {

    @Test
    void checkSpelling() {
        ArrayList<String> test = SpellChecker.checkSpelling("The quick brown fox jumps over the lazy dog");
        assert test.size() == 0;
        test = SpellChecker.checkSpelling("The quick brown foxs jumps over the lazy dogs");
        assert test.size() == 0;
        test = SpellChecker.checkSpelling("The quick brown foxes jumps over the lazy doges");
        assert test.size() == 0;
        test = SpellChecker.checkSpelling("The slow broen fox jumps over the lazy dog");
        assert test.size() == 1;
        test = SpellChecker.checkSpelling("The quivk brown fox jimps over the lzzy dog");
        assert test.size() == 3;
    }

    @Test
    void isAlpha() {
        assert SpellChecker.isAlpha("ABC") == true;
        assert SpellChecker.isAlpha("123") == false;
        assert SpellChecker.isAlpha("@BC") == false;
    }
}
