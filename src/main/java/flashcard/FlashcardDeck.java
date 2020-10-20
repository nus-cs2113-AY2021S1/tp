package flashcard;

import java.util.ArrayList;
import java.util.Collections;
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
        System.out.println("Here is the list of flashcards you have: ");
        int cardIndex = 1;
        for (Flashcard flashcard: flashcardDeck) {
            System.out.println(cardIndex + ". " + flashcard.question + "|" + flashcard.answer);
            cardIndex++;
        }
    }

    public void testRandomCard() {
        Scanner in = new Scanner(System.in);
        String attempt = "null";
        System.out.println("You have entered the test mode.");
        while (!attempt.equals("back")) {
            Random random = new Random();
            int randomIndex = random.nextInt(flashcardDeck.size());
            System.out.println("What is the answer to this question?");
            System.out.println(flashcardDeck.get(randomIndex).question);
            attempt = in.nextLine();
            while (!attempt.equals(flashcardDeck.get(randomIndex).answer) && !attempt.equals("back")) {
                System.out.println("Incorrect! Try again?");
                attempt = in.nextLine();
            }
            if (attempt.equals("back")) {
                System.out.println("Exiting test mode...");
            } else {
                System.out.println("This is the right answer!");
            }
        }
    }

    public void deleteCard() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the index of the card you wish to delete.");
        String userInput = in.nextLine();
        int cardIndex = 0;
        try {
            cardIndex = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Please enter the index of the card as an integer!");
        }
        if (cardIndex > flashcardDeck.size()) {
            System.out.println("Sorry, you only have " + flashcardDeck.size() + " cards in your deck!\n"
                    + "Please enter a number within the range of 1-" + flashcardDeck.size() + ".");
        } else {
            assert flashcardDeck.size() <= cardIndex : "size should not be greater than card index at this step";
            System.out.println("Noted. I have removed this card: "
                    + flashcardDeck.get(cardIndex - 1).question + "|" + flashcardDeck.get(cardIndex - 1).answer
                    + "\n" + "Now you have " + (flashcardDeck.size() - 1) + " cards in the list.");
            flashcardDeck.remove(cardIndex - 1);
        }
    }
}
