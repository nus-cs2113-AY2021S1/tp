package command.stuba;

import storage.DataFileDestroyer;

public class DataFileDestroyerStub extends DataFileDestroyer {
    boolean isDataDestroyed;

    public DataFileDestroyerStub() {
       super(null, null);
       isDataDestroyed = false;
    }

    public void reset() {
        isDataDestroyed = false;
    }

    @Override
    public void executeFunction() {
        isDataDestroyed = true;
    }
}
