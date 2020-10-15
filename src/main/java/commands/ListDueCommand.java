package commands;

import access.Access;

import manager.chapter.CardList;
import manager.chapter.DueChapter;

import scheduler.Scheduler;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ListDueCommand extends Command {
    public static final String COMMAND_WORD = "due";
    public ArrayList<DueChapter> allChapters;
    public ArrayList<DueChapter> dueChapters = new ArrayList<>();

    private void loadAllChapters(Storage storage, Ui ui) {
        try {
            allChapters = storage.loadAllDueChapters();
        } catch (FileNotFoundException e) {
            ui.showToUser("Sorry, you do not have any flashcards in the database yet. Please try this command again"
                    + "once you have added some flashcards!");
        }
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        loadAllChapters(storage, ui);

        for (DueChapter chapter : allChapters) {
            LocalDate deadline = chapter.getChapter().getDueBy();
            if (Scheduler.isDeadlineDue(deadline)) {
                dueChapters.add(chapter);
            }
        }
        if (dueChapters.size() == 0) {
            ui.showToUser("You have no tasks due today! Please check back again tomorrow!");
        } else {
            System.out.print("The chapter");
            if (dueChapters.size() > 1) {
                System.out.print("s");
            }
            System.out.print(" you have due by today ");
            if (dueChapters.size() > 1) {
                ui.showToUser("are:");
            } else {
                ui.showToUser("is:");
            }
            for (DueChapter dueChapter : dueChapters) {
                ui.showToUser(dueChapter.toString());
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

