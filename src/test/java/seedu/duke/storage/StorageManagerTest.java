package seedu.duke.storage;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import org.junit.jupiter.api.Test;
import seedu.duke.project.Project;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StorageManagerTest {
    private static final String TEST_FILENAME = "test-data.json";
    private static final String TEST_FILEPATH_STR = String.format("./data/%s", TEST_FILENAME);
    private static final Path TEST_FILEPATH = Paths.get(TEST_FILEPATH_STR);

    private static final JsonArray SAVE_EXPECTED_1 = Jsoner.deserialize(
            "[{\"title\":\"Game\",\"description\":\"Among Us\",\"deadline\":\"29/12/12\","
                    + "\"sprint_length\":9,\"start_date\":null,\"backlog\":[],\"members\":[]}]",
            new JsonArray());
    private static final JsonArray SAVE_EXPECTED_2 = Jsoner.deserialize(
            String.format("[{\"title\":\"Game\",\"description\":\"Among Us\",\"deadline\":\"29/12/12\","
                            + "\"sprint_length\":9,\"start_date\":null,\"backlog\":[],\"members\":[]},"
                            + "{\"title\":\"Webpage\",\"description\":\"Beautifying\",\"deadline\":\"23/05/19\","
                            + "\"sprint_length\":10,\"start_date\":\"%s\",\"backlog\":[],\"members\":[]}]",
                    LocalDate.now().toString()),
            new JsonArray());
    private static final JsonArray LOAD_ERROR_1 = Jsoner.deserialize(
            String.format("[{\"title\":\"Game\",\"description\":\"Among Us\",\"deadline\":\"29/12/12\""
                            + ",\"sprint_length\":9,\"start_date\":null,\"backlog\":[],\"members\":[]},"
                            + "{\"title\":\"Webpage\",\"description\":\"Beautifying\",\"deadline\":\"23/05/19\","
                            + "\"sprint_length\":10,\"start_date\":\"%s\",\"backlog\":[],\"members\":[]},1]",
                    LocalDate.now().toString()),
            new JsonArray());
    private static final JsonObject LOAD_ERROR_2 = Jsoner.deserialize(
            "{\"title\":\"Game\",\"description\":\"Among Us\",\"deadline\":\"29/12/12\","
                    + "\"sprint_length\":9,\"start_date\":null,\"backlog\":[],\"members\":[]}",
            new JsonObject());

    @Test
    void load() {
        ArrayList<Project> projList = new ArrayList<>();
        StorageManager sm = new StorageManager(TEST_FILENAME, projList);
        // JSON file should be loaded successfully if data file is valid
        assertDoesNotThrow(() -> {
            initTestFile(SAVE_EXPECTED_2);
            sm.load();
        });
        // Exception should be thrown if any of the element in the JSON array is NOT an json object
        assertThrows(ClassCastException.class, () -> {
            initTestFile(LOAD_ERROR_1);
            sm.load();
        });
        // Data file should start with a JSON array, load() will ignore the data file 
        // if it does not start with JSON array 
        assertDoesNotThrow(() -> {
            initTestFile(LOAD_ERROR_2);
            sm.load();
        });
    }

    @Test
    void save() {
        try {
            ArrayList<Project> projList = new ArrayList<>();
            StorageManager sm = new StorageManager(TEST_FILENAME, projList);
            Project p1 = new Project("Game", "Among Us", "29/12/12", "9");
            projList.add(p1);
            sm.save();
            String fileData = Files.readString(TEST_FILEPATH);
            assertEquals(SAVE_EXPECTED_1.toJson(), fileData); //1st assertion
            //Add a second object compare the changes
            Project p2 = new Project("Webpage", "Beautifying", "23/05/19", "10");
            p2.setStartDate();
            projList.add(p2);
            sm.save();
            fileData = Files.readString(TEST_FILEPATH);
            assertEquals(SAVE_EXPECTED_2.toJson(), fileData); //2nd assertion
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void initTestFile(Object data) throws IOException {
        FileWriter fw = new FileWriter(TEST_FILEPATH_STR);
        Jsoner.serialize(data, fw);
        fw.close();
    }
}