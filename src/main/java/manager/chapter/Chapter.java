package manager.chapter;

import access.Access;
import manager.card.Card;
import scheduler.Scheduler;
import storage.Storage;
import ui.Ui;

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

    public Chapter(String chapterName, LocalDate dueBy) {
        this.chapterName = chapterName;
        this.cards = new CardList();
        this.dueBy = dueBy;
    }

    public Chapter(String chapterName, String rating, Storage storage, Access access) {
        this.chapterName = chapterName;
        this.cards = new CardList();
        setNewDeckRating(rating, storage, access);
    }

    public static String rateChapter() {
        if (Ui.chooseToRateNewDeck()) {
            return Ui.getChoiceOfNewDeckRating();
        } else {
            return "N";
        }
    }

    public void setNewDeckRating(String rating, Storage storage, Access access) {
        switch (rating) {
        case "E":
            setDueBy(Scheduler.getCurrentDate().plusDays(1), storage, access);
            break;
        case "M":
            setDueBy(Scheduler.getCurrentDate().plusDays(2), storage, access);
            break;
        case "H":
            setDueBy(Scheduler.getCurrentDate().plusDays(4), storage, access);
            break;
        default:
            setDueBy(Scheduler.getCurrentDate(), storage, access);
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

    public void setDueBy(LocalDate dueBy, Storage storage, Access access) {
        this.dueBy = dueBy;
        dueBy.isEqual(LocalDate.now());
        String module = access.getModule().toString();
        storage.saveChapterDeadline(Scheduler.convertDueByToString(dueBy), module, chapterName);
    }

    public LocalDate getDueBy() {
        return dueBy;
    }

    @Override
    public String toString() {
        return chapterName;
    }
}
