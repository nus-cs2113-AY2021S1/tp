package seedu.duke.backend;

import seedu.duke.Command;
import seedu.duke.event.CommandEventAdd;
import seedu.duke.event.CommandEventDel;
import seedu.duke.event.CommandEventList;
import seedu.duke.others.CommandBye;
import seedu.duke.others.CommandHelp;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private Parser parser;
    private static ArrayList<Command> commandList = new ArrayList<>();
    private static boolean shutdown = false;
    private static final String UNDERSCORES = "____________________________________________________________";

    public Ui() {
        sc = new Scanner(System.in);
        parser = new Parser();
        initializeCommands();
    }

    public void run() {
        String inStr = sc.nextLine();
        UserInput userInput = parser.parse(inStr);
        /*System.out.println("Category: "+userInput.getCategory());
        System.out.println("Command: "+userInput.getCommand());
        System.out.println("Num Args: "+userInput.getNumArgs());
        System.out.println("Args: "+userInput.getArgs());*/
        Command cmd = findCommand(userInput);
        if (cmd != null) {
            printOutput(cmd.execute());
        } else {
            printOutput("No such command. Try 'help' for a list of commands.");
        }
    }

    public Command findCommand(UserInput ui) {
        for (Command c : commandList) {
            int result = c.validate(ui);
            if (result == Command.ARGUMENT_ERR) {
                printError(c.help());
                return null;
            } else if (result == Command.ACCEPT) {
                return c;
            }
        }
        return null;
    }

    /**
     * Returns if the loop should exit.
     * @return true if the program should terminate
     */
    public static boolean shouldShutdown() {
        return shutdown;
    }

    public static void setShutdown(boolean shut) {
        shutdown = shut;
    }

    /**
     * Utility function for printing errors triggered by other classes.
     * @param text The string to be printed
     */
    public void printError(String text) {
        printOutput(text, true);
    }

    private static void printOutput(String text) {
        printOutput(text, false);
    }

    /**
     * Prints the output with the divider lines and the supplied text.
     * Option to make the text non instant for extra effect
     * @param text string to be printed
     * @param isInstant whether the string is printed instantly
     */
    private static void printOutput(String text, boolean isInstant) {
        if(text == null) {
            return;
        }
        System.out.println(UNDERSCORES);
        // Split text according to the lines to format.
        String[] lines = text.split("\\r?\\n");
        for (String s : lines) {
            if (isInstant) {
                System.out.println("  " + s);
            } else {
                System.out.print("  ");
                // charAt is constant time lookup so we do that instead of splitting strings.
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    System.out.print(c);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(System.lineSeparator());
            }
        }
        System.out.println(UNDERSCORES);
    }

    /**
     * Creates a list of commands for the program to check through.
     */
    private static void initializeCommands() {
        commandList.add(new CommandHelp());
        commandList.add(new CommandBye());
        commandList.add(new CommandEventAdd());
        commandList.add(new CommandEventDel());
        commandList.add(new CommandEventList());
    }
}

