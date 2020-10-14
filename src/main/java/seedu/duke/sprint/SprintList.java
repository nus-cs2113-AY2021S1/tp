package seedu.duke.sprint;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.project.Project;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;

public class SprintList implements Jsonable {
    private ArrayList<Sprint> sprintList;
    private int currentSprintIndex;

    public SprintList() {
        this.sprintList = new ArrayList<>();
        setCurrentSprintIndex(-1);
    }

    public int size() {
        return sprintList.size();
    }

    public Sprint getSprint(int index) {
        return sprintList.get(index);
    }

    public void addSprint(Project proj, String goal, LocalDate start, LocalDate end) {
        int newSprintID = this.size();
        sprintList.add(new Sprint(newSprintID, proj, goal, start, end));
    }

    public int getCurrentSprintIndex() {
        return currentSprintIndex;
    }

    public void setCurrentSprintIndex(int currentSprintIndex) {
        this.currentSprintIndex = currentSprintIndex;
    }

    public boolean updateCurrentSprint() {
        for (int i = 0; i < this.size(); i++) {
            Sprint current = this.getSprint(i);
            if (DateTimeParser.diff(LocalDate.now(), current.getEndDate()) >= 0
                    && DateTimeParser.diff(current.getStartDate(), LocalDate.now()) >= 0) {
                this.setCurrentSprintIndex(i);
                return true;

            }
        }
        return false;
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
        final JsonObject jSprintObj = new JsonObject();
        final JsonArray jSprintList = new JsonArray(sprintList);
        jSprintObj.put("list", jSprintList);
        jSprintObj.put("currentSprintIndex", currentSprintIndex);
        jSprintObj.toJson(writer);
    }
}
