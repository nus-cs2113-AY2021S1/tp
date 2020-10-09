package seedu.duke.card;

import java.util.ArrayList;
import java.util.List;

public class Topic extends Subject {

    private List<Flashcard> flashcards;

    public Topic(String title) {
        super(title);
        this.flashcards = new ArrayList<>();
    }

    public Topic(String title, List<Flashcard> flashcards) {
        super(title);
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

    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void printTopic(TopicList topicList) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this topic:\n  " + this + "\n"
                + "Now you have " + topicList.getList().size() + (topicList.getList().size() == 1
                ? " topic in the list.\n" : " topics in the list.\n")
                + "____________________________________________________________");
    }

}
