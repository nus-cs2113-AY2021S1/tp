package seedu.financeit;

import seedu.financeit.manualtracker.LedgerList;
import seedu.financeit.manualtracker.ManualTracker;
import seedu.financeit.manualtracker.subroutine.EntryList;
import seedu.financeit.utils.*;

import java.util.Scanner;

import seedu.financeit.parser.InputParser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Financeit {
    public static void main(String[] args) {
        while (true) {

            MenuPrint.print();
            try {
                SaveManager.load();
            } catch (Exception m) {
                MenuPrint.prompt = "An exception has occurred: " + m;
            }

            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            switch (input) {
                case "manual":
                    ManualTracker.main();
                    break;
                case "auto": //AutoTracker.main();
                    break;
                case "acc": //AccSummary.main();
                    break;
                case "goal": //GoalTracker.main();
                    break;
                case "financial": //FinancialCalculator.main();
                    break;
                case "exit":
                    try {
                        SaveManager.save();
                    } catch (Exception m) {
                        System.out.println("An exception has occurred: " + m);
                    }
                    return;
                default:
                    MenuPrint.prompt = "Invalid Command";
            }
        }
/*
        InputParser inputParser = new InputParser();
        String inputString = "mom /m mom -d dad /s son /d daughter";
        //String inputString = "mom";
        CommandPacket packet = inputParser.parseInput(inputString);
        packet.getParamTypes();

        Printer.setTitle("Test table");
        Printer.addRow("Name;Age;Place;letters;60");
        Printer.addRow("John;15;Woodlands;g;5");
        Printer.addRow("Dog;16;Woodlands;g;6");
        Printer.printList();

        Printer.setTitle("Test table 2");
        Printer.addRow("Name;Age;Place;letters");
        Printer.addRow("Mary;16;Woodlands;g");
        Printer.addRow("Cat;16;Woodlands;gffffffffffffffffffffffffffffffffffffffffff");
        Printer.printList();

        String[][] input = {
                {"Name", "Age", "Place", "letters"},
                {"John", "15", "Woodlands", "g"},
                {"Mary", "18", "Johore", "l"}
        };
        String[][] input1 = {
                {"Name"},
                {"John"},
                {"Mary"}
        };

        Printer.printList();
        UiManager.refreshPage();

        //ManualTracker.main();
        print();
        //adjustToColWidth("asdsadsadadasdasd", 4);
        adjustToColWidth("asdsadsadadasdasd", 4);
*/
    }


    public static ArrayList<String> adjustToColWidth(String input, int length) {
        ArrayList<String> output = new ArrayList<>();
        Pattern pattern = Pattern.compile(String.format(".{%s}|.{1,}$", length));
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            System.out.println(matcher.group());
            output.add(matcher.group());
        }
        return output;

    }

}

// This prints a table in case we need it
