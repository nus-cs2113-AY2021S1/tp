package seedu.duke.model.task;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Task implements Jsonable {
    private int id;
    private String title;
    private String description;
    private Priority priority;
    private boolean isDone;
    private ArrayList<String> memberList;
    private ArrayList<Integer> sprintList;

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
        this.memberList = new ArrayList<>();
        this.sprintList = new ArrayList<>();
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

    public ArrayList<String> getMemberList() {
        return memberList;
    }

    public void setMemberList(ArrayList<String> memberList) {
        this.memberList = memberList;
    }

    public ArrayList<Integer> getSprintList() {
        return sprintList;
    }

    public void setSprintList(ArrayList<Integer> sprintList) {
        this.sprintList = sprintList;
    }

    public void allocateToMember(String memberId) {
        memberList.add(memberId);
    }

    public void removeFromMember(String memberId) {
        memberList.remove((Object) memberId);
    }

    public ArrayList<String> getAllocatedMembers() {
        return memberList;
    }

    public void allocateToSprint(int sprintId) {
        sprintList.add(sprintId);
    }

    public void removeFromSprint(int sprintId) {
        sprintList.remove((Object) sprintId);
    }

    public ArrayList<Integer> getAllocatedSprints() {
        return sprintList;
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
        if (!memberList.isEmpty()) {
            taskString.append("\tAssigned to: ");
            for (String member : memberList) {
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
        final JsonArray members = new JsonArray(memberList);
        jTask.put("id", id);
        jTask.put("title", title);
        jTask.put("description", description);
        jTask.put("priority", priority.name());
        jTask.put("isDone", isDone);
        jTask.put("membersAllocatedTo", members);
        jTask.put("sprintAllocatedTo", sprintList);
        jTask.toJson(writer);
    }
}
