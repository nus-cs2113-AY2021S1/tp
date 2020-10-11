package manager.chapter;

import manager.card.Card;

import java.util.ArrayList;

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
}
