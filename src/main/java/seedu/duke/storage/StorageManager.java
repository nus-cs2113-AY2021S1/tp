package seedu.duke.storage;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import seedu.duke.project.Project;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.lang.System.exit;

public class StorageManager {
    private static final String DEFAULT_FILEPATH = "./data/data.json";
    private static final String INVALID_FILE_REGEX = "";

    private final Path filepath;
    private final ArrayList<Project> projects;

    public StorageManager(String filename, ArrayList<Project> projects) {
        if (isValidFilename(filename)) {
            filepath = Paths.get("./data", filename);
        } else {
            filepath = Paths.get(DEFAULT_FILEPATH);
        }
        this.projects = projects == null ? new ArrayList<>() : projects;
        init();
    }

    //Getters
    public Path getFilepath() {
        return filepath;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    //Public functions to be invoked

    /**
     * Save all projects into JSON data file.
     * File name of the data file is specified when the StorageManager object is instantiated
     *
     */
    public void save() {
        try {
            JsonArray jsonProjects = new JsonArray(projects);
            FileWriter fw = new FileWriter((filepath.toFile()));
            Jsoner.serialize(jsonProjects, fw);
            fw.close();
        } catch (IOException e) {
            System.out.println("[Warning] Cannot save to data file, data will be lost when this program ends!");
            e.printStackTrace();
        }
    }

    /**
     * Load the data file and deserialize it as a list of Project objects.
     * File name of the data file is specified when the StorageManager object is instantiated.
     *
     * @throws IOException Thrown when there is error opening the file or reading to the file.
     */
    public void load() throws IOException {
        try {
            String rawData = loadRawData();
            JsonArray rawJson = Jsoner.deserialize(rawData, new JsonArray());
            ArrayList<Project> jsonProjects = parseJsonArray(rawJson);
        } catch (IOException e) {
            System.out.println("[Error] Unable to load the data file properly, exiting...");
            e.printStackTrace();
            throw e;
        } catch (ClassCastException e) {
            System.out.printf("[Error] Cannot parse an element as a JSON object properly!%n");
            e.printStackTrace();
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

    private ArrayList<Project> parseJsonArray(JsonArray raw) {
        ArrayList<JsonObject> jsonProjects = new ArrayList<>(raw.size());

        for (int i = 0; i < raw.size(); i++) {
            jsonProjects.add(raw.getMap(i));
        }

        return convertToProject(jsonProjects);
    }

    private ArrayList<Project> convertToProject(ArrayList<JsonObject> jsonProjects) {
        System.out.println("Convert JsonObject to Project object and its respective object members");
        return null;
    }

    private String loadRawData() throws IOException {
        return Files.readString(filepath);
    }

    //TODO Valid filename checker
    private boolean isValidFilename(String filename) {
        return true;
    }
}
