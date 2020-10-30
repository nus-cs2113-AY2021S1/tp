package commands;

import access.Access;
import manager.chapter.Chapter;
import manager.module.ChapterList;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

import static common.Messages.CHAPTER;

public class ListChaptersCommand extends ListCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of chapters available. \n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        String result = listChapters(access);
        ui.showToUser(result);
    }

    private String listChapters(Access access) {
        assert access.isModuleLevel() : "Not module level";
        ChapterList chapters = access.getModule().getChapters();
        ArrayList<Chapter> allChapters = chapters.getAllChapters();
        int chapterCount = chapters.getChapterCount();

        if (chapterCount == 0) {
            return String.format(MESSAGE_DOES_NOT_EXIST, CHAPTER);
        }

        StringBuilder result = new StringBuilder();
        result.append(String.format(MESSAGE_EXIST, CHAPTER));
        for (Chapter c : allChapters) {
            result.append("\n").append(allChapters.indexOf(c) + 1).append(".").append(c);
            if (c.getDueBy() == null) {
                result.append(" (Corrupted due date. Please revise soon.)");
            } else {
                result.append(" (due by ").append(c.getDueBy()).append(")");
            }
        }
        return result.toString();
    }
}
