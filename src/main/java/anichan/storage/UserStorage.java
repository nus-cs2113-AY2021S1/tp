package anichan.storage;

import anichan.exception.AniException;
import anichan.human.User;
import anichan.logger.AniLogger;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserStorage extends Storage {
    private static final String USER_FILE_NAME = "user.txt";
    private static final String USER_LINE_DELIMITER_FOR_DECODE = " \\| ";
    private static final String USER_LINE_DELIMITER_FOR_ENCODE = " | ";
    private static final Logger LOGGER = AniLogger.getAniLogger(UserStorage.class.getName());

    private final String storageDirectory;

    public UserStorage(String storageDirectory) {
        this.storageDirectory = storageDirectory;
    }

    // ========================== Save and Load ==========================

    public void save(User user) throws AniException {
        String userFilePath = storageDirectory + USER_FILE_NAME;
        String encodedUserString = encode(user);

        new File(storageDirectory).mkdirs();
        writeFile(userFilePath, encodedUserString);
    }

    public User load() throws AniException {
        String userFilePath = storageDirectory + USER_FILE_NAME;
        String fileContent = readFile(userFilePath);
        if (fileContent.isBlank()) {
            LOGGER.log(Level.WARNING, "Empty user file: " + userFilePath);
            throw new AniException("Empty user file.");
        }

        String[] fileContentSplit = fileContent.split(USER_LINE_DELIMITER_FOR_DECODE, 2);
        LOGGER.log(Level.FINE, "Processing: " + System.lineSeparator() + fileContent);
        if (!isValidUserString(fileContentSplit)) {
            LOGGER.log(Level.WARNING, "Invalid user file: " + userFilePath);
            throw new AniException("Not loaded successfully.");
        }

        return decode(fileContentSplit);
    }

    // ========================== Encode and Decode ==========================

    private String encode(User user) {
        String userName = user.getName();
        String userGender = user.getGender().toString();

        String encodedUserString = userName + USER_LINE_DELIMITER_FOR_ENCODE + userGender;
        assert (userName != null && userGender != null) : "User details should not have any null.";
        return encodedUserString;
    }

    private User decode(String[] fileContentSplit) throws AniException {
        String userName = fileContentSplit[0].trim();
        String userGender = fileContentSplit[1].trim();
        return new User(userName, userGender);
    }

    // ========================== Validation ==========================

    private boolean isValidUserString(String[] fileContentSplit) {
        return (fileContentSplit.length == 2);
    }
}
