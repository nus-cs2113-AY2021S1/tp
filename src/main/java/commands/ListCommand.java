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

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a list of modules / chapters / flashcards available. \n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(CardList cards, Ui ui, Access access, Storage storage) throws IncorrectAccessLevelException {
        if (access.isChapterLevel()) {
            listCards(ui, access);
        } else if (access.isModuleLevel()) {
            listChapters(ui, access);
        } else if (access.isAdminLevel()) {
            listModules(ui, access);
        }
        else {
            throw new IncorrectAccessLevelException("List command can only be called at admin, "
                    + "module and chapter level.\n");
        }
    }

    private void listCards(Ui ui, Access access) {
        CardList cards = access.getChapter().getCards();
        ArrayList<Card> allCards = cards.getAllCards();
        int cardCount = cards.getCardCount();
        ui.showCardList(allCards, cardCount);
    }

    private void listModules(Ui ui, Access access) {
        ModuleList modules = access.getAdmin().getModules();
        ArrayList<Module> allModules = modules.getAllModules();
        int chapterCount = modules.getModuleCount();
        ui.showModuleList(allModules, chapterCount);
    }

    private void listChapters(Ui ui, Access access) {
        ChapterList chapters = access.getModule().getChapters();
        ArrayList<Chapter> allChapters = chapters.getAllChapters();
        int chapterCount = chapters.getChapterCount();
        ui.showChapterList(allChapters, chapterCount);
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
