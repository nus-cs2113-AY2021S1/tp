package seedu.duke.project;

import java.time.LocalDate;

public class Project {

    public ProjectBacklog backlog;
    public ProjectMember members;
    String title;
    String description;
    String projectDeadline;
    int sprintLength;
    LocalDate startDate = null;

    public Project(String title, String description, String projectDeadline, String sprintLength) {
        this.title = title;
        this.description = description;
        this.projectDeadline = projectDeadline;
        this.sprintLength = Integer.parseInt(sprintLength);
        backlog = new ProjectBacklog();
        members = new ProjectMember();
    }

    public String toString() {
        return "Project title: " + title + "\nProject description " + description;
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

    public void displayProjectBacklog() {
        if (backlog.size() == 0) {
            System.out.println("No tasks currently added to project backlog.");
        } else {
            System.out.println("Current tasks in your project backlog");
            for (int i = 0; i < backlog.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + backlog.getTask(i).getTitle());
            }
        }
    }

    // Call this function every time a new sprint object is instantiated.
    // sets the start date the first time.
    public void setStartDate() {
        if (startDate != null) {
            startDate = LocalDate.now();
        }
    }
}
