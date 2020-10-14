package manager.chapter;

import manager.card.Card;
import parser.Parser;
import scheduler.Scheduler;

import java.time.LocalDate;

import java.util.ArrayList;

public class Chapter {
    protected String chapterName;
    protected CardList cards;
    protected LocalDate dueBy;

    public Chapter(String chapterName) {
        this.chapterName = chapterName;
        this.cards = new CardList();
    }

    public Chapter(String chapterName, String rating) {
        this.chapterName = chapterName;
        this.cards = new CardList();
        setNewDeckRating(rating);
    }

    public static String rateChapter() {
        if (Parser.chooseToRateNewDeck()) {
            return Parser.getChoiceOfNewDeckRating();
        } else {
            return null;
        }
    }

    public void setNewDeckRating(String rating) {
        switch (rating) {
        case "E":
            setDueBy(Scheduler.getCurrentDate().plusDays(1));
            break;
        case "M":
            setDueBy(Scheduler.getCurrentDate().plusDays(2));
            break;
        case "H":
            setDueBy(Scheduler.getCurrentDate().plusDays(4));
            break;
        default:
            setDueBy(Scheduler.getCurrentDate());
        }
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public CardList getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = new CardList(cards);
    }

    public void setDueBy(LocalDate dueBy) {
        this.dueBy = dueBy;
    }

    public LocalDate getDueBy() {
        return dueBy;
    }

    @Override
    public String toString() {
        return chapterName;
    }
}
