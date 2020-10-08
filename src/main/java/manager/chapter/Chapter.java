package manager.chapter;

import manager.card.Card;

import java.util.ArrayList;

public class Chapter {
    protected String chapterName;
    protected ArrayList<Card> cards;
    protected int cardAmount;
    protected static int totalChapter = 0;

    public Chapter(String chapterName) {
        this.chapterName = chapterName;
        cards = new ArrayList<Card>();
        cardAmount = 0;
        totalChapter++;
    }

    @Override
    public String toString() {
        return "<" + chapterName + ">";
    }

    public Chapter(String chapterName, ArrayList<Card> cards) {
        this.chapterName = chapterName;
        this.cards = new ArrayList<>(cards);
        cardAmount = cards.size();
    }

    public void add(Card card) {
        cards.add(card);
        cardAmount++;
    }

    public void doneAddChapter() {
        System.out.println("    Got it. I've added this chapter:");
        System.out.println("    " + getChapter());
        System.out.println("    Now you have " + totalChapter + " chapters in the list.");
    }

    public String getChapter() {
        return chapterName;
    }
}
