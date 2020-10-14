package seedu.duke.sprint;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import seedu.duke.project.Project;

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

    public Sprint(Project proj, String goal) {
        this(proj, goal, null, null);
    }

    public Sprint(Project proj, String goal, LocalDate startDate) {
        this(proj, goal, startDate, null);
    }

    public Sprint(Project proj, String goal, LocalDate startDate, LocalDate endDate) {
        setId(this.getId()+1);
        setGoal(goal);
        setStartDate(startDate);
        setEndDate(endDate);
        this.sprintTaskIds = new ArrayList<>();
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

    public void addSprintTask(int taskId) {
        this.sprintTaskIds.add(taskId);
    }

    public void removeSprintTask(int taskId) {
        this.sprintTaskIds.remove(taskId);
    }


    public ArrayList<Integer> getAllSprintTaskIds() {
        return this.sprintTaskIds;
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
//        for (Integer key : sprintTasks.keySet()) {
//            JsonArray jsonTask = new JsonArray(sprintTasks.get(key));
//            jsonSprintTasks.put(key.toString(), jsonTask);
//        }
        jObj.put("sprintTasks", jsonSprintTasks);
        jObj.toJson(writer);
    }
}
