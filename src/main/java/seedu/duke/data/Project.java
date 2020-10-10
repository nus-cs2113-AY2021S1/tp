package seedu.duke.data;

import java.time.LocalDate;
import java.util.ArrayList;

public class Project {

    protected SprintList allSprints;
    protected ProjectBacklog backlog;
    protected ProjectMember members;
    protected String title;
    protected String description;
    protected int projectDuration;
    protected int sprintLength;



    protected LocalDate startDate = null;
    protected LocalDate endDate = null;


    public ProjectMember getMembers() {
        return members;
    }

    public Project(String title, String description, String projectDuration, String sprintLength) {
        this.title = title;
        this.description = description;
        this.projectDuration = Integer.parseInt(projectDuration);
        this.sprintLength = Integer.parseInt(sprintLength);
        backlog = new ProjectBacklog();
        members = new ProjectMember();
        allSprints = new SprintList();
    }

    public String toString() {
        return "Project title: " + title + "\nProject description " + description;
    }

    public SprintList getAllSprints() {
        return allSprints;
    }

    public void setAllSprints(SprintList allSprints) {
        this.allSprints = allSprints;
    }

    public int getProjectDuration() {
        return projectDuration;
    }

    public int getSprintLength() {
        return sprintLength;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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

    public ProjectBacklog getProjectBacklog() {
        return backlog;
    }

    public void displayProjectBacklog() {
        if (backlog.size() == 0) {
            System.out.println("No tasks currently added to project backlog.");
        } else {
            System.out.println("Current tasks in your project backlog");
            for (int i = 0; i < backlog.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + backlog.getTask(i).title);
            }
        }
    }

}
