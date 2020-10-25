package seedu.duke.storage;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.TaskManager;
import seedu.duke.model.member.ProjectMembers;
import seedu.duke.model.member.Member;
import seedu.duke.model.sprint.Sprint;
import seedu.duke.model.sprint.SprintManager;
import seedu.duke.model.task.Priority;
import seedu.duke.model.task.Task;

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
    private final ProjectManager projectManager;

    public StorageManager(String filename, ProjectManager projectManager) {
        if (isValidFilename(filename)) {
            filepath = Paths.get("./data", filename);
        } else {
            filepath = Paths.get(DEFAULT_FILEPATH);
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
     * Load the data file and deserialize it as a list of Project objects.
     * File name of the data file is specified when the StorageManager object is instantiated.
     *
     * @throws IOException Thrown when there is error opening the file or reading to the file.
     */
    public void load() throws IOException {
        if (!Files.exists(filepath)) {
            return; //file does not exist, start from a new
        }
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
            projectManager.projectList.put(project.getProjectID(), project);
        }
    }

    private Project convertToProject(JsonObject jsonProject) {
        Project project = new Project();
        SprintManager allSprints = convertSprintsList((JsonObject) jsonProject.get("allSprints"), project);
        TaskManager backlog = convertBacklog((JsonObject) jsonProject.get("backlog"), project);
        ProjectMembers members = convertProjectMembers((JsonArray) jsonProject.get("members"));

        String title = (String) jsonProject.get("title");
        String description = (String) jsonProject.get("description");
        int projectDuration = ((BigDecimal) jsonProject.get("projectDuration")).intValue();
        int sprintLength = ((BigDecimal) jsonProject.get("sprintLength")).intValue();

        project.setTitle(title);
        project.setDescription(description);
        project.setProjectDuration(projectDuration);
        project.setSprintLength(sprintLength);
        project.setSprintList(allSprints);
        project.setBacklog(backlog);
        project.setMemberList(members);
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
            member.setTaskList(allocatedTaskIds);
            members.add(member);
        }
        projectMembers.setMemberList(members);
        return projectMembers;
    }

    private TaskManager convertBacklog(JsonObject rawBacklog, Project project) {
        TaskManager backlog = new TaskManager();
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
        task.setMemberList(membersAllocatedTo);
        JsonArray rawMembersAllocate = (JsonArray) jsonObject.get("membersAllocatedTo");
        rawMembersAllocate.asCollection(membersAllocatedTo);

        JsonArray rawSprintAllocate = (JsonArray) jsonObject.get("sprintAllocatedTo");
        ArrayList<Integer> sprintAllocatedTo = getIntegersFromJsonArray(rawSprintAllocate);
        task.setSprintList(sprintAllocatedTo);
        return task;
    }

    private SprintManager convertSprintsList(JsonObject rawSprints, Project project) {
        SprintManager sprintManager = new SprintManager();
        sprintManager.setCurrentSprintIndex(((BigDecimal) rawSprints.get("currentSprintIndex")).intValue());
        sprintManager.setSprintList(convertSprint((JsonArray) rawSprints.get("sprintList"), project));
        return sprintManager;
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
            sprint.setTaskList(sprintTaskIds);
            sprint.setOwner(project);

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
