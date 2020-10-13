package seedu.financeit.utils;

import seedu.financeit.common.CategoryMap;
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
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SSHMT extends SaveStateHandler {

    public SSHMT (){
        super();
    }

    public SSHMT (String filepath, String directory){
        super(filepath, directory);
    }

    public void save() throws IOException {
        buildFile();
        LedgerList ledList = ManualTracker.getLedgerList();
        StringBuilder str = new StringBuilder();
        int size = ledList.getItemsSize();
        for (int i = 0; i < size; i++) {
            Ledger led = (Ledger) ledList.getItemFromIndex(i);
            str.append(led.getDate().format(DateTimeFormatter.ofPattern("uuMMdd")) + System.lineSeparator());
            EntryList entList = led.entryList;
            int entsize = entList.getItemsSize();
            for (int x = 0; x < entsize; x++) {
                Entry ent = (Entry) entList.getItemFromIndex(x);
                str.append(ent.toString() + System.lineSeparator());
            }
        }
        FileWriter fw = new FileWriter(fullpath);
        fw.write(String.valueOf(str));
        fw.close();
    }

    public void load() throws IOException {
        buildFile();
        File f = new File(fullpath);
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
                buffer[1] = CategoryMap.categoryToInputMap.get(buffer[1]);
                if (buffer[3].length() >= 4) {
                    buffer[3] = charRemoveAt(buffer[3], 2);
                }
                EntryTracker.setCurrLedger((Ledger) ManualTracker.getLedgerList().getItemFromIndex(ledgerIndex));
                EntryTracker.setCommandPacket(in.parseInput("entry new /time " + buffer[3] + " /cat "
                        + buffer[1] + " /desc " + buffer[4] + " /amt " + buffer[2] + buffer[0]));
                EntryTracker.createEntry();
            } else {
                ManualTracker.setCommandPacket(in.parseInput("ledger new /date " + str));
                ManualTracker.createLedger();
                ledgerIndex++;
            }
        }
    }

    private String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }

}
