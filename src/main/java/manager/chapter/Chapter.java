package manager.chapter;

import manager.card.Card;
import manager.module.ChapterList;

import java.util.ArrayList;

public class Chapter {
    protected String chapterName;
    protected CardList cards;

    public Chapter(String chapterName) {
        this.chapterName = chapterName;
        this.cards = new CardList();
    }

    public String getChapterName() {
        return chapterName;
    }

    public CardList getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = new CardList(cards);
    }

    @Override
    public String toString() {
        return chapterName;
    }
}
