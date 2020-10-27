package seedu.duke.storage;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import seedu.duke.model.project.ProjectManager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.exit;

public class StorageManager {
    private static final String DEFAULT_FILEPATH = "./data/data.json";

    private final Path filepath;
    private final ProjectManager projectManager;

    public StorageManager(String filename, ProjectManager projectManager) {
        if (filename == null || filename.isBlank()) {
            filepath = Paths.get(DEFAULT_FILEPATH);
        } else {
            filepath = Paths.get("./data", filename);
        }
        this.projectManager = projectManager;
        init();
    }

    //Public functions to be invoked
    /**
     * Save all projects into JSON data file.
     * File name of the data file is specified when the StorageManager object is instantiated
     */
    public void save() {
        try {
            FileWriter fw = new FileWriter((filepath.toFile()));
            Jsoner.serialize(projectManager, fw);
            fw.close();
        } catch (IOException e) {
            System.out.println("[Warning] Cannot save to data file, data will be lost when this program ends!");
            e.printStackTrace();
        }
    }

    /**
     * Load the data file and deserialize it as ProjectManager.
     * File name of the data file is specified when the StorageManager object is instantiated.
     * If JSON is empty or invalid, no operations will be done to ProjectManager.
     * @throws IOException Thrown when there is error opening the file or reading to the file.
     */
    public void load() throws IOException {
        if (!Files.exists(filepath)) {
            return; //file does not exist, start from a new
        }
        try {
            String rawData = loadRawData();
            JsonObject rawJson = Jsoner.deserialize(rawData, new JsonObject());
            projectManager.fromJson(rawJson);
        } catch (IOException e) {
            System.out.println("[Error] Unable to load the data file properly, exiting...");
            throw e;
        } catch (ClassCastException e) {
            System.out.printf("[Error] Cannot parse an element as a JSON object properly!%n");
            throw e;
        }

    }

    //Private functions    
    private void init() {
        initDataDir();
    }

    private void initDataDir() {
        Path path = filepath.getParent();
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            System.out.println("[Error] Cannot create data directory for saving.");
            e.printStackTrace();
            exit(1);
        }
    }

    private String loadRawData() throws IOException {
        return Files.readString(filepath);
    }
}
