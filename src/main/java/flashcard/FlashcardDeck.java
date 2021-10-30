package flashcard;

import userinterface.Ui;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FlashcardDeck {

    public static final String BACK = "back";
    public static final String SHOW_ANSWER = "show answer";
    public ArrayList<Flashcard> flashcardDeck;

    public FlashcardDeck() {
        flashcardDeck = new ArrayList<Flashcard>();
    }

    public void addCards() {
        Ui.printDivider();
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter question: ");
        final String question = in.nextLine();
        System.out.println("Please enter answer: ");
        String answer = in.nextLine();
        while (answer.equalsIgnoreCase(BACK)) {
            System.out.println("The answer cannot be \"back\"! Please enter another answer: ");
            answer = in.nextLine();
        }
        while (answer.equalsIgnoreCase(SHOW_ANSWER)) {
            System.out.println("The answer cannot be \"show answer\"! Please enter another answer: ");
            answer = in.nextLine();
        }
        Ui.printDivider();
        flashcardDeck.add(new Flashcard(question, answer));
        System.out.println("You have successfully created the flashcard below: \n"
                + "Question: " + question + "\n"
                + "Answer: " + answer);
        Ui.printDivider();
    }

    public void listCards() {
        Ui.printDivider();
        if (flashcardDeck.size() == 0) {
            System.out.println("There are no cards in your deck!\n"
                    + "Use \"add\" to add flashcards.");
        } else {
            System.out.println("Here is the list of flashcards you have: ");
            int cardIndex = 1;
            for (Flashcard flashcard : flashcardDeck) {
                System.out.println(cardIndex + ". " + flashcard.question + "|" + flashcard.answer);
                cardIndex++;
            }
        }
        Ui.printDivider();
    }

    public void testRandomCard() {
        if (flashcardDeck.size() > 0) {
            Scanner in = new Scanner(System.in);
            int score = 0;
            Ui.printDivider();
            System.out.println("You have entered the test mode.\n"
                    + "Use \"back\" to return to flashcard main, or \"show answer\" to reveal answer.");
            Ui.printDivider();
            String attempt = "null";
            while (!attempt.equalsIgnoreCase(BACK)) {
                Random random = new Random();
                int randomIndex = random.nextInt(flashcardDeck.size());
                System.out.println("What is the answer to this question?");
                System.out.println(flashcardDeck.get(randomIndex).question);
                attempt = in.nextLine();
                while (!attempt.equalsIgnoreCase(flashcardDeck.get(randomIndex).answer)
                        && !attempt.equalsIgnoreCase(BACK) && !attempt.equalsIgnoreCase(SHOW_ANSWER)) {
                    System.out.println("Incorrect! Try again?");
                    attempt = in.nextLine();
                }
                if (attempt.equalsIgnoreCase(BACK)) {
                    Ui.printDivider();
                    System.out.println("Exiting test mode...\n"
                            + "You are now back in flashcard main.");
                } else if (attempt.equalsIgnoreCase(SHOW_ANSWER)) {
                    Ui.printDivider();
                    System.out.println("The answer for this question is: " + flashcardDeck.get(randomIndex).answer);
                } else {
                    score++;
                    System.out.print("This is the right answer! ");
                    if (score == 1) {
                        System.out.println("You now have " + score + " point.");
                    } else {
                        System.out.println("You now have " + score + " points.");
                    }
                }
                Ui.printDivider();
            }
        } else {
            Ui.printDivider();
            System.out.println("There are no cards in your deck to test from!\n"
                    + "Use \"add\" to add flashcards, before testing yourself.");
            Ui.printDivider();
        }
    }

    public void deleteCard() {
        Scanner in = new Scanner(System.in);
        Ui.printDivider();
        System.out.println("Please enter the index of the card you wish to delete.");
        String userInput = in.nextLine();
        int cardIndex = 0;
        try {
            cardIndex = Integer.parseInt(userInput);
            if (flashcardDeck.size() == 0) {
                Ui.printDivider();
                System.out.println("You do not have any cards in your deck!\n"
                        + "Please use \"add\" to add flashcards to your deck.");
                Ui.printDivider();
            } else if (cardIndex == 0 || cardIndex < 0) {
                Ui.printDivider();
                System.out.println("Please enter a card index greater than 0!");
                Ui.printDivider();
            } else if (cardIndex > flashcardDeck.size()) {
                Ui.printDivider();
                System.out.println("Sorry, you only have " + flashcardDeck.size() + " cards in your deck!\n"
                        + "Please enter a number within the range of 1-" + flashcardDeck.size() + ".");
                Ui.printDivider();
            } else {
                assert cardIndex <= flashcardDeck.size() : "card index inserted should be less than size of deck "
                        + "at this step";
                Ui.printDivider();
                System.out.println("Noted. I have removed this card: "
                        + flashcardDeck.get(cardIndex - 1).question + "|" + flashcardDeck.get(cardIndex - 1).answer
                        + "\n" + "Now you have " + (flashcardDeck.size() - 1) + " cards in the list.");
                Ui.printDivider();
                flashcardDeck.remove(cardIndex - 1);
            }
        } catch (NumberFormatException e) {
            Ui.printDivider();

            System.out.println("Please enter the index of the card as an integer!");
            Ui.printDivider();
        }
    }

    public void findCard() {
        int numberOfCardsFound = 0;
        Scanner in = new Scanner(System.in);
        Ui.printDivider();
        System.out.println("Please enter a search term: ");
        String searchItem = in.nextLine();
        ArrayList<Flashcard> cardsFound = (ArrayList<Flashcard>) flashcardDeck.stream()
                .filter((flashcard) -> flashcard.question.toLowerCase().contains(searchItem.toLowerCase()))
                .collect((Collectors.toList()));
        if (cardsFound.size() == 0) {
            Ui.printDivider();
            System.out.println("There are no matching cards in your list.");
            Ui.printDivider();
        } else {
            Ui.printDivider();
            System.out.println("Here are the matching cards in your list:");
            for (Flashcard flashcard: cardsFound) {
                numberOfCardsFound++;
                System.out.println(numberOfCardsFound + ". " + flashcard.question + "|" + flashcard.answer);
            }
            Ui.printDivider();
        }
    }
}
