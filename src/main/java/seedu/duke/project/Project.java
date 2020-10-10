package seedu.duke.project;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;

public class Project implements Jsonable {

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
        if (startDate == null) {
            startDate = LocalDate.now();
        }
    }

    public LocalDate getStartDate() {
        return startDate;
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
        final JsonObject json = new JsonObject();
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("deadline", this.projectDeadline);
        json.put("sprint_length", this.sprintLength);
        json.put("start_date", this.startDate == null ? null : this.startDate.toString());
        //TODO Make backlog and members parsable
        json.put("backlog", new JsonArray());
        json.put("members", new JsonArray());
        json.toJson(writer);
    }
}
