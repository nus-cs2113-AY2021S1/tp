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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.logging.Logger;
import java.util.logging.Level;

public class RemoveCommand extends Command {
    //private static Logger logger = Logger.getLogger("RemoveCommandLog");

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes module / chapter / flashcard based on a specified index in the list. \n"
            + "Parameters: INDEX\n" + "Example: " + COMMAND_WORD + " 2\n";

    public static final String MESSAGE_SUCCESS_MODULE = "The module <%1$s> has been removed.\n";
    public static final String MESSAGE_REMAINING_MODULE = "You currently have %1$d module(s).";
    public static final String MESSAGE_INVALID_INDEX_MODULE = "The module is not found, please try again.";
    public static final String MESSAGE_SUCCESS_CHAPTER = "The chapter <%1$s> has been removed.\n";
    public static final String MESSAGE_REMAINING_CHAPTER = "You currently have %1$d chapter(s) in this module.";
    public static final String MESSAGE_INVALID_INDEX_CHAPTER = "The chapter is not found, please try again.";
    public static final String MESSAGE_SUCCESS_FLASHCARD = "The following flashcard has been removed:\n";
    public static final String MESSAGE_REMAINING_FLASHCARD = "You currently have %1$d card(s) in this chapter.";
    public static final String MESSAGE_INVALID_INDEX_FLASHCARD = "The flashcard is not found, please try again.";

    private final int removeIndex;

    public RemoveCommand(int removeIndex) {
        this.removeIndex = removeIndex;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage)
            throws IncorrectAccessLevelException, IOException {
        if (access.isAdminLevel()) {
            String result = removeModule(access, storage);
            ui.showToUser(result);
        } else if (access.isModuleLevel()) {
            String result = removeChapter(access, storage);
            ui.showToUser(result);
        } else if (access.isChapterLevel()) {
            String result = removeCard(access, storage);
            ui.showToUser(result);
        } else {
            throw new IncorrectAccessLevelException("Remove command can only be called at admin, "
                    + "module and chapter level.\n");
        }
    }

    private String removeModule(Access access, Storage storage) throws IOException {
        assert access.isAdminLevel() : "Not admin level";
        StringBuilder result = new StringBuilder();
        try {
            ModuleList modules = access.getAdmin().getModules();
            ArrayList<Module> allModules = modules.getAllModules();
            Module module = allModules.get(removeIndex);
            File directory = new File(storage.getFilePath() + "/" + module.toString());
            //logger.log(Level.INFO, "Deleting module...");
            boolean isRemoved = storage.deleteDirectory(directory);
            if (!isRemoved) {
                throw new IOException("There was a problem deleting module in directory.");
            }
            result.append(String.format(MESSAGE_SUCCESS_MODULE, module.toString()));
            allModules.remove(removeIndex);
            result.append(String.format(MESSAGE_REMAINING_MODULE, allModules.size()));
        } catch (IndexOutOfBoundsException e) {
            result.append(MESSAGE_INVALID_INDEX_MODULE);
        }
        return result.toString();
    }

    private String removeChapter(Access access, Storage storage) throws IOException {
        assert access.isModuleLevel() : "Not module level";
        StringBuilder result = new StringBuilder();
        try {
            ChapterList chapters = access.getModule().getChapters();
            ArrayList<Chapter> allChapters = chapters.getAllChapters();
            Chapter chapter = allChapters.get(removeIndex);
            File directory = new File(storage.getFilePath() + "/" + access.getModule()
                + "/" + chapter.toString() + ".txt");
            //logger.log(Level.INFO, "Deleting chapter...");
            boolean isRemoved = storage.deleteDirectory(directory);
            if (!isRemoved) {
                //logger.log(Level.WARNING, "problem deleting chapter");
                throw new IOException("There was a problem deleting chapter in directory.");
            }
            result.append(String.format(MESSAGE_SUCCESS_CHAPTER, chapter.toString()));
            allChapters.remove(removeIndex);
            result.append(String.format(MESSAGE_REMAINING_CHAPTER, allChapters.size()));
            //logger.log(Level.INFO, "Chapter successfully deleted.");
        } catch (IndexOutOfBoundsException e) {
            result.append(MESSAGE_INVALID_INDEX_CHAPTER);
        }
        return result.toString();
    }

    private String removeCard(Access access, Storage storage) throws IOException {
        assert access.isChapterLevel() : "Not chapter level";
        StringBuilder result = new StringBuilder();
        try {
            CardList cards = access.getChapter().getCards();
            ArrayList<Card> allCards = cards.getAllCards();
            Card card = allCards.get(removeIndex);
            //logger.log(Level.INFO, "Deleting flashcard...");
            cards.removeCard(removeIndex);
            result.append(MESSAGE_SUCCESS_FLASHCARD + card.toString() + "\n"
                    + String.format(MESSAGE_REMAINING_FLASHCARD, cards.getCardCount()));
            storage.saveCards(cards, access.getModule().getModuleName(), access.getChapter().getChapterName());
            //logger.log(Level.INFO, "Flashcard successfully deleted.");
        } catch (IndexOutOfBoundsException e) {
            result.append(MESSAGE_INVALID_INDEX_FLASHCARD);
        }
        return result.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
