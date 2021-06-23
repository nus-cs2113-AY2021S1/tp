package storage.diet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import diet.dietsession.DietSession;
import logger.SchwarzeneggerLogger;
import ui.diet.dietsession.DietSessionUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ui.diet.dietmanager.DietManagerUi.DIET_FILE_CORRUPTED;
import static ui.diet.dietmanager.DietManagerUi.DIET_FILE_NOT_FOUND;

//@@author CFZeon
/**
 * This class holds the data loaded during runtime and read and writes to the local storage.
 */
public class DietStorage {
    private static Logger logger = SchwarzeneggerLogger.getInstanceLogger();
    private static Gson gson;
    private static File file = null;
    private static DietSessionUi ui = new DietSessionUi();

    /**
     * Initialise the database with locally stored data.
     * If the local file is not found. It creates the relevant file and folder.
     * @param filePath a string that represents file path.
     * @param filePathName a string that represents file path name.
     *
     * @throws IOException If director or file cannot be created.
     */
    public void init(String filePath, String filePathName) throws IOException {
        logger.log(Level.INFO, "creating diet session save file");

        gson = new GsonBuilder().setPrettyPrinting()
                .create();

        String fileName = filePath + filePathName + ".json";
        file = new File(fileName);

        file.getParentFile().mkdirs();
        file.createNewFile();

    }

    /**
     * Writes the content in dietSession to a local file.
     * If the local file is not found. It creates the relevant file and folder.
     * @param filePath a string that represents file path.
     * @param filePathName a string that represents file path name.
     * @param dietSession that represents the class diet session
     *
     * @throws IOException If director or file cannot be created.
     */
    public void writeToStorageDietSession(String filePath, String filePathName,
                                          DietSession dietSession) throws IOException {
        logger.log(Level.INFO, "saving file to location");
        File file = new File(filePath + filePathName + ".json");
        if (file.exists()) {
            file.delete();
        }
        FileWriter writer = new FileWriter(file.getPath());
        gson.toJson(dietSession, writer);
        logger.log(Level.INFO, "file saving complete");
        writer.flush();
        writer.close();
    }

    /**
     * Reads the content of the .json file and instantiates as a DietSession.
     *
     * @param filePath path from source folder to save folder
     * @param filePathName name of file
     * @return DietSession instance
     */
    public DietSession readDietSession(String filePath, String filePathName) {
        Gson gson = new Gson();
        DietSession dietSession;
        dietSession = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/"
                    + filePath + filePathName);
            Reader reader = new FileReader(file.getPath());
            dietSession = gson.fromJson(reader, DietSession.class);
            reader.close();
        } catch (FileNotFoundException e) {
            ui.showToUser(DIET_FILE_NOT_FOUND);
            logger.log(Level.WARNING, "Diet Session file not found");
        } catch (MalformedJsonException | JsonSyntaxException e) {
            ui.showToUser(DIET_FILE_CORRUPTED);
            logger.log(Level.WARNING, "Corrupted diet session");
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not read diet session");
        }
        return dietSession;
    }
}
