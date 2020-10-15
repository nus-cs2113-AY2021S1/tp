package seedu.duke.storage;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import seedu.duke.project.Project;
import seedu.duke.project.ProjectBacklog;
import seedu.duke.project.ProjectMembers;
import seedu.duke.sprint.Member;
import seedu.duke.sprint.Sprint;
import seedu.duke.sprint.SprintList;
import seedu.duke.task.Priority;
import seedu.duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
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
     */
    public void save() {
        try {
            JsonArray jsonProjects = new JsonArray(projects);
            FileWriter fw = new FileWriter((filepath.toFile()));
            Jsoner.serialize(jsonProjects, fw);
            fw.close();
            System.out.printf("Data saved to %s%n", filepath.toAbsolutePath().toString());
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
            parseJsonArray(rawJson);
        } catch (IOException e) {
            System.out.println("[Error] Unable to load the data file properly, exiting...");
            //e.printStackTrace();
            throw e;
        } catch (ClassCastException e) {
            System.out.printf("[Error] Cannot parse an element as a JSON object properly!%n");
            //e.printStackTrace();
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

    private void parseJsonArray(JsonArray raw) {
        ArrayList<JsonObject> jsonProjects = new ArrayList<>(raw.size());
        for (Object o : raw) {
            Project project = convertToProject((JsonObject) o);
            projects.add(project);
        }
    }

    private Project convertToProject(JsonObject jsonProject) {
        Project project = new Project();
        SprintList allSprints = convertSprintsList((JsonObject) jsonProject.get("allSprints"), project);
        ProjectBacklog backlog = convertBacklog((JsonObject) jsonProject.get("backlog"), project);
        ProjectMembers members = convertProjectMembers((JsonArray) jsonProject.get("members"));

        String title = (String) jsonProject.get("title");
        String description = (String) jsonProject.get("description");
        int projectDuration = ((BigDecimal) jsonProject.get("projectDuration")).intValue();
        int sprintLength = ((BigDecimal) jsonProject.get("sprintLength")).intValue();

        project.setTitle(title);
        project.setDescription(description);
        project.setProjectDuration(projectDuration);
        project.setSprintLength(sprintLength);
        project.setAllSprints(allSprints);
        project.setBacklog(backlog);
        project.setMembers(members);
        project.setStartDate(getDateFromJsonObj(jsonProject, "startDate"));
        project.setEndDate(getDateFromJsonObj(jsonProject, "endDate"));
        return project;
    }

    private ProjectMembers convertProjectMembers(JsonArray rawMembers) {
        ProjectMembers projectMembers = new ProjectMembers();
        ArrayList<Member> members = new ArrayList<>();
        for (Object o : rawMembers) {
            Member member = new Member();
            JsonObject rawMember = (JsonObject) o;
            JsonArray rawAllocatedTaskIds = (JsonArray) rawMember.get("allocatedTaskIds");
            ArrayList<Integer> allocatedTaskIds = getIntegersFromJsonArray(rawAllocatedTaskIds);
            String userId = (String) rawMember.get("userId");
            member.setUserId(userId);
            member.setAllocatedTaskIds(allocatedTaskIds);
            members.add(member);
        }
        projectMembers.setMemberList(members);
        return projectMembers;
    }

    private ProjectBacklog convertBacklog(JsonObject rawBacklog, Project project) {
        ProjectBacklog backlog = new ProjectBacklog();
        ArrayList<Task> backlogTasks = new ArrayList<>();
        JsonArray rawTasks = (JsonArray) rawBacklog.get("backlogTasks");
        for (Object rawTask : rawTasks) {
            backlogTasks.add(convertTask((JsonObject) rawTask));
        }
        backlog.setNextId(((BigDecimal) rawBacklog.get("nextId")).intValue());
        backlog.setBacklogTasks(backlogTasks);
        backlog.setProj(project);
        return backlog;
    }

    private Task convertTask(JsonObject jsonObject) {
        Task task = new Task();

        int id = ((BigDecimal) jsonObject.get("id")).intValue();
        task.setId(id);
        String title = (String) jsonObject.get("title");
        task.setTitle(title);
        String description = (String) jsonObject.get("description");
        task.setDescription(description);
        Priority priority = Priority.valueOf((String) jsonObject.get("priority"));
        task.setPriority(priority);
        boolean isDone = (Boolean) jsonObject.get("isDone");
        task.setDone(isDone);

        ArrayList<String> membersAllocatedTo = new ArrayList<>();
        task.setMembersAllocatedTo(membersAllocatedTo);
        JsonArray rawMembersAllocate = (JsonArray) jsonObject.get("membersAllocatedTo");
        rawMembersAllocate.asCollection(membersAllocatedTo);

        JsonArray rawSprintAllocate = (JsonArray) jsonObject.get("sprintAllocatedTo");
        ArrayList<Integer> sprintAllocatedTo = getIntegersFromJsonArray(rawSprintAllocate);
        task.setSprintAllocatedTo(sprintAllocatedTo);
        return task;
    }

    private SprintList convertSprintsList(JsonObject rawSprints, Project project) {
        SprintList sprintList = new SprintList();
        sprintList.setCurrentSprintIndex(((BigDecimal) rawSprints.get("currentSprintIndex")).intValue());
        sprintList.setSprintList(convertSprint((JsonArray) rawSprints.get("sprintList"), project));
        return sprintList;
    }

    private ArrayList<Sprint> convertSprint(JsonArray sprintList, Project project) {
        ArrayList<Sprint> sprints = new ArrayList<>();

        for (Object o : sprintList) {
            Sprint sprint = new Sprint();
            JsonObject rawSprint = (JsonObject) o;

            int id = ((BigDecimal) rawSprint.get("id")).intValue();
            String goal = (String) rawSprint.get("goal");
            JsonArray rawSprintTaskIds = (JsonArray) rawSprint.get("sprintTaskIds");
            ArrayList<Integer> sprintTaskIds = getIntegersFromJsonArray(rawSprintTaskIds);

            sprint.setId(id);
            sprint.setGoal(goal);
            sprint.setStartDate(getDateFromJsonObj(rawSprint, "startDate"));
            sprint.setEndDate(getDateFromJsonObj(rawSprint, "endDate"));
            sprint.setSprintTaskIds(sprintTaskIds);
            sprint.setProjAllocatedTo(project);

            sprints.add(sprint);
        }
        return sprints;
    }

    private ArrayList<Integer> getIntegersFromJsonArray(JsonArray rawIntegers) {
        ArrayList<Integer> numList = new ArrayList<>();
        for (Object rawInteger : rawIntegers) {
            int num = ((BigDecimal) rawInteger).intValue();
            numList.add(num);
        }
        return numList;
    }

    private LocalDate getDateFromJsonObj(JsonObject obj, String key) {
        Object rawDate = obj.get(key);
        if (rawDate != null) {
            return LocalDate.parse((String) rawDate);
        }
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
