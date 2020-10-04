package seedu.duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {

    static ProjectBacklog backlog;
    public static ArrayList<Member> member;
    String title;
    String description;
    int projectDuration;
    int sprintLength;
    LocalDate startDate = null;
    LocalDate endDate = null;

    public Project(String title, String description, int projectDuration, int sprintLength) {
        this.title = title;
        this.description = description;
        this.projectDuration = projectDuration;
        this.sprintLength = sprintLength;
        backlog = new ProjectBacklog();
        member = new ArrayList<>(100);
    }

    public String toString() {
        return "Project title: " + title + "\nProject description " + description;
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

    public void addMember(List<String> userId) {
        Member m;
        for (String s : userId) {
            if (member.contains(new Member(s))) {
                System.out.println("The user associated with " + s + "is already added to the project");
            } else {
                m = new Member(s);
                member.add(m);
                System.out.println("The user associated with " + s + "has been added");
            }
        }
    }

    //add comparator for removing object
    public void removeMember(List<String> userId) {
        for (String s : userId) {
            if (member.contains(new Member(s))) {
                member.remove(new Member(s));
                System.out.println("The user associated with " + s + "has been removed from the project");
            } else {
                System.out.println("This member is not associated with this project: " + new Member(s).userId);
            }
        }
    }

    public void deleteTask(int id){

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

    // Call this function every time a new sprint object is instantiated.
    // sets the start date the first time.
    public void setStartDate() {
        if (startDate != null) {
            startDate = LocalDate.now();
            endDate = startDate.plusDays(projectDuration);
        }
    }
}
