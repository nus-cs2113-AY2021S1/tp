package seedu.duke.model.project;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import seedu.duke.storage.JsonableObject;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Hashtable;

public class ProjectManager implements JsonableObject {
    public Hashtable<Integer, Project> projectList;
    private int selectedProject;

    public ProjectManager() {
        this.projectList = new Hashtable<>();
        this.selectedProject = -1;
    }

    public void addProject(String title, String description, int projectDuration, int sprintLength) {
        int newProjectID = this.size() + 1;
        this.projectList.put(newProjectID,
                new Project(newProjectID, title, description, projectDuration, sprintLength));
        this.selectedProject = newProjectID;
    }

    public Hashtable<Integer, Project> getProjectList() {
        return this.projectList;
    }

    public int getSelectedProjectIndex() {
        return selectedProject;
    }

    public void selectProject(int index) {
        selectedProject = index;
    }

    public int size() {
        return projectList.size();
    }

    public boolean isEmpty() {
        return projectList.isEmpty();
    }

    public Project getSelectedProject() {
        return projectList.get(selectedProject);
    }

    public Project getProject(int index) {
        return projectList.get(index);
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
        final JsonObject jsonProjMgr = new JsonObject();
        jsonProjMgr.put("selectedProject", this.selectedProject);
        jsonProjMgr.put("projectList", new JsonArray(this.projectList.values()));
        jsonProjMgr.toJson(writer);
    }

    @Override
    public void fromJson(JsonObject jsonObj) {
        if (jsonObj.isEmpty()) {
            return; //empty data
        }
        selectedProject = JsonableObject.parseInt(jsonObj, "selectedProject");
        
        for (Object o : (JsonArray) jsonObj.get("projectList")) {
            Project project = new Project();
            project.fromJson((JsonObject) o);
            projectList.put(project.getProjectID(), project);
        }
    }
}
