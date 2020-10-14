package seedu.duke.task;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;

public class Task implements Jsonable {

    protected int id;
    protected String title;
    protected String description;
    protected Priority priority;
    protected boolean isDone;
    protected ArrayList<String> membersAllocatedTo;
    protected ArrayList<Integer> sprintAllocatedTo;


    public Task(int id, String title, String description, String priority) {
        this(id, title, description, priority, false);
    }

    public Task(int id, String title, String description, String priority, boolean isDone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = Priority.valueOf(priority);
        this.isDone = false;
        this.membersAllocatedTo = new ArrayList<>();
        this.sprintAllocatedTo = new ArrayList<>();
    }

    public void allocateToMember(String memberId) {
        membersAllocatedTo.add(memberId);
    }

    public ArrayList<String> getAllocatedMembers() {
        return membersAllocatedTo;
    }

    public void allocateToSprint(int sprintId) {
        sprintAllocatedTo.add(sprintId);
    }

    public ArrayList<Integer> getAllocatedSprints() {
        return sprintAllocatedTo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setPriority(String input) {
        priority = Priority.valueOf(input);
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority.toString();
    }

    public String getTitle() {
        return title;
    }

    public boolean getDone() {
        return isDone;
    }

    public String toSimplifiedString() {
        StringBuilder taskString = new StringBuilder();
        taskString.append(String.format("\n[Task]"));
        taskString.append(String.format("\tID: %d", this.id));
        taskString.append(String.format("\tTitle: %s", this.title));
        taskString.append(String.format("\tCompletion: %s\n", this.isDone ? "Completed" : "Incomplete"));
        return taskString.toString();
    }

    @Override
    public String toString() {
        StringBuilder taskString = new StringBuilder();
        taskString.append(String.format("\n[Task]\n"));
        taskString.append(String.format("\tID: %d\n", this.id));
        taskString.append(String.format("\tTitle: %s\n", this.title));
        taskString.append(String.format("\tDescription: %s\n", this.description));
        taskString.append(String.format("\tPriority: %s\n", this.priority));
        taskString.append(String.format("\tCompletion: %s\n", this.isDone ? "Completed" : "Incomplete"));
        return taskString.toString();
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
        final JsonObject jTask = new JsonObject();
        jTask.put("id", id);
        jTask.put("title", title);
        jTask.put("description", description);
        jTask.put("priority", priority);
        jTask.put("isDone", isDone);
        jTask.toJson(writer);
    }


    //Delete seedu.duke.task handled outside using ArrayList remove() function
    //iD handled outside. (seedu.duke.task number)
    //valueOf for enum explained here:https://www.baeldung.com/java-string-to-enum

}
