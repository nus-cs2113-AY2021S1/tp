package seedu.duke.level;

import java.util.ArrayList;

public class Chapter {
    protected ArrayList<Card> cards;
    protected int cardAmount;
    protected String chapterName;
    protected int totalChapter = 0;

    public Chapter(String chapterName) {
        this.chapterName = chapterName;
        cards = new ArrayList<Card>();
        cardAmount = 0;
        totalChapter++;
    }

    public void add(Card card){
        cards.add(card);
        cards.get(cardAmount).doneAddCard();
        cardAmount++;
    }

    public void doneAddChapter() {
        totalChapter++;
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + getChapter());
        System.out.println("    Now you have " + totalChapter +" modules in the list.");
    }

    private String getChapter() {
        return chapterName;
    }

}
