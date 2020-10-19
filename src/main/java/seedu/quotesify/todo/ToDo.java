package seedu.quotesify.todo;

import org.json.simple.JSONObject;
import seedu.quotesify.parser.JsonSerializer;

public class ToDo implements JsonSerializer {
    private String name;
    private String deadline;
    private boolean isDone;

    public ToDo(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
        this.isDone = false;
    }

    public ToDo(String name, String deadline, boolean isDone) {
        this.name = name;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return  "[" + this.getStatusIcon() + "] "
                + this.name + " (by: " + this.deadline + ")";
    }

    public String getStatusIcon() {
        String signTick = "v";
        String signCross = "x";
        return (isDone ? signTick : signCross);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.getName());
        json.put("deadline", this.getDeadline());
        json.put("isDone", this.isDone());
        return json;
    }
}
