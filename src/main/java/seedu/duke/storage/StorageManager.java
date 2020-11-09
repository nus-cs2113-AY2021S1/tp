package seedu.duke.storage;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.project.ProjectManager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageManager {
    private static final String DEFAULT_FILEPATH = "./data/data.json";

    private final Path FILE_PATH;
    private final ProjectManager PROJECT_MANAGER;

    public StorageManager(String filename, ProjectManager projectManager) throws IOException {
        if (filename == null || filename.isBlank()) {
            FILE_PATH = Paths.get(DEFAULT_FILEPATH);
        } else {
            FILE_PATH = Paths.get("./data", filename);
        }
        this.PROJECT_MANAGER = projectManager;
        try {
            init();
        } catch (IOException e) {
            ScrumLogger.LOGGER.warning(String.format("Unable to create data directory: %s%n",
                    e.toString()));
            throw e;
        }
    }

    //Public functions to be invoked

    /**
     * Save all projects into JSON data file.
     * File name of the data file is specified when the StorageManager object is instantiated
     */
    public void save() throws IOException {
        assert FILE_PATH != null : "File path is null.";
        try {
            if (!Files.exists(FILE_PATH.getParent())) {
                initDataDir();
            }
            assert Files.exists(FILE_PATH.getParent()) : "Data directory is still not created";
            FileWriter fw = new FileWriter((FILE_PATH.toFile()));
            Jsoner.serialize(PROJECT_MANAGER, fw);
            fw.close();
            ScrumLogger.LOGGER.info("Data has been successfully saved to the data file");
        } catch (IOException e) {
            ScrumLogger.LOGGER.warning(String.format("Data failed to be saved: %s%n", e.toString()));
            throw e;
        }
    }

    /**
     * Load the data file and deserialize it as ProjectManager.
     * File name of the data file is specified when the StorageManager object is instantiated.
     * If JSON is empty or invalid, no operations will be done to ProjectManager.
     *
     * @throws IOException Thrown when there is error opening the file or reading to the file.
     */
    public void load() throws IOException, JsonException {
        assert FILE_PATH != null : "File path is null.";
        if (!Files.exists(FILE_PATH)) {
            return; //file does not exist, start from a new
        }
        try {
            String rawData = loadRawData();
            JsonObject rawJson = (JsonObject) Jsoner.deserialize(rawData);
            PROJECT_MANAGER.fromJson(rawJson);
            ScrumLogger.LOGGER.info("Data has been successfully loaded from the data file");
        } catch (IOException e) {
            ScrumLogger.LOGGER.warning(String.format("Parsing of JSON failed, proceeding in empty state: %s%n", 
                    e.toString()));
            throw e;
        } catch (ClassCastException | NullPointerException | JsonException e) {
            PROJECT_MANAGER.clearProjects();
            ScrumLogger.LOGGER.warning(String.format("Parsing of JSON failed, proceeding in empty state: %s%n", 
                    e.toString()));
            throw e;
        }
    }

    //Private functions    
    private void init() throws IOException {
        initDataDir();
    }

    private void initDataDir() throws IOException {
        assert FILE_PATH != null : "File path is null.";
        Path path = FILE_PATH.getParent();
        if (!Files.exists(path)) {
            ScrumLogger.LOGGER.info("Data directory does not exist, creating a data directory");
            Files.createDirectory(path);
        }
    }

    private String loadRawData() throws IOException {
        assert FILE_PATH != null && Files.exists(FILE_PATH) : "File path is null or the file does not exist.";
        return Files.readString(FILE_PATH);
    }
}
