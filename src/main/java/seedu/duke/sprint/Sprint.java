package seedu.duke.sprint;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.project.Project;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;

public class Sprint implements Jsonable {

    private int id;
    private String goal;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Integer> sprintTaskIds;
    private Project projAllocatedTo;

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
        this.sprintTaskIds = new ArrayList<>();
        this.projAllocatedTo = proj;
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
        for (Integer id : sprintTaskIds) {
            if (id == taskId) {
                return true;
            }
        }
        return false;
    }

    public void addSprintTask(int taskId) {
        this.sprintTaskIds.add(taskId);
    }

    public void removeSprintTask(int taskId) {
        this.sprintTaskIds.remove((Object) taskId);
    }


    public ArrayList<Integer> getAllSprintTaskIds() {
        return this.sprintTaskIds;
    }

    public String toSimplifiedString() {
        StringBuilder sprintInString = new StringBuilder();
        sprintInString.append(String.format("[Sprint ID: %d]", this.id));
        sprintInString.append(String.format("\t[Goal: %s]", this.goal));
        sprintInString.append(String.format("\t[Period: %s - %s]\n", this.startDate, this.endDate));
        return sprintInString.toString();
    }

    @Override
    public String toString() {
        boolean isCurrentSprint;
        isCurrentSprint = ((this.id - 1) == this.projAllocatedTo.getAllSprints().getCurrentSprintIndex());

        StringBuilder sprintInString = new StringBuilder();
        if (isCurrentSprint) {
            sprintInString.append("\n========================= CURRENT SPRINT ========================\n");
            sprintInString.append(String.format("[Remaining: %s days]\n", this.endDate.compareTo(LocalDate.now())));
        } else {
            sprintInString.append("\n============================ SPRINT =============================\n");
        }

        sprintInString.append(String.format("[ID: %d]\n", this.id));
        sprintInString.append(String.format("[Goal: %s]\n", this.goal));
        sprintInString.append(String.format("[Period: %s - %s] \n", this.startDate, this.endDate));


        if (sprintTaskIds.size() == 0) {
            sprintInString.append("[No allocated tasks]\n");
        } else {
            for (int taskIds : sprintTaskIds) {
                Task task = projAllocatedTo.getProjectBacklog().getTask(taskIds);
                System.out.println(taskIds);
                sprintInString.append(task.toString());
            }
        }
        sprintInString.append("=================================================================\n");
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
        jObj.put("goal", goal);
        jObj.put("startDate", startDate == null ? null : startDate.toString());
        jObj.put("endDate", endDate == null ? null : endDate.toString());
        final JsonObject jsonSprintTasks = new JsonObject();
        jObj.put("sprintTasks", jsonSprintTasks);
        jObj.toJson(writer);
    }
}
