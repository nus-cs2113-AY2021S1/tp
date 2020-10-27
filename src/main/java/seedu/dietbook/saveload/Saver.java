package seedu.dietbook.saveload;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;


/**
 * Saver class allows storage of string data into a table with a given width and height
 * It has a function that can write the data stored in it's table.
 */
public class Saver {
    private static final String ROOT_DIRECTORY = System.getProperty("user.home");
    private static final String BASE_FOLDER_NAME =  ROOT_DIRECTORY + File.separator + "dietbook";
    private static final String EMPTY_SYMBOL = "%NULL&!!LL";
    private static final String SEPERATOR_SYMBOL = "&%SEPERATOR%AAA%";
    private static final String FILE_EXTENSION = ".txt";

    static {
        File rootDirectory = new File(BASE_FOLDER_NAME);
        rootDirectory.mkdir();
    }

    private String[][] entries;
    private int height;
    private int width;

    public Saver(int width, int height) {
        setWidthAndHeight(width,height);
        initEntries();
    }

    private void setWidthAndHeight(int width, int height) {
        this.height = height;
        this.width = width;
    }

    private void initEntries() {
        this.entries = new String[height][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                entries[j][i] = EMPTY_SYMBOL;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Clears the entire table and set it to the new size.
     * @param newWidth the new width
     * @param newHeight the new height
     */
    public void resetSize(int newWidth, int newHeight) {
        setWidthAndHeight(newWidth, newHeight);
        initEntries();
    }

    /**
     * Clears the entire table.
     */
    public void reset() {
        initEntries();
    }

    /**
     * Adds the string provided to the position x,y on the table.
     * @param entry the entry to be provided into this position
     * @param xposition x position
     * @param yposition y position
     * @throws IndexOutOfBoundsException x or y position is out of bounds
     */
    public void add(String entry, int xposition, int yposition) throws IndexOutOfBoundsException {
        try {
            this.entries[yposition - 1][xposition - 1] = entry;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("The x or y position provided must be within the the dimensions of the"
                    + "saver table!");
        }
    }

    /**
     * Deletes the entry in the table.
     * @param xposition x position
     * @param yposition y position
     * @throws IndexOutOfBoundsException x or y position is out of bounds
     */
    public void delete(int xposition, int yposition) throws IndexOutOfBoundsException {
        try {
            this.entries[yposition - 1][xposition - 1] = EMPTY_SYMBOL;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("The x or y position provided must be within the the dimensions of the"
                    + "saver table!");
        }
    }

    /**
     * Gets a entry stored in the table.
     * @param xposition x position
     * @param yposition y position
     * @return Optional of String that is empty if the position does not contain an entry.
     */
    public Optional<String> get(int xposition, int yposition) {
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

    /**
     * Saves the data table into a text file in the following format:
     * width
     * height
     * (seperator) row 1 entry 1 (seperator) row 1 entry 2 (separator) ....
     * (seperator) row 2 entry 1 (separator) row 2 entry 2 (separator) ....
     * ....
     *
     * @param folderName name of the folder
     * @param fileName name of the file
     */
    public void save(String folderName, String fileName) {
        try {
            File directory = new File(BASE_FOLDER_NAME + File.separator + folderName);
            directory.mkdir();
            FileWriter writer = new FileWriter(BASE_FOLDER_NAME + File.separator + folderName
                    + File.separator + fileName + FILE_EXTENSION);
            writer.write(width + "\n");
            writer.write(height + "\n");
            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    writer.write(SEPERATOR_SYMBOL);
                    writer.write(entries[j][i]);
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Oops, the file writer took in a directory for some reason!");
        }
    }
}
