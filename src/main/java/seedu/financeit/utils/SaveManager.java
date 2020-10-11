package seedu.financeit.utils;

import seedu.financeit.manualtracker.Ledger;
import seedu.financeit.manualtracker.LedgerList;
import seedu.financeit.manualtracker.ManualTracker;
import seedu.financeit.manualtracker.subroutine.Entry;
import seedu.financeit.manualtracker.subroutine.EntryList;
import seedu.financeit.manualtracker.subroutine.EntryTracker;
import seedu.financeit.parser.InputParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class SaveManager {
    public static void save() throws IOException {
        buildFile();
        LedgerList ledList = ManualTracker.getLedgerList();
        StringBuilder str = new StringBuilder();
        int size = ledList.getItemsSize();
        for (int i = 0; i < size; i++) {
            Ledger led = (Ledger) ledList.getItemFromIndex(i);
            str.append(led.toString() + System.lineSeparator());
            EntryList entList = led.entryList;
            int entsize = entList.getItemsSize();
            for (int x = 0; x < entsize; x++) {
                Entry ent = (Entry) entList.getItemFromIndex(x);
                str.append(ent.toString() + System.lineSeparator());
            }
        }
        FileWriter fw = new FileWriter("./data/save.txt");
        fw.write(String.valueOf(str));
        fw.close();
    }

    public static void load() throws IOException {
        buildFile();
        File f = new File("./data/save.txt");
        Scanner s = new Scanner(f);
        InputParser in = new InputParser();
        String[] buffer;
        int ledgerIndex = -1;
        while (s.hasNext()) {
            String str = s.nextLine();
            if (str.contains(";")) {
                buffer = str.split(";", 5);
                if (buffer[0].contentEquals("Expense")) {
                    buffer[0] = " -e";
                } else if (buffer[0].contentEquals("Income")) {
                    buffer[0] = " -i";
                }
                buffer[1] = dictionary(buffer[1]);
                if (buffer[3].length() >= 4) {
                    buffer[3] = charRemoveAt(buffer[3], 2);
                }
                EntryTracker.setCurrLedger((Ledger) ManualTracker.getLedgerList().getItemFromIndex(ledgerIndex));
                EntryTracker.setCommandPacket(in.parseInput("entry new /time " + buffer[3] + " /cat "
                    + buffer[1] + " /desc " + buffer[4] + " /amt " + buffer[2] + buffer[0]));
                EntryTracker.createEntry();
            } else {
                buffer = str.split(" ", 3);
                buffer[0] = dictionary(buffer[0]);
                buffer[2] = buffer[2].substring(2);
                ManualTracker.setCommandPacket(in.parseInput("ledger new /date " + buffer[2] + buffer[0] + buffer[1]));
                ManualTracker.createLedger();
                ledgerIndex++;
            }
        }
    }

    private static String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }

    private static void buildFile() throws IOException {
        if (!Files.exists(Paths.get("./data"))) {
            Files.createDirectory(Paths.get("./data"));
        }
        if (!Files.exists(Paths.get("./data/save.txt"))) {
            Files.createFile(Paths.get("./data/save.txt"));
        }
    }

    private static String dictionary(String input) {
        switch (input) {
        case "Jan":
            return "01";
        case "Feb":
            return "02";
        case "Mar":
            return "03";
        case "Apr":
            return "04";
        case "May":
            return "05";
        case "Jun":
            return "06";
        case "Jul":
            return "07";
        case "Aug":
            return "08";
        case "Sep":
            return "09";
        case "Oct":
            return "10";
        case "Nov":
            return "11";
        case "Dec":
            return "12";
        case "TRANSPORT":
            return "tpt";
        case "FOOD":
            return "fd";
        case "TRAVEL":
            return "tvl";
        case "SHOPPING":
            return "shp";
        case "BILLS":
            return "bll";
        case "SALARY":
            return "slr";
        case "ALLOWANCE":
            return "alw";
        default:
            return "";
        }
    }
}
