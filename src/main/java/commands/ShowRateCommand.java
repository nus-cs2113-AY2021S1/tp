package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import exception.InvalidFileFormatException;
import exception.InvalidInputException;
import manager.card.Card;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import storage.Storage;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static common.Messages.CARD;

public class ShowRateCommand extends Command {
    public static final String COMMAND_WORD = "showrate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Show rate percentage in chapter based on individual cards. \n"
            + "Only available at chapter level. \n"
            + "Example: " + COMMAND_WORD + "\n";

    public static final String MESSAGE_NO_CARDS_IN_CHAPTER = "You currently have no cards in %1$s";
    public static final String MESSAGE_SHOW_REVISE_PROMPT = "Are you sure you want to revise this? (Y/N)";
    public static final String MESSAGE_SHOW_EASY_PROMPT = "The percentage of card that is labeled <easy> is: %1$.2f";
    public static final String MESSAGE_SHOW_MEDIUM_PROMPT = "The percentage of card that is labeled <medium> is: %1$.2f";
    public static final String MESSAGE_SHOW_HARD_PROMPT = "The percentage of card that is labeled <hard> is: %1$.2f";
    public static final String MESSAGE_SHOW_CANNOT_ANSWER_PROMPT
            = "The percentage of card that is labeled <cannot answer> is: %1$.2f";

    private Double easyPercentage;
    private Double mediumPercentage;
    private Double hardPercentage;
    private Double cannotAnswerPercentage;
    private int easyCard = 0;
    private int mediumCard = 0;
    private int hardCard = 0;
    private int cannotAnswerCard = 0;


    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        Chapter chapter = access.getChapter();
        computePercentage(chapter, ui);

    }

    private void computePercentage(Chapter chapter, Ui ui) {
        ArrayList<Card> allCards = chapter.getCards().getAllCards();
        int cardCount = allCards.size();
        ui.showToUser("\nCard count: " + cardCount);
        if (cardCount == 0) {
            ui.showToUser(String.format(MESSAGE_NO_CARDS_IN_CHAPTER, chapter));
            return;
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
        ui.showToUser(String.format(MESSAGE_SHOW_EASY_PROMPT, easyPercentage));
        ui.showToUser(String.format(MESSAGE_SHOW_MEDIUM_PROMPT, mediumPercentage));
        ui.showToUser(String.format(MESSAGE_SHOW_HARD_PROMPT, hardPercentage));
        ui.showToUser(String.format(MESSAGE_SHOW_CANNOT_ANSWER_PROMPT, cannotAnswerPercentage));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
