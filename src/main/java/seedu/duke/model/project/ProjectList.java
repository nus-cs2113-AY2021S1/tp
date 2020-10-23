package seedu.duke.model.project;

import java.util.ArrayList;

public class ProjectList {
    public ArrayList<Project> projectList;
    public int selectedProject;

    public ProjectList() {
        this.projectList = new ArrayList<>(10);
        this.selectedProject = 1;
    }

    public void addProject(Project project) {
        projectList.add(project);
        selectedProject = projectList.size();
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

    public Project getProject() {
        return projectList.get(selectedProject - 1);
    }
}
