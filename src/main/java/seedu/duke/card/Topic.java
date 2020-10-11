package seedu.duke.card;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String title;

    private List<Flashcard> flashcards;

    public Topic(String title) {
        this(title, new ArrayList<>());
    }

    public Topic(String title, List<Flashcard> flashcards) {
        this.title = title;
        this.flashcards = flashcards;
    }

    public void addFlashCard(Flashcard flashcard) {
        flashcards.add(flashcard);
    }

    public boolean removeFlashcard(Flashcard flashcard) {
        return flashcards.remove(flashcard);
    }

    public Flashcard removeFlashcard(int index) {
        return flashcards.remove(index);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Flashcard> getFlashcards() {
        return flashcards;
    }
}
