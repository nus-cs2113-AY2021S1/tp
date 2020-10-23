package seedu.duke.model.project;

import java.util.Hashtable;

public class ProjectManager {
    public Hashtable<Integer, Project> projectList;
    public int selectedProject;

    public ProjectManager() {
        this.projectList = new Hashtable<>();
        this.selectedProject = -1;
    }

    public void addProject(String title, String description, int projectDuration, int sprintLength) {
        int newProjectID = this.size() + 1;
        this.projectList.put(newProjectID, new Project(newProjectID, title, description, projectDuration, sprintLength));
        this.selectedProject = newProjectID;
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
}
