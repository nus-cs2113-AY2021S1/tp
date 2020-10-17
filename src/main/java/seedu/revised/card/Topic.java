package seedu.revised.card;

import seedu.revised.card.quiz.ResultList;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String title;
    private List<Flashcard> flashcards;
    private ResultList results;

    public Topic(String title) {
        this.title = title;
        this.flashcards = new ArrayList<>();
        results = new ResultList(new ArrayList<>());
    }

    public Topic(String title, List<Flashcard> flashcards) {
        this.title = title;
        this.flashcards = flashcards;
        results = new ResultList(new ArrayList<>());
    }

    public String getTitle() {
        return title;
    }

    public void addFlashcard(Flashcard flashcard) {
        flashcards.add(flashcard);
    }

    public boolean removeFlashcard(Flashcard flashcard) {
        return flashcards.remove(flashcard);
    }

    public Flashcard removeFlashcard(int index) {
        return flashcards.remove(index);
    }

    public List<Flashcard> getFlashcards() {
        return this.flashcards;
    }

    public ResultList getResults() {
        return results;
    }


    @Override
    public String toString() {
        return this.title;
    }
}
