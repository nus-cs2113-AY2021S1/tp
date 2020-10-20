package flashcard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlashcardTest {

    @Test
    void writeToFile_addCards_savedToFile() {
        Flashcard flashcard = new Flashcard("2+2", "4");
        String expected = "2+2|4\n";
        String actual = flashcard.writeToFile();
        assertEquals(expected, actual);
    }
}