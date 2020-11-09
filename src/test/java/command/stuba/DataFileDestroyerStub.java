package command.stuba;

import cheatsheet.CheatSheetList;
import exception.CommandException;
import storage.DataFileDestroyer;

public class DataFileDestroyerStub extends DataFileDestroyer {
    boolean isDataDestroyed;

    public DataFileDestroyerStub(CheatSheetList list) {
        super(null, list);
        isDataDestroyed = false;
    }

    public void reset() {
        isDataDestroyed = false;
    }

    @Override
    public void executeFunction(String name) {
        isDataDestroyed = true;
    }

    @Override
    public void executeFunction() {
        isDataDestroyed = true;
    }
}
