package manager.chapter;

import manager.card.Card;

import java.util.ArrayList;

/**
 * A list of cards.
 */
public class CardList {
    private final ArrayList<Card> cards;

    public CardList() {
        this.cards = new ArrayList<>();
    }

    public CardList(ArrayList<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(int removeIndex) {
        cards.remove(removeIndex);
    }

    public int getCardCount() {
        return cards.size();
    }

    public Card getCard(int cardIndex) {
        return cards.get(cardIndex);
    }

    public ArrayList<Card> getAllCards() {
        return cards;
    }

    /**
     * Checks if the list contains an equivalent card that has the same {@code newQuestion} and {@code newAnswer}.
     *
     * @param newQuestion new question for the card
     * @param newAnswer new answer for the card
     * @return true if the list contains an equivalent card that has the same content as the given arguments.
     */
    public boolean checkCardExistence(String newQuestion, String newAnswer) {
        for (Card card : cards) {
            String question = card.getQuestion().toLowerCase();
            String answer = card.getAnswer().toLowerCase();
            if (question.equals(newQuestion) && answer.equals(newAnswer)) {
                return true;
            }
        }
        return false;
    }
}
