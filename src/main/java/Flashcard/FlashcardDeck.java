package Flashcard;

import java.util.ArrayList;
import java.util.Scanner;

public class FlashcardDeck {


    public ArrayList<Flashcard> flashcardDeck;

    public FlashcardDeck() {
        flashcardDeck = new ArrayList<Flashcard>();
    }

    public void addCards() {
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

    public void listCards() {
        Scanner in = new Scanner(System.in);
        System.out.println("Here is the list of flashcards you have: ");
        for (Flashcard flashcard: flashcardDeck){
            int cardIndex = 0;
            cardIndex ++;
            System.out.println(cardIndex + ". " + flashcard.question + "|" + flashcard.answer);
        }
    }

}
