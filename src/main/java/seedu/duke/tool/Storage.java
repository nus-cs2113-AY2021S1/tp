package seedu.duke.tool;

import seedu.duke.level.Card;
import seedu.duke.level.Chapter;
import seedu.duke.level.Module;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
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
        int totalModule = 0;

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
        int totalChapter = 0;
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


/*

    public void writeFile(ArrayList<Task> taskList, int taskAmount) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write("");
        fw.close();

        for(int i=0; i<taskAmount; i++) {
            taskList.get(i).writeFile();
        }
    }*/

    public String getFilePath() {
        return filePath;
    }
}
