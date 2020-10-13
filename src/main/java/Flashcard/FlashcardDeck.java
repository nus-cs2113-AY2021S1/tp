package Flashcard;

import java.util.ArrayList;
import java.util.Scanner;

public class FlashcardDeck {

    public static final String ADD = "add";
    public static final String LIST = "list";
    public static final String EXIT = "exit";
    public ArrayList<Flashcard> flashcardDeck;

    public FlashcardDeck() {
        flashcardDeck = new ArrayList<Flashcard>();
        Scanner in = new Scanner(System.in);
        String command;
        do {
            command = in.nextLine();
            switch(command) {
            case ADD: addCards();
                break;
            case LIST: listCards();
                break;
            default: System.out.println("Invalid command. Valid commands are 'add', 'list'." +
                    "Use 'exit' to exit the flashcard mode.");
            }
        } while (!command.equals(EXIT));
    }

    private void addCards() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter question: ");
        String question = in.nextLine();
        System.out.println("Please enter answer: ");
        String answer = in.nextLine();
        flashcardDeck.add(new Flashcard(question, answer));
        System.out.println("You have successfully created the flashcards below: \n" +
                "Question: " + question + "\n" +
                "Answer: " + answer);
    }

    private void listCards() {
        Scanner in = new Scanner(System.in);
        System.out.println("Here is the list of flashcards you have: ");
        for (Flashcard flashcard: flashcardDeck){
            int cardIndex = 0;
            cardIndex ++;
            System.out.println(cardIndex + ". " + flashcard.question + "|" + flashcard.answer);
        }
    }

}
