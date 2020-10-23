package fitr.storage;

import fitr.exception.InvalidFileFormatException;
import fitr.user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

import static fitr.storage.StorageManager.COMMA_SEPARATOR;

public class UserStorage {
    private static final Logger LOGGER = Logger.getLogger(UserStorage.class.getName());
    private static final String USER_PROFILE_PATH = "userProfile.txt";

    public UserStorage() throws IOException {
        File userProfile = new File(USER_PROFILE_PATH);
        if (!userProfile.exists()) {
            boolean isFileExists = userProfile.createNewFile();
            LOGGER.fine("User profile created: " + USER_PROFILE_PATH);
        }
    }


    /**
     * Reads the user's data from the text file.
     *
     * @return an User object with data from the text file
     * @throws FileNotFoundException if the file is not found
     * @throws InvalidFileFormatException if the file format is invalid
     */
    public User loadUserProfile() throws FileNotFoundException, InvalidFileFormatException {
        LOGGER.fine("Attempting to read file: " + USER_PROFILE_PATH);

        File file = new File(USER_PROFILE_PATH);
        Scanner readFile = new Scanner(file);
        String line;

        try {
            line = readFile.nextLine();
        } catch (NoSuchElementException e) {
            LOGGER.fine("New user created.");
            return new User();
        }

        String[] arguments = line.split(COMMA_SEPARATOR);

        if (arguments.length != 6) {
            throw new InvalidFileFormatException();
        }

        String name = arguments[0];
        String gender = arguments[1];
        int age = Integer.parseInt(arguments[2]);
        double height = Double.parseDouble(arguments[3]);
        double weight = Double.parseDouble(arguments[4]);
        int userFitnessLevel = Integer.parseInt(arguments[5]);

        LOGGER.fine("User profile file read successfully.");
        return new User(name, age, height, weight, gender, userFitnessLevel);
    }

    /**
     * Writes the user's data into the text file.
     *
     * @param user the user to load the file into
     * @throws IOException if an I/O error has occurred
     */
    public void writeUserProfile(User user) throws IOException {
        assert user != null;
        LOGGER.fine("Attempting to write to file: " + USER_PROFILE_PATH);

        FileWriter file = new FileWriter(USER_PROFILE_PATH);

        file.write(user.getName()
                + COMMA_SEPARATOR + user.getGender()
                + COMMA_SEPARATOR + user.getAge()
                + COMMA_SEPARATOR + user.getHeight()
                + COMMA_SEPARATOR + user.getWeight()
                + COMMA_SEPARATOR + user.getFitnessLevel() + System.lineSeparator());

        LOGGER.fine("User profile file written successfully.");
        file.close();
    }
}
