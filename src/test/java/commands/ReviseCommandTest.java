package commands;

import manager.card.Card;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviseCommandTest {
    @Test
    public void rateCard_cannotAnswer_returnsSizeOne() {
        String question = "1+1?";
        String answer = "2";
        ArrayList<Card> cards = new ArrayList<>();
        Card card = new Card(question, answer);
        Ui ui = new Ui();
        assertEquals(ReviseCommand.rateCard(ui, cards, card, "c").size(), 1);
    }

    @Test
    public void rateCard_easy_returnsSizeZero() {
        String question = "1+1?";
        String answer = "2";
        ArrayList<Card> cards = new ArrayList<>();
        Card card = new Card(question, answer);
        Ui ui = new Ui();
        assertEquals(ReviseCommand.rateCard(ui, cards, card, "e").size(), 0);
    }
}
