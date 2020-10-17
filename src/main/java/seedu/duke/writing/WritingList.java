package seedu.duke.writing;

import seedu.duke.commands.CommandChecker;
import seedu.duke.database.FileFunctions;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.File;

import static seedu.duke.Duke.user;
import static seedu.duke.Duke.writings;

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
    private int countWriting;
    public static ArrayList<Writings> writing;

    public WritingList() {
        countWriting = 0;
        this.writing = new ArrayList<>();
    }

    public void add(Writings w) {
        countWriting++;
        writing.add(w);
    }

    public Writings get(int i) {
        return writing.get(i);
    }

    public void remove(int i) {
        writing.remove(i);
    }

    public int getCountWritings() {
        return this.countWriting;
    }

    /**
     *  print all of the current writings in the Arraylist with details.
     */
    public static void printWritings() {
        for (int i = 0; i < writing.size(); i++) {
            System.out.println("This is a " + writing.get(i).getType());
            System.out.println("Written by " + writing.get(i).getAuthor().getName() + "\n");
            System.out.println(writing.get(i).title.toUpperCase() + "\n");
            System.out.println(writing.get(i).content);
            System.out.println("This writing was created on " + writing.get(i).date);
            System.out.println(PLAIN_TEXT_DIVIDER);
        }
    }

    public static void printAskForType() {
        System.out.println("Please let us know your type of writings, either poem or essay");
    }

    public static void printAskForTitle() {
        System.out.println("Please let us know the title of your writing");
    }

    public static int getWritingSize() {
        return writing.size();
    }
    
    public static void checkStart() {
        Scanner scanner = new Scanner(System.in);
        String newUserInput = null;
        try {
            CommandChecker commandStartChecker = UNRECOGNISED;
            while (commandStartChecker != TYPE) {
                System.out.println("Please indicate your type by typing in \"type\" command");
                newUserInput = getUserInput(scanner);
                commandStartChecker = extractCommandType(newUserInput);
            }
            executeCommand(commandStartChecker, newUserInput);
            //checkType();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void checkType() {
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
                writings.add(new Poem(title, newId, "nothing", content, user.getName()));
            } else if (commandStartChecker == ESSAY) {
                writings.add(new Essay(title, newId, "nothing", content, user.getName()));
            }
            System.out.println("Done! We have added your writing to our storage! You can type \"stats\" "
                    + "for future reference!");
            recordListToFile(f, writings);

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
