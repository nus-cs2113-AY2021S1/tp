package ui;

import storage.DataFileReader;

public class DataFileReaderStub extends DataFileReader {
    boolean isPreloadedExtracted = false;
    boolean isDataRead = false;

    public DataFileReaderStub() {
        super(null, null, null);
    }

    public void reset() {
        isPreloadedExtracted = false;
        isDataRead = false;
    }

    public void extractPreloadedCheatSheets() {
        isPreloadedExtracted = true;
    }

    public void executeFunction() {
        isDataRead = true;
    }
}
