package anichan.storage;

import anichan.exception.AniException;
import anichan.logger.AniLogger;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the storage of script data.
 */
public class ScriptStorage extends Storage {
    private final String storageDirectory;

    private static final String EMPTY_SCRIPT_FILE = "Script file is empty.";
    private static final Logger LOGGER = AniLogger.getAniLogger(ScriptStorage.class.getName());

    /**
     * Creates a new instance of ScriptStorage with the specified storage directory.
     *
     * @param storageDirectory the specified path to storage directory in hard disk
     */
    public ScriptStorage(String storageDirectory) {
        this.storageDirectory = storageDirectory;
    }

    /**
     * Reads the script file.
     *
     * @param workspaceName name of the workspace where the script can be found
     * @param fileName file name of the script file
     * @return the content of the script file
     * @throws AniException when an error occurred while loading the script data
     */
    public String readScript(String workspaceName, String fileName) throws AniException {
        String scriptFilePath = storageDirectory + workspaceName + File.separator + fileName;
        String fileContent = readFile(scriptFilePath);
        if (fileContent.isBlank()) {
            LOGGER.log(Level.WARNING, "Empty script file: " + scriptFilePath);
            throw new AniException(EMPTY_SCRIPT_FILE);
        }

        return fileContent;
    }
}
