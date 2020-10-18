package storage;

import access.Access;
import exception.InvalidFileFormatException;
import manager.card.Card;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import manager.chapter.DueChapter;
import parser.Parser;
import manager.module.Module;
import scheduler.Scheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static final String DELIMITER = " \\| ";
    public static final String QUESTION_PREFIX = "[Q]";
    public static final String ANSWER_PREFIX = "[A]";
    public static final String PREVIOUS_INTERVAL_PREFIX = "[P]";

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    //create the folder --> 'data/admin'
    public void createAdmin() {
        File f = new File(filePath);
        System.out.println("Filepath: " + filePath);

        boolean dataDirExists = f.getParentFile().exists();
        boolean dataDirCreated = false;
        if (!dataDirExists) {
            dataDirCreated = f.getParentFile().mkdir();
        } else {
            System.out.println("Directory " + f.getParentFile().getName() + " already exists");
        }
        if (dataDirCreated) {
            System.out.println("Successfully created new directory " + f.getParentFile().getName());
        }

        boolean adminDirExists = f.exists();
        boolean adminDirCreated = false;
        if (!adminDirExists) {
            adminDirCreated = f.mkdir();
        } else {
            System.out.println("Directory " + f + " already exists");
        }
        if (adminDirCreated) {
            System.out.println("Successfully created new directory " + f);
        }
    }

    public void createModule(String moduleName) {
        File f = new File(filePath + "/" + moduleName);
        boolean moduleDirExists = f.exists();
        boolean moduleDirCreated = false;
        if (!moduleDirExists) {
            moduleDirCreated = f.mkdir();
        } else {
            System.out.println("Directory " + f + " already exists");
        }
        if (moduleDirCreated) {
            System.out.println("Successfully created new directory " + f);
        }
    }

    public void createChapter(String chapterName, String moduleName) {
        try {
            File f = new File(filePath + "/" + moduleName + "/" + chapterName + ".txt");
            boolean chapterFileExists = f.exists();
            boolean chapterFileCreated = false;
            if (!chapterFileExists) {
                chapterFileCreated = f.createNewFile();
            } else {
                System.out.println("File " + f + " already exists");
            }
            if (chapterFileCreated) {
                System.out.println("Successfully created new file " + chapterName + ".txt");
            }
        } catch (IOException e) {
            System.out.println("Error creating the file.");
        }
    }

    public ArrayList<Module> loadModule() throws FileNotFoundException {
        File f = new File(filePath);
        boolean dirExists = f.exists();
        if (!dirExists) {
            throw new FileNotFoundException();
        }

        ArrayList<Module> modules = new ArrayList<>();
        String[] contents = f.list();
        System.out.println("List of files and directories in the specified directory:");
        for (String content : contents) {
            System.out.println(content);
            modules.add(new Module(content));
        }
        return modules;
    }

    public ArrayList<Chapter> loadChapter(String module) throws FileNotFoundException {
        File f = new File(filePath + "/" + module);
        boolean dirExists = f.exists();
        if (!dirExists) {
            throw new FileNotFoundException();
        }

        ArrayList<Chapter> chapters = new ArrayList<>();
        String[] contents = f.list();
        if (contents.length == 0) {
            return chapters;
        }
        System.out.println("List of files and directories in the specified directory:");
        for (String content : contents) {
            String target = content.replace(".txt", "");
            System.out.println(content);
            chapters.add(new Chapter(target));
        }
        return chapters;
    }

    private String retrieveChapterDeadline(String moduleName, String chapterName) {
        File f = new File(filePath + "/" + moduleName + "/" + "dues" + "/" + chapterName + "due" + ".txt");
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

    private boolean missingDueFileCheck(String moduleName, String chapterName) {
        File f = new File(filePath + "/" + moduleName + "/" + chapterName + ".txt");
        return f.exists();
    }

    public ArrayList<DueChapter> loadAllDueChapters() throws FileNotFoundException {
        //Loading in Modules
        File f = new File(filePath);
        boolean moduleExists = f.exists();
        if (!moduleExists) {
            throw new FileNotFoundException();
        }

        String[] modules = f.list();
        ArrayList<DueChapter> dueChapters = new ArrayList<>();
        for (String module : modules) {

            //Loading in Chapters for each module
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
                String target = chapter.replace(".txt", "");
                String deadline = retrieveChapterDeadline(module, target);
                assert !deadline.equals(null) : "Invalid deadline retrieved";
                if (deadline.equals("Invalid Date")) {
                    System.out.println("\nThe chapter:");
                    System.out.println("Module: " + module + "; " + "Chapter: " + target);
                    System.out.println("has a corrupted deadline. Please revise it ASAP! It will be considered due!\n");
                    dueChapters.add(new DueChapter(module, new Chapter(target, Scheduler.parseDate(deadline))));
                } else if (deadline.equals("Does not exist")) {
                    if (missingDueFileCheck(module, target)) {
                        deadline = "Invalid Date";
                        System.out.println("\nThe chapter:");
                        System.out.println("Module: " + module + "; " + "Chapter: " + target);
                        System.out.print("has a corrupted deadline. Please revise it ASAP!");
                        System.out.println(" It will be considered due!\n");
                        dueChapters.add(new DueChapter(module, new Chapter(target, Scheduler.parseDate(deadline))));
                    }
                } else {
                    dueChapters.add(new DueChapter(module, new Chapter(target, Scheduler.parseDate(deadline))));
                }
            }
        }
        return dueChapters;
    }

    public ArrayList<Card> loadCard(String module, String chapter) throws FileNotFoundException {
        File f = new File(filePath + "/" + module + "/" + chapter + ".txt");
        boolean fileExists = f.exists();
        if (!fileExists) {
            throw new FileNotFoundException();
        }

        ArrayList<Card> cards = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            //to read the card
            String fileCommand = s.nextLine();
            String[] args = fileCommand.split(DELIMITER, 3);
            try {
                String question = Parser.parseQuestionInFile(args[0]);
                String answer = Parser.parseAnswerInFile(args[1]);
                String interval = Parser.parsePreIntervalInFile(args[2]);
                int preInterval = Integer.parseInt(interval);

                Card card = new Card(question, answer, preInterval);
                cards.add(card);
            } catch (InvalidFileFormatException e) {
                return null;
            }
        }
        s.close();
        return cards;
    }

    public void saveCards(CardList cards, String module, String chapter) throws IOException {
        FileWriter fw = new FileWriter(getFilePath() + "/" + module + "/" + chapter + ".txt");
        for (int i = 0; i < cards.getCardCount(); i++) {
            fw.write(cards.getCard(i).toString()
                    + " | [P] " + cards.getCard(i).getPreviousInterval() + "\n");
        }
        fw.close();
    }

    private boolean createChapterDue(String duePath, String dirPath) throws IOException {
        File due = new File(duePath);
        File dir = new File(dirPath);
        boolean isDirValid = dir.exists() && dir.isDirectory();
        if (!isDirValid) {
            return dir.mkdir() && due.createNewFile();
        } else if (!due.exists()) {
            return due.createNewFile();
        } else {
            return true;
        }
    }

    private void writeDeadlineToChapterDue(String dueBy, String chapterPath) throws IOException {
        FileWriter fw = new FileWriter(chapterPath);
        fw.write(dueBy);
        fw.close();
    }

    public void saveChapterDeadline(String dueBy, String moduleName, String chapterName) {
        try {
            String dirPath = filePath + "/" + moduleName + "/" + "dues";
            String duePath = filePath + "/" + moduleName + "/" + "dues" + "/" + chapterName + "due" + ".txt";
            if (createChapterDue(duePath, dirPath)) {
                writeDeadlineToChapterDue(dueBy, duePath);
            } else {
                System.out.println("Unable to produce ChapterDue");
            }
        } catch (IOException e) {
            System.out.println("Error in saving chapter deadline");
        }
    }

    public boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    public boolean renameChapter(String newChapterName, Access access, Chapter chapter) {
        File file = new File(getFilePath()
                + "/" + access.getModule()
                + "/" + chapter.toString() + ".txt");
        boolean success = file.renameTo(new File(getFilePath()
                + "/" + access.getModule()
                + "/" + newChapterName + ".txt"));
        return success;
    }

    public boolean renameModule(String newModuleName, Module module) {
        File file = new File(getFilePath() + "/" + module.toString());
        boolean success = file.renameTo(new File(getFilePath() + "/" + newModuleName));
        return success;
    }
}
