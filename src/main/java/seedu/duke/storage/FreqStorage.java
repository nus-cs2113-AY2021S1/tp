package seedu.duke.storage;

import seedu.duke.model.bus.BusData;
import seedu.duke.model.bus.BusStops;
import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class FreqStorage extends Storage {
    private File file;
    private static final Logger LOGGER = Logger.getLogger(FreqStorage.class.getName());
    private boolean isCorrupted = false;
    private static final String FILE_READ = "FreqList.txt Read with no issues";
    private static final int ALL_STOPS = 32;

    public FreqStorage(String dir) {
        super(dir);
        file = getFile();
    }

    /**
     * Reads stored TXT file by calling on private loadFile function.
     */
    @Override
    public void readFile() throws CustomException {
        LOGGER.fine("Attempting to read file: " + dir);
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            throw new CustomException(ExceptionType.FREQ_READ_FILE_FAIL);
        }
    }

    /**
     * Updates stored TXT file by calling on private saveFile function.
     *
     * @throws CustomException If file can't be updated
     */
    @Override
    public void updateFile() throws CustomException {
        ArrayList<Integer> frequencyList = BusData.getAllSearchCount();
        try {
            saveFile(frequencyList);
        } catch (IOException e) {
            throw new CustomException(ExceptionType.UPDATE_FILE_FAIL);
        }
    }

    /**
     * Saves files in a line of integers for the bus stops.
     *
     * @param frequencyList ArrayList of all the search frequencies
     * @throws IOException catch error occurred during an input-output operation
     */
    private void saveFile(ArrayList<Integer> frequencyList) throws IOException {
        File savedFile = new File(dir);
        FileWriter writer = new FileWriter(savedFile);
        for (int i = 0; i < frequencyList.size(); i++) {
            String currFreq = Integer.toString(frequencyList.get(i));
            writer.write(currFreq + System.lineSeparator());
        }
        writer.close();
    }

    /**
     * Loads file from FreqList.txt file and update respective search counts
     *
     * @throws FileNotFoundException If file can't be located
     * @throws CustomException       If file was tampered with eg, removal of entries/ injection of senseless data
     */
    private void loadFile() throws FileNotFoundException, CustomException {
        File savedFile = new File(dir);
        Scanner fileScanner = new Scanner(savedFile);
        int index = 0;

        if (savedFile.length() == 0) {
            initialiseFile();
        }

        while (fileScanner.hasNext() && !isCorrupted) {
            int currInt = 0;
            try {
                currInt = Integer.parseInt(fileScanner.nextLine());
            } catch (NumberFormatException e) {
                isCorrupted = true;
                initialiseFile();
                throw new CustomException(ExceptionType.FREQ_READ_FILE_FAIL);
            }
            BusStops.values()[index].setCount(currInt);
            index++;
        }
        handlesCorruptedFile(index);
    }

    /**
     * Handles corrupted file eg missing entries/ senseless data.
     *
     * @throws CustomException If file can't be updated
     */
    private void handlesCorruptedFile(int index) throws CustomException {
        if (index < ALL_STOPS - 1) {
            isCorrupted = true;
            initialiseFile();
            throw new CustomException(ExceptionType.FREQ_READ_FILE_FAIL);
        }
        if (isCorrupted) {
            initialiseFile();
        } else {
            LOGGER.fine("FreqList.txt file read successfully.");
            System.out.println(FILE_READ);
        }
    }

    /**
     * Initialise the file to all zeroes.
     *
     * @throws CustomException If file can't be updated
     */
    public void initialiseFile() throws CustomException {
        BusStops.resetSearchFrequency();
        updateFile();
        isCorrupted = false;
    }
}