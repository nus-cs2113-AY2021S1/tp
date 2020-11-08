package seedu.duke.storage;

import seedu.duke.model.favorite.Fav;
import seedu.duke.model.favorite.FavList;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Stores FavList in txt file.
 */
public class FavStorage extends Storage {
    private static final String FILE_READ = "FavList.txt Read with no issues";
    private File file;
    private boolean isCorrupted = false;
    private static final List<String> VALID_COMMANDS = Arrays.asList("/help", "/route", "/routemap", "/bus",
            "allbus", "liststops", "/faculty", "/dine", "/dineinfo", "/reset");

    public FavStorage(String dir) {
        super(dir);
        file = getFile();
    }

    /**
     * Reads file and prints corrupted file message if file corrupted.
     *
     * @throws FileNotFoundException if file not found
     */
    @Override
    public void readFile() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String entry = s.nextLine();
            readLine(entry);
        }
        if (isCorrupted) {
            Ui.printCorruptedDataRead();
        }
        System.out.println(FILE_READ);
    }

    /**
     * Reads line in txt file and adds required Fav objects to FavList.
     *
     * @param entry if number of delimiter not 1 or command does not contain "/"
     */
    private void readLine(String entry) {
        String[] entryWords = entry.split("\\|",2);
        if (entryWords.length != 2 || entryWords[1].isBlank()) {
            isCorrupted = true;
            return;
        }
        if (!isValidCommand(entryWords[0])) {
            isCorrupted = true;
            return;
        }
        FavList.addFav(new Fav(entryWords[0], entryWords[1]));

    }

    /**
     * Checks if command type can be added to favlist.
     *
     * @param command String of command portion of line from favlist.txt
     * @return boolean which indicates if command type is exists in VALID_COMMANDS
     */
    private Boolean isValidCommand(String command) {
        String [] entryWords = command.split(" ");
        String commandType = entryWords[0].toLowerCase();
        boolean isValidCommand = VALID_COMMANDS.contains(commandType);
        return isValidCommand;
    }

    /**
     * Updates file based on FavList.
     *
     * @throws IOException if file cannot be updated
     */

    @Override
    public void updateFile() throws IOException {
        String line = new String();
        FileWriter fw = new FileWriter(file);
        for (Fav f: FavList.getList()) {
            line += f.getCommand() + "|" + f.getDesc();
            line += System.lineSeparator();
        }
        fw.write(line);
        fw.close();
    }
}
