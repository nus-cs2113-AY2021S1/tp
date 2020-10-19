package seedu.duke.writing;

import seedu.duke.commands.CommandChecker;
import seedu.duke.database.FileFunctions;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.File;

import static seedu.duke.Duke.user;

//import static seedu.duke.Duke.writings;
import static seedu.duke.commands.CommandChecker.UNRECOGNISED;
import static seedu.duke.commands.CommandChecker.TYPE;
import static seedu.duke.commands.CommandChecker.POEM;
import static seedu.duke.commands.CommandChecker.ESSAY;
import static seedu.duke.commands.CommandChecker.extractCommandType;

import static seedu.duke.constants.Logos.PLAIN_TEXT_DIVIDER;
import static seedu.duke.constants.FilePaths.WRITING_FILE_PATH;
import static seedu.duke.functions.CommandExecutor.executeCommand;
import static seedu.duke.parsers.Parsers.getUserInput;
import static seedu.duke.constants.DataFileConvention.MAX_NUM_WRITINGS;
import static seedu.duke.database.WritingsLoader.recordListToFile;

public class WritingList {
    public static ArrayList<Writings> writinglist = new ArrayList<>();
    //Used to clear all of writings when resetting database
    static int countWritings = 0;

    public WritingList() {
        this.writinglist = new ArrayList<>();
    }

    public void add(Writings w) {
        this.writinglist.add(w);
    }

    public Writings get(int i) {
        return this.writinglist.get(i);
    }

    public void remove(int i) {
        this.writinglist.remove(i);
        //countWritings--;
    }

    public static int getCountWritings() {
        return writinglist.size();
    }

    public static void printWritingSize() {
        System.out.println("In our storage, there is/are currently " + getWritingSize() + " writing(s)");
    }

    /**
     *  print all of the current writings in the Arraylist with details.
     *  Triggered when "stats" command is called.
     */
    public static void printWritings() {
        if (writinglist.size() > 0) {
            for (Writings w : writinglist) {
                System.out.println("This is a " + w.getType());
                System.out.println("Written by " + w.getAuthor().getName() + "\n");
                System.out.println("Id: " + w.getId());
                System.out.println(w.getTitle().toUpperCase() + "\n");
                System.out.println(w.getContent());
                System.out.println("This writing was created on " + w.date);
                System.out.println(PLAIN_TEXT_DIVIDER);
                if (w.getType().equals(POEM)) {
                    System.out.println("This poem has " + w.getNumberOfLines()
                            + " and " + w.getNumberOfWords());
                } else if (w.getType().equals(ESSAY)) {
                    System.out.println("This essay has " + w.getNumberOfSentences()
                            + " and " + w.getNumberOfWords());
                }
            }
        } else {
            System.out.println("The storage is currently empty, please type \"start\" command to add");
        }
    }

    public static void printAskForType() {
        System.out.println("Please let us know your type of writings, either poem or essay");
    }

    public static void printAskForTitle() {
        System.out.println("Please let us know the title of your writing");
    }

    public static int getWritingSize() {
        return writinglist.size();
    }
    
    public static void checkStart(WritingList writings) {
        Scanner scanner = new Scanner(System.in);
        String newUserInput = null;
        try {
            CommandChecker commandStartChecker = UNRECOGNISED;
            while (commandStartChecker != TYPE) {
                System.out.println("Please indicate your type by typing in \"type\" command");
                newUserInput = getUserInput(scanner);
                commandStartChecker = extractCommandType(newUserInput);
            }
            executeCommand(commandStartChecker, newUserInput, writings);
            //checkType();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void checkType(WritingList writings) {
        Scanner scanner = new Scanner(System.in);
        String newUserInput;
        File f = FileFunctions.getFileFromFilePath(WRITING_FILE_PATH);
        try {
            CommandChecker commandStartChecker = UNRECOGNISED;
            while (commandStartChecker != POEM && commandStartChecker != ESSAY) {
                WritingList.printAskForType();
                newUserInput = getUserInput(scanner);
                commandStartChecker = extractCommandType(newUserInput);
            }
            WritingList.printAskForTitle();
            newUserInput = getUserInput(scanner);
            String title = newUserInput;
            System.out.println("Now you can type your content, terminate by typing \"end\"");
            String content = "";
            while (!newUserInput.equals("end")) {
                content = content.concat(newUserInput + "\n");
                newUserInput = getUserInput(scanner);
            }
            Random rand = new Random();
            int newId = rand.nextInt(MAX_NUM_WRITINGS);
            if (commandStartChecker == POEM) {
                addPoem(title, newId, "nothing", content, user.getName());
            } else if (commandStartChecker == ESSAY) {
                addEssay(title, newId, "nothing", content, user.getName());
            }
            System.out.println("Done! We have added your writing to our storage! You can type \"stats\" "
                    + "for future reference!");
            recordListToFile(f, writings);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Clear all data stored in writing.txt.
     * To be associated with command "reset writing".
     */
    public static void clearAll(WritingList writings) {
        writings.writinglist.clear();
        System.out.println("We have clear all data in the writings list");
        //Reset countWritings
        File f = FileFunctions.getFileFromFilePath(WRITING_FILE_PATH);
        recordListToFile(f, writings);
        writings.countWritings = 0;
    }

    public static void addPoem(String title, int id, String topic, String content, String author) {
        Poem toBeAdded = new Poem(title, id, topic, content, author);
        writinglist.add(toBeAdded);
        countWritings++;
        System.out.println("This Poem, " + title +  " has been added");
    }

    public static void addEssay(String title, int id, String topic, String content, String author) {
        Essay toBeAdded = new Essay(title, id, topic, content, author);
        writinglist.add(toBeAdded);
        countWritings++;
        System.out.println("This Essay, " + title +  " has been added");
    }

}
