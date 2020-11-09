package seedu.dietbook.saveload;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

/**
 * Handles reading of stored text file.
 * Note: the first five fields must be same
 */
public class FileLoader extends Loader {
    private static final String ROOT_DIRECTORY = System.getProperty("user.home");
    private static final String BASE_FOLDER_NAME =  ROOT_DIRECTORY + File.separator + "dietbook";
    private static final String EMPTY_SYMBOL = "%NULL&!!LL";
    private static final String SEPARATOR_SYMBOL = "&%SEPERATOR%AAA%";
    private static final String FILE_EXTENSION = ".txt";

    private final String[][] entries;
    private final int width;
    private final int height;


    protected FileLoader(String folderName, String fileName) throws FileNotFoundException {
        File file = new File(BASE_FOLDER_NAME + File.separator + folderName
            + File.separator + fileName + FILE_EXTENSION);
        Scanner reader = new Scanner(file);
        width = Integer.parseInt(reader.nextLine());
        height = Integer.parseInt(reader.nextLine());
        entries = new String[height][width];
        String[] line;
        for (int j = 0; j < height; j++) {
            line = reader.nextLine().split(SEPARATOR_SYMBOL);
            if (width >= 0) {
                System.arraycopy(line, 1, entries[j], 0, width);
            }
        }
        reader.close();
    }

    /**
     * Get the String entry stored at the specified position in the table if it is present.
     * @param xposition the x position in the table from 1 to the table width
     * @param yposition the y position in the table from 1 to the table height
     * @return Optional of the String. The Optional is empty is no entry is stored
     * @throws IndexOutOfBoundsException if the x or y given is not as above
     */
    public Optional<String> get(int xposition, int yposition) throws IndexOutOfBoundsException {
        try {
            if (this.entries[yposition - 1][xposition - 1].equals(EMPTY_SYMBOL)) {
                return Optional.empty();
            } else {
                return Optional.of(this.entries[yposition - 1][xposition - 1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("The x or y position provided must be within the the dimensions of the"
                    + "saver table!");
        }
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
