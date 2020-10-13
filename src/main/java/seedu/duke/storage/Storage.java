package seedu.duke.storage;

import seedu.duke.exception.AniException;
import seedu.duke.human.User;
import seedu.duke.ui.Ui;
import seedu.duke.watchlist.Watchlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final Encoder encoder;
    private final Decoder decoder;
    private final String userFilePath;
    private final String watchlistFilePath;
    private final String storageDirectory = "data" + File.separator + "AniChan" + File.separator;

    public Storage(String userFileName, String watchlistFileName) {
        encoder = new Encoder();
        decoder = new Decoder();
        userFilePath = storageDirectory + userFileName;
        watchlistFilePath = storageDirectory + watchlistFileName;
    }

    public User loadUser(Ui ui) {
        String fileString = readFile(ui, userFilePath);
        if (fileString.isBlank()) {
            return null;
        }

        return decoder.decodeUserString(ui, fileString);
    }

    public ArrayList<Watchlist> loadWatchlist(Ui ui) {
        String fileString = readFile(ui, watchlistFilePath);
        if (fileString.isBlank()) {
            return new ArrayList<>();
        }

        return decoder.decodeWatchlistString(ui, fileString);
    }

    public void saveUser(User user) throws AniException {
        try {
            String userString = encoder.encodeUser(user);
            assert userString != null : "Encoded user string should not be null.";
            writeFile(userFilePath, userString);
        } catch (NullPointerException exception) {
            throw new AniException("AniChan could not save this user.");
        }
    }

    public void saveWatchlist(ArrayList<Watchlist> watchlists) throws AniException {
        try {
            String watchlistString = encoder.encodeWatchlist(watchlists);
            assert watchlistString != null : "Encoded watchlist string should not be null.";
            writeFile(watchlistFilePath, watchlistString);
        } catch (NullPointerException exception) {
            throw new AniException("AniChan could not save the watchlist.");
        }
    }

    private String readFile(Ui ui, String filePath) {
        StringBuilder sbFileString = new StringBuilder();
        try {
            File fileToRead = new File(filePath);
            Scanner fileReader = new Scanner(fileToRead);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                sbFileString.append(line);
                sbFileString.append(System.lineSeparator());
            }
        } catch (FileNotFoundException exception) {
            if (filePath.equals(userFilePath)) {
                ui.printMessage("User file is not found, let's start afresh.");
            } else {
                ui.printMessage("Watchlist file is not found, let's start a afresh.");
            }
        }

        return sbFileString.toString();
    }

    private void writeFile(String filePath, String fileString) throws AniException {
        new File(storageDirectory).mkdirs();
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(fileString);
            fileWriter.close();
        } catch (IOException exception) {
            throw new AniException("AniChan could not write to the file.");
        }
    }
}
