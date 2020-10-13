package flashcard;

import java.util.ArrayList;
import java.util.Random;
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
        System.out.println("You have successfully created the flashcard below: \n"
                + "Question: " + question + "\n"
                + "Answer: " + answer);
    }

    public void listCards() {
        Scanner in = new Scanner(System.in);
        System.out.println("Here is the list of flashcards you have: ");
        int cardIndex = 1;
        for (Flashcard flashcard: flashcardDeck) {
            System.out.println(cardIndex + ". " + flashcard.question + "|" + flashcard.answer);
            cardIndex++;
        }
    }

    public void testRandomCard() {
        Random random = new Random();
        int randomIndex = random.nextInt(flashcardDeck.size());
        System.out.println("What is the answer to this question?");
        System.out.println(flashcardDeck.get(randomIndex).question);
        Scanner in = new Scanner(System.in);
        String attempt = in.nextLine();
        while (!attempt.equals(flashcardDeck.get(randomIndex).answer)) {
            System.out.println("Incorrect! Try again?");
            attempt = in.nextLine();
        }
        System.out.println("This is the right answer!");
    }
}
