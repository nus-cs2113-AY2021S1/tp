package storage;

import access.Access;
import exception.InvalidFileFormatException;
import manager.card.Card;
import manager.chapter.CardList;
import manager.chapter.Chapter;
import parser.Parser;
import manager.module.Module;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static final String QUESTION_ANSWER_PREFIX = " \\| ";
    public static final String QUESTION_PREFIX = "[Q]";
    public static final String ANSWER_PREFIX = "[A]";

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
            System.out.println("    Directory " + f + " already exists");
        }
        if (moduleDirCreated) {
            System.out.println("    Successfully created new directory " + f);
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
                System.out.println("    File " + f + " already exists");
            }
            if (chapterFileCreated) {
                System.out.println("    Successfully created new file " + chapterName + ".txt");
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
            String[] args = fileCommand.split(QUESTION_ANSWER_PREFIX, 2);
            try {
                String question = Parser.parseQuestionInFile(args[0]);
                String answer = Parser.parseAnswerInFile(args[1]);
                Card card = new Card(question, answer);
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
            fw.write(cards.getCard(i).toString() + "\n");
        }
        fw.close();
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
