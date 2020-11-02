package storage;

import exception.ExclusionFileException;
import manager.chapter.Chapter;
import manager.chapter.DueChapter;
import scheduler.Scheduler;
import ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//@@author Darticune
public class StorageLoad {

    public static final String MESSAGE_MODULE_CHAPTER = "Module: %1$s ; Chapter: %2$s";

    protected static void checkExists(File f) throws FileNotFoundException {
        boolean dirExists = f.exists();
        if (!dirExists) {
            throw new FileNotFoundException();
        }
    }

    protected static String[] loadChaptersFromSpecifiedModule(String moduleName, String filePath)
            throws FileNotFoundException {
        File file = new File(filePath + "/" + moduleName);
        boolean moduleExists = file.exists();
        if (!moduleExists) {
            throw new FileNotFoundException();
        }
        return file.list();
    }

    protected static String retrieveChapterDeadline(String moduleName, String chapterName, String filePath) {
        File f = new File(filePath + "/" + moduleName + "/dues/" + chapterName + "due" + ".txt");
        try {
            Scanner s = new Scanner(f);
            if (s.hasNext()) {
                String deadline = s.nextLine();
                s.close();
                return deadline;
            } else {
                s.close();
                return "Invalid Date";
            }
        } catch (FileNotFoundException e) {
            return "Does not exist";
        }
    }

    protected static ArrayList<String> loadExclusionFile(String filePath) throws ExclusionFileException {
        File exclusionFile = new File(filePath + "/exclusions.txt");
        ArrayList<String> excludedChapters = new ArrayList<>();
        try {
            if (!exclusionFile.exists()) {
                if (!exclusionFile.createNewFile()) {
                    throw new ExclusionFileException();
                } else {
                    return new ArrayList<String>();
                }
            }
            Scanner exclusionFileScanner = new Scanner(exclusionFile);
            while (exclusionFileScanner.hasNext()) {
                //to read the card
                String entry = exclusionFileScanner.nextLine();
                excludedChapters.add(entry);
            }
            exclusionFileScanner.close();
            return excludedChapters;
        } catch (IOException e) {
            throw new ExclusionFileException();
        }
    }

    protected static ArrayList<DueChapter> checkAllChaptersForDue(Ui ui, ArrayList<String> excludedChapters,
                                                                  ArrayList<DueChapter> dueChapters,
                                                                  String[] modules, String filePath)
                                                                  throws FileNotFoundException {
        for (String module : modules) {
            if (module.equals("exclusions.txt")) {
                continue;
            }
            File f2 = new File(filePath + "/" + module);
            boolean chapterExists = f2.exists();
            if (!chapterExists) {
                throw new FileNotFoundException();
            }
            String[] chapters = f2.list();
            if (chapters.length == 0) {
                return dueChapters;
            }
            for (String chapter : chapters) {
                processChapterForDue(ui, excludedChapters, dueChapters, module, chapter, filePath);
            }
        }
        return dueChapters;
    }

    private static void processChapterForDue(Ui ui, ArrayList<String> excludedChapters,
                                             ArrayList<DueChapter> dueChapters, String module, String chapter,
                                             String filePath) {
        if (chapter.equals("dues")) {
            return;
        }
        String target = chapter.replace(".txt", "");
        String entry = "Module: " + module + "; Chapter: " + target;
        if (excludedChapters.contains(entry)) {
            return;
        }
        checkChapterDeadline(ui, dueChapters, module, target, filePath);
    }

    private static void checkChapterDeadline(Ui ui, ArrayList<DueChapter> dueChapters, String module,
                                             String target, String filePath) {
        String deadline = retrieveChapterDeadline(module, target, filePath);
        assert !deadline.equals(null) : "Invalid deadline retrieved";
        if (deadline.equals("Invalid Date")) {
            ui.showToUser("\nThe chapter:");
            ui.showToUser(String.format(MESSAGE_MODULE_CHAPTER, module, target)
                    + " has a corrupted deadline. Please revise it ASAP! It will be considered due!\n");
            dueChapters.add(new DueChapter(module, new Chapter(target, Scheduler.parseDate(deadline))));
        } else if (deadline.equals("Does not exist")) {
            deadline = "Invalid Date";
            ui.showToUser("\nThe chapter:");
            ui.showToUser(String.format(MESSAGE_MODULE_CHAPTER, module, target)
                    + " has a corrupted deadline. Please revise it ASAP! It will be considered due!\n");
            dueChapters.add(new DueChapter(module, new Chapter(target, Scheduler.parseDate(deadline))));
        } else {
            dueChapters.add(new DueChapter(module, new Chapter(target, Scheduler.parseDate(deadline))));
        }
    }
}
