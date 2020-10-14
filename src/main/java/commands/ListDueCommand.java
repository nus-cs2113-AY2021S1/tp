package commands;

import access.Access;
import manager.chapter.Chapter;
import manager.module.Module;
import scheduler.Scheduler;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ListDueCommand extends Command {
    public static final String COMMAND_WORD = "listDue";
    ArrayList<Chapter> dueChapters = new ArrayList<>();

    public ArrayList<Module> modules;
    public ArrayList<Chapter> allChapters = new ArrayList<>();

    private void loadAllChapters(Storage storage, Ui ui) {
        try {
            modules = storage.loadModule();
            for (Module module : modules) {
                ArrayList<Chapter> loadedChapters = storage.loadChapter(module.toString());
                allChapters.addAll(loadedChapters);
            }
        } catch (FileNotFoundException e) {
            ui.showToUser("Sorry, you do not have any flashcards in the database yet. Please try this command again"
                    + "once you have added some flashcards!");
        }
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) {
        loadAllChapters(storage, ui);

        for (Chapter chapter : allChapters) {
            if (Scheduler.isDeadlineDue(chapter.getDueBy())) {
                dueChapters.add(chapter);
            }
        }
        ui.showToUser("The chapters you have due by today are:");
        for (Chapter dueChapter : dueChapters) {
            ui.showToUser(dueChapter.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

