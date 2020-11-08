package seedu.duke.writing;

import seedu.duke.commands.CommandChecker;
import seedu.duke.database.FileFunctions;
import seedu.duke.exceptions.FileEmptyException;
import seedu.duke.exceptions.ItemNotFoundedException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import static seedu.duke.constants.FluffleMessages.ASKING_FOR_REMINDER;
import static seedu.duke.constants.FluffleMessages.ASKING_FOR_TITLE;
import static seedu.duke.constants.FluffleMessages.ASKING_FOR_TOPIC;
import static seedu.duke.constants.FluffleMessages.ASKING_FOR_TYPE;
import static seedu.duke.constants.FluffleMessages.ASSERTION_ID_ERROR;
import static seedu.duke.constants.FluffleMessages.INSTRUCTION_FOR_ADDING_NEW_WRITINGS;
import static seedu.duke.constants.FluffleMessages.CLEAR_DATA_MESSAGE;
import static seedu.duke.constants.FluffleMessages.EMPTY_WRITING_MESSAGE;
import static seedu.duke.constants.FluffleMessages.SUCCESSFUL_ADD_WRITING_TO_DATABASE;
import static seedu.duke.constants.FluffleMessages.TYPE_COMMAND_INSTRUCTION;
import static seedu.duke.functions.CommandExecutor.executeCommand;
import static seedu.duke.parsers.Parsers.getUserInput;
import static seedu.duke.constants.DataFileConvention.MAX_NUM_WRITINGS;


public class WritingList {

    public static ArrayList<Writings> writingList = new ArrayList<>();
    //Used to clear all of writings when resetting database
    static int countWritings = 0;
    private static String topic;
    private static String content;
    private static String title;
    private static String type;

    public WritingList() {
        writingList = new ArrayList<>();
    }

    public int getSize() {
        return writingList.size();
    }

    public void add(Writings w) {
        writingList.add(w);
        countWritings++;
    }

    public Writings get(int i) {
        return writingList.get(i);
    }

    public static void removeWriting(int i) throws FileEmptyException {
        if (writingList.size() == 0) {
            throw new FileEmptyException();
        } else {
            assert (i <= writingList.size() && i >= 0) : "Your item is out of bound";
            writingList.remove(i);
            countWritings--;
        }
    }

    public static void removeID(int id) throws FileEmptyException, ItemNotFoundedException {
        int idExists = 0;
        if (writingList.size() == 0) {
            throw new FileEmptyException();
        } else {
            for (int i = 0; i < writingList.size(); i++) {
                //Use "while" loop to clean out the same IDs
                while (i < writingList.size() && writingList.get(i).getId() == id) {
                    System.out.println("This writing: " + writingList.get(i).getTitle() + " has been deleted");
                    writingList.remove(i);
                    idExists = 1;
                    System.out.println("You have " + writingList.size() + " items remain");
                    countWritings--;
                }
            }
            if (idExists == 0) {
                throw new ItemNotFoundedException();
            } else {
                return;
            }
        }
    }

    /** Get the number of writings available in the storage. */
    public static int getCountWritings() {
        return writingList.size();
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
        if (writingList.size() > 0) {
            for (Writings w : writingList) {
                w.printWritingsProperties();
                if (w.getType().equals("Poem")) {
                    w.printPoemProperties();
                } else if (w.getType().equals("Essay")) {
                    w.printEssayProperties();
                }
            }
        } else {
            System.out.println(EMPTY_WRITING_MESSAGE);
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

    public static void printAskForReminderDate() {
        System.out.println(ASKING_FOR_REMINDER);
    }

    public static int getWritingSize() {
        return writingList.size();
    }

    /**
     * Operate when command "start" is called, embark the process the writing process.
     *
     */
    public static void checkStart() {
        Scanner scanner = new Scanner(System.in);
        String newUserInput = null;
        try {
            CommandChecker commandStartChecker = UNRECOGNISED;
            while (commandStartChecker != TYPE) {
                System.out.println(TYPE_COMMAND_INSTRUCTION);
                newUserInput = getUserInput(scanner);
                commandStartChecker = extractCommandType(newUserInput);
            }
            executeCommand(commandStartChecker, newUserInput);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Operate when command "type" is called, allow the user to choose the desire topic.
     *
     */
    public static void checkType() {
        Scanner scanner = new Scanner(System.in);
        String newUserInput;
        File f = FileFunctions.getFileFromFilePath(WRITING_FILE_PATH);
        try {
            CommandChecker commandTypeChecker = UNRECOGNISED;
            while (commandTypeChecker != POEM && commandTypeChecker != ESSAY) {
                WritingList.printAskForType();
                newUserInput = getUserInput(scanner);
                commandTypeChecker = extractCommandType(newUserInput);
            }
            getInformation(commandTypeChecker);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void getInformation(CommandChecker commandTypeChecker) {
        Scanner scanner = new Scanner(System.in);
        String newUserInput;
        WritingList.printAskForTopic();
        newUserInput = getUserInput(scanner);
        String topic = newUserInput;
        WritingList.printAskForTitle();
        newUserInput = getUserInput(scanner);
        String title = newUserInput;
        System.out.println(INSTRUCTION_FOR_ADDING_NEW_WRITINGS);
        String content = "";
        String contentUserInput = getUserInput(scanner);
        while (!contentUserInput.equals("end")) {
            content = content.concat(contentUserInput + "\n");
            contentUserInput = getUserInput(scanner);
        }
        WritingList.printAskForReminderDate();
        newUserInput = getUserInput(scanner);
        LocalDate reminderDate = LocalDate.parse(newUserInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Random rand = new Random();
        int newId = rand.nextInt(MAX_NUM_WRITINGS);
        if (commandTypeChecker == POEM) {
            addPoem(title, newId, topic, content, user.getName(), reminderDate);
        } else if (commandTypeChecker == ESSAY) {
            addEssay(title, newId, topic, content, user.getName(), reminderDate);
        }
        System.out.println(SUCCESSFUL_ADD_WRITING_TO_DATABASE);
    }

    /**
     * Clear all data stored in writing.txt.
     * To be associated with command "reset writing".
     */
    public static void clearAll() {
        writingList.clear();
        System.out.println(CLEAR_DATA_MESSAGE);
        //Reset countWritings
        //File f = FileFunctions.getFileFromFilePath(WRITING_FILE_PATH);
        //recordListToFile(f, writings);
        countWritings = 0;
    }

    /** Adding a poem to the database. */
    public static void addPoem(String title, int id, String topic, String content, String author, LocalDate reminder) {
        assert (id <= MAX_NUM_WRITINGS && id >= 0) : ASSERTION_ID_ERROR;
        Poem toBeAdded = new Poem(title, id, topic, content, author, reminder);
        writingList.add(toBeAdded);
        System.out.println("This Poem, " + title +  " has been added");
        countWritings++;
    }

    /** Adding an essay to the database. */
    public static void addEssay(String title, int id, String topic, String content, String author, LocalDate reminder) {
        assert (id <= MAX_NUM_WRITINGS && id >= 0) : ASSERTION_ID_ERROR;
        Essay toBeAdded = new Essay(title, id, topic, content, author, reminder);
        writingList.add(toBeAdded);
        System.out.println("This Essay, " + title +  " has been added");
        countWritings++;
    }
}
