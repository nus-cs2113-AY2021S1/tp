package storage;

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
    public static final String FILE_PATHWAY = "questions.txt";   // file pathway

    public static final String QUESTION_ANSWER_PREFIX = " \\| ";
    public static final String QUESTION_PREFIX = "[Q]";
    public static final String ANSWER_PREFIX = "[A]";

    protected String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    //create the folder --> 'data/admin'
    public void createAdmin() {
        File f = new File(filePath);
        boolean success = f.getParentFile().mkdir();
        System.out.println("    "+ filePath);
        if(success) {
            System.out.println("    Successfully created new directory");
        }
        else {
            System.out.println("    Failed to create new directory");
        }
    }

    public void createModule(String moduleName) {
        File f = new File(filePath + "/" + moduleName);
        boolean success = f.getParentFile().mkdir();
        //File f1 = f.getParentFile();
        //String v = f1.getAbsolutePath();
        //System.out.println("    getParentFile: " + v);
        //System.out.println("    filePath: " + f.getPath());
        if(success) {
            System.out.println("    Successfully created new directory " + moduleName);
        }
        else {
            System.out.println("    Failed to create new directory");
        }
    }

    public void createChapter(String chapterName, String moduleName) {
        File f = new File(filePath + "/" + moduleName + "/" + chapterName + ".txt");
        boolean success = f.getParentFile().mkdir();
        if(success) {
            System.out.println("    Successfully created new directory " + chapterName);
        }
        else {
            System.out.println("    Failed to create new directory");
        }
    }

    public ArrayList<Module> loadModule() throws FileNotFoundException {
        File f = new File(filePath);
        ArrayList<Module> modules = new ArrayList<Module>();
        Scanner s = new Scanner(f);

        String contents[] = f.list();
        System.out.println("List of files and directories in the specified directory:");
        for(int i=0; i<contents.length; i++) {
            System.out.println(contents[i]);
            modules.add(new Module(contents[i]));
        }
        return modules;
    }

    public ArrayList<Chapter> loadChapter(String module) throws FileNotFoundException {
        File f = new File(filePath + "/" + module);
        ArrayList<Chapter> chapters = new ArrayList<Chapter>();
        Scanner s = new Scanner(f);
        String contents[] = f.list();
        System.out.println("List of files and directories in the specified directory:");
        for(int i=0; i<contents.length; i++) {
            String target = contents[i].replace(".txt", "");
            System.out.println(contents[i]);
            chapters.add(new Chapter(target));
        }
        return chapters;
    }

    public ArrayList<Card> loadCard(String module, String chapter) throws FileNotFoundException {
        File f = new File(filePath + "/" + module + "/" + chapter + ".txt");
        ArrayList<Card> cards = new ArrayList<Card>();
        Scanner s = new Scanner(f);
        int totalCards = 0;
        while (s.hasNext()) {
            //to read the card
        }
        return cards;
    }

    public static void getFileContents(CardList cards) {
        try {
            File f = new File(FILE_PATHWAY);    // create a File for the given file path
            Scanner s = new Scanner(f);     // create a Scanner using the File as the source
            while (s.hasNext()) {
                String fileCommand = s.nextLine();
                String[] args = fileCommand.split(QUESTION_ANSWER_PREFIX, 2);
                String question = Parser.parseQuestioninFile(args[0]);
                String answer = Parser.parseAnswerinFile(args[1]);
                Card card = new Card(question, answer);
                cards.addCard(card);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (InvalidFileFormatException e) {
            System.out.println("The format of some commands in the file is invalid");
        }
    }

    public static void writeToFile(CardList cards) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATHWAY);
        for (int i = 0; i < cards.getCardCount(); i++) {
            fw.write(cards.getCard(i).toString() + "\n");
        }
        fw.close();
    }

    public String getFilePath() {
        return filePath;
    }
}
