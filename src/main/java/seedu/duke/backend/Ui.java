package seedu.duke.backend;


import seedu.duke.Command;
import seedu.duke.DukeArgumentException;
import seedu.duke.DukeFinanceAddDescriptionLostException;
import seedu.duke.DukeNoMatchException;
import seedu.duke.DukeNotNumberException;
import seedu.duke.event.CommandAddEventAttendance;
import seedu.duke.event.CommandDelEventAttendance;
import seedu.duke.event.CommandEventAdd;
import seedu.duke.event.CommandEventCountdown;
import seedu.duke.event.CommandEventDel;
import seedu.duke.event.CommandEventList;
import seedu.duke.event.CommandEventStatus;
import seedu.duke.event.CommandSearchEvent;
import seedu.duke.event.CommandViewEventAttendance;
import seedu.duke.finance.CommandFinanceAdd;
import seedu.duke.finance.CommandFinanceChange;
import seedu.duke.finance.CommandFinanceDel;
import seedu.duke.finance.CommandFinanceSummary;
import seedu.duke.hr.CommandAddMember;
import seedu.duke.hr.CommandChangeMemberInfo;
import seedu.duke.hr.CommandDelMember;
import seedu.duke.hr.CommandListConnection;
import seedu.duke.hr.CommandListProfAdmin;
import seedu.duke.hr.CommandSearchMember;
import seedu.duke.hr.CommandViewMember;
import seedu.duke.others.CommandBye;
import seedu.duke.others.CommandHelp;
import seedu.duke.others.CommandImportCsv;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private Parser parser;
    private static ArrayList<Command> commandList = new ArrayList<>();
    private static boolean shutdown = false;
    private static final String UNDERSCORES = "_".repeat(100);

    public Ui() {
        sc = new Scanner(System.in);
        parser = new Parser();
        initializeCommands();
    }

    public void run() {
        String inStr = sc.nextLine();
        UserInput userInput = parser.parse(inStr);
        /*
        System.out.println("Category: "+userInput.getCategory());
        System.out.println("Command: "+userInput.getCommand());
        System.out.println("Num Args: "+userInput.getNumArgs());
        System.out.println("Args: "+userInput.getArgs());
         */

        try {
            Command cmd = findCommand(userInput);
            printOutput(cmd.execute());
        } catch (DukeArgumentException ae) {
            // Placeholder if additional routine is required when user enters incorrect parameters
        } catch (DukeNoMatchException ne) {
            printOutput("No such command. Try 'help' for a list of commands.");
        } catch (DukeFinanceAddDescriptionLostException e) {
            printOutput("Please enter the complete command. Format is:"
                    + "finance addLog ITEM_NAME ITEM_VALUE");
        } catch (DukeNotNumberException e) {
            printOutput("The format requires a number in the end.");
        } catch (Exception e) {
            printOutput("Command execution failed with an unhandled error!", true);
        }
    }

    public Command findCommand(UserInput ui) throws DukeArgumentException, DukeNoMatchException {
        for (Command c : commandList) {
            int result = c.validate(ui);
            if (result == Command.ARGUMENT_ERR) {
                printError(c.help());
                throw new DukeArgumentException();
            } else if (result == Command.ACCEPT) {
                return c;
            }
        }
        throw new DukeNoMatchException();
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
        if (text == null) {
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
                        Thread.sleep(1);
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
        commandList.add(new CommandFinanceSummary());
        commandList.add(new CommandFinanceAdd());
        commandList.add(new CommandFinanceDel());
        commandList.add(new CommandEventAdd());
        commandList.add(new CommandEventDel());
        commandList.add(new CommandEventList());
        commandList.add(new CommandAddMember());
        commandList.add(new CommandViewMember());
        commandList.add(new CommandDelMember());
        commandList.add(new CommandSearchMember());
        commandList.add(new CommandListProfAdmin());
        commandList.add(new CommandListConnection());
        commandList.add(new CommandChangeMemberInfo());
        commandList.add(new CommandEventStatus());
        commandList.add(new CommandEventCountdown());
        commandList.add(new CommandSearchEvent());
        commandList.add(new CommandImportCsv());
        commandList.add(new CommandFinanceChange());
        commandList.add(new CommandAddEventAttendance());
        commandList.add(new CommandViewEventAttendance());
        commandList.add(new CommandDelEventAttendance());
    }
}

