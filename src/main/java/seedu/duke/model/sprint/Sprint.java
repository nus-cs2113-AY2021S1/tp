package seedu.duke.model.sprint;

import com.github.cliftonlabs.json_simple.JsonObject;
import seedu.duke.model.project.Project;
import seedu.duke.model.task.Task;
import seedu.duke.storage.JsonableObject;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;

public class Sprint implements JsonableObject {

    private int id;
    private String goal;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Integer> taskList;
    private Project owner;

    public Sprint() {
    }
    
    public Sprint(int sprintID, Project proj, String goal) {
        this(sprintID, proj, goal, null, null);
    }

    public Sprint(int sprintID, Project proj, String goal, LocalDate startDate) {
        this(sprintID, proj, goal, startDate, null);
    }

    public Sprint(int sprintID, Project proj, String goal, LocalDate startDate, LocalDate endDate) {
        setId(sprintID);
        setGoal(goal);
        setStartDate(startDate);
        setEndDate(endDate);
        this.taskList = new ArrayList<>();
        this.owner = proj;
    }

    public Project getOwner() {
        return owner;
    }

    public void setOwner(Project owner) {
        this.owner = owner;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean checkTaskExist(int taskId) {
        for (Integer id : taskList) {
            if (id == taskId) {
                return true;
            }
        }
        return false;
    }

    public void addSprintTask(int taskId) {
        this.taskList.add(taskId);
    }

    public void removeSprintTask(int taskId) {
        this.taskList.remove((Object) taskId);
    }
    
    public ArrayList<Integer> getAllSprintTaskIds() {
        return this.taskList;
    }

    public String toIdString() {
        StringBuilder sprintInString = new StringBuilder();
        sprintInString.append(String.format("[Sprint ID: %s]%n", this.id));
        return sprintInString.toString();
    }

    public String toSimplifiedString() {
        StringBuilder sprintInString = new StringBuilder();
        sprintInString.append(String.format("[Sprint ID: %d]", this.id));
        sprintInString.append(String.format("\t[Goal: %s]", this.goal));
        sprintInString.append(String.format("\t[Period: %s - %s]%n", this.startDate, this.endDate));
        return sprintInString.toString();
    }

    @Override
    public String toString() {
        boolean isCurrentSprint;
        isCurrentSprint = ((this.id) == this.owner.getSprintList().getCurrentSprintIndex());

        StringBuilder sprintInString = new StringBuilder();
        if (isCurrentSprint) {
            sprintInString.append(String.format("========================= CURRENT SPRINT ========================%n"));
        } else {
            sprintInString.append(String.format("============================ SPRINT =============================%n"));
        }

        sprintInString.append(String.format("[ID: %d]%n", this.id));
        sprintInString.append(String.format("[Goal: %s]%n", this.goal));
        sprintInString.append(String.format("[Period: %s - %s]%n", this.startDate, this.endDate));
        if (isCurrentSprint) {
            sprintInString.append(String.format("[Remaining: %s days]%n", this.endDate.compareTo(LocalDate.now())));
        }
        if (taskList.size() == 0) {
            sprintInString.append(String.format("[No allocated tasks]%n"));
        } else {
            for (int taskIds : taskList) {
                Task task = owner.getBacklog().getTask(taskIds);
                sprintInString.append(task.toString());
            }
        }
        sprintInString.append(String.format("=================================================================%n"));
        return sprintInString.toString();
    }

    @Override
    public String toJson() {
        final StringWriter writeable = new StringWriter();
        try {
            this.toJson(writeable);
        } catch (IOException e) {
            System.out.println("[Error] Cannot convert this project to JSON");
            e.printStackTrace();
        }
        return writeable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject jObj = new JsonObject();
        jObj.put("id", id);
        jObj.put("goal", goal);
        jObj.put("startDate", startDate == null ? null : startDate.toString());
        jObj.put("endDate", endDate == null ? null : endDate.toString());
        jObj.put("sprintTaskIds", taskList);
        jObj.put("owner", owner.getProjectID());
        jObj.toJson(writer);
    }

    public void fromJson(JsonObject jsonObj, Project project) {
        owner = project;
        id = JsonableObject.parseInt(jsonObj, "id");
        goal = JsonableObject.parseString(jsonObj, "goal");
        startDate = JsonableObject.parseDate(jsonObj, "startDate");
        endDate = JsonableObject.parseDate(jsonObj, "endDate");
        taskList = JsonableObject.parseIntegerList(jsonObj, "sprintTaskIds");
    }
    
    @Override
    public void fromJson(JsonObject jsonObj) {
        fromJson(jsonObj, null);
    }
}
