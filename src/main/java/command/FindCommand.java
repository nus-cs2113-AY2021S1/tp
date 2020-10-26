package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import exception.CommandException;
import parser.CommandFlag;
import sort.SortBySubject;
import sort.SortBySubjectRev;
import sort.SortByName;
import sort.SortByNameRev;
import ui.ConsoleColorsEnum;
import ui.Printer;

import java.util.ArrayList;
import java.util.Scanner;

public class FindCommand extends FinderCommand {
    public static final String invoker = "/find";

    public FindCommand(Printer printer, CheatSheetList cheatSheetList) {
        super(printer, cheatSheetList);

        flagsToDescriptions.put(CommandFlag.SUBJECT, null);
        flagsToDescriptions.put(CommandFlag.SECTIONKEYWORD, null);
        alternativeArguments.add(CommandFlag.SUBJECT);
        alternativeArguments.add(CommandFlag.SECTIONKEYWORD);
    }

    @Override
    public void execute() throws CommandException {
        String subject = "";
        String keyword = "";
        ArrayList<CheatSheet> filteredCheatSheetList = new ArrayList<>();

        if (flagsToDescriptions.containsKey(CommandFlag.SUBJECT)) {
            subject = flagsToDescriptions.get(CommandFlag.SUBJECT);
        }
        if (flagsToDescriptions.containsKey(CommandFlag.SECTIONKEYWORD)) {
            keyword = flagsToDescriptions.get(CommandFlag.SECTIONKEYWORD);
        }

        for (CheatSheet cs : cheatSheetList.getList()) {
            if (!subject.isEmpty() && keyword.isEmpty()) {
                if (cs.getSubject().contains(subject)) {
                    filteredCheatSheetList.add(cs);
                }
            } else if (!keyword.isEmpty() && subject.isEmpty()) {
                if (cs.getDetails().contains(keyword)) {
                    filteredCheatSheetList.add(cs);
                }
            } else if (!subject.isEmpty() && !keyword.isEmpty()) {
                if (cs.getSubject().contains(subject)
                        && cs.getDetails().contains(keyword)) {
                    filteredCheatSheetList.add(cs);
                }
            } else {
                throw new CommandException("Please enter at least an argument");
            }
        }

        if (filteredCheatSheetList.isEmpty()) {
            throw new CommandException("No matching content found");
        }

        printMatches(filteredCheatSheetList);
        askForSortingConfigAndPrint(filteredCheatSheetList);
    }

    protected void askForSortingConfigAndPrint(ArrayList<CheatSheet> filteredCheatSheetList) {
        final String promptSortConfig = ConsoleColorsEnum.RED_TEXT
                + "Sort filter (na: name ascending, sa: subject ascending, nd: name descending" + ", sd: "
                + "subject descending or <<enter>> to skip)" + ConsoleColorsEnum.RESET_TEXT;

        printer.print(promptSortConfig);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.isEmpty()) {
            switch (input) {
            case "na":
                filteredCheatSheetList.sort(new SortByName());
                printer.print("Sorted name in ascending order");
                break;
            case "sa":
                filteredCheatSheetList.sort(new SortBySubject());
                printer.print("Sorted subject in ascending order");
                break;
            case "nd":
                filteredCheatSheetList.sort(new SortByNameRev());
                printer.print("Sorted name in descending order");
                break;
            case "sd":
                filteredCheatSheetList.sort(new SortBySubjectRev());
                printer.print("Sorted subject in descending order");
                break;
            default:
                filteredCheatSheetList.sort(new SortByName());
            }
            printer.printCheatSheetList(cheatSheetList);
            printer.print(promptSortConfig);
            input = scanner.nextLine();
        }
    }

    private void printMatches(ArrayList<CheatSheet> cheatSheetArrayList) {
        printer.print("Showing all matches: ");
        for (CheatSheet cs : cheatSheetArrayList) {
            printer.printCheatSheet(cs);
            printer.printWhiteSpace();
        }
    }
}
