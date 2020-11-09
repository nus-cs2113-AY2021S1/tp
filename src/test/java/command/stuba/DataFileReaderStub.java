package command.stuba;

import storage.DataFileReader;

public class DataFileReaderStub extends DataFileReader {
    boolean isDataRead;

    public DataFileReaderStub() {
        super(null,null, null);
        isDataRead = false;
    }

    public void reset() {
        isDataRead = false;
    }

    @Override
    public void executeFunction() {
        isDataRead = true;
    }
}
