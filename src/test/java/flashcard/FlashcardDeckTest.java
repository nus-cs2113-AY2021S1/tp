package flashcard;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardDeckTest {

    @Test
    void addCards() {
        FlashcardDeck flashcardDeck = new FlashcardDeck();
        String input = "2+2\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        flashcardDeck.addCards();
        assertEquals("2+2", flashcardDeck.flashcardDeck.get(0).question);
        assertEquals("4", flashcardDeck.flashcardDeck.get(0).answer);
    }

    @Test
    void deleteCard() {
        FlashcardDeck flashcardDeck = new FlashcardDeck();
        Flashcard flashcard1 = new Flashcard("1+1", "2");
        Flashcard flashcard2 = new Flashcard("2+2", "4");
        Flashcard flashcard3 = new Flashcard("3+3", "6");
        Flashcard flashcard4 = new Flashcard("4+4", "8");
        flashcardDeck.flashcardDeck.add(flashcard1);
        flashcardDeck.flashcardDeck.add(flashcard2);
        flashcardDeck.flashcardDeck.add(flashcard3);
        flashcardDeck.flashcardDeck.add(flashcard4);
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(4, flashcardDeck.flashcardDeck.size());
        flashcardDeck.deleteCard();
        assertEquals(3, flashcardDeck.flashcardDeck.size());
        assertEquals("4+4", flashcardDeck.flashcardDeck.get(2).question);
    }

}