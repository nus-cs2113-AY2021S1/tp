package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
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

    public static final String MESSAGE_EXIST = "Here are the %s(s) in your list:\n";
    public static final String MESSAGE_DOES_NOT_EXIST = "There are no %s(s) in your list.";

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IncorrectAccessLevelException {
        if (access.isChapterLevel()) {
            String result = listCards(access);
            ui.showToUser(result);
        } else if (access.isModuleLevel()) {
            String result = listChapters(access);
            ui.showToUser(result);
        } else if (access.isAdminLevel()) {
            String result = listModules(access);
            ui.showToUser(result);
        } else {
            throw new IncorrectAccessLevelException("List command can only be called at admin, "
                    + "module and chapter level.\n");
        }
    }

    private String listCards(Access access) {
        assert access.isChapterLevel() : "Not chapter level";
        CardList cards = access.getChapter().getCards();
        ArrayList<Card> allCards = cards.getAllCards();
        int cardCount = cards.getCardCount();
        StringBuilder result = new StringBuilder();
        if (cardCount == 0) {
            result.append(String.format(MESSAGE_DOES_NOT_EXIST, CARD));
            return result.toString();
        }
        result.append(String.format(MESSAGE_EXIST, CARD));
        for (Card c : allCards) {
            if (allCards.indexOf(c) == cardCount - 1) {
                result.append(allCards.indexOf(c) + 1).append(".").append(c);
            } else {
                result.append(allCards.indexOf(c) + 1).append(".").append(c).append("\n");
            }
        }
        return result.toString();
    }

    private String listModules(Access access) {
        ModuleList modules = access.getAdmin().getModules();
        ArrayList<Module> allModules = modules.getAllModules();
        int moduleCount = modules.getModuleCount();
        StringBuilder result = new StringBuilder();
        if (moduleCount == 0) {
            result.append(String.format(MESSAGE_DOES_NOT_EXIST, MODULE));
            return result.toString();
        }
        result.append(String.format(MESSAGE_EXIST, MODULE));
        for (Module m : allModules) {
            if (allModules.indexOf(m) == moduleCount - 1) {
                result.append(allModules.indexOf(m) + 1).append(".").append(m);
            } else {
                result.append(allModules.indexOf(m) + 1).append(".").append(m).append("\n");
            }
        }
        return result.toString();
    }

    private String listChapters(Access access) {
        ChapterList chapters = access.getModule().getChapters();
        ArrayList<Chapter> allChapters = chapters.getAllChapters();
        int chapterCount = chapters.getChapterCount();
        StringBuilder result = new StringBuilder();
        if (chapterCount == 0) {
            result.append(String.format(MESSAGE_DOES_NOT_EXIST, CHAPTER));
            return result.toString();
        }
        result.append(String.format(MESSAGE_EXIST, CHAPTER));
        for (Chapter c : allChapters) {
            if (allChapters.indexOf(c) == chapterCount - 1) {
                result.append(allChapters.indexOf(c) + 1).append(".").append(c);
            } else {
                result.append(allChapters.indexOf(c) + 1).append(".").append(c).append("\n");
            }
        }
        return result.toString();
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
