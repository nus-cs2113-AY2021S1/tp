package seedu.duke.saveload;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {
    private static final String ROOT_DIRECTORY = System.getProperty("user.home");
    private static final String BASE_FOLDER_NAME =  ROOT_DIRECTORY + File.separator + "dietbook";
    private static final String EMPTY_SYMBOL = "%NULL&!!LL";
    private static final String SEPERATOR_SYMBOL = "&%SEPERATOR%$$";
    private static final String FILE_EXTENSION = ".txt";

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

    public void add(String entry, int x_position, int y_position){
        this.entries[y_position][x_position] = entry;
    }

    public void delete(int x_position, int y_position){
        this.entries[y_position][x_position] = EMPTY_SYMBOL;
    }

    public void save(String folderName, String fileName){
        try {
            File directory = new File(BASE_FOLDER_NAME + File.separator + folderName);
            directory.mkdir();
            FileWriter writer = new FileWriter(BASE_FOLDER_NAME + File.separator + folderName
                    + File.separator + fileName + FILE_EXTENSION);
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
