package flashcard;

import java.io.IOException;
import java.util.Scanner;

public class FlashcardRun {
    public static final String TEST = "test";
    public FlashcardDeck flashcardDeck;
    public FlashcardStorage storage;
    public static final String ADD = "add";
    public static final String LIST = "list";
    public static final String EXIT = "exit";

    public FlashcardRun() {
        flashcardDeck = new FlashcardDeck();
        try {
            storage = new FlashcardStorage("flashcard.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        storage.readFromFile(flashcardDeck);
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        String command;
        do {
            command = in.nextLine();
            switch (command) {
            case ADD: flashcardDeck.addCards();
                break;
            case LIST: flashcardDeck.listCards();
                break;
            case TEST: flashcardDeck.testRandomCard();
                break;
            default: System.out.println("Invalid command. Valid commands are 'add', 'list'. "
                    + "Use 'exit' to exit the flashcard mode.");
            }
        } while (!command.equals(EXIT));
        storage.writeToFile(flashcardDeck);
    }
}
