package profile.storage;

import profile.Profile;
import profile.exceptions.InvalidSaveFormatException;
import profile.exceptions.LoadingException;
import profile.exceptions.SavingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static profile.Constants.PATH_TO_PROFILE_FILE;
import static profile.Constants.PATH_TO_PROFILE_FOLDER;
import static profile.Constants.PROFILE_SAVE_FORMAT;
import static profile.Constants.VERTICAL_BAR_REGREX;

public class Storage {
    private boolean hasExistingDataFile;

    /**
     * Constructs Storage object where data file is assumed to be existed.
     */
    public Storage() {
        hasExistingDataFile = true;
    }

    /**
     * Returns boolean stating if data file is existed.
     *
     * @return Boolean stating if data file is existed.
     */
    public boolean getHasExistingDataFile() {
        return hasExistingDataFile;
    }

    /**
     * Loads user profile from data file.
     *
     * @return User profile.
     * @throws InvalidSaveFormatException If data line in Schwarzenegger has invalid encode format.
     * @throws LoadingException If there are failed or interrupted I/O operations.
     */
    public Profile loadData() throws InvalidSaveFormatException, LoadingException {
        Profile profile = null;

        if (Files.exists(PATH_TO_PROFILE_FOLDER)) {
            // Create a File in the given file path
            File file = new File(PATH_TO_PROFILE_FILE.toString());

            try {
                // Create a Scanner using the File as the source
                Scanner scanner = new Scanner(file);

                if (scanner.hasNext()) {
                    String encodedProfile = scanner.nextLine();
                    profile = decodeProfile(encodedProfile);
                } else {
                    hasExistingDataFile = false;
                }
            } catch (FileNotFoundException e) {
                createDataFile(PATH_TO_PROFILE_FILE);
            }
        } else {
            createDataFolder(PATH_TO_PROFILE_FOLDER);
            createDataFile(PATH_TO_PROFILE_FILE);
        }

        return profile;
    }

    /**
     * Decodes user profile save data to a profile object.
     *
     * @param encodedProfile User profile save data.
     * @return Profile object.
     * @throws InvalidSaveFormatException If the saving format is invalid.
     */
    private Profile decodeProfile(String encodedProfile) throws InvalidSaveFormatException {
        Profile profile = null;

        String[] split = encodedProfile.split(VERTICAL_BAR_REGREX);

        try {
            String name = split[0];
            int age = Integer.parseInt(split[1]);
            int height = Integer.parseInt(split[2]);
            double weight = Double.parseDouble(split[3]);
            double expectedWeight = Double.parseDouble(split[4]);

            return new Profile(name, age, height, weight, expectedWeight);
        } catch (NumberFormatException e) {
            throw new InvalidSaveFormatException(encodedProfile);
        }
    }

    /**
     * Creates data file.
     *
     * @param pathToDataFile Path to data file.
     * @throws LoadingException If there are failed or interrupted I/O operations.
     */
    private void createDataFile(Path pathToDataFile) throws LoadingException {
        try {
            hasExistingDataFile = false;
            Files.createFile(pathToDataFile);
        } catch (IOException e) {
            throw new LoadingException(e.getMessage());
        }
    }

    /**
     * Creates data folder.
     *
     * @param pathToDataFolder Path to data folder.
     * @throws LoadingException If there are failed or interrupted I/O operations.
     */
    private void createDataFolder(Path pathToDataFolder) throws LoadingException {
        try {
            Files.createDirectories(pathToDataFolder);
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
            FileWriter fw = new FileWriter(PATH_TO_PROFILE_FILE.toString());

            fw.write(String.format(PROFILE_SAVE_FORMAT, profile.getName(), profile.getAge(), profile.getHeight(),
                    profile.getWeight(), profile.getExpectedWeight()));

            fw.close();
        } catch (IOException e) {
            throw new SavingException(e.getMessage());
        }
    }
}
