package storage.profile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import exceptions.profile.InvalidSaveFormatException;
import exceptions.profile.LoadingException;
import exceptions.profile.SavingException;
import logger.SchwarzeneggerLogger;
import models.Profile;
import profile.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ui.CommonUi.EMPTY_STRING;

//@@author tienkhoa16
/**
 * A class that saves and loads user profile data on local hard disk.
 */
public class ProfileStorage {
    private static Logger logger = SchwarzeneggerLogger.getInstanceLogger();
    private Gson gson;
    private Path pathToProfileFolder;
    private Path pathToProfileFile;

    /**
     * Constructs Storage object.
     *
     * @param pathToProfileFolder Path to profile data folder.
     * @param pathToProfileFile Path to profile data file.
     */
    public ProfileStorage(Path pathToProfileFolder, Path pathToProfileFile) {
        gson = new GsonBuilder().setPrettyPrinting().create();
        this.pathToProfileFolder = pathToProfileFolder;
        this.pathToProfileFile = pathToProfileFile;
    }

    /**
     * Loads user profile from data file.
     *
     * @return User profile.
     * @throws LoadingException If there are failed or interrupted I/O operations.
     * @throws InvalidSaveFormatException If data file saving format is corrupted.
     */
    public Profile loadData() throws LoadingException, InvalidSaveFormatException {
        Profile profile = null;

        if (Files.exists(pathToProfileFolder)) {
            try {
                logger.log(Level.INFO, "starting to decode profile data");
                profile = decodeProfile();
                assert profile != null : "profile should not be null";
                logger.log(Level.INFO, "finishing profile data decoding");
            } catch (FileNotFoundException e) {
                createDataFile();
            }
        } else {
            createDataFolder();
            createDataFile();
        }

        return profile;
    }

    /**
     * Decodes user profile save data to a profile object.
     *
     * @return Profile object.
     * @throws InvalidSaveFormatException If the saving format is invalid.
     * @throws FileNotFoundException If data file is not found.
     */
    public Profile decodeProfile() throws InvalidSaveFormatException, FileNotFoundException {
        try {
            logger.log(Level.INFO, "decoding profile data");
            Type profileType = new TypeToken<Profile>() {
            }.getType();
            File file = new File(pathToProfileFile.toString());
            JsonReader reader = new JsonReader(new FileReader(file.getPath()));
            Profile profile = gson.fromJson(reader, profileType);

            if (!Utils.checkValidProfile(profile)) {
                logger.log(Level.WARNING, "processing invalid profile data");
                throw new InvalidSaveFormatException(pathToProfileFile.toString());
            }

            return profile;
        } catch (JsonSyntaxException e) {
            logger.log(Level.WARNING, "processing invalid syntax in data file", e);
            throw new InvalidSaveFormatException(pathToProfileFile.toString());
        }
    }

    /**
     * Creates data file.
     *
     * @throws LoadingException If there are failed or interrupted I/O operations.
     */
    public void createDataFile() throws LoadingException {
        try {
            Files.createFile(pathToProfileFile);
            logger.log(Level.INFO, "created saves/profile/profile.txt");
        } catch (IOException e) {
            throw new LoadingException(e.getMessage());
        }
    }

    /**
     * Creates data folder.
     *
     * @throws LoadingException If there are failed or interrupted I/O operations.
     */
    public void createDataFolder() throws LoadingException {
        try {
            Files.createDirectories(pathToProfileFolder);
            logger.log(Level.INFO, "created saves/profile");
        } catch (IOException e) {
            throw new LoadingException(e.getMessage());
        }
    }

    /**
     * Saves user profile data to hard disk after profile changes.
     *
     * @param profile User's profile.
     * @throws SavingException If there are failed or interrupted I/O operations.
     */
    public void saveData(Profile profile) throws SavingException {
        try {
            logger.log(Level.INFO, "starting to save profile data");
            FileWriter fw = new FileWriter(pathToProfileFile.toString());

            if (profile == null) {
                fw.write(EMPTY_STRING);
            } else {
                gson.toJson(profile, fw);
                fw.flush();
            }

            fw.close();
            logger.log(Level.INFO, "finishing data saving");
        } catch (IOException e) {
            logger.log(Level.WARNING, "processing IOException while saving data", e);
            throw new SavingException(e.getMessage());
        }
    }
}
