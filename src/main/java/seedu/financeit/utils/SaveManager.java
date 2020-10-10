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
import java.util.ArrayList;
import java.util.Scanner;

public class SaveManager {
    public static void save() throws IOException {
        if(!Files.exists(Paths.get("./data"))){
            Files.createDirectory(Paths.get("./data"));
        }
        if(!Files.exists(Paths.get("./data/save.txt"))){
            Files.createFile(Paths.get("./data/save.txt"));
        }
        LedgerList LedList = ManualTracker.getLedgerList();
        StringBuilder str = new StringBuilder();
        int size = LedList.getItemsSize();

        for (int i = 0; i < size; i++){
            Ledger led = (Ledger) LedList.getItemFromIndex(i);
            str.append(led.toString() + System.lineSeparator());
            EntryList EntList = led.entryList;
            int entsize = EntList.getItemsSize();
            for (int x = 0; x < entsize; x++){
                Entry ent = (Entry) EntList.getItemFromIndex(x);
                str.append(ent.toString() + System.lineSeparator());
            }
        }
        FileWriter fw = new FileWriter("./data/save.txt");
        fw.write(String.valueOf(str));
        fw.close();
    }
    public static void load() throws IOException{
        if(!Files.exists(Paths.get("./data"))){
            Files.createDirectory(Paths.get("./data"));
        }
        if(!Files.exists(Paths.get("./data/save.txt"))){
            Files.createFile(Paths.get("./data/save.txt"));
        }
        File f = new File("./data/save.txt");
        Scanner s = new Scanner(f);
        InputParser in = new InputParser();
        String buffer[];
        int ledger_index = -1;
        while (s.hasNext()) {
            String str = s.nextLine();

            if(str.contains(";")){
                buffer = str.split(";", 5);
                if (buffer[0].contentEquals("Expense")){
                    buffer[0] = " -e";
                } else if (buffer[0].contentEquals("Income")) {
                    buffer[0] = " -i";
                }
                switch (buffer[1]){
                    case "TRANSPORT": buffer[1] = "tpt";
                        break;
                    case "FOOD": buffer[1] = "fd";
                        break;
                    case "TRAVEL": buffer[1] = "tvl";
                        break;
                    case "SHOPPING": buffer[1] = "shp";
                        break;
                    case "BILLS": buffer[1] = "bll";
                        break;
                    case "SALARY": buffer[1] = "slr";
                        break;
                    case "ALLOWANCE": buffer[1] = "alw";
                        break;
                    default:
                }
                if (buffer[3].length() >= 4) {
                    buffer[3] = charRemoveAt(buffer[3], 2);
                }
                EntryTracker.setCurrLedger((Ledger)ManualTracker.getLedgerList().getLedgerByIndex(ledger_index));
                EntryTracker.packet = in.parseInput("entry new /time " + buffer[3] + " /cat " + buffer[1] + " /desc " + buffer[4] + " /amt " + buffer[2] + buffer[0]);
                EntryTracker.handleCreateEntry();


            } else {
                buffer = str.split(" ", 3);
                switch(buffer[0]){
                    case "Jan": buffer[0] = "01";
                        break;
                    case "Feb": buffer[0] = "02";
                        break;
                    case "Mar": buffer[0] = "03";
                        break;
                    case "Apr": buffer[0] = "04";
                        break;
                    case "May": buffer[0] = "05";
                        break;
                    case "Jun": buffer[0] = "06";
                        break;
                    case "Jul": buffer[0] = "07";
                        break;
                    case "Aug": buffer[0] = "08";
                        break;
                    case "Sep": buffer[0] = "09";
                        break;
                    case "Oct": buffer[0] = "10";
                        break;
                    case "Nov": buffer[0] = "11";
                        break;
                    case "Dec": buffer[0] = "12";
                        break;
                    default:
                }
                buffer[2] = buffer[2].substring(2);
                ManualTracker.packet = in.parseInput("ledger new /date " + buffer[2] + buffer[0] + buffer[1]);
                ManualTracker.handleCreateLedger();
                ledger_index++;
            }

        }

    }
    public static String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }
}
