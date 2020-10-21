package seedu.duke.saveload;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class Saver {
    private static final String ROOT_DIRECTORY = System.getProperty("user.home");
    private static final String BASE_FOLDER_NAME =  ROOT_DIRECTORY + File.separator + "dietbook";
    private static final String EMPTY_SYMBOL = "%NULL&!!LL";
    private static final String SEPERATOR_SYMBOL = "&%SEPERATOR%$$";
    private static final String FILE_EXTENSION = ".txt";

    static {
        File rootDirectory = new File(BASE_FOLDER_NAME);
        rootDirectory.mkdir();
    }

    private final String[][] entries;
    private final int height;
    private final int width;

    public Saver(int width, int height){
        this.height = height;
        this.width = width;
        this.entries = new String[height][width];

        for (int i = 0; i < width; i ++){
            for (int j = 0; j < height; j++){
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

    public void add(String entry, int x_position, int y_position) throws IndexOutOfBoundsException{
        try {
            this.entries[y_position - 1][x_position - 1] = entry;
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("The x or y position provided must be within the the dimensions of the" +
                    "saver table!");
        }
    }

    public void delete(int x_position, int y_position) throws IndexOutOfBoundsException{
        try {
            this.entries[y_position - 1][x_position - 1] = EMPTY_SYMBOL;
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("The x or y position provided must be within the the dimensions of the" +
                    "saver table!");
        }
    }

    public Optional<String> get(int x_position, int y_position){
        try {
            if (this.entries[y_position - 1][x_position - 1].equals(EMPTY_SYMBOL)) {
                return Optional.empty();
            } else {
                return Optional.of(this.entries[y_position][x_position]);
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("The x or y position provided must be within the the dimensions of the" +
                    "saver table!");
        }
    }

    /***
     * Saves the data table into a text file in the following format:
     * width
     * height
     * row 1 entry 1 (seperator) row 1 entry 2 (separator) ....
     * row 2 entry 1 (separator) row 2 entry 2 (separator) ....
     * ....
     *
     * @param folderName name of the folder
     * @param fileName name of the file
     */
    public void save(String folderName, String fileName){
        try {
            File directory = new File(BASE_FOLDER_NAME + File.separator + folderName);
            directory.mkdir();
            FileWriter writer = new FileWriter(BASE_FOLDER_NAME + File.separator + folderName
                    + File.separator + fileName + FILE_EXTENSION);
            writer.write(width + "\n");
            writer.write(height + "\n");
            for (int j = 0; j < height; j ++){
                for (int i = 0; i < width; i++){
                    writer.write(SEPERATOR_SYMBOL);
                    writer.write(entries[j][i]);
                }
                writer.write("\n");
            }
            writer.close();
        }
        catch(IOException e){
            System.out.println("Oops, the file writer took in a directory for some reason!");
        }
    }
}
