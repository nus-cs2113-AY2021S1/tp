package seedu.duke.storage;

import seedu.duke.model.favorite.Fav;
import seedu.duke.model.favorite.FavList;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FavStorage extends Storage {
    private static final String FILE_READ = "FavList.txt Read with no issues";
    private File file;
    private boolean isCorrupted = false;

    public FavStorage(String dir) {
        super(dir);
        file = getFile();
    }


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

    private void readLine(String entry) {
        String[] entryWords = entry.split("\\|");
        if (entryWords.length != 2) {
            isCorrupted = true;
            return;
        }
        if (!entryWords[0].contains("/")) {
            isCorrupted = true;
            return;
        }
        FavList.addFav(new Fav(entryWords[0], entryWords[1]));

    }

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
