package seedu.financeit.utils;

import seedu.financeit.manualtracker.Ledger;
import seedu.financeit.manualtracker.LedgerList;
import seedu.financeit.manualtracker.ManualTracker;
import seedu.financeit.manualtracker.subroutine.Entry;
import seedu.financeit.manualtracker.subroutine.EntryList;
import seedu.financeit.manualtracker.subroutine.EntryTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SaveManager {
    public static void save() throws IOException {
        if(!Files.exists(Paths.get("./data"))){
            Files.createDirectory(Paths.get("./data"));
        }
        if(!Files.exists(Paths.get("./data/save.txt"))){
            Files.createFile(Paths.get("./data/save.txt"));
        }
        LedgerList LedList = ManualTracker.getLedgerList();
        EntryList EntList = EntryTracker.getEntryList();
        StringBuilder str = new StringBuilder();
        int size = LedList.getLedgersSize();

        for (int i = 0; i < size; i++){
            Ledger led = LedList.getLedgerFromIndex(i);
            str.append(led.toString() + System.lineSeparator());
        }

        size = EntList.getEntriesSize();
        for (int i = 0; i < size; i++){
            Entry ent = EntList.getEntryFromIndex(i);
            str.append(ent.toString() + System.lineSeparator());
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

    }
}
