package seedu.duke.task;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Task implements Jsonable {
    protected int id;
    protected String title;
    protected String description;
    protected Priority priority;
    protected boolean isDone;
    protected ArrayList<String> membersAllocatedTo;
    protected ArrayList<Integer> sprintAllocatedTo;

    public Task() {
    }

    public Task(int id, String title, String description, String priority) {
        this(id, title, description, priority, false);
    }

    public Task(int id, String title, String description, String priority, boolean isDone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = Priority.valueOf(priority);
        this.isDone = isDone;
        this.membersAllocatedTo = new ArrayList<>();
        this.sprintAllocatedTo = new ArrayList<>();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    
    public void setPriority(String input) {
        priority = Priority.valueOf(input);
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public ArrayList<String> getMembersAllocatedTo() {
        return membersAllocatedTo;
    }

    public void setMembersAllocatedTo(ArrayList<String> membersAllocatedTo) {
        this.membersAllocatedTo = membersAllocatedTo;
    }

    public ArrayList<Integer> getSprintAllocatedTo() {
        return sprintAllocatedTo;
    }

    public void setSprintAllocatedTo(ArrayList<Integer> sprintAllocatedTo) {
        this.sprintAllocatedTo = sprintAllocatedTo;
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
        taskString.append(String.format("[Task ID: %d]", this.id));
        taskString.append(String.format("\t[Title: %s]", this.title));
        taskString.append(String.format("\t[Completion: %s]\n", this.isDone ? "Completed" : "Incomplete"));
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
        if (!membersAllocatedTo.isEmpty()) {
            taskString.append("\tAssigned to: ");
            for (String member : membersAllocatedTo) {
                taskString.append(String.format("%s ", member));
            }
            taskString.append("\n");
        } else {
            taskString.append("\tTask have yet to be assigned to anyone\n");
        }
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
        final JsonArray members = new JsonArray(membersAllocatedTo);
        jTask.put("id", id);
        jTask.put("title", title);
        jTask.put("description", description);
        jTask.put("priority", priority.name());
        jTask.put("isDone", isDone);
        jTask.put("membersAllocatedTo", members);
        jTask.put("sprintAllocatedTo", sprintAllocatedTo);
        jTask.toJson(writer);
    }


    //Delete seedu.duke.task handled outside using ArrayList remove() function
    //iD handled outside. (seedu.duke.task number)
    //valueOf for enum explained here:https://www.baeldung.com/java-string-to-enum

}
