package seedu.duke.storage;

import seedu.duke.model.favorite.Fav;
import seedu.duke.model.favorite.FavList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FavStorage extends Storage {
    private File file;
    private int favCount = 0;

    public FavStorage(String dir) {
        super(dir);
        file = getFile();
    }

    @Override
    public void readFile() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String entry = s.nextLine();
            String[] entryWords = entry.split("\\|");
            FavList.addFav(new Fav(entryWords[0], entryWords[1]));
        }
        System.out.println("File Read");
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
