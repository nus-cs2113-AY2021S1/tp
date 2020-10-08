package seedu.financeit;

import seedu.financeit.manualtracker.ManualTracker;
import seedu.financeit.utils.*;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Financeit {
    public static void main(String[] args) {
        MenuPrint.print();
        Scanner input = new Scanner(System.in);

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
        ManualTracker.main();


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
