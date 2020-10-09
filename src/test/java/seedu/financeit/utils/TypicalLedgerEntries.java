package seedu.financeit.utils;

import seedu.financeit.manualtracker.Ledger;

import java.util.ArrayList;
import java.util.HashMap;

public class TypicalLedgerEntries {
    public static ArrayList<String> commandInputs = new ArrayList<>();
    Ledger ledger = null;

    TypicalLedgerEntries() {
    }

    TypicalLedgerEntries(String... commandInputs) {
        for (String commandInput : commandInputs) {
            this.commandInputs.add(commandInput);
        }
    }

    TypicalLedgerEntries(HashMap<String, String> ledgerParams, HashMap<String, String>[] entryParams) {
        this();

    }
}
