package command;

import cheatsheet.CheatSheetList;
import sort.SortBySubject;
import sort.SortBySubjectRev;
import sort.SortByName;
import sort.SortByNameRev;
import ui.ConsoleColorsEnum;
import ui.Printer;

import java.util.Scanner;

public class ListCommand extends Command {
    public static final String invoker = "/list";

    public ListCommand(Printer printer, CheatSheetList cheatSheetList) {
        super(printer);
        this.cheatSheetList = cheatSheetList;
    }

    @Override
    public boolean hasAlternativeArgument() {
        return true;
    }

    @Override
    public void execute() {
        cheatSheetList.getList().sort(new SortByName());
        printer.printCheatSheetList(cheatSheetList);
        askForSortingConfigAndPrint();
    }

    private void askForSortingConfigAndPrint() {
        final String promptSortConfig = ConsoleColorsEnum.RED_TEXT
                + "Sort filter (na: name ascending, sa: subject ascending, nd: name descending" + ", sd: "
                + "subject descending or <<enter>> to skip)" + ConsoleColorsEnum.RESET_TEXT;

        printer.print(promptSortConfig);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.isEmpty()) {
            switch (input) {
            case "na":
                cheatSheetList.getList().sort(new SortByName());
                printer.print("Sorted name in ascending order");
                break;
            case "sa":
                cheatSheetList.getList().sort(new SortBySubject());
                printer.print("Sorted language in ascending order");
                break;
            case "nd":
                cheatSheetList.getList().sort(new SortByNameRev());
                printer.print("Sorted name in descending order");
                break;
            case "sd":
                cheatSheetList.getList().sort(new SortBySubjectRev());
                printer.print("Sorted language in descending order");
                break;
            default:
                cheatSheetList.getList().sort(new SortByName());
            }
            printer.printCheatSheetList(cheatSheetList);
            printer.print(promptSortConfig);
            input = scanner.nextLine();
        }
    }
}

