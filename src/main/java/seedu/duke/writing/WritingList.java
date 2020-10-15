package seedu.duke.writing;

import seedu.duke.commands.CommandChecker;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.Duke.user;
import static seedu.duke.Duke.writings;
import static seedu.duke.commands.CommandChecker.*;
import static seedu.duke.constants.Logos.PLAIN_TEXT_DIVIDER;
import static seedu.duke.functions.CommandExecutor.executeCommand;
import static seedu.duke.parsers.Parsers.getUserInput;

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
            System.out.println("This writing is created on " + writing.get(i).date);
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
        Scanner SCANNER = new Scanner(System.in);
        String newnewUserInput = null;
        try {
            CommandChecker commandStartChecker = UNRECOGNISED;
            while (commandStartChecker != TYPE) {
                System.out.println("Please indicate your type by typing in \"type\" command");
                newnewUserInput = getUserInput(SCANNER);
                commandStartChecker = extractCommandType(newnewUserInput);
            }
            executeCommand(commandStartChecker, newnewUserInput);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void checkType() {
        Scanner SCANNER = new Scanner(System.in);
        String newUserInput = null;
        try {
            String type = "";
            while (! (newUserInput.equals("poem") || newUserInput.equals("essay"))) {
                WritingList.printAskForType();
                newUserInput = getUserInput(SCANNER);
                type = newUserInput;
            }
            WritingList.printAskForTitle();
            newUserInput = getUserInput(SCANNER);
            String title = newUserInput;
            System.out.println("Now you can type your content, terminate by typing \"end\"");
            String content = "";
            while (!newUserInput.equals("end")) {
                content = content.concat(newUserInput + "\n");
                newUserInput = getUserInput(SCANNER);
            }
            if (type.equals("poem")) {
                writings.add(new Poem(title, 0, "nothing", content, user.getName()));
            } else if (type.equals("essay")) {
                writings.add(new Essay(title, 0, "nothing", content, user.getName()));
            }
            System.out.println("Done! We have added your writing to our storage! You can type \"stats\" "
                    + "for future reference!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
