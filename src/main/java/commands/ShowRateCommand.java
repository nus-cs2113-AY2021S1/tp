package commands;

import access.Access;
import manager.card.Card;
import manager.chapter.Chapter;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

public class ShowRateCommand extends Command {
    public static final String COMMAND_WORD = "showrate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows rate percentage in chapter based on individual cards. \n"
            + "Only available at chapter level. \n"
            + "Example: " + COMMAND_WORD + "\n";

    public static final String MESSAGE_NO_CARDS_IN_CHAPTER = "You currently have no cards in %1$s";
    public static final String MESSAGE_SHOW_PERCENTAGE_PROMPT = "The percentage of card that "
            + "is labeled <%1$s> is: %2$.2f";
    public static final String CANNOT_ANSWER = "cannot answer";
    public static final String EASY = "easy";
    public static final String MEDIUM = "medium";
    public static final String HARD = "hard";

    private Double easyPercentage;
    private Double mediumPercentage;
    private Double hardPercentage;
    private Double cannotAnswerPercentage;
    private int easyCard = 0;
    private int mediumCard = 0;
    private int hardCard = 0;
    private int cannotAnswerCard = 0;
    private int noCard = 0;


    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        Chapter chapter = access.getChapter();
        int cardCount = computePercentage(chapter);
        if (cardCount == noCard) {
            ui.showToUser(String.format(MESSAGE_NO_CARDS_IN_CHAPTER, chapter));
            return;
        }
        ui.showToUser("\nCard count: " + cardCount);
        ui.showToUser(String.format(MESSAGE_SHOW_PERCENTAGE_PROMPT, EASY, easyPercentage));
        ui.showToUser(String.format(MESSAGE_SHOW_PERCENTAGE_PROMPT, MEDIUM, mediumPercentage));
        ui.showToUser(String.format(MESSAGE_SHOW_PERCENTAGE_PROMPT, HARD, hardPercentage));
        ui.showToUser(String.format(MESSAGE_SHOW_PERCENTAGE_PROMPT, CANNOT_ANSWER, cannotAnswerPercentage));
    }

    private int computePercentage(Chapter chapter) {
        ArrayList<Card> allCards = chapter.getCards().getAllCards();
        int cardCount = allCards.size();
        if (cardCount == 0) {
            return cardCount;
        }

        for (Card c : allCards) {
            switch (c.getRating()) {
            case Card.EASY:
                easyCard++;
                break;
            case Card.MEDIUM:
                mediumCard++;
                break;
            case Card.HARD:
                hardCard++;
                break;
            default:
                cannotAnswerCard++;
                break;
            }
        }

        assert ((cannotAnswerCard + hardCard + easyCard + mediumCard) == cardCount) : "Wrong card count";
        easyPercentage = ((double)easyCard) / cardCount;
        mediumPercentage = ((double)mediumCard) / cardCount;
        hardPercentage = ((double)hardCard) / cardCount;
        cannotAnswerPercentage = ((double)cannotAnswerCard) / cardCount;
        return cardCount;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
