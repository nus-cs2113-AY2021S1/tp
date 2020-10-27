package flashcard;

import java.io.IOException;
import java.util.Scanner;

public class FlashcardRun {
    public static final String TEST = "test";
    public static final String DELETE = "delete";
    public FlashcardDeck flashcardDeck;
    public FlashcardStorage storage;
    public static final String ADD = "add";
    public static final String LIST = "list";
    public static final String EXIT = "exit";

    public FlashcardRun() {
        flashcardDeck = new FlashcardDeck();
        try {
            storage = new FlashcardStorage("data/flashcard.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        storage.readFromFile(flashcardDeck);
    }

    public void run(String command) {
        switch (command) {
        case ADD:
            flashcardDeck.addCards();
            break;
        case DELETE:
            flashcardDeck.deleteCard();
            break;
        case LIST:
            flashcardDeck.listCards();
            break;
        case TEST:
            flashcardDeck.testRandomCard();
            break;
        default:
            System.out.println("Invalid command. Valid commands are 'add', 'delete', 'list', 'test'. "
                    + "Use 'exit' to exit the flashcard mode.");
        }
        storage.writeToFile(flashcardDeck);
    }
}
