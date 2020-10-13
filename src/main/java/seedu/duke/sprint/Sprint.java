package seedu.duke.sprint;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class Sprint implements Jsonable {


    private String goal;
    private LocalDate startDate;
    private LocalDate endDate;
    private Hashtable<Integer, ArrayList<String>> sprintTasks;
    //private ArrayList<Integer> sprintTasks;

    public Sprint(String goal) {
        this(goal, null, null);
    }

    public Sprint(String goal, LocalDate startDate) {
        this(goal, startDate, null);
    }

    public Sprint(String goal, LocalDate startDate, LocalDate endDate) {
        setGoal(goal);
        setStartDate(startDate);
        setEndDate(endDate);
        sprintTasks = new Hashtable<>();
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
        this.sprintTasks.put(taskId, new ArrayList<>());
    }

    public void removeSprintTask(int taskId) {
        this.sprintTasks.remove(taskId);
    }

    public void allocateSprintTask(int taskId, ArrayList<String> users) {
        this.sprintTasks.put(taskId, users);
    }

    public Hashtable<Integer, ArrayList<String>> getAllSprintTask() {
        return this.sprintTasks;
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
        for (Integer key : sprintTasks.keySet()) {
            JsonArray jsonTask = new JsonArray(sprintTasks.get(key));
            jsonSprintTasks.put(key.toString(), jsonTask);
        }
        jObj.put("sprintTasks", jsonSprintTasks);
        jObj.toJson(writer);
    }
}
