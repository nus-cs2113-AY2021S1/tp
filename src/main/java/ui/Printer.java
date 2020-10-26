package ui;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import command.Command;
import parser.CommandFlag;

/**
 * This class manages the output of text.
 */
public final class Printer {
    private static final String LINE = "----------------------------------------------------------------"
            + "----------------------------------------";
    private static final String NEWLINE = System.lineSeparator();

    public Printer() {
    }

    public void print(Object obj) {
        System.out.println(obj);
    }

    public void printWelcomeScreen() {
        print(ConsoleColorsEnum.CYAN_TEXT
                + " ________  ___  ___  _______   ________  _________  ________  "
                + "___  ___  _______   _______  _________" + NEWLINE
                + "|\\   ____\\|\\  \\|\\  \\|\\  ___ \\ |\\   __  \\|\\___   ___\\"
                + "\\   ____\\|\\  \\|\\  \\|\\  ___ \\ |\\  ___ \\|\\___   ___\\" + NEWLINE
                + "\\ \\  \\___|\\ \\  \\\\\\  \\ \\   __/|\\ \\  \\|\\  \\|___ \\  "
                + "\\_\\ \\  \\___|\\ \\  \\\\\\  \\ \\   __/|\\ \\   __/\\|___ \\  \\_|" + NEWLINE
                + " \\ \\  \\    \\ \\   __  \\ \\  \\_|/_\\ \\   __  \\   \\ \\  \\ "
                + "\\ \\_____  \\ \\   __  \\ \\  \\_|/_\\ \\  \\_|/__  \\ \\  \\" + NEWLINE
                + "  \\ \\  \\____\\ \\  \\ \\  \\ \\  \\_|\\ \\ \\  \\ \\  \\   \\ \\  "
                + "\\ \\|____|\\  \\ \\  \\ \\  \\ \\  \\_|\\ \\ \\  \\_|\\ \\  \\ \\  \\" + NEWLINE
                + "   \\ \\_______\\ \\__\\ \\__\\ \\_______\\ \\__\\ \\__\\   \\ \\__"
                + "\\  ____\\_\\  \\ \\__\\ \\__\\ \\_______\\ \\_______\\  \\ \\__\\" + NEWLINE
                + "    \\|_______|\\|__|\\|__|\\|_______|\\|__|\\|__|    \\|__| |\\____"
                + "_____\\|__|\\|__|\\|_______|\\|_______|   \\|__|" + NEWLINE
                + "                                      ___       ________  ________" + NEWLINE
                + "                                     |\\  \\     |\\   __  \\|\\   ____\\" + NEWLINE
                + "                                     \\ \\  \\    \\ \\  \\|\\  \\ \\  \\___|" + NEWLINE
                + "                                      \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\  ___" + NEWLINE
                + "                                       \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\|\\  \\" + NEWLINE
                + "                                        \\ \\_______\\ \\_______\\ \\_______\\" + NEWLINE
                + "                                         \\|_______|\\|_______|\\|_______|" + NEWLINE
                + LINE + NEWLINE
                + "Welcome to CheatLogs, the one stop cheat sheet management systems" + NEWLINE
                + "Your CAP is our upmost priority"
                + ConsoleColorsEnum.RESET_TEXT
        );
    }

    public void printExitLogo() {
        print(ConsoleColorsEnum.CYAN_TEXT
                + " ________  ________  ________  ________  ________      ___    ___ _______" + NEWLINE
                + "|\\   ____\\|\\   __  \\|\\   __  \\|\\   ___ \\|\\   __  "
                + "\\    |\\  \\  /  /|\\  ___ \\" + NEWLINE
                + "\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\_|\\ \\ \\  "
                + "\\|\\ /_   \\ \\  \\/  / | \\   __/|" + NEWLINE
                + " \\ \\  \\  __\\ \\  \\\\\\  \\ \\  \\\\\\  \\ \\  \\ \\\\ \\ "
                + "\\   __  \\   \\ \\    / / \\ \\  \\_|/__" + NEWLINE
                + "  \\ \\  \\|\\  \\ \\  \\\\\\  \\ \\  \\\\\\  \\ \\  \\_\\\\ \\"
                + " \\  \\|\\  \\   \\/  /  /   \\ \\  \\_|\\ \\" + NEWLINE
                + "   \\ \\_______\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\"
                + "__/  / /      \\ \\_______\\" + NEWLINE
                + "    \\|_______|\\|_______|\\|_______|\\|_______|\\|_______|\\___"
                + "/ /        \\|_______|" + NEWLINE
                + "                                                     \\|___|/" + NEWLINE
                + ConsoleColorsEnum.RESET_TEXT
        );
    }

    public void printUserInputPrompt() {
        print(LINE + "" + NEWLINE + NEWLINE + "Please enter a Command:");
    }

    public void printWhiteSpace() {
        print(" ");
    }

    public void printHelpSheet() {
        print(ConsoleColorsEnum.BOLD + "/add /n <CHEAT_SHEET_NAME> /s<PROGRAMMING_LANGUAGE> /d <DESCRIPTION>"
                + ConsoleColorsEnum.RESET_TEXT + NEWLINE
                + "\tAdds a new cheat sheet to the application and prompts user to include data" + NEWLINE
                + "\tinto the cheat sheet." + NEWLINE
                + ConsoleColorsEnum.BOLD + "/delete /i <CHEAT_SHEET_INDEX>"
                + ConsoleColorsEnum.RESET_TEXT + NEWLINE
                + "\tTo be used after using /list, Deletes the cheat sheet at INDEX items down the list." + NEWLINE
                + "\tThe first cheat sheet has an index of 1." + NEWLINE
                + ConsoleColorsEnum.BOLD + "/find /s<PROGRAMMING LANGUAGE> k/ <KEYWORD>"
                + ConsoleColorsEnum.RESET_TEXT + NEWLINE
                + "\tFinds a cheat sheet whose names contain any of the given keywords." + NEWLINE
                + ConsoleColorsEnum.BOLD + "/view /i <CHEAT_SHEET_INDEX>"
                + ConsoleColorsEnum.RESET_TEXT + NEWLINE
                + "\tView the contents of a cheat sheet either by the name or index" + NEWLINE
                + ConsoleColorsEnum.BOLD + "/list"
                + ConsoleColorsEnum.RESET_TEXT + NEWLINE
                + "\tLists all the possible commands that can be executed in the application." + NEWLINE
                + ConsoleColorsEnum.BOLD + "/clear"
                + ConsoleColorsEnum.RESET_TEXT + NEWLINE
                + "\tClears all entries from the cheat sheet." + NEWLINE
                + ConsoleColorsEnum.BOLD + "/exit"
                + ConsoleColorsEnum.RESET_TEXT + NEWLINE
                + "\tExits the application." + NEWLINE);
    }

    public void printCheatSheet(CheatSheet cheatSheet) {
        print("\tName: " + cheatSheet.getName() + NEWLINE
                + "\tSubject: " + cheatSheet.getSubject() + NEWLINE
                + "\tDetails: " + cheatSheet.getDetails());
    }

    public void printCheatSheetList(CheatSheetList cheatSheetList) {
        int i = 0;
        for (CheatSheet cs : cheatSheetList.getList()) {
            print("\t"
                    + (cs.getIsFavourite() ? ConsoleColorsEnum.YELLOW_TEXT : "")
                    + (++i) + ". " + cs.getName()
                    + " (Subject: " + cs.getSubject() + ")"
                    + (cs.getIsFavourite() ? " *" + ConsoleColorsEnum.RESET_TEXT : "") + "\n");
        }
    }

    public void printCheatSheetSize(CheatSheetList cheatSheetList) {
        print("Now you have " + cheatSheetList.getSize() + " cheatsheet(s)");
    }

    public void printAddNewCheatSheetMessage(CheatSheet cheatSheet, CheatSheetList cheatSheetList) {
        print("Added new cheat sheet:");
        printCheatSheet(cheatSheet);
        print(LINE);
        printCheatSheetSize(cheatSheetList);
    }

    public void printClearCheatSheetMessage(int number) {
        print("Cleared total of " + number + " cheat sheets");
        print(LINE);
        print("Now you have no cheatsheets");
    }

    public void printDeleteCheatSheetMessage(CheatSheet cheatSheet, CheatSheetList cheatSheetList) {
        print("This cheat sheet has been deleted: ");
        printCheatSheet(cheatSheet);
        print(LINE);
        printCheatSheetSize(cheatSheetList);
    }

    public void printViewCheatSheetMessage(CheatSheet cheatSheet) {
        print("This is your content for " + cheatSheet.getName() + ": ");
        printCheatSheet(cheatSheet);
    }

    public void printFavouritedCheatSheetMessage(CheatSheet cheatSheet) {
        print("This cheat sheet has been favourited: ");
        printCheatSheet(cheatSheet);
    }

    public void printAlternativeArgumentPrompt(Command command) {
        print(NEWLINE);
        System.out.print(ConsoleColorsEnum.RED_TEXT + "Please enter at least ");
        for (CommandFlag arg :command.getAlternativeArguments()) {
            System.out.print(arg + " ");
        }
        print(ConsoleColorsEnum.RESET_TEXT);
        print(NEWLINE);
    }

    public void printMissingArgument(CommandFlag curArg) {
        System.out.print("Please input " + curArg.name() + ": ");

    }
}
