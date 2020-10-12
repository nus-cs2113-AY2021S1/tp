package ui;

import access.Access;
import commands.RemoveCommand;
import commands.add.AddChapterCommand;
import commands.add.AddCommand;
import commands.ExitCommand;
import commands.add.AddModuleCommand;
import commands.back.BackChapterCommand;
import commands.go.GoChapterCommand;
import commands.go.GoModuleCommand;
import commands.list.ListChapterCommand;
import commands.list.ListCommand;
import commands.HelpCommand;
import commands.ReviseCommand;

import commands.list.ListModuleCommand;
import manager.card.Card;
import manager.chapter.Chapter;
import manager.module.Module;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static commands.ReviseCommand.MESSAGE_SHOW_ANSWER_PROMPT;
import static commands.ReviseCommand.MESSAGE_SHOW_RATING_PROMPT;

public class Ui {
    private final Scanner in;
    private final PrintStream out;
    private static final String lineSplit = "    ____________________________________________________________";

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readCommand() {
        String userCommand = in.nextLine();
        while (userCommand.trim().isEmpty()) {
            userCommand = in.nextLine();
        }
        return userCommand;
    }

    public void showWelcome() {
        out.println("Welcome to Kaji!\n");
    }

    public void showLevel(Access access) {
        out.println(access.getLevel());
    }

    public void printEmptyLine() {
        out.println();
    }

    public void showCardAdded(Card card, int cardCount) {
        out.println("Got it. I've added this card:");
        out.println(card);
        if (cardCount == 1) {
            out.println("Now you have " + cardCount + " card in the list.");
            return;
        }
        out.println("Now you have " + cardCount + " cards in the list.");
    }

    public void showCardList(ArrayList<Card> cards, int cardCount) {
        if (cardCount == 0) {
            out.println("There are no cards in your list.");
            return;
        }
        out.println("Here are the cards in your list:");
        for (Card c : cards) {
            out.println((cards.indexOf(c) + 1) + "." + c);
        }
    }

    public void showToUser(String message) {
        out.println(message);
    }

    public void showCardRevision(Card c) {
        out.println(c.getQuestion() + MESSAGE_SHOW_ANSWER_PROMPT);
        getAnswerInput(c);
    }

    public void getAnswerInput(Card c) {
        String input = in.nextLine();
        while (!input.equalsIgnoreCase("s")) {
            out.println("You have entered an invalid input, please try again.");
            input = in.nextLine();
        }
        out.println(c.getAnswer());
    }

    public String getRating() {
        out.println(MESSAGE_SHOW_RATING_PROMPT);
        return in.nextLine();
    }

    public void showExit() {
        out.println("Exiting the program...");
    }

    public void showHelpList() {
        out.println("Here is a list of commands available:" + "\n");
        out.println("1.  " + ListCommand.MESSAGE_USAGE);
        out.println("2.  " + ReviseCommand.MESSAGE_USAGE);
        out.println("3.  " + HelpCommand.MESSAGE_USAGE);
        out.println("4.  " + AddCommand.MESSAGE_USAGE);
        out.println("5.  " + ExitCommand.MESSAGE_USAGE);
        out.println("6.  " + RemoveCommand.MESSAGE_USAGE);
        out.println("7.  " + GoChapterCommand.MESSAGE_USAGE);
        out.println("8.  " + GoModuleCommand.MESSAGE_USAGE);
        out.println("9.  " + BackChapterCommand.MESSAGE_USAGE);
        out.println("10. " + GoModuleCommand.MESSAGE_USAGE);
        out.println("11. " + ListChapterCommand.MESSAGE_USAGE);
        out.println("12. " + ListModuleCommand.MESSAGE_USAGE);
        out.println("13. " + AddChapterCommand.MESSAGE_USAGE);
        out.println("14. " + AddModuleCommand.MESSAGE_USAGE);

    }

    public void showModuleAdded(Module module, int moduleCount) {
        out.println("    Got it. I've added this module:");
        out.println("    " + module);
        out.println("    Now you have " + moduleCount + " modules in the list.");
    }

    public void showChapterAdded(Chapter chapter, int chapterCount) {
        out.println("    Got it. I've added this chapter:");
        out.println("    " + chapter);
        out.println("    Now you have " + chapterCount + " chapters in the list.");
    }

    public void showChapterList(ArrayList<Chapter> chapters, int chapterCount) {
        if (chapterCount == 0) {
            out.println("There are no chapters in your list.");
            return;
        }
        out.println("Here are the chapters in your list:");
        for (Chapter c : chapters) {
            out.println((chapters.indexOf(c) + 1) + "." + c);
        }
    }

    public void showModuleList(ArrayList<Module> modules, int moduleCount) {
        if (moduleCount == 0) {
            out.println("There are no modules in your list.");
            return;
        }
        out.println("Here are the modules in your list:");
        for (Module m : modules) {
            out.println((modules.indexOf(m) + 1) + "." + m);
        }
    }
}
