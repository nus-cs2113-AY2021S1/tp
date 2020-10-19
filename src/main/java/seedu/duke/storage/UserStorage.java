package seedu.duke.storage;

import seedu.duke.exception.AniException;
import seedu.duke.human.User;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.logger.AniLogger.getAniLogger;

public class UserStorage extends Storage {
    private static final String USER_FILE_NAME = "user.txt";
    private static final String USER_LINE_DELIMITER_FOR_DECODE = " \\| ";
    private static final String USER_LINE_DELIMITER_FOR_ENCODE = " | ";
    private static final Logger LOGGER = getAniLogger(UserStorage.class.getName());

    private final String storageDirectory;

    public UserStorage(String storageDirectory) {
        this.storageDirectory = storageDirectory;
    }

    // ========================== Save and Load ==========================

    public void save(User user) throws AniException {
        String userFilePath = storageDirectory + USER_FILE_NAME;
        String encodedUserString = encode(user);
        LOGGER.log(Level.INFO, "Encoded: " + System.lineSeparator() + encodedUserString);

        new File(storageDirectory).mkdirs();
        writeFile(userFilePath, encodedUserString);
    }

    public User load() throws AniException {
        String userFilePath = storageDirectory + USER_FILE_NAME;
        String fileString = readFile(userFilePath);
        if (fileString.isBlank()) {
            LOGGER.log(Level.WARNING, "Empty user file: " + userFilePath);
            throw new AniException("Empty user file.");
        }

        String[] fileStringSplit = fileString.split(USER_LINE_DELIMITER_FOR_DECODE, 2);
        LOGGER.log(Level.INFO, "Processing: " + System.lineSeparator() + fileString);
        if (!isValidUserString(fileStringSplit)) {
            LOGGER.log(Level.WARNING, "Invalid user file: " + userFilePath);
            throw new AniException("Not loaded successfully.");
        }

        return decode(fileStringSplit);
    }

    // ========================== Encode and Decode ==========================

    private String encode(User user) {
        String userName = user.getName();
        String userGender = user.getGender().toString();

        String encodedUserString = userName + USER_LINE_DELIMITER_FOR_ENCODE + userGender;
        assert (userName != null && userGender != null) : "User details should not have any null.";
        return encodedUserString;
    }

    private User decode(String[] fileStringSplit) throws AniException {
        String userName = fileStringSplit[0].trim();
        String userGender = fileStringSplit[1].trim();
        return new User(userName, userGender);
    }

    // ========================== Validation ==========================

    private boolean isValidUserString(String[] fileStringSplit) {
        return (fileStringSplit.length == 2);
    }
}
