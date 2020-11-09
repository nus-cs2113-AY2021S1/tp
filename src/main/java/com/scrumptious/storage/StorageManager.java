package com.scrumptious.storage;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.scrumptious.logger.ScrumLogger;
import com.scrumptious.model.project.ProjectManager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageManager {
    private static final String DEFAULT_FILEPATH = "./data/data.json";

    private final Path filepath;
    private final ProjectManager projectManager;

    public StorageManager(String filename, ProjectManager projectManager) throws IOException {
        if (filename == null || filename.isBlank()) {
            filepath = Paths.get(DEFAULT_FILEPATH);
        } else {
            filepath = Paths.get("./data", filename);
        }
        this.projectManager = projectManager;
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
        assert filepath != null : "File path is null.";
        try {
            if (!Files.exists(filepath.getParent())) {
                initDataDir();
            }
            assert Files.exists(filepath.getParent()) : "Data directory is still not created";
            FileWriter fw = new FileWriter((filepath.toFile()));
            Jsoner.serialize(projectManager, fw);
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
        assert filepath != null : "File path is null.";
        if (!Files.exists(filepath)) {
            return; //file does not exist, start from a new
        }
        try {
            String rawData = loadRawData();
            JsonObject rawJson = (JsonObject) Jsoner.deserialize(rawData);
            projectManager.fromJson(rawJson);
            ScrumLogger.LOGGER.info("Data has been successfully loaded from the data file");
        } catch (IOException e) {
            ScrumLogger.LOGGER.warning(String.format("Parsing of JSON failed, proceeding in empty state: %s%n", 
                    e.toString()));
            throw e;
        } catch (ClassCastException | NullPointerException | JsonException e) {
            projectManager.clearProjects();
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
        assert filepath != null : "File path is null.";
        Path path = filepath.getParent();
        if (!Files.exists(path)) {
            ScrumLogger.LOGGER.info("Data directory does not exist, creating a data directory");
            Files.createDirectory(path);
        }
    }

    private String loadRawData() throws IOException {
        assert filepath != null && Files.exists(filepath) : "File path is null or the file does not exist.";
        return Files.readString(filepath);
    }
}
