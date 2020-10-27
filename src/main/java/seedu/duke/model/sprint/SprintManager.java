package seedu.duke.model.sprint;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.model.project.Project;
import seedu.duke.storage.JsonableObject;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class SprintManager implements JsonableObject {
    
    private ArrayList<Sprint> sprintList;
    private int currentSprintIndex;

    public SprintManager() {
        this.sprintList = new ArrayList<>();
        setCurrentSprintIndex(-1);
    }
    
    public ArrayList<Sprint> getSprintList() {
        return sprintList;
    }

    public int getCurrentSprintIndex() {
        return currentSprintIndex;
    }

    public void setCurrentSprintIndex(int sprintId) {
        this.currentSprintIndex = sprintId;
    }

    public int size() {
        return sprintList.size();
    }

    public Sprint getSprint(int sprintId) {
        return sprintList.get(sprintId - 1);
    }

    public Sprint getCurrentSprint() {
        return sprintList.get(this.currentSprintIndex - 1);
    }

    public void addSprint(Project proj, String goal, LocalDate start, LocalDate end) {
        int newSprintID = this.size() + 1;
        sprintList.add(new Sprint(newSprintID, proj, goal, start, end));
    }

    public boolean updateCurrentSprint() {
        for (int id = 1; id <= this.size(); id++) {
            Sprint current = this.getSprint(id);
            if (DateTimeParser.diff(LocalDate.now(), current.getEndDate()) >= 0
                    && DateTimeParser.diff(current.getStartDate(), LocalDate.now()) >= 0) {
                this.setCurrentSprintIndex(id);
                return true;

            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder allSprintsInString = new StringBuilder();
        allSprintsInString.append("\n-------------------------- ALL SPRINTS --------------------------\n");
        for (Sprint sprint : sprintList) {
            allSprintsInString.append(sprint.toSimplifiedString());
        }
        allSprintsInString.append("-----------------------------------------------------------------\n");
        return allSprintsInString.toString();
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
        jSprintObj.put("sprintList", sprintList);
        jSprintObj.put("currentSprintIndex", currentSprintIndex);
        jSprintObj.toJson(writer);
    }

    public void fromJson(JsonObject jsonObject, Project project) {
        this.currentSprintIndex = ((BigDecimal) jsonObject.get("currentSprintIndex")).intValue();
        JsonArray jsonSprints = new JsonArray((JsonArray) jsonObject.get("sprintList"));

        for (Object o : jsonSprints) {
            Sprint sprint = new Sprint();
            sprint.fromJson((JsonObject) o, project);
            sprintList.add(sprint);
        }
    }
    
    @Override
    public void fromJson(JsonObject jsonObj) {
        fromJson(jsonObj, null);
    }
}
