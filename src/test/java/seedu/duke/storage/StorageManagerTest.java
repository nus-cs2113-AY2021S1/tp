package seedu.duke.storage;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import org.junit.jupiter.api.Test;
import seedu.duke.project.Project;
import seedu.duke.project.ProjectBacklog;
import seedu.duke.project.ProjectMembers;
import seedu.duke.sprint.Member;
import seedu.duke.sprint.SprintList;
import seedu.duke.task.Priority;

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

    @Test
    void load() {
        ArrayList<Project> projList = new ArrayList<>();
        StorageManager sm = new StorageManager(TEST_FILENAME, projList);
        // JSON file should be loaded successfully if data file is valid
        assertDoesNotThrow(() -> {
            String expectedStr = Files.readString(Paths.get("test-data/LOAD_EXPECTED_1.json"));
            JsonArray expected = Jsoner.deserialize(expectedStr, new JsonArray());
            initTestFile(expected);
            sm.load();
        });
    }

    @Test
    void save() {
        try {
            ArrayList<Project> projList = new ArrayList<>();
            StorageManager sm = new StorageManager(TEST_FILENAME, projList);
            projList.add(generateProject()); //2nd assertion
            sm.save();
            String expectedStr = Files.readString(Paths.get("test-data/SAVE_EXPECTED_1.json"));
            JsonArray expected = Jsoner.deserialize(expectedStr, new JsonArray());
            String fileData = Files.readString(sm.getFilepath());
            assertEquals(expected.toJson(), fileData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Project generateProject() {
        Project project = new Project("Game", "Among Us", "100", "10");
        project.setAllSprints(generateSprintList(project));
        project.setBacklog(generateProjectBacklog(project));
        project.setMembers(generateProjectMembers());
        project.setStartDate(LocalDate.parse("2020-10-10"));
        project.setEndDate(LocalDate.parse("2021-01-10"));
        return project;
    }
    
    private SprintList generateSprintList(Project project) {
        SprintList sprintList = new SprintList();
        sprintList.addSprint(project, "Goal", LocalDate.parse("2020-10-10"), LocalDate.parse("2020-10-19"));
        sprintList.addSprint(project, "Goal1", LocalDate.parse("2020-10-20"), LocalDate.parse("2020-10-29"));
        return sprintList;
    }
    
    private ProjectBacklog generateProjectBacklog(Project project) {
        ProjectBacklog backlog = new ProjectBacklog(project);
        backlog.addTask("Task1", "Task1 desc", "LOW");
        return backlog;
    }
    
    private ProjectMembers generateProjectMembers() {
        ProjectMembers members = new ProjectMembers();
        members.addMember(new Member("Lily"));
        members.addMember(new Member("Bob"));
        return members;
    }
    
    private void initTestFile(Object data) throws IOException {
        FileWriter fw = new FileWriter(TEST_FILEPATH_STR);
        Jsoner.serialize(data, fw);
        fw.close();
    }
}