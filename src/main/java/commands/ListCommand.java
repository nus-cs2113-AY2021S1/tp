package commands;

import access.Access;
import manager.admin.ModuleList;
import manager.card.Card;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

import static common.Messages.CARD;
import static common.Messages.CHAPTER;
import static common.Messages.MODULE;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a list of modules / chapters / flashcards available. \n"
            + "Example: " + COMMAND_WORD + "\n";

    public static final String MESSAGE_EXIST = "Here are the %s(s) in your list:";
    public static final String MESSAGE_DOES_NOT_EXIST = "There are no %s(s) in your list.";

    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        String result = "";

        if (access.isChapterLevel()) {
            result = listCards(access);
        } else if (access.isModuleLevel()) {
            result = listChapters(access);
        } else if (access.isAdminLevel()) {
            result = listModules(access);
        }

        ui.showToUser(result);
    }

    private String listCards(Access access) {
        assert access.isChapterLevel() : "Not chapter level";
        CardList cards = access.getChapter().getCards();
        ArrayList<Card> allCards = cards.getAllCards();
        int cardCount = cards.getCardCount();

        if (cardCount == 0) {
            return String.format(MESSAGE_DOES_NOT_EXIST, CARD);
        }

        StringBuilder result = new StringBuilder();
        result.append(String.format(MESSAGE_EXIST, CARD));
        for (Card c : allCards) {
            result.append("\n").append(allCards.indexOf(c) + 1).append(".").append(c);
        }
        return result.toString();
    }

    private String listModules(Access access) {
        ModuleList modules = access.getAdmin().getModules();
        ArrayList<Module> allModules = modules.getAllModules();
        int moduleCount = modules.getModuleCount();

        if (moduleCount == 0) {
            return String.format(MESSAGE_DOES_NOT_EXIST, MODULE);
        }

        StringBuilder result = new StringBuilder();
        result.append(String.format(MESSAGE_EXIST, MODULE));
        for (Module m : allModules) {
            result.append("\n").append(allModules.indexOf(m) + 1).append(".").append(m);
        }
        return result.toString();
    }

    private String listChapters(Access access) {
        ChapterList chapters = access.getModule().getChapters();
        ArrayList<Chapter> allChapters = chapters.getAllChapters();
        int chapterCount = chapters.getChapterCount();

        if (chapterCount == 0) {
            return String.format(MESSAGE_DOES_NOT_EXIST, CHAPTER);
        }

        StringBuilder result = new StringBuilder();
        result.append(String.format(MESSAGE_EXIST, CHAPTER));
        for (Chapter c : allChapters) {
            result.append("\n").append(allChapters.indexOf(c) + 1).append(".")
                    .append(c);
            if (c.getDueBy() == null) {
                result.append(" (No due date)");
            } else {
                result.append(" (due by ").append(c.getDueBy()).append(")");
            }
        }
        return result.toString();
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
