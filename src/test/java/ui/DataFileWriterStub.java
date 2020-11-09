package ui;

import storage.DataFileWriter;

public class DataFileWriterStub extends DataFileWriter {
    boolean isDataWritten = false;

    public DataFileWriterStub() {
        super(null, null, null);
    }

    public void reset() {
        isDataWritten = false;
    }

    public void executeFunction() {
        isDataWritten = true;
    }
}
