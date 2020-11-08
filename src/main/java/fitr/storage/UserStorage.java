package fitr.storage;

import fitr.ui.Ui;
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
     */
    public User loadUserProfile() throws FileNotFoundException {
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

        String name;
        String gender;
        int age;
        double height;
        double weight;
        int userFitnessLevel;

        if (isValidEntry(arguments)) {
            name = arguments[0];
            gender = arguments[1];
            age = Integer.parseInt(arguments[2]);
            height = Double.parseDouble(arguments[3]);
            weight = Double.parseDouble(arguments[4]);
            userFitnessLevel = Integer.parseInt(arguments[5]);
        } else {
            LOGGER.fine("Invalid data found in user profile, new user created.");
            Ui.printCustomError("Error: Invalid user file - creating a new user!");
            return new User();
        }

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

    private boolean isValidEntry(String[] arguments) {
        if (arguments.length != 6) {
            return false;
        }

        String name = arguments[0];
        if (name.isBlank()) {
            return false;
        }

        String gender = arguments[1];
        if (!gender.equals("Male") && !gender.equals("Female")) {
            return false;
        }

        try {
            int age = Integer.parseInt(arguments[2]);
            if (age < 1 || age > 130) {
                return false;
            }

            double height = Double.parseDouble(arguments[3]);
            if (height < 0.50 || height > 4.00) {
                return false;
            }

            double weight = Double.parseDouble(arguments[4]);
            if (weight < 2.0 || weight > 1000.00) {
                return false;
            }

            int fitnessLevel = Integer.parseInt(arguments[5]);
            if (fitnessLevel != 0 && fitnessLevel != 1 && fitnessLevel != 2) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
