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

import static seedu.duke.constants.FilePaths.WRITING_FILE_PATH;
import static seedu.duke.functions.CommandExecutor.executeCommand;
import static seedu.duke.parsers.Parsers.getUserInput;
import static seedu.duke.constants.DataFileConvention.MAX_NUM_WRITINGS;
import static seedu.duke.database.WritingsLoader.recordListToFile;
import static seedu.duke.constants.FluffleMessages.ASSERTION_ID_ERROR;
import static seedu.duke.constants.FluffleMessages.SUCCESSFUL_ADD_WRITING_TO_DATABASE;
import static seedu.duke.constants.FluffleMessages.INSTRUCTION_FOR_ADDING_NEW_WRITINGS;
import static seedu.duke.constants.FluffleMessages.TYPE_COMMAND_INSTRUCTION;
import static seedu.duke.constants.FluffleMessages.ASKING_FOR_TYPE;
import static seedu.duke.constants.FluffleMessages.ASKING_FOR_TITLE;
import static seedu.duke.constants.FluffleMessages.EMPTY_WRITING_MESSAGE;
import static seedu.duke.constants.FluffleMessages.CLEAR_DATA_MESSAGE;
import static seedu.duke.constants.FluffleMessages.ASKING_FOR_TOPIC;


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
        assert (i <= writinglist.size() && i >= 0) : "Your item is out of bound";
        this.writinglist.remove(i);
    }

    public void removeID(int i) {
        int idExists = 0;
        for (Writings w: writinglist) {
            if (w.getId() == i) {
                writinglist.remove(w);
                idExists = 1;
            }
        }
        assert (idExists == 1) : "This ID does not exists";
    }

    /** Get the number of writings available in the storage. */
    public static int getCountWritings() {
        return writinglist.size();
    }

    /** Print the UI message along with number of writings. */
    public static void printWritingSize() {
        System.out.println("In our storage, there is/are currently " + getWritingSize() + " writing(s)");
    }

    /**
     *  print all of the current writings in the Arraylist with details.
     *  Triggered when "stats" command is called.
     */
    public static void printWritings() {
        assert (writinglist.size() > 0) : EMPTY_WRITING_MESSAGE;
        for (Writings w : writinglist) {
            w.printWritingsProperties();
            if (w.getType().equals(POEM)) {
                w.printPoemProperties();
            } else if (w.getType().equals(ESSAY)) {
                w.printEssayProperties();
            }
        }
    }

    public static void printAskForType() {
        System.out.println(ASKING_FOR_TYPE);
    }

    public static void printAskForTitle() {
        System.out.println(ASKING_FOR_TITLE);
    }

    public static void printAskForTopic() {
        System.out.println(ASKING_FOR_TOPIC);
    }

    public static int getWritingSize() {
        return writinglist.size();
    }

    /**
     * Operate when command "start" is called, embark the process the writing process.
     *
     * @param writings the list of writings to be modified (in this case: added new item)
     */
    public static void checkStart(WritingList writings) {
        Scanner scanner = new Scanner(System.in);
        String newUserInput = null;
        try {
            CommandChecker commandStartChecker = UNRECOGNISED;
            while (commandStartChecker != TYPE) {
                System.out.println(TYPE_COMMAND_INSTRUCTION);
                newUserInput = getUserInput(scanner);
                commandStartChecker = extractCommandType(newUserInput);
            }
            executeCommand(commandStartChecker, newUserInput, writings);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Operate when command "type" is called, allow the user to choose the desire topic.
     *
     * @param writings the list of writings to be modified (in this case: added new item)
     */
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
            WritingList.printAskForTopic();
            newUserInput = getUserInput(scanner);
            String topic = newUserInput;
            WritingList.printAskForTitle();
            newUserInput = getUserInput(scanner);
            String title = newUserInput;
            System.out.println(INSTRUCTION_FOR_ADDING_NEW_WRITINGS);
            String content = "";
            while (!newUserInput.equals("end")) {
                newUserInput = getUserInput(scanner);
                content = content.concat(newUserInput + "\n");
            }
            Random rand = new Random();
            int newId = rand.nextInt(MAX_NUM_WRITINGS);
            if (commandStartChecker == POEM) {
                addPoem(title, newId, topic, content, user.getName());
            } else if (commandStartChecker == ESSAY) {
                addEssay(title, newId, topic, content, user.getName());
            }
            System.out.println(SUCCESSFUL_ADD_WRITING_TO_DATABASE);
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
        System.out.println(CLEAR_DATA_MESSAGE);
        //Reset countWritings
        File f = FileFunctions.getFileFromFilePath(WRITING_FILE_PATH);
        recordListToFile(f, writings);
        writings.countWritings = 0;
    }

    /** Adding a poem to the database. */
    public static void addPoem(String title, int id, String topic, String content, String author) {
        assert (id <= MAX_NUM_WRITINGS && id >= 0) : ASSERTION_ID_ERROR;
        Poem toBeAdded = new Poem(title, id, topic, content, author);
        writinglist.add(toBeAdded);
        countWritings++;
        System.out.println("This Poem, " + title +  " has been added");
    }

    /** Adding an essay to the database. */
    public static void addEssay(String title, int id, String topic, String content, String author) {
        assert (id <= MAX_NUM_WRITINGS && id >= 0) : ASSERTION_ID_ERROR;
        Essay toBeAdded = new Essay(title, id, topic, content, author);
        writinglist.add(toBeAdded);
        countWritings++;
        System.out.println("This Essay, " + title +  " has been added");
    }
}
